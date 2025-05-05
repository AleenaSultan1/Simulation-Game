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
