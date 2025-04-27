/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:35 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: Sword
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

public class Sword extends Item {
    GameController gameController;

    public Sword(GameController gameController) {
        name = "Sword";
        try{
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sword.png")));
            utilTool.scaleImage(image, gameController.tileSize, gameController.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
