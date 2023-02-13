package com.game.controller;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GamePuzzles {

    public static String delegatePuzzle(String selectedOption) {
        switch (selectedOption) {
            case "Attempt to get the item":
                return poolPuzzleNumOne();
            case "Search the basement":
                return poolPuzzleNumTwo();
            case "DIMITIS":
                return poolPuzzleNumThree();
            default:
                return selectedOption;
        }
    }

    public static String poolPuzzleNumOne() {

        Ascii.clearTerminal();

        // 4 9 13 9 20 9 19
        int[] targets = {4, 9, 13, 9, 20, 9, 19};
        int currentCount = 1;
        int correctCount = 0;
        boolean startRound = true;

        Scanner scanner = new Scanner(System.in);


        while (correctCount != 7) {
            Ascii.clearTerminal();

            Ascii.printTextCenterWithDelay("You are in Limbo \n You must attempt to guess 7 numbers in 60 seconds (1-20) \n");

            for (int target : targets) {
                System.out.println("Guess number #" + currentCount++ + " between 1 and 20 as fast as possible:");
                long startTime = System.currentTimeMillis();

                while (startRound && correctCount != 7) {


                    System.out.print("-> ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("That's not a valid number. Please enter an integer.");

                        scanner.next();
                    }

                    int guess = scanner.nextInt();

                    long elapsedTime = System.currentTimeMillis() - startTime;
                    if (elapsedTime >= 60000) {
                        System.out.println("Time's up! The number was " + target);
                        correctCount = 0;
                        startRound = false;
                        Ascii.printTextCenterWithDelay("Sorry try again..........");
                        Ascii.clearTerminal();
                        currentCount = 1;
                        break;
                    } else if (guess == target) {
                        System.out.println("You won! The number was " + target + ". Time remaining: " + (60000 - elapsedTime) / 1000 + " seconds");
                        correctCount++;
                        break;
                    } else if (guess < target) {
                        System.out.println("The number is higher. Time remaining: " + (60000 - elapsedTime) / 1000 + " seconds");
                    } else if (guess > target) {
                        System.out.println("The number is lower. Time remaining: " + (60000 - elapsedTime) / 1000 + " seconds");
                    }
                }
                System.out.println();
            }

            startRound = true;
        }


        Ascii.printKey();

        Ascii.clearTerminal();
        Ascii.printTextCenterWithDelay("4   9   13   9    20    9    19  ");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Solved Puzzle1";

    }

    public static String poolPuzzleNumTwo() {

        String target = "dimitis";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess the word:");
        Ascii.clearTerminal();

        while (true) {
            Ascii.printSpaces(10);
            Ascii.printTextCenterWithDelay("Decipher \n 4   9   13   9    20    9    19  ");

            System.out.print("-> ");
            String guess = scanner.next();

            if (guess.toLowerCase().equals(target)) {
                System.out.println("dimitis?");
                break;
            } else {
                System.out.println("Incorrect. Please try again.");
            }
        }

        return "dim";
    }

    public static String poolPuzzleNumThree() {

        Ascii.printDimitis();
        Ascii.clearTerminal();
        Ascii.printTextCenterWithDelay("DIMITIS \n as you shout that word you feel the eerie sensation vanish. \n As if you had released something from this dark place.");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Solved Puzzle2";

    }

}
