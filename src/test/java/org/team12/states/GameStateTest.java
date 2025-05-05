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
 * Class: GameStateTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.states;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void testAllEnumValuesExist() {
        GameState[] values = GameState.values();

        assertEquals(6, values.length);
        assertArrayEquals(new GameState[] {
                GameState.PLAYING,
                GameState.QUIZ,
                GameState.PLAYER_DEAD,
                GameState.LILY_CURED,
                GameState.PAUSE,
                GameState.END
        }, values);
    }

    @Test
    void testEnumValueOf() {
        assertEquals(GameState.PLAYING, GameState.valueOf("PLAYING"));
        assertEquals(GameState.QUIZ, GameState.valueOf("QUIZ"));
        assertEquals(GameState.PLAYER_DEAD, GameState.valueOf("PLAYER_DEAD"));
        assertEquals(GameState.LILY_CURED, GameState.valueOf("LILY_CURED"));
        assertEquals(GameState.PAUSE, GameState.valueOf("PAUSE"));
        assertEquals(GameState.END, GameState.valueOf("END"));
    }

    @Test
    void testEnumNames() {
        assertEquals("PLAYING", GameState.PLAYING.name());
        assertEquals("QUIZ", GameState.QUIZ.name());
        assertEquals("PLAYER_DEAD", GameState.PLAYER_DEAD.name());
        assertEquals("LILY_CURED", GameState.LILY_CURED.name());
        assertEquals("PAUSE", GameState.PAUSE.name());
        assertEquals("END", GameState.END.name());
    }
}
