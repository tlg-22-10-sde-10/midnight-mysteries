package com.game.utils;

import java.util.Scanner;

public class InputHelper {
    public static String updateConfirmSelection() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }
}
