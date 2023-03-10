package com.game.menu;

import com.game.controller.Ascii;

public class StoryTutorial {

    public static final String ANSI_RED = 	"\u001B[31m";
    public static final String ANSI_RESET = "\u001B[30m";

    public static void printStory() throws InterruptedException {
        Ascii.clearTerminal();

        String story = "You're a private detective who is investigating strange occurrences at a seemingly abandoned hotel.\n" +
                "As you delve deeper, you discover that the hotel is haunted by the spirits of its past guests, \n" +
                "who all met tragic ends. To solve the case and lay the spirits to rest, you must explore the hotel,\n " +
                "gather clues, and piece together the events that led to their deaths.";


        Ascii.printTextCenterWithDelay(story);

    }

}
