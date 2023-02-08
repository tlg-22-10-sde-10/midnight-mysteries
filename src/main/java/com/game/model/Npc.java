package com.game.model;

import java.util.List;

public class Npc {
    private String characterName;
    private String characterLocation;
    private List<String> dialogueOptions;

    public Npc(String characterName, String characterLocation, List<String> dialogueOptions) {
        this.characterName = characterName;
        this.characterLocation = characterLocation;
        this.dialogueOptions = dialogueOptions;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterLocation() {
        return characterLocation;
    }

    public void setCharacterLocation(String characterLocation) {
        this.characterLocation = characterLocation;
    }

    public List<String> getDialogueOptions() {
        return dialogueOptions;
    }

    public void setDialogueOptions(List<String> dialogueOptions) {
        this.dialogueOptions = dialogueOptions;
    }
}
