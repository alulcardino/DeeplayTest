package com.deeplay.task2;

public enum MySolutionExceptionErrorCode {
    EMPTY_FIELD("EMPTY FIELD!"),
    INCORRECT_CREATURE("INCORRECT CREATURE!"),
    INCORRECT_CELL("INCORRECT CELL!"),
    NO_FILE_DATA("NO FILE DATA!"),
    NO_FILE_COST("NO FILE COST TABLE!");


    private final String str;

    MySolutionExceptionErrorCode(String str) {
        this.str = str;
    }

    public String getDescription() {
        return str;
    }
}
