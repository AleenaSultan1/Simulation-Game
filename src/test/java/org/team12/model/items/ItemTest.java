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
 * Class: ItemTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.states.ItemState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    private Item item;

    // Dummy subclass for testing abstract Item
    static class TestItem extends Item {
        @Override
        public BufferedImage getSprite() {
            return null;
        }
    }

    @BeforeEach
    void setUp() {
        item = new TestItem();
    }

    @Test
    void testInitialItemStateIsInteractable() {
        assertEquals(ItemState.INTERACTABLE, item.getItemState());
    }

    @Test
    void testPickUpChangesStateToUninteractable() {
        item.pickUp();
        assertEquals(ItemState.UNINTERACTABLE, item.getItemState());
    }

    @Test
    void testSetAndGetWorldX() {
        item.setX(2);
        assertEquals(2 * 48, item.getWorldX()); // Assuming tile size is 48
    }

    @Test
    void testSetAndGetWorldY() {
        item.setY(3);
        assertEquals(3 * 48, item.getWorldY()); // Assuming tile size is 48
    }

    @Test
    void testGetHitboxNotNull() {
        assertNotNull(item.getHitbox());
    }
}
