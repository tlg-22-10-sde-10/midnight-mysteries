package com.game.utils;

import java.util.*;

public class ColoredPrinter {

    public static final String ANSI_RESET = "\u001B[0m";
    public static Map<String,String> ANSI_CODES;

    public ColoredPrinter() {
        ANSI_CODES = new HashMap<>();

        ANSI_CODES.put("RED","\u001B[0m");
        ANSI_CODES.put("GREEN","\u001B[32m");
        ANSI_CODES.put("RED","\u001B[31m");
    }

    public static void print(String color, String text){
        System.out.println(ANSI_CODES.get(color.toUpperCase()) + text + ANSI_RESET);
    }

    public static void print(String color, char text){
        System.out.print(ANSI_CODES.get(color.toUpperCase()) + text + ANSI_RESET);
    }

}
