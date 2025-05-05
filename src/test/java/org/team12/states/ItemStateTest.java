/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.states
 * Class: ItemStateTest
 *
 * Description:
 *
 * ****************************************
 */

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
