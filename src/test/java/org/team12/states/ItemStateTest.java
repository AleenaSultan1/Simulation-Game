package org.team12.states;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemStateTest {

    @Test
    void testAllEnumValuesExist() {
        ItemState[] values = ItemState.values();

        assertEquals(2, values.length);
        assertArrayEquals(new ItemState[] {
                ItemState.INTERACTABLE,
                ItemState.UNINTERACTABLE
        }, values);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(ItemState.INTERACTABLE, ItemState.valueOf("INTERACTABLE"));
        assertEquals(ItemState.UNINTERACTABLE, ItemState.valueOf("UNINTERACTABLE"));
    }

    @Test
    void testEnumNames() {
        assertEquals("INTERACTABLE", ItemState.INTERACTABLE.name());
        assertEquals("UNINTERACTABLE", ItemState.UNINTERACTABLE.name());
    }
}
