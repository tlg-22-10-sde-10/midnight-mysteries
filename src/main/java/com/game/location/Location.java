package com.game.location;

import com.game.inventory.Item;

import java.util.List;

public class Location {
    private String locationName;
    private List<Item> locationItems;
    private String locationDescription;
    private String npc;

    public Location(){}
    public Location(String locationName, List<Item> locationItems, String locationDescription, String npc) {
        this.locationName = locationName;
        this.locationItems = locationItems;
        this.locationDescription = locationDescription;
        this.npc = npc;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public List<Item> getLocationItems() {
        return locationItems;
    }

    public void setLocationItems(List<Item> locationItems) {
        this.locationItems = locationItems;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getNpc() {
        return npc;
    }

    public void setNpc(String npc) {
        this.npc = npc;
    }
}
