package com.game.controller;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.model.Item;
import com.game.model.Player;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ItemGenerator {
    private static Player currentPlayer;
    public ItemGenerator(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private static Map<String, Object> itemList = new HashMap<>();
    private static List<String> items = new ArrayList<>();

    public static void readFiles() {
        Map<String, Object> map = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = ItemGenerator.class.getResourceAsStream("/items.json");
            map = mapper.readValue(inputStream, Map.class);
            items.add("-1");
            for (String key : map.keySet()) {
                items.add(key.toLowerCase());
            }
            itemList = map;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void searchForRandomItem() {
        readFiles();
        Ascii.clearTerminal();

        // weighted chance for each item
        int[] weights = {50, 5, 5, 5, 5, 5, 5};

        String generatedItem = randomWeightedString(items, weights);

        if (generatedItem != "-1") {
            Item createdItem = new Item(generatedItem,
                    "Change description later...", 1, true);


            currentPlayer.getPlayerStorage().addToStorage(createdItem);
            Ascii.printTextCenterWithDelay("You find a " + generatedItem + "!");

            System.out.println();
        } else {
            Ascii.printTextCenterWithDelay("You attempt to search the room but find nothing...");
        }


        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static String randomWeightedString(List<String> strings, int[] weights) {
        int totalWeight = Arrays.stream(weights).sum();
        int randomIndex = (int) (Math.random() * totalWeight);

        for (int i = 0; i < weights.length; i++) {
            if (randomIndex < weights[i]) {
                return strings.get(i);
            }
            randomIndex -= weights[i];
        }
        return "-1";
    }


    public static void takeItem(String itemToTake) {
        readFiles();
        String[] words = itemToTake.split("\\s+");
        String itemToBeTaken = toTitleCase(words[1]);
        Object itemFromList = itemList.get(itemToBeTaken);

        Ascii.clearTerminal();

        if (itemFromList != null) {
            // create a new item
            Item createdItem = new Item(itemToBeTaken, itemList.get(itemToBeTaken).toString(), 1, false);
            currentPlayer.getPlayerStorage().addToStorage(createdItem);
            Ascii.printTextCenterWithDelay("You take a " + itemToBeTaken + "!");
            System.out.println();


        } else {
            Ascii.printTextCenterWithDelay("You can't take what doesn't belong...");
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static String toTitleCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        Ascii.clearTerminal();


        for (char c : input.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}

//
//class m{
//    public static void main(String[] args) {
//        new Ascii();
//        Player player = new Player("t","Pool",)
//        ItemGenerator.takeItem("Take Key");
//    }
//}