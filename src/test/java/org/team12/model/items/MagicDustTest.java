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
 * Class: MagicDustTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.entities.LilyFinalBoss;
import org.team12.states.EnemyStatus;

import static org.junit.jupiter.api.Assertions.*;

class MagicDustTest {

    private MagicDust magicDust;

    @BeforeEach
    void setUp() {
        magicDust = new MagicDust();
    }

    @Test
    void testGetCuredChangesLilyState() {
        LilyFinalBoss lily = new LilyFinalBoss(10, 3);
        assertNotEquals(EnemyStatus.CURED, lily.getState());
        magicDust.getCured(lily);
        assertEquals(EnemyStatus.PEACEFUL, lily.getState());
    }

    @Test
    void testGetSpriteReturnsNonNullImage() {
        assertNotNull(magicDust.getSprite());
    }
}
