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

import org.team12.controller.InputController;

import javax.swing.JPanel;
import java.awt.*;

public class GameUI extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16 pixels wide
    final int scale = 3; // scale everything up by a factor or 3
    final int tileSize = originalTileSize * scale; // Standard tile size
    final int maxScreenCol = 16; // Number of tiles visible on the screen (vertically)
    final int maxScreenRow = 12; // number of tiles visible on the screen (horizontally)
    final int screenWidth = tileSize * maxScreenCol; // Determines the default screen width of the UI
    final int screenHeight = tileSize * maxScreenRow; // Determines the default screen height of the UI

    InputController inputController = new InputController(); // Constructs and handles user movement
    Thread gameThread; // Thread to start the game loop of 60 frames per second
    int FPS = 60; // Used in the main game loop to run the game at 60 frames per second

    // Set player's default position. Normally the player spawns in the top left at (0, 0). Moves the player more towards the center of the screen
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // moves 4 pixels per frame

    // Constructor for a game UI
    public GameUI(){
        // Set the size of the UI to the size of the screen
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Sets the background of the map: will need to load/generate a map
        this.setBackground(Color.black);
        // Double buffer for better efficiency
        this.setDoubleBuffered(true);
        // Creates an object to register user inputs
        this.addKeyListener(inputController);
        // Makes the GameUI interactable with the keyboard
        this.setFocusable(true);
    }

    // Starts a gameThread which is used to run the game loop
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Runs the actual GameUI/Game
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // implement GameLoop
        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta>=1){
                // update player position, will also be used to update enemy position and status of items
                update();
                // Makes a new frame for the Game UI with the updated changes
                repaint();
                delta--;
            }
        }
    }

    // At the moment: moves the player according to which key is pressed
    public void update(){
        if(inputController.upPressed){
            playerY -= playerSpeed;
        } else if (inputController.downPressed){
            playerY += playerSpeed;
        } else if (inputController.leftPressed){
            playerX -= playerSpeed;
        } else if (inputController.rightPressed){
            playerX += playerSpeed;
        }

    }

    // Paints the player as a white rectangle
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
