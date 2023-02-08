package com.game.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Ascii {

    private static final int ROWS = 30;
    private static final int COLUMNS = 120;
    private ColoredPrinter printer = new ColoredPrinter();


    // default is 25L
    private static final long DELAY = 25L;



    public static void printTitleBanner() throws InterruptedException {

        String centeredBanner = "       ███▄ ▄███▓    ██▓   ▓█████▄     ███▄    █     ██▓     ▄████     ██░ ██    ▄▄▄█████▓        \n" +
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


        centeredBanner = addSpaces(centeredBanner);
        ColoredPrinter.print("red", centeredBanner);
        TimeUnit.SECONDS.sleep(5);
    }

    public static String addSpaces(String text) {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder();

        int leftPadding = 0;
        int topPadding = (ROWS - lines.length) / 2;

        for (String word : lines) {
            if (word.length() > leftPadding) {
                leftPadding = word.length();
            }
        }


        leftPadding = (COLUMNS - leftPadding) / 2;

//        System.out.println("LeftPadding: " + leftPadding);
//        System.out.println("topPadding: " + topPadding);

        // top spacing
        for (int i = 0; i < topPadding; i++) {
            sb.append("\n");
        }

        // left spacing
        for (String line : lines) {
            for (int i = 0; i < leftPadding; i++) {
                sb.append(" ");
            }
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    public static void clearTerminal() {

        String os = System.getProperty("os.name").toLowerCase();
//        System.out.println(os);
        if (os.contains("windows")) {
            try {
                new ProcessBuilder("cmd", "/c", "cls", "clear", "\033[H\033[2J", "\033\143").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing terminal: " + e.getMessage());
            }
        } else if (os.contains("mac") || os.contains("os x")) {
            try {
                new ProcessBuilder("clear", "/c", "cls", "cmd", "\033[H\033[2J", "\033\143").inheritIO().start().waitFor();
            } catch (Exception e) {
                System.out.println("Error clearing terminal: " + e.getMessage());
            }
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

    public static void printSpaces(int numOfSpaces) {
        for (int i = 0; i < numOfSpaces; i++) {
            System.out.println((""));
        }
    }

    public static void printTextCenterWithDelay(String text) {

        List<String> lines = new ArrayList<>();
        text.lines().forEach(s -> lines.add(s));
        int topPadding = 0;

        topPadding = (15 - lines.size());

        printSpaces(topPadding);

        for (int i = 0; i < lines.size(); i++) {
            String temp = lines.get(i);
            int lineSize = lines.get(i).length();
            int numSpaces = (COLUMNS - lineSize) / 2;
            String spaces = "";
            for (int j = 0; j < numSpaces; j++) {
                spaces += " ";
            }

            temp = spaces + temp;

            for (int k = 0; k < temp.length(); k++) {
                char tempChar = temp.charAt(k);
                ColoredPrinter.print("red", tempChar);
                if (tempChar != ' ') {
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("\n");

        }
    }

    public static void setTerminalTitle() {
        System.out.print("\033]0;" + "Midnight Mysteries" + "\007");
    }

    public static void printExitBanner() {
        clearTerminal();
        String centeredBanner = "  ██████    ▓█████    ▓█████    ▓██   ██▓    ▒█████      █    ██                   \n" +
                "▒██    ▒    ▓█   ▀    ▓█   ▀     ▒██  ██▒   ▒██▒  ██▒    ██  ▓██▒                  \n" +
                "░ ▓██▄      ▒███      ▒███        ▒██ ██░   ▒██░  ██▒   ▓██  ▒██░                  \n" +
                "  ▒   ██▒   ▒▓█  ▄    ▒▓█  ▄      ░ ▐██▓░   ▒██   ██░   ▓▓█  ░██░                  \n" +
                "▒██████▒▒   ░▒████▒   ░▒████▒     ░ ██▒▓░   ░ ████▓▒░   ▒▒█████▓     ██▓  ██▓  ██▓ \n" +
                "▒ ▒▓▒ ▒ ░   ░░ ▒░ ░   ░░ ▒░ ░      ██▒▒▒    ░ ▒░▒░▒░    ░▒▓▒ ▒ ▒     ▒▓▒  ▒▓▒  ▒▓▒ \n" +
                "░ ░▒  ░ ░    ░ ░  ░    ░ ░  ░    ▓██ ░▒░      ░ ▒ ▒░    ░░▒░ ░ ░     ░▒   ░▒   ░▒  \n" +
                "░  ░  ░        ░         ░       ▒ ▒ ░░     ░ ░ ░ ▒      ░░░ ░ ░     ░    ░    ░   \n" +
                "      ░        ░  ░      ░  ░    ░ ░            ░ ░        ░          ░    ░    ░  \n" +
                "                                 ░ ░                                  ░    ░    ░  ";

        centeredBanner = addSpaces(centeredBanner);
        fadeText(centeredBanner);
    }

    private static void fadeText(String text) {

        List<String> lines = new ArrayList<>();
        text.lines().forEach(s -> lines.add(s));

        for (int i = 0; i < 10; i++) {
            for (String line : lines) {
                ColoredPrinter.print("red", fadeLine(line, i));
            }
            System.out.println();
            if (i == 0) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            try {
                TimeUnit.MILLISECONDS.sleep(100);
                clearTerminal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.exit(0);

    }

    private static String fadeLine(String line, int step) {
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            int value = (int) c;
            value = value - step * 10;
            if (value < 32) {
                value = 32;
            }
            sb.append((char) value);
        }
        return sb.toString();
    }

    public static void printHelpMenu(String warning) {
        clearTerminal();

        String centerMenu = " " + warning + "\n" +
                " ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄ \n" +
                " █                                               █ \n" +
                " █                                               █ \n" +
                " █                                               █ \n" +
                " █   Valid options:                              █ \n" +
                " █                                               █ \n" +
                " █      \"help\", \"exit\", \"quit\", \"volume\",        █ \n" +
                " █       \"mute\", \"?\",\"search\", \"take\", \"return\"  █ \n" +
                " █                                               █ \n" +
                " █                                               █ \n" +
                " █   Dialogue options:                           █ \n" +
                " █                                               █ \n" +
                " █      \"1\",\"2\",\"3\",\"4\"                          █ \n" +
                " █                                               █ \n" +
                " █                                               █ \n" +
                " █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n" +
                "\t        Type \"return\" to resume";

        centerMenu = addSpaces(centerMenu);
        ColoredPrinter.print("red", centerMenu);


    }

}

