package com.game.menu;

import com.game.client.session.Session;
import com.game.inventory.Inventory;
import com.game.inventory.Item;
import com.game.player.Player;
import com.game.utils.Ascii;
import com.game.utils.InputHelper;
import com.game.utils.TextParser;

import java.util.Map;

public class MainMenu extends Menu {
    protected Session session;
    protected Player player;
    protected int selection;

    public MainMenu(Session session, Player player) {
        this.session = session;
        this.player = player;
    }

    @Override
    public void renderMenu() {
        System.out.println("Welcome Detective " + player.getPlayerName());
        Ascii.clearTerminal();
        do {
            mainMenuHeader();
            loadDialogue();
            setSelection(Integer.parseInt(TextParser.validateInput()));
        } while (getSelection() != 0);

    }

    private void mainMenuHeader() {
        System.out.println("Current Location: " + player.getLocation());
        System.out.print("Inventory: ");
        player.printPlayerStorage();
    }

    private void loadDialogue() {
        int optionSelect = 1;
        if (player.getLocation() == "Lobby") {
            System.out.println(session.getLocations().get(0).getLocationDescription());
            for (String option : session.getNpcs().get(0).getDialogueOptions()) {
                System.out.println(optionSelect + ") " + option);
                optionSelect++;
            }
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
}
