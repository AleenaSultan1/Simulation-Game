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
 * Represents a collectible weapon item that increases the player's attack strength.
 * When picked up, it enables the player to deal damage to enemies.
 * ****************************************
 */

package org.team12.model.items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sword extends Item {

    /** The strength or damage this sword deals to enemies */
    public int strength;

    /** Constructs a sword with default strength */
    public Sword() {
        strength = 5;
    }

    /**
     * Loads and returns the sword's sprite image.
     * @return BufferedImage representing the sword icon
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
