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

package org.team12.model.Items;

import org.team12.model.entities.LilyFinalBoss;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MagicDust extends Item {

    public MagicDust() {
    }

    public void getCured(LilyFinalBoss lilyFinalBoss) {
        lilyFinalBoss.getCured();
    }

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
