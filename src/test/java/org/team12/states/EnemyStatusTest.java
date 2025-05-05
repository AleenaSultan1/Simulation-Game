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
 * Class: EnemyStatusTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.states;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnemyStatusTest {

    @Test
    void testPeacefulCanOnlyTransitionToPeaceful() {
        assertTrue(EnemyStatus.PEACEFUL.canTransitionTo(EnemyStatus.PEACEFUL));
        assertFalse(EnemyStatus.PEACEFUL.canTransitionTo(EnemyStatus.HOSTILE));
        assertFalse(EnemyStatus.PEACEFUL.canTransitionTo(EnemyStatus.DEAD));
        assertFalse(EnemyStatus.PEACEFUL.canTransitionTo(EnemyStatus.CURED));
    }

    @Test
    void testHostileCanTransitionToDeadOrCured() {
        assertTrue(EnemyStatus.HOSTILE.canTransitionTo(EnemyStatus.DEAD));
        assertTrue(EnemyStatus.HOSTILE.canTransitionTo(EnemyStatus.CURED));
        assertFalse(EnemyStatus.HOSTILE.canTransitionTo(EnemyStatus.PEACEFUL));
        assertFalse(EnemyStatus.HOSTILE.canTransitionTo(EnemyStatus.HOSTILE));
    }

    @Test
    void testDeadCannotTransitionToAnyState() {
        for (EnemyStatus next : EnemyStatus.values()) {
            assertFalse(EnemyStatus.DEAD.canTransitionTo(next));
        }
    }

    @Test
    void testCuredCannotTransitionToAnyState() {
        for (EnemyStatus next : EnemyStatus.values()) {
            assertFalse(EnemyStatus.CURED.canTransitionTo(next));
        }
    }
}
