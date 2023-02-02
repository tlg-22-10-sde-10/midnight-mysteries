package com.game.location;

import com.game.inventory.Item;

import java.util.List;

public class Location {
    private String locationName;
    private List<Item> locationItems;
    private String locationDescription;

    public Location(){}
    public Location(String locationName, List<Item> locationItems, String locationDescription) {
        this.locationName = locationName;
        this.locationItems = locationItems;
        this.locationDescription = locationDescription;
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
}
