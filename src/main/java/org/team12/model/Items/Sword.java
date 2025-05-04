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

package org.team12.model.Items;

import org.team12.view.PlayerHud;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sword extends Item {
    public int strength;
    private PlayerHud playerHud;

    public Sword(PlayerHud playerHud) {
        strength = 5;
        this.playerHud = playerHud;
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

    @Override
    public void pickUp() {
        super.pickUp();
        playerHud.setMessage("Sword Picked Up");
    }
}
