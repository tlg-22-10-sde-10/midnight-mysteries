package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.player.Player;
import com.game.utils.InputHelper;

import java.util.HashMap;
import java.util.Map;

public class RenderStartUI {
    public RenderStartUI() {
        generateStartMenu();
    }
    private void generateStartMenu() {
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println("To start a new game please enter 1");
            String input = InputHelper.updateConfirmSelection();

            if (input.equals("1")) {
                Player player = processPlayerInformation();
                Session newSession = new Session(player);
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
                    "\nIs that correct? Yes/No");
            String editConfirmation = InputHelper.updateConfirmSelection();
            if (editConfirmation.equalsIgnoreCase("y") || editConfirmation.equalsIgnoreCase("yes")) {
                isConfirmed = true;
            }
        } while (!isConfirmed);

        Player newPlayer = new Player(playerName, "Default", newInventory);

        return newPlayer;
    }

}
