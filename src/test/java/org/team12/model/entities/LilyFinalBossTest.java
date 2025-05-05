/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:49 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: LilyFinalBossTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.controller.CollisionController;
import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import static org.junit.jupiter.api.Assertions.*;

public class LilyFinalBossTest {

    private LilyFinalBoss boss;

    @BeforeEach
    public void setUp() {
        boss = new LilyFinalBoss(20, 100);
    }

    @Test
    public void testInitialValues() {
        assertEquals(20, boss.getHP());
        assertEquals(EnemyStatus.PEACEFUL, boss.getState());
        assertEquals(4, boss.speed);
    }

    @Test
    public void testSetEnemyState() {
        boss.setEnemyState(EnemyStatus.HOSTILE);
        assertEquals(EnemyStatus.HOSTILE, boss.getState());
    }

    @Test
    public void testSetCoord() {
        boss.setCoord(3, 5);
        assertEquals(3 * GameUI.getTileSize(), boss.worldX);
        assertEquals(5 * GameUI.getTileSize(), boss.worldY);
    }

    @Test
    public void testGetCured() {
        boss.getCured();
        assertEquals(EnemyStatus.PEACEFUL, boss.getState());
    }

    @Test
    public void testGetCurrentSpriteNotNullAfterSetDirection() {
        boss.direction = "down";
        assertNotNull(boss.getCurrentSprite());
    }
}
