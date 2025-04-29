/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/28/25
 * Time: 10:23 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: Chest
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

public class Chest extends Item {
    GameController gameController;

    public Chest(GameController gameController) {
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/objects/chest1.png")));
            utilTool.scaleImage(image, gameController.tileSize, gameController.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
