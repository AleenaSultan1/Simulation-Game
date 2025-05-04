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

package org.team12.model.Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Heart {
    private BufferedImage image, image2, image3;
    private String name;


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

    public BufferedImage getFullHeart() {
        return image;
    }

    public BufferedImage getHalfHeart() {
        return image2;
    }

    public BufferedImage getEmptyHeart() {
        return image3;
    }

    public BufferedImage getSprite() {
        return null;
    }
}

