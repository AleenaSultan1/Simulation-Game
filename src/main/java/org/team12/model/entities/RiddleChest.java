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
                "I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?",
                "echo"));
        riddles.add(new Riddle(
                "The more you take, the more you leave behind. What am I?",
                "footsteps"));
        riddles.add(new Riddle(
                "What has keys but can't open locks, has space but no room, and you can enter but not go in?",
                "keyboard"));
    }

    public void presentRiddle() {
        if (currentRiddleIndex < riddles.size()) {
            Riddle currentRiddle = riddles.get(currentRiddleIndex);
            System.out.println("\nRiddle " + (currentRiddleIndex + 1) + ":");
            System.out.println(currentRiddle.getQuestion());
            System.out.print("Your answer: ");

            String userAnswer = scanner.nextLine().trim().toLowerCase();
            checkUserInput(userAnswer, currentRiddle.getAnswer());
        } else {
            System.out.println("You opened the chest!");
            chestSuccessfullyOpened();
        }
    }

    private void checkUserInput(String userAnswer, String correctAnswer) {
        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            System.out.println("Correct! Well done!");
            currentRiddleIndex++;
            presentRiddle(); // Present next riddle or open chest if done
        } else {
            System.out.println("Incorrect! Try again.");
            presentRiddle(); // Present same riddle again
        }
    }

    public void chestSuccessfullyOpened() {
        super.itemState = ItemState.UNINTERACTABLE;// Inherited from Item class to change state to INTERACTABLE
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
