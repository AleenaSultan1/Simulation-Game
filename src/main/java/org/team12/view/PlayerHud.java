/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 9:47 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: PlayerHud
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import java.awt.*;

public class PlayerHud {
    GameUI gameUI;
    Font arial_40;
    //BufferedImage

    public PlayerHud(GameUI gameUI) {
        this.gameUI = gameUI;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Hello", 25, 50);
    }

}

