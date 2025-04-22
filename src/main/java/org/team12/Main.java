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