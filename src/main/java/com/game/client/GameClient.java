package com.game.client;

import com.game.menu.StoryTutorial;

import com.game.menu.RenderStartUI;

public class GameClient {
    public static void main(String[] args) throws InterruptedException {
        StoryTutorial.printStory();
        new RenderStartUI();
    }
}
