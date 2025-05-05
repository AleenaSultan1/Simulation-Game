/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.items
 * Class: HeartTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class HeartTest {

    private Heart heart;

    @BeforeEach
    void setUp() {
        heart = new Heart();
    }

    @Test
    void testFullHeartImageNotNull() {
        assertNotNull(heart.getFullHeart(), "Full heart image should not be null");
    }

    @Test
    void testHalfHeartImageNotNull() {
        assertNotNull(heart.getHalfHeart(), "Half heart image should not be null");
    }

    @Test
    void testEmptyHeartImageNotNull() {
        assertNotNull(heart.getEmptyHeart(), "Empty heart image should not be null");
    }

    @Test
    void testGetSpriteReturnsNull() {
        assertNull(heart.getSprite(), "getSprite should return null by default");
    }
}
