package com.deeplay.task2;


import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ChangerTest {
    @Test
    public void getResultTest1() throws MySolutionException, IOException {
        assertEquals(30, Solution.getResult("SSSSSSSSSSSSSSSSSSSS", "Human"));
        assertEquals(18, Solution.getResult("SSSSSSSSSSSSSSSSSSSS", "Woodman"));
        assertEquals(12, Solution.getResult("SSSSSSSSSSSSSSSSSSSS", "Swamper"));
    }

    @Test
    public void getResultTest2() throws MySolutionException, IOException {
        assertEquals(14, Solution.getResult("SPSWSTTSWPTSWPSTWSTP", "Human"));
        assertEquals(13, Solution.getResult("SPSWSTTSWPTSWPSTWSTP", "Woodman"));
        assertEquals(15, Solution.getResult("SPSWSTTSWPTSWPSTWSTP", "Swamper"));
    }

    @Test
    public void getResultTest3() throws MySolutionException, IOException {
        assertEquals(10, Solution.getResult("STWSWTPPTPTTPWPP", "Human"));
        assertEquals(12, Solution.getResult("STWSWTPPTPTTPWPP", "Woodman"));
        assertEquals(15, Solution.getResult("STWSWTPPTPTTPWPP", "Swamper"));
    }

    @Test (expected = MySolutionException.class)
    public void getResultTestError1() throws MySolutionException, IOException {
        Solution.getResult("", "Human");
    }

    @Test (expected = MySolutionException.class)
    public void getResultTestError2() throws MySolutionException, IOException {
       Solution.getResult("STWSWTPPTPTTPWPP", "SWAMPER");
    }

    @Test (expected = MySolutionException.class)
    public void getResultTestError3() throws MySolutionException, IOException {
        Solution.getResult("", "");
    }
}
