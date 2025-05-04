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

import org.team12.view.GameUI;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sword extends Item {
    public int strength;

    public Sword() {
        strength = 5;
    }

    @Override
    public BufferedImage getSprite() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/sword.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
