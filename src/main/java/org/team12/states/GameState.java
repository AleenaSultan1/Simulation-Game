/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:38 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.states
 * Class: GameState
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.states;

/**
 * Represents the possible states of the game.
 */
public enum GameState {
    /** The game is currently being played normally. */
    PLAYING,

    /** The game is in quiz mode (possibly for a puzzle or dialogue). */
    QUIZ,

    /** The player has died and the game is over. */
    PLAYER_DEAD,

    /** Lily has been cured (special win condition). */
    LILY_CURED,

    /** The game is currently paused. */
    PAUSE,

    /** The game has ended (terminal state). */
    END
}
