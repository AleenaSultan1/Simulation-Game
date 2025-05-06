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

package org.team12.model.items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a sword item in the game that can be picked up and used by the player.
 * The sword provides additional strength to the player for attacking enemies.
 */
public class Sword extends Item {
    public int strength;

    /**
     * Constructs a new Sword object with a default strength value.
     */
    public Sword() {
        strength = 5;
    }

    /**
     * Loads and returns the sprite image representing the sword.
     *
     * @return a {@link BufferedImage} of the sword sprite
     */
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
