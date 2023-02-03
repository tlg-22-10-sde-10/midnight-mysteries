package com.game.client.session;

import com.game.location.Location;
import com.game.npc.Npc;
import com.game.player.Player;

import java.util.List;

public class Session {
    private Player player;
    private List<Location> locations;
    private List<Npc> npcs;

    public Session(Player player, List<Location> locations, List<Npc> npcs) {

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
