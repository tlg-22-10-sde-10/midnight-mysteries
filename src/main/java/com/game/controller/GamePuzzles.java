package com.game.controller;

import java.util.Scanner;

public class GamePuzzles {

    public static String delegatePuzzle(String selectedOption) {
        switch (selectedOption) {
            case "Attempt to get the item":
                return poolPuzzleNumOne(selectedOption);
            case "Search the basement":
                return poolPuzzleNumTwo(selectedOption);
            default:
                return selectedOption;
        }
    }

    public static String poolPuzzleNumOne(String selectedOption) {
        boolean isSolved = true;

        System.out.println("Puzzle one");


        if (isSolved) {
            return "Solved Puzzle1";
        }

        return selectedOption;
    }

    public static String poolPuzzleNumTwo(String selectedOption) {
        boolean isSolved = true;

        System.out.println("Puzzle Two");

        if (isSolved) {
            return "Solved Puzzle2";
        }
        return selectedOption;
    }
}
