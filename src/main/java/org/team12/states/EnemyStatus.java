/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:37PM
 *
 * Project: csci205_final_project
 * Package: org.team12.states
 * Class: EnemyState
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.states;

/**
 * Represents the possible states of an enemy in the game, along with their valid state transitions.
 * Each state defines which other states it can transition to.
 */
public enum EnemyStatus {
    /**
     * Enemy is not attacking the player.
     * Can only transition to itself (no state change).
     */
    PEACEFUL {
        @Override
        public boolean canTransitionTo(EnemyStatus nextState) {
            return nextState == PEACEFUL;
        }
    },

    /**
     * Enemy is actively attacking the player.
     * Can transition to either DEAD (if defeated) or CURED (if Lily is cured).
     */
    HOSTILE {
        @Override
        public boolean canTransitionTo(EnemyStatus nextState) {
            return nextState == DEAD || nextState == CURED;
        }
    },

    /**
     * Enemy has been defeated.
     * This is a terminal state with no allowed transitions.
     */
    DEAD {
        @Override
        public boolean canTransitionTo(EnemyStatus nextState) {
            return false;
        }
    },

    /**
     * Lily has been cured (specific enemy state).
     * This is a terminal state with no allowed transitions.
     */
    CURED {
        @Override
        public boolean canTransitionTo(EnemyStatus nextState) {
            return false;
        }
    };

    /**
     * Checks if transitioning to the specified state is valid from the current state.
     * @param nextState The desired state to transition to
     * @return true if the transition is allowed, false otherwise
     */
    public abstract boolean canTransitionTo(EnemyStatus nextState);
}