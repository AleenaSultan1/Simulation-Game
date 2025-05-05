/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:56 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: ItemTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Items.Item;
import org.team12.states.ItemState;
import org.team12.view.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item testItem;
    private int tileSize;

    @BeforeEach
    public void setUp() {
        tileSize = GameUI.getTileSize();

        testItem = new Item() {
            @Override
            public BufferedImage getSprite() {
                return null; // Stub for abstract method
            }
        };
    }

    @Test
    public void testInitialItemState() {
        assertEquals(ItemState.INTERACTABLE, testItem.getItemState());
    }

    @Test
    public void testPickUpSetsState() {
        testItem.pickUp();
        assertEquals(ItemState.UNINTERACTABLE, testItem.getItemState());
    }

    @Test
    public void testSetAndGetWorldCoordinates() {
        testItem.setX(2);
        testItem.setY(3);

        assertEquals(2 * tileSize, testItem.getWorldX());
        assertEquals(3 * tileSize, testItem.getWorldY());
    }

    @Test
    public void testHitboxIsInitialized() {
        Rectangle hitbox = testItem.getHitbox();
        assertNotNull(hitbox);
        assertEquals(tileSize, hitbox.width);
        assertEquals(tileSize, hitbox.height);
    }
}

