package com.game.model;

import java.util.List;

public class Dialogue {
    private String location;
    private String dialogue;
    private List<String> options;

    public Dialogue(String location, String dialogue, List<String> options) {
        this.location = location;
        this.dialogue = dialogue;
        this.options = options;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }
}
