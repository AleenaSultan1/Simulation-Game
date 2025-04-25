/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 2:32 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: Heart
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Heart extends Item{
    GameUI gameUI;

    public Heart(GameUI gameUI) {
        this.gameUI = gameUI;
        name = "HEART";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartFull.png")));
            //uTool.scaleImage(image, gameUI.tileSize, gameUI.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

