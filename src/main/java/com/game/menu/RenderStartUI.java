package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.location.Location;
import com.game.npc.Npc;
import com.game.player.Player;
import com.game.utils.InputHelper;
import com.game.utils.TextParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenderStartUI {

    private Map<String, Location> locations;
    private Map<String, Npc> npcs;

    public RenderStartUI(Map<String, Location> locations, Map<String, Npc> npcs) {
        this.locations = locations;
        this.npcs = npcs;
        generateStartMenu();
    }
    private void generateStartMenu() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("To start a new game please enter \"start\"");

//            String input = InputHelper.updateConfirmSelection();
            String input = TextParser.validateInput();

            if (input.equals("start")) {
                Player player = processPlayerInformation();
                Session newSession = new Session(player, locations, npcs);
                MainMenu mainMenu = new MainMenu(newSession, player);
                mainMenu.renderMenu();
                isValidInput = true;
            } else {
                System.out.println("Not a valid input");
            }
        }
    }

    private Player processPlayerInformation() {
        Map<String, Item> storage = new HashMap<>();
        Item flashlight = new Item("flashlight", "can use to light up the room", 1, true);
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
            String editConfirmation = TextParser.validateInput();
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
}
