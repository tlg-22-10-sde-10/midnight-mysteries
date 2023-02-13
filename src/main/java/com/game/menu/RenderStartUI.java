package com.game.menu;

import com.game.client.session.Session;
import com.game.controller.Ascii;
import com.game.model.Inventory;
import com.game.model.Item;
import com.game.controller.ItemGenerator;
import com.game.model.Location;
import com.game.model.Dialogue;
import com.game.model.Npc;
import com.game.model.Player;
import com.game.controller.TextParser;

import java.util.HashMap;
import java.util.Map;

public class RenderStartUI {

    private Map<String, Location> locations;
    private Map<String, Npc> npcs;

    private Map<String, Dialogue> dialogue;

    public RenderStartUI(Map<String, Location> locations, Map<String, Npc> npcs, Map<String, Dialogue> dialogue) {
        this.locations = locations;
        this.npcs = npcs;
        this.dialogue = dialogue;
        generateStartMenu();
    }
    private void generateStartMenu() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("To start a new game please enter \"start\"");

//            String input = InputHelper.updateConfirmSelection();
            String input = TextParser.optionalInput();

            if (input.equals("start")) {
                Player player = processPlayerInformation();
                Session newSession = new Session(player, locations, npcs, dialogue);
                MainMenu mainMenu = new MainMenu(newSession, player);
                ItemGenerator itemGenerator = new ItemGenerator(player);
                Ascii.printHotel();
                mainMenu.renderMenu();
                isValidInput = true;
            } else {
                System.out.println("Not a valid input");
            }
        }
    }

    private Player processPlayerInformation() {
        Map<String, Item> storage = new HashMap<>();

        Item flashlight = new Item("Flashlight", "can use to light up the room", 1, true);
        storage.put(flashlight.getItemName(), flashlight);

        Inventory newInventory = new Inventory(storage);
        String playerName;
        boolean isConfirmed = false;

        do {
            System.out.println("Please enter your player's name: ");
//            playerName = InputHelper.updateConfirmSelection();
            playerName = TextParser.optionalInput();


            System.out.println("\nYou entered.. " +
                    "\n name: " + playerName +
                    "\n\nIs that correct? Yes/No");
//            String editConfirmation = InputHelper.updateConfirmSelection();
            String editConfirmation = TextParser.optionalInput();
            if (editConfirmation.equalsIgnoreCase("y") || editConfirmation.equalsIgnoreCase("yes")) {
                isConfirmed = true;
            }
        } while (!isConfirmed);

        Player newPlayer = new Player(playerName, "Lobby", newInventory);

        return newPlayer;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public void setLocations(Map<String, Location> locations) {
        this.locations = locations;
    }

    public Map<String, Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(Map<String, Npc> npcs) {
        this.npcs = npcs;
    }

    public Map<String, Dialogue> getDialogue() {
        return dialogue;
    }

    public void setDialogue(Map<String, Dialogue> dialogue) {
        this.dialogue = dialogue;
    }
}
