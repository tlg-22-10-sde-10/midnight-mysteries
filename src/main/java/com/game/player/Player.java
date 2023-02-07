package com.game.player;

import com.game.inventory.Inventory;
import com.game.inventory.Item;

public class Player {
    private String playerName;
    private String location;
    private Inventory playerStorage;

    public Player(String playerName, String location, Inventory playerStorage) {
        this.playerName = playerName;
        this.location = location;
        this.playerStorage = playerStorage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Inventory getPlayerStorage() {
        return playerStorage;
    }

    public void setPlayerStorage(Inventory playerStorage) {
        this.playerStorage = playerStorage;
    }

    public void addToPlayerStorage(Item itemToAdd) {
        this.playerStorage.getStorage().put(itemToAdd.toString(), itemToAdd);
    }

}
