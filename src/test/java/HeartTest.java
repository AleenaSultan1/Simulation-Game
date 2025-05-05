/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:54 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: HeartTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Items.Heart;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class HeartTest {

    private Heart heart;

    @BeforeEach
    public void setUp() {
        heart = new Heart();
    }

    @Test
    public void testHeartImagesAreLoaded() {
        BufferedImage full = heart.getFullHeart();
        BufferedImage half = heart.getHalfHeart();
        BufferedImage empty = heart.getEmptyHeart();

        assertNotNull(full, "Full heart image should be loaded");
        assertNotNull(half, "Half heart image should be loaded");
        assertNotNull(empty, "Empty heart image should be loaded");
    }

    @Test
    public void testGetSpriteReturnsNull() {
        assertNull(heart.getSprite(), "getSprite() should return null");
    }
}

