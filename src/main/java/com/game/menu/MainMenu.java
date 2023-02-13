package com.game.menu;

import com.game.client.Game;
import com.game.client.session.Session;
import com.game.controller.GamePuzzles;
import com.game.model.Item;
import com.game.model.Dialogue;
import com.game.model.Player;
import com.game.controller.Ascii;
import com.game.controller.TextParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainMenu {
    protected Session session;
    protected Player player;
    protected int selection;
    private int savedSelection;
    private List<String> options;

    private String currentScene;

    public MainMenu(Session session, Player player) {
        this.session = session;
        this.player = player;
    }
    public void renderMenu() {
        Ascii.clearTerminal();
        loadStartingDialogue();
        //print
        do {
            playerStatus();
            setSelection(Integer.parseInt(TextParser.validateInput()));
            processSelection();
        } while (getSelection() != 0);

    }

    private void playerStatus() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Current Location: " + player.getLocation());
        System.out.println(session.getLocations().get(player.getLocation()).getLocationDescription());
        System.out.print("Inventory: ");
        player.printPlayerStorage();
    }

    private void loadStartingDialogue() {
        int optionSelect = 1;
        setOptions(new ArrayList<>());
        String npc = getSession().getLocations().get("Lobby").getNpc();

        Ascii.printTextCenterWithDelay(npc + ": Greetings Detective " + player.getPlayerName() + ", welcome to the Paradise Hotel,\n where death has cast its shadow over the bar, room, pool, and restaurant. \n The once tranquil atmosphere has been disturbed by a string of gruesome murders,\n and it falls upon you to unravel the mystery and bring justice to the victims.\n Which location shall you visit first, to begin your journey into the heart of darkness? \n");

        Ascii.printSpaces(4);
        for (String option : session.getNpcs().get(npc).getDialogueOptions()) {
            getOptions().add(option);
            System.out.println(optionSelect++ + ") " + option);
        }

        String choice = TextParser.validateInput();

        switch (choice) {
            case "1":
                String room = "/room.json";
                Map<String, Dialogue> path = Game.getPath(room);
                session.setDialogue(path);
                loadDialogue("Book a room");
                break;
            case "2":
                String bar = "/bar.json";
                Map<String, Dialogue> path1 = Game.getPath(bar);
                session.setDialogue(path1);
                loadDialogue("Go to the bar");
                break;
            case "3":
                String restaurant = "/restaurant.json";
                Map<String, Dialogue> path2 = Game.getPath(restaurant);
                session.setDialogue(path2);
                loadDialogue("Go to the restaurant");
                break;
            case "4":
                String pool = "/pool.json";
                Map<String, Dialogue> path3 = Game.getPath(pool);
                session.setDialogue(path3);
                loadDialogue("Pool path");
                break;
            case "-1":
                String helpMenu = TextParser.validateInput();
                while (!helpMenu.equals("5")){
                    helpMenu = TextParser.validateInput();
                }
                Ascii.clearTerminal();
                loadStartingDialogue();
                break;

            default:
                Ascii.clearTerminal();
                loadStartingDialogue();
                break;
        }

    }

    private void loadDialogue(String option) {

        option = GamePuzzles.delegatePuzzle(option);

        option = TextParser.textParserItems(option);
        if (option.equals("")){
            option = currentScene;
        }else{
            currentScene = option;
        }

        int optionSelect = 1;
        String currentLocation = getSession().getDialogue().get(option).getLocation();
        String sceneDialogue = getSession().getDialogue().get(option).getDialogue();

        Ascii.clearTerminal();

        setOptions(new ArrayList<>());
        player.setLocation(currentLocation);
        Ascii.printTextCenterWithDelay(sceneDialogue);

        if (puzzleCheck(option)) {return;}
        changeStory(option);

        // print options
        List<String> dialogue = getSession().getDialogue().get(option).getOptions();


        // shuffle dialogue options
        // uncomment for release v1.3
//        Collections.shuffle(dialogue);

        for (int i = 0; i < dialogue.size(); i++) {
            getOptions().add(dialogue.get(i));
            System.out.println(optionSelect + ") " + dialogue.get(i));
            optionSelect++;
        }
    }
    private void processSelection() {

        if (getSelection() != -1 || getSelection() != 5) setSavedSelection(getSelection());

        // load options for the dialogue that was picked
        switch (getSelection()) {
            case 1:
                loadDialogue(getOptions().get(0));
                break;
            case 2:
                loadDialogue(getOptions().get(1));
                break;
            case 3:
                loadDialogue(getOptions().get(2));
                break;
            case 4:
                loadDialogue(getOptions().get(3));
                break;
            case 5:
                if (currentScene == null) {
                    Ascii.clearTerminal();
                    loadStartingDialogue();
                } else {
                    loadDialogue(currentScene);
                }
            default:
                break;
        }
    }

    private void changeStory(String option) {
        switch (option) {
            case "Book a room":
                session.setDialogue(Game.getPath("/room.json"));
                break;
            case "Go to the bar":
                session.setDialogue(Game.getPath("/bar.json"));
                break;
            case "Go to the restaurant":
                session.setDialogue(Game.getPath("/restaurant.json"));
                break;
            case "Go to the pool":
                session.setDialogue(Game.getPath("/pool.json"));
                break;
            default:
                break;
        }
    }
    private boolean puzzleCheck(String option) {
        boolean isPuzzle = false;
        if (option.equals("Try opening the safe")) {
            System.out.println("Guess the last number to open the safe");
            openSafe();
            isPuzzle = true;
        } else if (option.equals("Try unlocking computer")) {
            System.out.println("Please enter the password");
            unlockComputer();
            isPuzzle = true;
        } else if (option.equals("Take key")) {
            Item key = getSession().getLocations().get(player.getLocation()).getLocationItems().get(0);
            player.getPlayerStorage().addToStorage(key);
        } else if (option.equals("Open the door")) {
            openDoor();
            isPuzzle = true;
        }
        return isPuzzle;
    }

    private void openSafe() {
        int lastDigit = ((int) (Math.random() * (5 - 1))) + 1;
        setSelection(-1);
        while (getSelection() != lastDigit) {
            setSelection(Integer.parseInt(TextParser.validateInput()));
            if (lastDigit == getSelection()) {
                // string of json key
                loadDialogue("Safe unlocked");
            } else {
                System.out.println("Number didn't work..");
            }
        }
    }

    private void openDoor() {
        if (player.getPlayerStorage().getStorage().containsKey("key")) {
            loadDialogue("Office unlocked");
        } else {
            System.out.println("Looks like you need a key to unlock the door. Try searching for one.");
            loadDialogue("Need key");
        }
    }

    private void unlockComputer() {
        int password = 2012;
        setSelection(Integer.parseInt(TextParser.optionalInput()));
        if (password == getSelection()) {
            loadDialogue("Computer unlocked");
        } else {
            loadDialogue("Incorrect password");
        }
    }

    public Session getSession() {return session;}
    public void setSession(Session session) {this.session = session;}
    public Player getPlayer() {return player;}
    public void setPlayer(Player player) {this.player = player;}
    public int getSelection() {return selection;}
    public void setSelection(int selection) {this.selection = selection;}
    public List<String> getOptions() {return options;}
    public void setOptions(List<String> options) {this.options = options;}
    public int getSavedSelection() {return savedSelection;}
    public void setSavedSelection(int savedSelection) {this.savedSelection = savedSelection;}
}
