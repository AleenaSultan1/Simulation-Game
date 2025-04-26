/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:30 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: GameController
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.view.GameUI;
import javax.swing.JFrame;

public class GameController {
    // Create a new game UI: This is the actual game
    static GameUI gameUI = new GameUI();

    public static void initializeWindow(){
        // Create a screen window to display the game
        JFrame window = new JFrame();

        // Format basic screen settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");

        // Add it to the screen
        window.add(gameUI);

        // Automatically size the window
        window.pack();
        // More Formatting
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void initializeGame(){
        // spawn objects and items into the map
        gameUI.populateMap();
        // start the main game loop
        gameUI.startGameThread();
    }


    public static void main(String[] args) {
        // Formats the window from which the game will be run
        initializeWindow();

        // starts the main game loop and spawns in entities and items onto the map
        initializeGame();
    }
}
