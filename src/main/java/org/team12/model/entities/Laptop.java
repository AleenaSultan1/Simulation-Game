/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 5/1/25
 * Time: 12:11 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities.mapObstacles
 * Class: Laptop
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Laptop extends Item {
    public Laptop(GameController gameController) {
        name = "Laptop";
        try{
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/openLaptop.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/closedLaptop.png")));
            image = utilTool.scaleImage(image, gameController.tileSize, gameController.tileSize);
            image2 = utilTool.scaleImage(image2, gameController.tileSize, gameController.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}

