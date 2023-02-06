package com.game.client.session;

import com.game.location.Location;
import com.game.model.Dialogue;
import com.game.npc.Npc;
import com.game.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Session {
    private Player player;
    private Map<String, Location> locations;
    private Map<String, Npc> npcs;
    private Map<String, Dialogue> dialogue;


    public Session(Player player, Map<String, Location> locations, Map<String, Npc> npcs, Map<String, Dialogue> dialogue) {

        this.player = player;
        this.locations = locations;
        this.npcs = npcs;
        this.dialogue = dialogue;
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

    public Map<String, Dialogue> getDialogue() {
        return dialogue;
    }

    public void setDialogue(Map<String, Dialogue> dialogue) {
        this.dialogue = dialogue;
    }
}
