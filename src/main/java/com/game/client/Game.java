package com.game.client;

import com.game.controller.Sound;
import com.game.model.Location;
import com.game.menu.RenderStartUI;
import com.game.menu.StoryTutorial;
import com.game.model.Dialogue;
import com.game.model.Npc;
import com.game.controller.Ascii;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class Game {
    private static Scanner userInput = new Scanner(System.in);
    private static Map<String, Location> locations = new HashMap<>();
    private static Map<String, Npc> npcs = new HashMap();
    private static Map<String, Dialogue> dialogue = new HashMap<>();

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

        //music
        Thread backgroundThread = new Thread(() -> {
            Sound sound = new Sound();
            while (true) {
                sound.playMusic();
            }
        });

        backgroundThread.start();


        // print game background
        StoryTutorial.printStory();

        readFiles();

        // game start menu
        System.out.print("\033[" + 29 + ";1H");
        new RenderStartUI(locations, npcs, dialogue);
    }

    private void readFiles() {
        // load external json
        Gson gson = new Gson();


        //Reader reader = new InputStreamReader

        // 55-60
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

    public static Map<String, Dialogue> getPath(String path) {
        Map<String, Dialogue> dialogue1 = new HashMap<>();
        Gson gson = new Gson();

        try (InputStream inputStream = Game.class.getResourceAsStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Type type = new TypeToken<HashMap<String, Dialogue>>(){}.getType();

            Map<String, Dialogue> newMap = new HashMap<>();

            // Convert JSON File to Java Object
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            for (int i = 0; i < array.size(); i++) {
                newMap = gson.fromJson(array.get(i), type);
                for (Map.Entry<String, Dialogue> entry : newMap.entrySet()) {
                    dialogue1.put(entry.getKey(), entry.getValue());
                }
            }
            return dialogue1;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dialogue1;
    }


}
