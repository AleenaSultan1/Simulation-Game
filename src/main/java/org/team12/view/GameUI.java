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

import org.team12.controller.CollisionController;
import org.team12.controller.GameController;
import org.team12.controller.InputController;
import org.team12.model.Map;
import org.team12.model.entities.*;
import org.team12.states.GameState;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Main game panel that handles rendering and game loop execution.
 * Manages all game components including map, entities, and UI elements.
 */
public class GameUI extends JPanel implements Runnable {
    // SCREEN SETTINGS AND VARIABLES
    /** Original tile size before scaling */
    private static final int originalTileSize = 16; // 16 x 16 pixel tile

    /** Scaling factor for graphics */
    private static final int scale = 3; // scale everything up by a factor or 3

    /** Scaled tile size */
    private static final int tileSize = originalTileSize * scale; // Standard tile size 48x48 pixels

    /** Maximum number of visible screen columns */
    private static final int maxScreenCol = 16; // Number of tiles visible on the screen (vertically)

    /** Maximum number of visible screen rows */
    private static final int maxScreenRow = 12; // number of tiles visible on the screen (horizontally)

    /** Total screen width in pixels */
    private static final int screenWidth = tileSize * maxScreenCol; // 786 pixels

    /** Total screen height in pixels */
    private static final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    /** Main game controller */
    private final GameController gameController;

    /**
     * @return The current tile size in pixels
     */
    public static int getTileSize() {
        return tileSize;
    }

    /**
     * @return The maximum number of world columns
     */
    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    /**
     * @return The screen width in pixels
     */
    public static int getScreenWidth() {
        return screenWidth;
    }

    /**
     * @return The screen height in pixels
     */
    public static int getScreenHeight() {
        return screenHeight;
    }

    /**
     * @return The maximum number of world rows
     */
    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    // GAME LOOP VARIABLES
    /** Thread for game execution */
    private Thread gameThread; // Thread to start the game loop of 60 frames per second

    /** Target frames per second */
    int FPS = 60; // Used in the main game loop to run the game at 60 frames per second

    // WORLD SETTINGS: Will change to specific map size
    /** Maximum columns in the game world */
    public final int maxWorldCol = 50;

    /** Maximum rows in the game world */
    public final int maxWorldRow = 12;

    /** Total world width in pixels */
    public final int worldWidth = tileSize * maxWorldCol;

    /** Total world height in pixels */
    public final int worldHeight = tileSize * maxWorldRow;

    /** Game map */
    private Map map;

    /** Map rendering component */
    private final MapRenderer mapRenderer;

    /** Entity rendering component */
    private final EntityRenderer entityRenderer;

    /** Player HUD component */
    private final PlayerHud playerHud;

    /** Input handler */
    private InputController inputController = new InputController();

    /** Collision detector */
    private CollisionController collisionController;

    /** Player character */
    public Player player;

    /**
     * Constructs the game UI and initializes all game components.
     */
    public GameUI() {
        map = new Map("/map/dungeonMap.txt", false);

        inputController = new InputController();
        playerHud = new PlayerHud(tileSize);

        gameController = new GameController(map, inputController, playerHud);

        player = gameController.getPlayer();
        mapRenderer = new MapRenderer(player, map, tileSize);
        entityRenderer = new EntityRenderer(tileSize, player);

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

    /**
     * Starts the game thread which runs the main game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Main game loop that updates game state and renders frames at target FPS.
     * Handles timing, updates, and rendering in a continuous loop.
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
                // Makes a new frame for the Game UI with the updated changes
                repaint();
                delta--;
                drawCount++;
                gameController.update();
            }

            // Displays FPS
            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    /**
     * Renders all game components based on current game state.
     * @param g The Graphics context to draw with
     */
    public void paintComponent(Graphics g) {
        // inherit the paintComponent method
        super.paintComponent(g);
        // create a new Graphics 2d Object
        Graphics2D g2 = (Graphics2D) g;

        if (gameController.getGameState() == GameState.QUIZ) {
            playerHud.drawLaptopQuiz(g2);
        } else if (gameController.getGameState() == GameState.PLAYING) {
            // draw the map
            mapRenderer.draw(g2);
            for (Entity entity : map.getEntitiesOnMap()) {
                entityRenderer.drawEntity(g2, entity);
                if (entity instanceof Enemy) {
                    entityRenderer.drawEnemyHP(g2, (Enemy) entity);
                }
            }
        } else {
            playerHud.drawTitleScreen(g2, gameController.getGameState());
        }

        // dispose of the objects
        g2.dispose();
    }
}