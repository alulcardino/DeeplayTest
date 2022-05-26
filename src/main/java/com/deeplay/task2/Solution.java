package com.deeplay.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Solution {
    private static final int HEIGHT = 4;
    private static final int WIDTH = 4;
    private static final int MAX_INT = Integer.MAX_VALUE;
    private static int[][] costTable;


    public static int getResult(String fieldStr, String raceStr) throws MySolutionException, IOException {
        fillCostTable();
        if (fieldStr == null || fieldStr.trim().isEmpty())
            throw new MySolutionException(MySolutionExceptionErrorCode.EMPTY_FIELD);

        final int[][] field = getField(fieldStr, raceStr);

        return dijkstra(new Vertex(0, 0), field)[HEIGHT - 1][WIDTH - 1];
    }

    public static int[][] dijkstra(Vertex vertex, int[][] field) {
        final boolean[][] marked = new boolean[HEIGHT][WIDTH];
        final int[][] distance = new int[HEIGHT][WIDTH];

        for (int[] row : distance)
            Arrays.fill(row, MAX_INT);
        distance[vertex.getY()][vertex.getX()] = 0;

        for (; ; ) { // за такое банить надо, я считаю
            Vertex currentVertex = null;
            for (int i = 0; i < HEIGHT; i++)
                for (int j = 0; j < WIDTH; j++)
                    if (!marked[i][j] &&
                            distance[i][j] < MAX_INT &&
                            (currentVertex == null || distance[currentVertex.getY()][currentVertex.getX()] > distance[i][j]))
                        currentVertex = new Vertex(i, j);

            if (currentVertex == null) break;
            marked[currentVertex.getY()][currentVertex.getX()] = true;

            for (int i = 0; i < HEIGHT; i++)
                for (int j = 0; j < WIDTH; j++)
                    if (!marked[i][j] && isNear(currentVertex, new Vertex(i, j)))
                        distance[i][j] = Math.min(
                                distance[i][j],
                                distance[currentVertex.getY()][currentVertex.getX()] + field[i][j]);
        }
        return distance;
    }

    private static void fillCostTable() throws IOException {
        String costTablePath = (Objects.requireNonNull(Solution.class.getClassLoader().getResource("CostTable.txt"))).getFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(costTablePath))) {
            List<String> lines = new ArrayList<>();
            while (reader.ready()) {
                lines.add(reader.readLine());
            }
            int matrixWidth = lines.get(0).split(" ").length;
            int matrixHeight = lines.size();

            costTable = new int[matrixHeight][matrixWidth];

            for (int i = 0; i < matrixHeight; i++) {
                for (int j = 0; j < matrixWidth; j++) {
                    String[] line = lines.get(i).split(" ");
                    costTable[i][j] = Integer.parseInt(line[j]);
                }
            }
        } catch (NullPointerException e) {
            System.out.println(MySolutionExceptionErrorCode.NO_FILE_COST);
        }
    }

    private static List<String> getDataFromFiles(String nameOfFile) throws IOException {
        List<String> arrayOfCreatures = new ArrayList<>();
        String dataPath = (Objects.requireNonNull(Solution.class.getClassLoader().getResource(nameOfFile))).getFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(dataPath))) {
            String line = reader.readLine();
            while (line != null) {
                arrayOfCreatures.add(line);
                line = reader.readLine();
            }
        } catch (NullPointerException ex) {
            System.out.println(MySolutionExceptionErrorCode.NO_FILE_DATA);
        }
        return arrayOfCreatures;
    }

    private static int[][] getField(String field, String creatureName) throws MySolutionException, IOException {
        List<String> arrayOfCreatures = Solution.getDataFromFiles("Creatures.txt");
        if (!arrayOfCreatures.contains(creatureName)) {
            throw new MySolutionException(MySolutionExceptionErrorCode.INCORRECT_CREATURE);
        }
        int size = (int) Math.sqrt(field.length());
        int[][] finalField = new int[size][size];
        List<String> arrayOfCells = Solution.getDataFromFiles("Cells.txt");
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                String currentChar = String.valueOf(field.charAt(i * size + j));
                if (!arrayOfCells.contains(currentChar)) {
                    throw new MySolutionException(MySolutionExceptionErrorCode.INCORRECT_CELL);
                }
                finalField[i][j] = costTable[arrayOfCreatures.indexOf(creatureName)][arrayOfCells.indexOf(currentChar)];
            }
        return finalField;
    }

    private static boolean isNear(Vertex vertex1, Vertex vertex2) {
        return Math.abs(vertex1.getY() - vertex2.getY()) + Math.abs(vertex1.getX() - vertex2.getX()) == 1;
    }
}
