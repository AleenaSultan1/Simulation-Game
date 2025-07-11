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
 * Class: MagicDust
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.team12.model.entities.LilyFinalBoss;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a special item called MagicDust used to cure the final boss character Lily.
 * When the player interacts with Lily using this item, it triggers the cure event.
 */
public class MagicDust extends Item {

    /**
     * Constructs a new MagicDust item.
     */
    public MagicDust() {
    }

    /**
     * Applies the curing effect to the final boss, Lily.
     *
     * @param lilyFinalBoss the LilyFinalBoss instance to be cured
     */
    public void getCured(LilyFinalBoss lilyFinalBoss) {
        lilyFinalBoss.getCured();
    }

    /**
     * Loads and returns the sprite image representing MagicDust.
     *
     * @return a {@link BufferedImage} of the magic dust sprite
     */
    @Override
    public BufferedImage getSprite() {
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/magicDust.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
