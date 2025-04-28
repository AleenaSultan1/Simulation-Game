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

import org.team12.model.Map;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.Entity;
import org.team12.model.entities.Item;
import org.team12.model.entities.Player;
import org.team12.view.AssetSetter;
import org.team12.view.GameUI;
import javax.swing.JFrame;

public class GameController implements Runnable{


    // GAME SETTINGS
    private static final int FPS = 60; // Used in the main game loop to run the game at 60 frames per second




    // MAP SETTINGS
    private final int originalTileSize = 16; // 16 x 16 pixel tile
    private final int scale = 3; // scale everything up by a factor or 3
    public int tileSize = originalTileSize * scale; // Standard tile size 48x48 pixels

    // SCREEN SETTINGS AND VARIABLES
    public int maxScreenCol = 16; // Number of tiles visible on the screen (vertically)
    public int maxScreenRow = 12; // number of tiles visible on the screen (horizontally)
    public int screenWidth =  tileSize * maxScreenCol; // 786 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS: Will change to specific map size
    public final int maxWorldCol = 35;
    public final int maxWorldRow = 27;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // GAME LOOP VARIABLES
    Thread gameThread; // Thread to start the game loop of 60 frames per second

    // Game objects
    public GameUI gameUI;
    public InputController inputController;
    public Player player;
    public AssetSetter assetSetter;
    public Entity[] enemies = new Entity[20];
    public Entity lilyFinalBoss;
    public Map map;
    public CollisionController cController;



    public GameController() {
        // Initialize Objects
        inputController = new InputController();
        cController = new CollisionController(this);

        // Initialize game objects
        map = new Map(this);
        player = new Player(this, inputController);

//        enemies[0] = new Enemy(this);
//        enemies[0].worldX = tileSize * 9;
//        enemies[0].worldY = tileSize * 7;
//
//        enemies[1] = new Enemy(this);
//        enemies[1].worldX = tileSize * 7;
//        enemies[1].worldY = tileSize * 5;

        // Initialize UI
        gameUI = new GameUI(this);
        gameUI.initializeWindow();

    }



    public void startGame() {
        populateMap();
        // Starts a gameThread which is used to run the game loop
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Used to spawn in items, enemies, etc.
    public void populateMap(){
        map.placeItems();
        //map.placeEnemy();
        //map.placeLilyFinalBoss();
    }

    // At the moment: moves the player according to which key is pressed
    public void update(){
        player.update();

        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemies[i].update();
            }
        }

        if (lilyFinalBoss != null) {
            lilyFinalBoss.update();
        }

    }

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

        // Variables to display FPS every second
        long timer = 0;
        int drawCount = 0;

        // implement GameLoop: Update backend, update front end
        while (gameThread != null) {


            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta>=1){
                // update player position, will also be used to update enemy position and status of items
                update();
                // Makes a new frame for the Game UI with the updated changes
                gameUI.repaint();
                delta--;
                drawCount++;
            }

            // Displays FPS
//            if(timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public static void main(String[] args) {// Run on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            GameController gameController = new GameController();
            gameController.startGame();
        });
    }
}
