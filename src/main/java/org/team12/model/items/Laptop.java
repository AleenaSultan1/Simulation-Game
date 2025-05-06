/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 5/1/25
 * Time: 12:11 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities.mapObstacles
 * Class: Laptop
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import org.team12.states.ItemState;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Laptop item in the game that triggers a short quiz interaction
 * when activated by the player. It contains multiple choice questions that
 * the player must answer to complete the interaction.
 */
public class Laptop extends Item {

    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    private List<QuizQuestion> currentQuizSession = new ArrayList<>();
    public int currentQuestionIndex = 0;
    private boolean isActive = false;
    private int selectedOption = 0;
    private boolean answerSubmitted = false;

    /**
     * Constructs a new Laptop object, loads the quiz questions,
     * and initializes the laptop as active.
     */
    public Laptop() {
        initializeQuizQuestions();
        try {
            image= ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/openLaptop.png")));
            image2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/closedLaptop.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        activate();
    }

    /**
     * Loads all predefined quiz questions into the quiz question list.
     */
    private void initializeQuizQuestions() {
        // Multiple choice questions with 4 options each
        quizQuestions.add(new QuizQuestion(
                "What command shows which files have unchanged but not committed?",
                "git status",
                new String[]{"git commit", "git push", "git status", "git diff"}));
        quizQuestions.add(new QuizQuestion(
                "What data structure uses FIFO order?",
                "queue",
                new String[]{"stack", "tree", "graph", "queue"}));
        quizQuestions.add(new QuizQuestion("Which traversal visits root, then left, then right in a tree?",
                "Preorder Traversal",
                new String[] {"Inorder Traversal", "Preorder Traversal", "Postorder Traversal", "Breadth First Search"}));
        quizQuestions.add(new QuizQuestion("What keyword refers to the current object?",
                "this",
                new String[]{"this", "that", "self", "Lily"}));
    }

    /**
     * Activates the laptop, starting a new quiz session with two random questions.
     */
    public void activate() {
        // Select 2 unique random questions
        currentQuizSession = new ArrayList<>(quizQuestions);
        Collections.shuffle(currentQuizSession);
        if (currentQuizSession.size() > 2) {
            currentQuizSession = currentQuizSession.subList(0, 2);
        }
        currentQuestionIndex = 0;
            isActive = true;
            selectedOption = 0;
            answerSubmitted = false;
    }

    /**
     * Deactivates the laptop and marks it as uninteractable.
     */
    public void deactivate() {
        itemState = ItemState.UNINTERACTABLE;
        isActive = false;
    }

    /**
     * Checks whether the laptop is currently active.
     *
     * @return true if the laptop is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Retrieves the current quiz question being asked.
     *
     * @return the current {@link QuizQuestion}, or null if quiz is done
     */
    public QuizQuestion getCurrentQuestion() {
        if (currentQuestionIndex < currentQuizSession.size()) {
            return currentQuizSession.get(currentQuestionIndex);
        }
        return null;
    }

    /**
     * Moves the selected answer option up (cyclically).
     */
    public void moveSelectionUp() {
        QuizQuestion current = getCurrentQuestion();
        if (current != null && selectedOption == 0){
            selectedOption = current.getOptions().length - 1;
        } else if (current != null && selectedOption > 0) {
            selectedOption--;
        }
    }

    /**
     * Moves the selected answer option down (cyclically).
     */
    public void moveSelectionDown() {
        QuizQuestion current = getCurrentQuestion();
        if (current != null && selectedOption < current.getOptions().length - 1) {
            selectedOption++;
        } else if (current != null && selectedOption == current.getOptions().length - 1) {
            selectedOption = 0;
        }
    }

    /**
     * Submits the selected answer for the current question.
     *
     * @return true if the selected answer is correct, false otherwise
     */
    public boolean submitAnswer() {
        answerSubmitted = true;
        QuizQuestion current = getCurrentQuestion();
        if (current != null) {
            String selectedAnswer = current.getOptions()[selectedOption];
            if (selectedAnswer.equalsIgnoreCase(current.getAnswer())) {
                currentQuestionIndex++;
                if (currentQuestionIndex >= currentQuizSession.size()) {
                    deactivate();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the index of the currently selected answer option.
     *
     * @return the selected option index
     */
    public int getSelectedOption() {
        return selectedOption;
    }

    /**
     * Checks whether the current question has been answered.
     *
     * @return true if the answer has been submitted, false otherwise
     */
    public boolean isAnswerSubmitted() {
        return answerSubmitted;
    }

    /**
     * Resets the laptop and restarts a new quiz session.
     */
    public void resetLaptop() {
        currentQuestionIndex = 0;
        activate();
    }

    /**
     * Resets the answer submission flag (e.g., after rendering feedback).
     */
    public void resetAnswerSubmitted() {
        answerSubmitted = false;
    }

    /**
     * Inner class representing a single quiz question with multiple choices.
     */
    public static class QuizQuestion {
        private String question;
        private String answer;
        private String[] options;

        /**
         * Constructs a new QuizQuestion object.
         *
         * @param question the question text
         * @param answer the correct answer
         * @param options the list of answer choices
         */
        public QuizQuestion(String question, String answer, String[] options) {
            this.question = question;
            this.answer = answer;
            this.options = options;
        }

        /**
         * Gets the question text.
         *
         * @return the question string
         */
        public String getQuestion() { return question; }

        /**
         * Gets the correct answer.
         *
         * @return the answer string
         */
        public String getAnswer() { return answer; }

        /**
         * Gets the list of answer choices.
         *
         * @return an array of option strings
         */
        public String[] getOptions() { return options; }
    }

    /**
     * Gets the appropriate laptop sprite based on whether the laptop is active.
     *
     * @return the open or closed laptop sprite
     */
    @Override
    public BufferedImage getSprite() {
        if (isActive) return image;
        else return image2;
    }

    /**
     * Overrides the pickUp method to do nothing.
     * Laptops are interacted with but not picked up.
     */
    @Override
    public void pickUp() {
    }
}

