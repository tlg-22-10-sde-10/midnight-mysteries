package com.game.controller;

import com.game.controller.Ascii;
import com.game.controller.ItemGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextParser {

    public static final List<String> COMMANDS
            = Arrays.asList("help", "exit", "quit", "volume",
            "mute","?", "search", "take","return");

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
                    Ascii.printHelpMenu("");
                    break;
                case "exit":
                case "quit":
                    Ascii.printExitBanner();
                    break;
                case "search":
                    ItemGenerator.searchForRandomItem();
                    break;
                case "volume":
                    System.out.println("Volume Function");
                    break;
                case "mute":
                    System.out.println("Mute Function");
                    break;
                case "return":
                default:
                    return "5";
            }

        } else if (isDigit && inputText.length() == 1 && Pattern.matches("^[1-4]$", Integer.toString(Integer.parseInt(inputText)))) {
            return inputText;
        } else if(inputText.contains("take")){
            ItemGenerator.takeItem(inputText);
        } else if (inputText.contains("look")) {
            ItemGenerator.searchForRandomItem();
        }else {
            Ascii.clearTerminal();
            Ascii.printHelpMenu("Invalid option!");
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


    public static String optionalInput(int options) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        String inputText = scanner.nextLine();

        return inputText;
    }
}
