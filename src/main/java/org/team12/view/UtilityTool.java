/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 2:38 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: UtilityTool
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public static BufferedImage scaleImage(BufferedImage originalImage, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}

