/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/30/25
 * Time: 1:01 PM
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

import org.team12.controller.GameController;
import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Heart extends Item{
    private BufferedImage image2;
    private BufferedImage image3;

    public Heart() {
        name = "Heart";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartFull.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartHalf.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartEmpty.png")));
            image=utilTool.scaleImage(image, GameUI.getTileSize(), GameUI.getTileSize());
            image2= utilTool.scaleImage(image2, GameUI.getTileSize(), GameUI.getTileSize());
            image3 = utilTool.scaleImage(image3, GameUI.getTileSize(), GameUI.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getHalfHeart() {
        return image2;
    }

    public BufferedImage getEmptyHeart() {
        return image3;
    }



}

