package com.game.utils;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextParser {

    public static final List<String> COMMANDS
            = Arrays.asList("help", "exit", "quit", "volume", "mute", "start", "y", "yes","?","search");

    public static String validateInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        String inputText = scanner.nextLine().toLowerCase();

        // check to see if first char of string a digit
        Boolean isDigit = Character.isDigit(inputText.charAt(0));

        if (COMMANDS.contains(inputText)) {
            switch (inputText) {
                case "help":
                case "?":
                    Ascii.clearTerminal();
                    System.out.println("Valid options are: " + COMMANDS.toString());
                    System.out.println("And dialogue options 1-4");
                    break;
                case "exit":
                case "quit":
                    Ascii.printExitBanner();
                    break;
                case "Search":
                    break;
                case "volume":
                    System.out.println("Volume Function");
                    break;
                case "mute":
                    System.out.println("Mute Function");
                    break;
                case "start":
                case "y":
                case "yes":
                default:
                    return inputText;
            }

        } else if (isDigit && inputText.length() == 1 && Pattern.matches("^[1-4]$", Integer.toString(Integer.parseInt(inputText)))) {
            return inputText;
        } else {
            System.out.println("Invalid option");
            System.out.println("Valid options are: " + COMMANDS.toString());
            System.out.println("And dialogue options 1-4");
            return "-1";
        }

        return "-1";
    }


    public static String optionalInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        String inputText = scanner.nextLine();

        return inputText;
    }
}
