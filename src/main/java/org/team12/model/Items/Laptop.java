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

package org.team12.model.Items;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import org.team12.states.ItemState;
import java.util.ArrayList;
import java.util.List;

public class Laptop extends Item {

    private List<QuizQuestion> quizQuestions = new ArrayList<>();
    private List<QuizQuestion> currentQuizSession = new ArrayList<>();
    public int currentQuestionIndex = 0;
    private boolean isActive = false;
    private int selectedOption = 0;
    private boolean answerSubmitted = false;

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

    public void activate() {
        // Select 2 unique random questions
        currentQuizSession = new ArrayList<>(quizQuestions);
        Collections.shuffle(currentQuizSession);
        if (currentQuizSession.size() > 2) {
            currentQuizSession = currentQuizSession.subList(0, 2);
        }
//        if (currentQuestionIndex < quizQuestions.size()) {
        currentQuestionIndex = 0;
            isActive = true;
            selectedOption = 0;
            answerSubmitted = false;
//        }
    }

    public void deactivate() {
        itemState = ItemState.UNINTERACTABLE;
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

    public QuizQuestion getCurrentQuestion() {
        if (currentQuestionIndex < currentQuizSession.size()) {
            return currentQuizSession.get(currentQuestionIndex);
        }
        return null;
    }

    public void moveSelectionUp() {
        QuizQuestion current = getCurrentQuestion();
        if (current != null && selectedOption == 0){
            selectedOption = current.getOptions().length - 1;
        } else if (current != null && selectedOption > 0) {
            selectedOption--;
        }
    }

    public void moveSelectionDown() {
        QuizQuestion current = getCurrentQuestion();
        if (current != null && selectedOption < current.getOptions().length - 1) {
            selectedOption++;
        } else if (current != null && selectedOption == current.getOptions().length - 1) {
            selectedOption = 0;
        }
    }

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

    public int getSelectedOption() {
        return selectedOption;
    }

    public boolean isAnswerSubmitted() {
        return answerSubmitted;
    }

    public void resetLaptop() {
        currentQuestionIndex = 0;
        activate();
    }

    public void resetAnswerSubmitted() {
        answerSubmitted = false;
    }



    public static class QuizQuestion {
        private String question;
        private String answer;
        private String[] options;

        public QuizQuestion(String question, String answer, String[] options) {
            this.question = question;
            this.answer = answer;
            this.options = options;
        }

        public String getQuestion() { return question; }
        public String getAnswer() { return answer; }
        public String[] getOptions() { return options; }
    }
    @Override
    public BufferedImage getSprite() {
        if (isActive) return image;
        else return image2;
    }

    @Override
    public void pickUp() {
    }
}

