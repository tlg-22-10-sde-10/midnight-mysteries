package com.game.inventory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.player.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ItemGenerator {

    private static Map<String,Object> itemList = new HashMap<>();
    private static List<String> items = new ArrayList<>();
    public static void readFiles(){
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = null;

        try {
            map = mapper.readValue(new File("src/main/resources/items.json"), Map.class);
            for (String key : map.keySet()) {
                System.out.println(key);
                items.add(key);
            }

            itemList = map;

        } catch (Exception e) {
            System.out.println(e);
        }



    }

    public static void generateItem(Player currentPlayer){
        readFiles();
        System.out.println("Searching for a item...");

        Collections.shuffle(items);
        String randomItem = items.get(0);
        System.out.println("Random item: " + randomItem);
        System.out.println(itemList.get(randomItem));

        // create a new item
        Item createdItem = new Item(randomItem, itemList.get(randomItem).toString(), 1, false);

        // add item to passed player
        currentPlayer.addToPlayerStorage(createdItem);
    }



}



class M {
    public static void main(String[] args) {
        System.out.println("HERE");
//        ItemGenerator.readFiles();

        Map<String, Item> storage = new HashMap<>();
        Player player = new Player("Joe", "Lobby", new Inventory(storage));
        ItemGenerator.generateItem(player);



    }
}