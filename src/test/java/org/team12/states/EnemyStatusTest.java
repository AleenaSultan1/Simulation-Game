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
