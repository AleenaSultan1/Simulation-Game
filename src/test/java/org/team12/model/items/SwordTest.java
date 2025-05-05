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
 * Class: SwordTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwordTest {

    private Sword sword;

    @BeforeEach
    void setUp() {
        sword = new Sword();
    }

    @Test
    void testSwordStrengthIsInitializedCorrectly() {
        assertEquals(5, sword.strength);
    }

    @Test
    void testGetSpriteReturnsNonNullImage() {
        assertNotNull(sword.getSprite());
    }
}
