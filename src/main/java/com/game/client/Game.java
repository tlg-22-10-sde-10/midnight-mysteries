package com.game.client;

import com.game.menu.StoryTutorial;
import com.game.utils.Ascii;

import java.util.Scanner;

public class Game {
    private static Scanner userInput = new Scanner(System.in);

    public Game() throws InterruptedException {
        main();
    }

    public static void main() throws InterruptedException {

        // set title
        Ascii.setTerminalTitle();

        // setup ascii
        Ascii temp = new Ascii();

        // print title "Midnight Mysteries"
        Ascii.printTitle();

        // print game background
        StoryTutorial.printStory();

        //move cursor to bottom of screen
        System.out.print("\033[" + 29 + ";1H");
        System.out.println("Type 'start' to Start Game 'exit' to Exit: ");
        String pressedKey = userInput.nextLine();
    }
}
