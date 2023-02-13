package com.game.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.game.controller.Ascii;
import com.game.controller.ItemGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class TextParser {

    public static final List<String> COMMANDS
            = Arrays.asList("help", "exit", "quit",
            "mute music", "mute sound", "?", "search", "take", "return","answers");

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
                    return "5";
                case "mute music":
                    Sound.getInstance().muteMusic();
                    Ascii.printMute();
                    return "5";
                case "answers":
                    Ascii.printAnswerMenu();
                    break;
                case "mute sound":
                    Sound.getInstance().muteSound();
                    Ascii.printMute();
                    return "5";
                case "return":
                default:
                    return "5";
            }

        } else if (isDigit && inputText.length() == 1 && Pattern.matches("^[1-4]$", Integer.toString(Integer.parseInt(inputText)))) {
            return inputText;
        } else if (inputText.contains("take")) {
            ItemGenerator.takeItem(inputText);
        } else if (inputText.contains("look")) {
            ItemGenerator.searchForRandomItem();
        } else if (inputText.contains("music volume")) {
            String[] parts = inputText.split(" ");
            if (parts.length >= 2) {
                try {
                    int value = Integer.parseInt(parts[2]);
                    if (value >= 1 && value <= 4) {
                        Sound.getInstance().setMusicVolume(value);
                        Ascii.printVolumeLevel(value);
                        return "5";
                    } else {
                        Ascii.printHelpMenu("Try 'music volume 1-4'");
                    }
                } catch (Exception e) {
                    Ascii.printHelpMenu("Try 'music volume 1-4'");
                }
            } else {
                Ascii.printHelpMenu("Try 'music volume 1-4'");
            }

            Ascii.printHelpMenu("Try 'music volume 1-4'");

        } else if (inputText.contains("sound volume")) {
            String[] parts = inputText.split(" ");
            if (parts.length >= 2) {
                try {
                    int value = Integer.parseInt(parts[2]);
                    if (value >= 1 && value <= 4) {
                        Sound.getInstance().setSoundVolume(value);
                        Ascii.printVolumeLevel(value);
                        return "5";
                    } else {
                        Ascii.printHelpMenu("Try 'sound volume 1-4'");
                    }
                } catch (Exception e) {
                    Ascii.printHelpMenu("Try 'sound volume 1-4'");
                }
            } else {
                Ascii.printHelpMenu("Try 'sound volume 1-4'");
            }

            Ascii.printHelpMenu("Try 'sound volume 1-4'");

        }
        else {
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

    public static String textParserItems(String option) {
        if (option.toLowerCase().contains("take")) {
            ItemGenerator.takeItem(option);
            return option;
        } else if (option.toLowerCase().contains("look")) {
            ItemGenerator.searchForRandomItem();
            return option;
        }

        return option;
    }

    public static String optionalInput(int options) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");

        String inputText = scanner.nextLine();

        return inputText;
    }




}
