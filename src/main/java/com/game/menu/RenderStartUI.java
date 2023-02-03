package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.location.Location;
import com.game.npc.Npc;
import com.game.player.Player;
import com.game.utils.InputHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RenderStartUI {

    private List<Location> locations;
    private List<Npc> npcs;
    public RenderStartUI(List<Location> locations, List<Npc> npcs) {
        this.locations = locations;
        this.npcs = npcs;
        generateStartMenu();
    }
    private void generateStartMenu() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("To start a new game please enter \"start\"");
            String input = InputHelper.updateConfirmSelection();

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
            playerName = InputHelper.updateConfirmSelection();

            System.out.println("\nYou entered.. " +
                    "\n name: " + playerName +
                    "\n\nIs that correct? Yes/No");
            String editConfirmation = InputHelper.updateConfirmSelection();
            if (editConfirmation.equalsIgnoreCase("y") || editConfirmation.equalsIgnoreCase("yes")) {
                isConfirmed = true;
            }
        } while (!isConfirmed);

        Player newPlayer = new Player(playerName, "Lobby", newInventory);

        return newPlayer;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Npc> getNpcs() {
        return npcs;
    }

    public void setNpcs(List<Npc> npcs) {
        this.npcs = npcs;
    }
}
