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
