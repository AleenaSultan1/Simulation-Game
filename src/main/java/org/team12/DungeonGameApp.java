package org.team12;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.team12.view.GameUI;

import javax.swing.*;

public class DungeonGameApp {

    public static void main(String[] args) {

        // Create a screen window to display the game
        JFrame window = new JFrame();

        // Format basic screen settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");

        // Create a new game UI: This is the actual game
        GameUI gameUI = new GameUI();
        // Add it to the screen
        window.add(gameUI);

        // Automatically size the window
        window.pack();
        // More Formatting
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Start the game loop
        gameUI.startGameThread();
    }


}
