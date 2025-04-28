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
import org.team12.model.Map;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Player;

import javax.swing.JPanel;
import java.awt.*;

public class GameUI extends JPanel implements Runnable{

    // SCREEN SETTINGS AND VARIABLES
    private static final int originalTileSize = 16; // 16 x 16 pixel tile
    private static final int scale = 3; // scale everything up by a factor or 3
    public static int tileSize = originalTileSize * scale; // Standard tile size 48x48 pixels

    public static int maxScreenCol = 16; // Number of tiles visible on the screen (vertically)
    public static int maxScreenRow = 12; // number of tiles visible on the screen (horizontally)
    public static int screenWidth = tileSize * maxScreenCol; // 786 pixels
    public static int screenHeight = tileSize * maxScreenRow; // 576 pixels


    // GAME LOOP VARIABLES
    private Thread gameThread; // Thread to start the game loop of 60 frames per second
    int FPS = 60; // Used in the main game loop to run the game at 60 frames per second

    // WORLD SETTINGS: Will change to specific map size
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public Map map;
    private MapRenderer mapRenderer;
    private EntityRenderer entityRenderer;
    private InputController inputController = new InputController();
    public Player player;



    // Constructor for a game UI
    public GameUI(){
        player = new Player(this, inputController, 20);
        map = new Map("/map/dungeonMap.txt");
        mapRenderer = new MapRenderer(player, map, tileSize);
        entityRenderer = new EntityRenderer(tileSize);
        // Set the size of the UI to the size of the screen
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // Sets the background of the map: will need to load/generate a map
        this.setBackground(Color.black);
        // Double buffer for better efficiency
        this.setDoubleBuffered(true);
        // Makes the GameUI interactable with the keyboard
        this.requestFocusInWindow(); // focuses everything in the window
        this.setFocusable(true);
        // Creates an object to register user inputs
        this.addKeyListener(inputController);

    }

    // Starts a gameThread which is used to run the game loop
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Runs the actual GameUI/Game

    /**
     * Update information such as character position
     * Draw the screen with the updated information
     */
    @Override
    public void run() {

        // Set variables to run the game at 60FPS
        double drawInterval = (double) 1000000000 / FPS; // 0.01666 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long moveCooldown = 10000;

        // Variables to display FPS every second
        long timer = 0;
        int drawCount = 0;
        long lastMoveTime = System.currentTimeMillis();


        // implement GameLoop: Update backend, update front end
        while (gameThread != null) {


            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;


            if(delta>=1){
                // update player position, will also be used to update enemy position and status of items
                // Makes a new frame for the Game UI with the updated changes
                repaint();
                delta--;
                drawCount++;
                player.update();
                if (currentTime - lastMoveTime > moveCooldown) {
                    for (Enemy enemy : map.enemiesOnMap) {
                        enemy.moveRandomly();
                    }
                    lastMoveTime = currentTime;
                }

            }

            // Displays FPS
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    // At the moment: moves the player according to which key is pressed
//    public void update(){
//        player.update();
//
//    }

    // Paints the player as a white rectangle
    // HIERARCHY MATTERS, map should always be first and player should always be last
    public void paintComponent(Graphics g){
        // inherit the paintComponent method
        super.paintComponent(g);
        // create a new Graphics 2d Object
        Graphics2D g2 = (Graphics2D) g;

        // draw the map
        mapRenderer.draw(g2);
        entityRenderer.drawEntity(g2, player, player);
        for (Enemy enemy : map.enemiesOnMap) {
            entityRenderer.drawEntity(g2, enemy, player);
        }
        // dispose of the objects
        g2.dispose();
    }
}
