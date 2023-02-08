package com.game.model;

import java.util.Map;

public class Inventory {
    private Map<String, Item> storage;

    public Inventory(Map<String, Item> storage) {
        this.storage = storage;
    }

    public void addToStorage(Item item) {
        this.storage.put(item.getItemName(), item);
    }

    public void removeFromStorage(Item item) {
        this.storage.remove(item);
    }

    public Map<String, Item> getStorage() {
        return storage;
    }

    public void setStorage(Map<String, Item> storage) {
        this.storage = storage;
    }

}
