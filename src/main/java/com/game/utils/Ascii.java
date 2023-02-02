package com.game.utils;

import java.util.Arrays;
import java.util.List;

public class Ascii {

    private static int rows;
    private static int columns;
    private ColoredPrinter printer;


    public Ascii() {
        rows = 30;
        columns = 120;
        setTerminalWidthAndHeight();
        System.out.println("Ran constructor");
        printer = new ColoredPrinter();
    }

    public static void printTitle() {
        String temp = "       ███▄ ▄███▓    ██▓   ▓█████▄     ███▄    █     ██▓     ▄████     ██░ ██    ▄▄▄█████▓        \n" +
                "      ▓██▒▀█▀ ██▒   ▓██▒   ▒██▀ ██▌    ██ ▀█   █    ▓██▒    ██▒ ▀█▒   ▓██░ ██▒   ▓  ██▒ ▓▒        \n" +
                "      ▓██    ▓██░   ▒██▒   ░██   █▌   ▓██  ▀█ ██▒   ▒██▒   ▒██░▄▄▄░   ▒██▀▀██░   ▒ ▓██░ ▒░        \n" +
                "      ▒██    ▒██    ░██░   ░▓█▄   ▌   ▓██▒  ▐▌██▒   ░██░   ░▓█  ██▓   ░▓█ ░██    ░ ▓██▓ ░         \n" +
                "      ▒██▒   ░██▒   ░██░   ░▒████▓    ▒██░   ▓██░   ░██░   ░▒▓███▀▒   ░▓█▒░██▓     ▒██▒ ░         \n" +
                "      ░ ▒░   ░  ░   ░▓      ▒▒▓  ▒    ░ ▒░   ▒ ▒    ░▓      ░▒   ▒     ▒ ░░▒░▒     ▒ ░░           \n" +
                "      ░  ░      ░    ▒ ░    ░ ▒  ▒    ░ ░░   ░ ▒░    ▒ ░     ░   ░     ▒ ░▒░ ░       ░            \n" +
                "      ░      ░       ▒ ░    ░ ░  ░       ░   ░ ░     ▒ ░   ░ ░   ░     ░  ░░ ░     ░              \n" +
                "             ░       ░        ░                ░     ░           ░     ░  ░  ░                    \n" +
                "                            ░                                                                     \n" +
                " ███▄ ▄███▓   ▓██   ██▓     ██████    ▄▄▄█████▓   ▓█████     ██▀███      ██▓   ▓█████      ██████ \n" +
                "▓██▒▀█▀ ██▒    ▒██  ██▒   ▒██    ▒    ▓  ██▒ ▓▒   ▓█   ▀    ▓██ ▒ ██▒   ▓██▒   ▓█   ▀    ▒██    ▒ \n" +
                "▓██    ▓██░     ▒██ ██░   ░ ▓██▄      ▒ ▓██░ ▒░   ▒███      ▓██ ░▄█ ▒   ▒██▒   ▒███      ░ ▓██▄   \n" +
                "▒██    ▒██      ░ ▐██▓░     ▒   ██▒   ░ ▓██▓ ░    ▒▓█  ▄    ▒██▀▀█▄     ░██░   ▒▓█  ▄      ▒   ██▒\n" +
                "▒██▒   ░██▒     ░ ██▒▓░   ▒██████▒▒     ▒██▒ ░    ░▒████▒   ░██▓ ▒██▒   ░██░   ░▒████▒   ▒██████▒▒\n" +
                "░ ▒░   ░  ░      ██▒▒▒    ▒ ▒▓▒ ▒ ░     ▒ ░░      ░░ ▒░ ░   ░ ▒▓ ░▒▓░   ░▓     ░░ ▒░ ░   ▒ ▒▓▒ ▒ ░\n" +
                "░  ░      ░    ▓██ ░▒░    ░ ░▒  ░ ░       ░        ░ ░  ░     ░▒ ░ ▒░    ▒ ░    ░ ░  ░   ░ ░▒  ░ ░\n" +
                "░      ░       ▒ ▒ ░░     ░  ░  ░       ░            ░        ░░   ░     ▒ ░      ░      ░  ░  ░  \n" +
                "       ░       ░ ░              ░                    ░  ░      ░         ░        ░  ░         ░  \n" +
                "               ░ ░                                                                                 ";



        System.out.println(countLines(temp));
        temp = addSpaces(temp,10,5);
        ColoredPrinter.print("red",temp);

    }

    public static List<Integer> countLines(String input) {
        String[] lines = input.split("\n");
        int height = lines.length;
        int width = 0;
        int topSpace = 30-height/2;
        for (String line : lines) {
            width = Math.max(width, line.length());
        }

        return Arrays.asList(width,height,topSpace);
    }

    public static String addSpaces(String text, int spaces, int rowsTop) {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowsTop; i++) {
            sb.append("\n");
        }
        for (String line : lines) {
            for (int i = 0; i < spaces; i++) {
                sb.append(" ");
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    public static void clearTerminal() {
        try {
            new ProcessBuilder("cmd", "/c", "cls", "clear", "\033[H\033[2J", "\033\143").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("Error clearing terminal: " + e.getMessage());
        }
    }

    public static void setTerminalWidthAndHeight() {
        try {
            Process p = new ProcessBuilder("cmd", "/c", "mode con: cols=120 lines=30").inheritIO().start();
            p.waitFor();

        } catch (Exception e) {
            System.out.println("Couldn't set terminal size");
        }

    }

    public static void printTextCenter(String text) {

        // up & down
        int middleRow = (rows / 2) - 3;

        // left & right
        int middleColumn = (columns / 2) - 9;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < middleRow; i++) {
            sb.append("\n");
        }
        for (int i = 0; i < middleColumn; i++) {
            sb.append(" ");
        }
        sb.append(text);
        for (int i = 0; i < middleRow + 4; i++) {
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

}


