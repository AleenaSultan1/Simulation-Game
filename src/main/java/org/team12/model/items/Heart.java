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

package org.team12.model.items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


/**
 * Represents the heart icons used to display the player's health in the game.
 * This class loads and provides access to full, half, and empty heart sprites.
 */
public class Heart {
    private BufferedImage image, image2, image3;
    private String name;

    /**
     * Constructs a Heart object and loads the heart sprites (full, half, empty).
     */
    public Heart() {
        name = "Heart";

        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartFull.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartHalf.png")));
            image3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/heartEmpty.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the sprite for a full heart.
     *
     * @return BufferedImage of a full heart
     */
    public BufferedImage getFullHeart() {
        return image;
    }

    /**
     * Gets the sprite for aa half heart.
     *
     * @return BufferedImage of a half heart
     */
    public BufferedImage getHalfHeart() {
        return image2;
    }

    /**
     * Gets the sprite for an empty heart.
     *
     * @return BufferedImage of an empty heart
     */
    public BufferedImage getEmptyHeart() {
        return image3;
    }

    /**
     * Returns a sprite representation of this item.
     * Not used currently — always returns null.
     *
     * @return null
     */
    public BufferedImage getSprite() {
        return null;
    }
}

