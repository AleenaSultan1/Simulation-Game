/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/24/25
 * Time: 2:03 PM
 *
 * Project: csci205_final_project
 * Package: org.team12
 * Class: Main
 *
 * Description:
 *
 * ****************************************
 */

package org.team12;

import org.team12.view.GameUI;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");

        GameUI gameUI = new GameUI();
        window.add(gameUI);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);


        gameUI.startGameThread();
    }
}

