package com.game.client;

import com.game.client.session.Session;
import com.game.location.Location;
import com.game.menu.RenderStartUI;
import com.game.menu.StoryTutorial;
import com.game.npc.Npc;
import com.game.utils.Ascii;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.*;
import java.util.*;

public class Game {
    private static Scanner userInput = new Scanner(System.in);
    private static Map<String, Location> locations = new HashMap<>();
    private static Map<String, Npc> npcs = new HashMap();
    private static Map<String, ArrayList<String>> dialogue = new HashMap<>();

    public Game() throws InterruptedException {
        main();
    }

    public void main() throws InterruptedException {

        // set title
        Ascii.setTerminalTitle();

        // setup ascii
        Ascii temp = new Ascii();

        // print title "Midnight Mysteries"
        Ascii.printTitleBanner();

        // print game background
        StoryTutorial.printStory();

        readFiles();

        // game start menu
        System.out.print("\033[" + 29 + ";1H");
        new RenderStartUI(locations, npcs);
    }

    private void readFiles() {
        // load external json
        Gson gson = new Gson();

        try (InputStream inputStream = getClass().getResourceAsStream("/locations.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Convert JSON File to Java Object
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            for (int i = 0; i < array.size(); i++) {
                Location location = gson.fromJson(array.get(i), Location.class);
                locations.put(location.getLocationName(), location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream inputStream = getClass().getResourceAsStream("/npcs.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            // Convert JSON File to Java Object
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            for (int i = 0; i < array.size(); i++) {
                Npc npc = gson.fromJson(array.get(i), Npc.class);
                npcs.put(npc.getCharacterName(), npc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
