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
 * Class: LaptopTest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LaptopTest {

    private Laptop laptop;

    @BeforeEach
    void setUp() {
        laptop = new Laptop();
    }

    @Test
    void testLaptopIsActiveAfterConstruction() {
        assertTrue(laptop.isActive());
    }

    @Test
    void testDeactivateSetsInactiveAndUninteractable() {
        laptop.deactivate();
        assertFalse(laptop.isActive());
        assertEquals(org.team12.states.ItemState.UNINTERACTABLE, laptop.getItemState());
    }

    @Test
    void testMoveSelectionWrapsAroundUp() {
        laptop.resetLaptop();
        laptop.moveSelectionUp();
        int maxOption = laptop.getCurrentQuestion().getOptions().length - 1;
        assertEquals(maxOption, laptop.getSelectedOption());
    }

    @Test
    void testMoveSelectionWrapsAroundDown() {
        laptop.resetLaptop();
        int optionCount = laptop.getCurrentQuestion().getOptions().length;
        for (int i = 0; i < optionCount; i++) {
            laptop.moveSelectionDown();
        }
        assertEquals(0, laptop.getSelectedOption());
    }

    @Test
    void testSubmitCorrectAnswerIncrementsQuestionIndex() {
        Laptop.QuizQuestion q = laptop.getCurrentQuestion();
        String[] options = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            if (options[i].equalsIgnoreCase(q.getAnswer())) {
                while (laptop.getSelectedOption() != i) {
                    laptop.moveSelectionDown();
                }
                break;
            }
        }

        int currentIndex = laptop.currentQuestionIndex;
        assertTrue(laptop.submitAnswer());
        assertEquals(currentIndex + 1, laptop.currentQuestionIndex);
    }

    @Test
    void testSubmitIncorrectAnswerReturnsFalse() {
        Laptop.QuizQuestion q = laptop.getCurrentQuestion();
        String[] options = q.getOptions();
        for (int i = 0; i < options.length; i++) {
            if (!options[i].equalsIgnoreCase(q.getAnswer())) {
                while (laptop.getSelectedOption() != i) {
                    laptop.moveSelectionDown();
                }
                break;
            }
        }

        int currentIndex = laptop.currentQuestionIndex;
        assertFalse(laptop.submitAnswer());
        assertEquals(currentIndex, laptop.currentQuestionIndex);
    }

    @Test
    void testResetLaptopResetsIndexAndActivates() {
        laptop.deactivate();
        laptop.resetLaptop();
        assertEquals(0, laptop.currentQuestionIndex);
        assertTrue(laptop.isActive());
    }

    @Test
    void testResetAnswerSubmitted() {
        laptop.submitAnswer();
        assertTrue(laptop.isAnswerSubmitted());
        laptop.resetAnswerSubmitted();
        assertFalse(laptop.isAnswerSubmitted());
    }
}
