/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:58 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: LaptopTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Items.Laptop;

import static org.junit.jupiter.api.Assertions.*;

public class LaptopTest {
    private Laptop laptop;

    @BeforeEach
    public void setUp() {
        laptop = new Laptop();
    }

    @Test
    public void testLaptopIsActiveOnStart() {
        assertTrue(laptop.isActive());
    }

    @Test
    public void testSubmitCorrectAnswerAdvancesQuestion() {
        String correctAnswer = laptop.getCurrentQuestion().getAnswer();
        int correctIndex = -1;

        for (int i = 0; i < laptop.getCurrentQuestion().getOptions().length; i++) {
            if (laptop.getCurrentQuestion().getOptions()[i].equalsIgnoreCase(correctAnswer)) {
                correctIndex = i;
                break;
            }
        }

        laptop.resetAnswerSubmitted(); // Just in case
        for (int i = 0; i < correctIndex; i++) laptop.moveSelectionDown();
        boolean result = laptop.submitAnswer();

        assertTrue(result);
        assertEquals(1, laptop.currentQuestionIndex);
    }

    @Test
    public void testResetLaptop() {
        laptop.resetLaptop();
        assertTrue(laptop.isActive());
        assertEquals(0, laptop.currentQuestionIndex);
    }
}

