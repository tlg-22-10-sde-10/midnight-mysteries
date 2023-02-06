package com.game.client.session;

import com.game.location.Location;
import com.game.npc.Npc;
import com.game.player.Player;

import java.util.List;
import java.util.Map;

public class Session {
    private Player player;
    private Map<String, Location> locations;
    private Map<String, Npc> npcs;

    public Session(Player player, Map<String, Location> locations, Map<String, Npc> npcs) {

        this.player = player;
        this.locations = locations;
        this.npcs = npcs;
    }

    // Accessor Methods
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
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
