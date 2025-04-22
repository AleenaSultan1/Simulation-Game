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
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    InputController inputController = new InputController();
    Thread gameThread;

    int FPS = 60;

    // get player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // moves 4 pixels

    public GameUI(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(inputController);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

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
                update();
                repaint();
                delta--;
            }
        }
    }

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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
