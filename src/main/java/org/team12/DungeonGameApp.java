package org.team12;

import org.team12.view.GameUI;

import javax.swing.*;

public class DungeonGameApp {

    public static void main(String[] args) {
        // Create a window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");

        // Create the GameUI (our main game panel)
        GameUI gameUI = new GameUI();
        window.add(gameUI);
        window.pack(); // fit window to GameUI's preferred size

        // Center window and make it visible
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Start the game loop
        gameUI.startGameThread();
    }
}

