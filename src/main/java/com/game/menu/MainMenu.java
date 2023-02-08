package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.player.Player;
import com.game.utils.Ascii;
import com.game.utils.TextParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainMenu extends Menu {
    protected Session session;
    protected Player player;
    protected int selection;
    private int savedSelection;
    private List<String> options;

    public MainMenu(Session session, Player player) {
        this.session = session;
        this.player = player;
    }

    @Override
    public void renderMenu() {
        Ascii.clearTerminal();
        loadStartingDialogue();
        do {
            mainMenuHeader();
            setSelection(Integer.parseInt(TextParser.validateInput()));
            processSelection();
        } while (getSelection() != 0);

    }

    private void mainMenuHeader() {
        System.out.println();
        System.out.println("====================================");
        System.out.println("Current Location: " + player.getLocation());
        System.out.println(session.getLocations().get(player.getLocation()).getLocationDescription());
        System.out.print("Inventory: ");
        player.printPlayerStorage();
    }

    private void loadStartingDialogue() {
        int optionSelect = 1;
        setOptions(new ArrayList<>());
        String npc = getSession().getLocations().get("Lobby").getNpc();

        Ascii.printTextCenterWithDelay("Hello Detective " +player.getPlayerName()+ ". There has been a murder at the bar, " +
                "room, pool and restaurant.\n We need your help in solving these murders. Where would you like to" +
                " go first? ");

        for (String option : session.getNpcs().get(npc).getDialogueOptions()) {
            getOptions().add(option);
            System.out.println(optionSelect + ") " + option);
            optionSelect++;
        }
    }

    private void loadDialogue(String option) {
        Ascii.clearTerminal();
        int optionSelect = 1;
        setOptions(new ArrayList<>());
        player.setLocation(getSession().getDialogue().get(option).getLocation());

        // print backstory
//        System.out.println(getSession().getDialogue().get(option).getDialogue());
        Ascii.printTextCenterWithDelay(getSession().getDialogue().get(option).getDialogue());

        if (option.equals("Try opening the safe")) {
            System.out.println("Guess the last number to open the safe");
            openSafe();
            return;
        } else if (option.equals("Try unlocking computer")) {
            System.out.println("Please enter the password");
            unlockComputer();
            return;
        } else if (option.equals("Take key")) {
            Item key = getSession().getLocations().get(player.getLocation()).getLocationItems().get(0);
            player.getPlayerStorage().addToStorage(key);
        } else if (option.equals("Open the door")) {
            openDoor();
            return;
        }

        // print options
        List<String> dialogue = getSession().getDialogue().get(option).getOptions();
        for (int i = 0; i < dialogue.size(); i++) {
            getOptions().add(dialogue.get(i));
            System.out.println(optionSelect + ") " + dialogue.get(i));
            optionSelect++;
        }
    }

    private void loadPreviousDialogue() {
        Ascii.clearTerminal();
        int optionSelect = 1;

        // print previous options after closing help menu
        for (String dialogueOption : getOptions()) {
            System.out.println(optionSelect + ") " + dialogueOption);
            optionSelect++;
        }
    }

    private void processSelection() {
        if (getSelection() != -1 || getSelection() != 5) setSavedSelection(getSelection());

        // load options for the dialogue that was picked
        switch (getSelection()) {
            case 1:
                loadDialogue(getOptions().get(0));
                break;
            case 2:
                loadDialogue(getOptions().get(1));
                break;
            case 3:
                loadDialogue(getOptions().get(2));
                break;
            case 4:
                loadDialogue(getOptions().get(3));
                break;
            case 5:
                loadPreviousDialogue();
            default:
                break;
        }
    }
    private void openSafe() {
        int lastDigit = (int) Math.random() * (4 - 1);
        while (getSelection() != 3) {
            setSelection(Integer.parseInt(TextParser.validateInput()));
            if (lastDigit == getSelection()) {
                // string of json key
                loadDialogue("Safe unlocked");
            } else {
                System.out.println("Number didn't work..");
            }
        }
    }

    private void openDoor() {
        if (player.getPlayerStorage().getStorage().containsKey("key")) {
            loadDialogue("Office unlocked");
        } else {
            System.out.println("Looks like you need a key to unlock the door. Try searching for one.");
            loadDialogue("Need key");
        }
    }

    private void unlockComputer() {
        int password = 2012;
        setSelection(Integer.parseInt(TextParser.optionalInput()));
        if (password == getSelection()) {
            loadDialogue("Computer unlocked");
        } else {
            loadDialogue("Incorrect password");
        }
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getSelection() {
        return selection;
    }

    public void setSelection(int selection) {
        this.selection = selection;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getSavedSelection() {
        return savedSelection;
    }

    public void setSavedSelection(int savedSelection) {
        this.savedSelection = savedSelection;
    }
}
