package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.player.Player;

import java.util.Map;

public class MainMenu extends Menu {
    protected Session session;
    protected Player player;

    public MainMenu(Session session, Player player) {
        this.session = session;
        this.player = player;
    }

    @Override
    public void renderMenu() {
        System.out.println("Game started..");
        mainMenuHeader();
        System.out.println("Welcome Detective " + player.getPlayerName());
    }

    private void mainMenuHeader() {
        System.out.println("Current Location: " + player.getLocation());
        System.out.print("Inventory: ");
        Map<String, Item> inventory = player.getPlayerStorage().getStorage();
        if (inventory.size() == 0) {
            System.out.println("Empty");
        }
        for (Map.Entry<String, Item> item : inventory.entrySet()) {
            String itemName = item.getKey();
            System.out.println(itemName);
        }
        System.out.println("====================================");
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
