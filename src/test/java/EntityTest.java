/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:29 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: EntityTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.model.entities.Entity;
import org.team12.states.EnemyStatus;

import java.awt.image.BufferedImage;

public class EntityTest {

    // Anonymous concrete subclass of Entity
    private Entity testEntity;

    @BeforeEach
    public void setUp() {
        testEntity = new Entity(10) {
            @Override
            public BufferedImage getCurrentSprite() {
                return null;
            }
        };
    }

    @Test
    public void testInitialHP() {
        assertEquals(10, testEntity.getHP());
    }

    @Test
    public void testSetHP() {
        testEntity.setHP(5);
        assertEquals(5, testEntity.getHP());
    }

    @Test
    public void testTakeDamageReducesHP() {
        testEntity.takeDamage(3);
        assertEquals(7, testEntity.getHP());
    }

    @Test
    public void testTakeDamageKillsEntity() {
        testEntity.takeDamage(15); // More than initial HP
        assertTrue(testEntity.getHP() <= 0);
    }

    @Test
    public void testMaxHP() {
        assertEquals(10, testEntity.getMaxHP());
    }

    @Test
    public void testHitboxNotNull() {
        assertNotNull(testEntity.getHitbox());
    }
}

