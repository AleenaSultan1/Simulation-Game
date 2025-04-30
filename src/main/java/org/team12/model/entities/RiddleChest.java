/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:35 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: RiddleChest
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.states.ItemState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RiddleChest extends Item {

    private List<Riddle> riddles = new ArrayList<>();
    private int currentRiddleIndex = 0;
    private Scanner scanner = new Scanner(System.in);

    public RiddleChest() {
        initializeRiddles();
    }

    private void initializeRiddles() {
        // Add your riddles and answers here
        riddles.add(new Riddle(
                "What command shows which files have unchanged but not committed?",
                "git status"));
        riddles.add(new Riddle(
                "What data structure uses FIFO order?",
                "queue"));
        riddles.add(new Riddle(
                "What keyword is used to handle exceptions?",
                "try-catch"));
        riddles.add(new Riddle(
                "What are the four pillars of OOP?",
                "encapsulation, abstraction, inheritance, polymorphism"));
        riddles.add(new Riddle(
                "What is the worst-case time complexity of HashMap operations?",
                "O(n)"));
        riddles.add(new Riddle(
                "What Java type is used for precise decimal calculations?",
                "BigDecimal"));
        riddles.add(new Riddle(
                "How do you list all branches in a repository?",
                "git branch"));
        riddles.add(new Riddle(
                "Which traversal visits root, then left, then right in a tree?",
                "Preorder"));
        riddles.add(new Riddle(
                "Which keyword is used to inherit a class in Java?",
                "extends"));
        riddles.add(new Riddle(
                "What keyword refers to the current object?",
                "this"));
        riddles.add(new Riddle(
                "Which Java keyword indicates a missing return value?",
                "void"));
    }

@Override
    public void pickUp() {
        if (currentRiddleIndex < riddles.size()) {
            Riddle currentRiddle = riddles.get(currentRiddleIndex);
            System.out.println("\nRiddle " + (currentRiddleIndex + 1) + ":");
            System.out.println(currentRiddle.getQuestion());
            System.out.print("Your answer: ");

            String userAnswer = scanner.nextLine().trim().toLowerCase();
        } else {
            System.out.println("You opened the chest!");
            super.itemState = ItemState.UNINTERACTABLE;
        }
    }

    public boolean checkUserInput(String userAnswer, String correctAnswer) {
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! Well done!");
            currentRiddleIndex++;
            pickUp(); // Present next riddle or open chest if done
        } else {
            System.out.println("Incorrect! Try again.");
            pickUp(); // Present same riddle again
        }

        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    // Inner class to hold riddle questions and answers
    private static class Riddle {
        private String question;
        private String answer;

        public Riddle(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() { return question; }
        public String getAnswer() { return answer; }
    }

}
