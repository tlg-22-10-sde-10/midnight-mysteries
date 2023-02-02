package com.game.client.session;

import com.game.location.Location;
import com.game.player.Player;

public class Session {
    private Player player;
    private Location location;

    public Session(Player player) {
        this.player = player;
    }

    // Accessor Methods
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
