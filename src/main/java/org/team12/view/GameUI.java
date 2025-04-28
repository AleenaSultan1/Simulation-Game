/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:31 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: GameUI
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;


import org.team12.controller.GameController;
import org.team12.model.Map;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Player;

import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class GameUI extends JPanel{
    private GameController gameController;
    private Map map;
    private MapRenderer mapRenderer;
    private EntityRenderer entityRenderer;
    public Player player;

    private final int originalTileSize = 16; // 16 x 16 pixel tile
    private final int scale = 3; // scale everything up by a factor or 3
    public int tileSize = originalTileSize * scale; // Standard tile size 48x48 pixels


    // Constructor for a game UI
    public GameUI(GameController gameController){
        this.gameController = gameController;

        // Set the size of the UI to the size of the screen
        this.setPreferredSize(new Dimension(gameController.screenWidth, gameController.screenHeight));
        // Sets the background of the map: will need to load/generate a map
        this.setBackground(Color.black);
        // Makes the GameUI interactable with the keyboard
        this.requestFocusInWindow(); // focuses everything in the window
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        // Creates an object to register user inputs
        this.addKeyListener(gameController.inputController);

    }




    public void initializeWindow(){
        // Create a screen window to display the game
        JFrame window = new JFrame();

        // Format basic screen settings
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Dungeon Game");

        // Add it to the screen
        window.add(this);

        // Automatically size the window
        window.pack();
        // More Formatting
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }



    // Paints the player as a white rectangle
    // HIERARCHY MATTERS, map should always be first and player should always be last
    public void paintComponent(Graphics g){
        // inherit the paintComponent method
        super.paintComponent(g);
        // create a new Graphics 2d Object
        Graphics2D g2 = (Graphics2D) g;

        // draw the map
        gameController.map.draw(g2);

        // draw the objects on the map
        // Parse through our possible object list and draw the appropriate ones for each object
        for (int i = 0; i < gameController.map.obj.length; i++){
            if (gameController.map.obj[i] != null){
                gameController.map.obj[i].draw(g2, gameController);
            }
        }

        // Draw enemies
//        entityRenderer.drawEntity(g2, player, player);
//        for (Enemy enemy : map.enemiesOnMap) {
//            entityRenderer.drawEntity(g2, enemy, player);
//        }

        // draw the player
        gameController.player.draw(g2);

//        // draw the map
//        mapRenderer.draw(g2);
//        entityRenderer.drawEntity(g2, player, player);
//        for (Enemy enemy : map.enemiesOnMap) {
//            entityRenderer.drawEntity(g2, enemy, player);
//        }

        // dispose of the objects
        g2.dispose();
    }
}
