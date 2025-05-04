package org.team12;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import org.team12.controller.GameController;
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

