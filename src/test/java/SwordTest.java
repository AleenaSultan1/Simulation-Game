/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:59 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: SwordTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Items.Sword;

import static org.junit.jupiter.api.Assertions.*;

public class SwordTest {

    private Sword sword;

    @BeforeEach
    public void setUp() {
        sword = new Sword();
    }

    @Test
    public void testInitialStrength() {
        assertEquals(5, sword.strength);
    }

    @Test
    public void testGetSpriteNotNull() {
        assertNotNull(sword.getSprite());
    }
}
