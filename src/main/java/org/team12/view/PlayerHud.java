/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 9:47 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: PlayerHud
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import org.team12.model.items.Laptop;
import org.team12.states.GameState;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class PlayerHud {
    private int tileSize;

    Font arial_40;
    public boolean messageOn = false;
    public String message = "";
    int messageTimer = 0;
    public int commandNumber = -1;

    private BufferedImage openLaptop, skull, lily;

    private Laptop laptop;


    public PlayerHud(int tileSize) {
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        this.tileSize = tileSize;

        try{
            openLaptop = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/openLaptop.png")));
            skull = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/skull.png")));
            lily = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Lily/lily_down_1.png")));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadImages() {

    }

    public void setMessage(String text){
        message = text;
        messageOn = true;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public void drawGameState(GameState gameState, Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if (gameState == GameState.PAUSE) {
//            drawTitleScreen(g2);
        }

    }

    public void drawMessage(Graphics2D g2, String text) {
        // display messages when items or events happen
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20f));
            g2.drawString(message, tileSize/2, tileSize*5);
            messageTimer++;

            // Displays the message for 2 seconds
            if (messageTimer > 120){
                messageTimer = 0;
                messageOn = false;
            }
        }

    }

    public void drawTitleScreen(Graphics2D g2, GameState gameState) {
        g2.setColor(new Color (10,10,50));
        g2.fillRect(0,0, GameUI.getScreenWidth(), GameUI.getScreenHeight());


        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50f));

        String text = "Default, Error";
        BufferedImage image = null;


        if (gameState == GameState.PLAYER_DEAD) {
            text = "You're dead, haha!";
            image = skull;
        } else if (gameState == GameState.LILY_CURED) {
            text = "Cured Lily, A+!";
            image = lily;
        } else if (gameState == GameState.PAUSE) {
            text = "CSCI 205: Code Crisis";
            image = openLaptop;
        } else if (gameState == GameState.END) {
            System.exit(0);
        }
        int x = centerText(text, g2);
        int y = tileSize*3;

        // Shadow Text
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        //Laptop Image
        x=GameUI.getScreenWidth()/2 - (tileSize*2)/2;
        y+=tileSize*2 - 50;
        g2.drawImage(image, x, y - 30, tileSize *2, tileSize*2, null);

        if (gameState == GameState.PAUSE) {
            // Story text
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
            String story = "Professor Lily has been possessed by evil code!";
            String story2 = "Complete coding quizzes to earn weapons, defeat the TA Goons,";
            String story3 = "and use MagicDust to debug Lily's corrupted code!";

            int storyX = centerText(story, g2);
            g2.setColor(new Color(192, 204, 213));
            g2.drawString(story, storyX, y + tileSize * 2 );
            g2.drawString(story2, centerText(story2, g2), y + tileSize * 2 + 30);
            g2.drawString(story3, centerText(story3, g2), y + tileSize * 2 + 60);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15f));
            g2.setColor(new Color(147, 180, 204));
            String controlsTitle = "- CONTROLS -";
            String instructions = "WASD/Arrows: Move - ESC: Menu";
            String instructions2 = "SPACE: Interact - E: Attack";
            g2.drawString(controlsTitle, centerText(controlsTitle, g2), y + tileSize * 2 + 90);
            g2.drawString(instructions, centerText(instructions, g2), y + tileSize * 2 + 110);
            g2.drawString(instructions2, centerText(instructions2, g2), y + tileSize * 2 + 130);

            //MENU
            g2.setColor(Color.WHITE);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));

            text = "Play Game";
            x = centerText(text, g2);
            y += tileSize * 6 - 10;
            g2.drawString(text, x, y);
            if(commandNumber == -1) {
                g2.drawString(">", x - tileSize, y);
            }
            text = "Quit";
            x = centerText(text, g2);
            y += tileSize;
            g2.drawString(text, x, y);
            if(commandNumber == 1) {
                g2.drawString(">", x - tileSize, y);
            }
        } else {
            text = "Quit";
            x = centerText(text, g2);
            y += 4*tileSize;
            commandNumber = 1;
            g2.drawString(text, x, y);
            g2.drawString(">", x - tileSize, y);
        }


    }

    public int centerText(String text, Graphics2D g2) {
        FontMetrics fm = g2.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        return (GameUI.getScreenWidth() - textWidth) / 2;
    }

    public void drawLaptopQuiz(Graphics2D g2) {
        if (laptop == null || !laptop.isActive()) return;

        // Draw laptop screen background
        g2.setColor(new Color(0,0,0, 60));
        g2.fillRect(0, 0, GameUI.getScreenWidth(), GameUI.getScreenHeight());

        // Draw laptop bezel
        g2.setColor(new Color(50, 59, 80));
        int screenWidth = GameUI.getScreenWidth() * 9/10;
        int screenHeight = GameUI.getScreenHeight() * 9/10;
        int screenX = (GameUI.getScreenWidth() - screenWidth) / 2;
        int screenY = (GameUI.getScreenHeight() - screenHeight) / 2 ;

        // Main screen area
        g2.fillRoundRect(screenX, screenY, screenWidth, screenHeight, 10, 10);

        // Screen "glow"
        g2.setColor(new Color(161, 193, 202));
        g2.fillRoundRect(screenX + 15, screenY + 15, screenWidth - 30, screenHeight - 30, 10, 10);

        // Draw laptop details
        // Shadow Text
//        int y = tileSize*3;
//
//        g2.setColor(Color.gray);
//        g2.drawString(header, headerX+3, y+3);
//
//        g2.setColor(Color.WHITE);
//        g2.drawString(header, headerX, y);

        int boxWidth = screenWidth * 3/4;
        int boxHeight = screenHeight *3/4;
        int boxX = (GameUI.getScreenWidth() - boxWidth) / 2;
        int boxY = (GameUI.getScreenHeight() - boxHeight) / 2;

        // === 2. Browser Window ===
        g2.setColor(new Color(240, 240, 240));
        g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 20, 20);

        // === 3. Top Bar (Browser header) ===
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(boxX, boxY + 40, boxWidth, boxHeight - 20, 20, 20);

        // Tab
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(boxX + 10, boxY + 10, 100, 40, 30, 30);

        // === Window Controls ===
        int controlX = boxX + boxWidth - 100; // Start 100px from right edge
        int controlY = boxY + 10;
        int controlSize = 20;
        int controlSpacing = 30;

        // Minimize button (-)
        g2.setColor(Color.lightGray);
        g2.drawLine(controlX + 5, controlY + controlSize/2,
                controlX + controlSize - 5, controlY + controlSize/2);

        // Maximize button (□)
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(controlX + controlSpacing + 4, controlY + 4,
                controlSize - 8, controlSize - 8, 2, 2);
        // Close button (X)
        g2.drawLine(controlX + controlSpacing*2 + 5, controlY + 5,
                controlX + controlSpacing*2 + controlSize - 5, controlY + controlSize - 5);
        g2.drawLine(controlX + controlSpacing*2 + controlSize - 5, controlY + 5,
                controlX + controlSpacing*2 + 5, controlY + controlSize - 5);

        // Header with text shadow
        g2.setColor(Color.BLACK);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24f));
        String header = "CSCI 205 Quiz: Answer or Die";
        int headerX = centerText(header, g2);

        g2.setColor(new Color(100, 100, 100, 100));
        g2.drawString(header, headerX + 2, boxY + 77);
        g2.setColor(new Color(30, 50, 80));
        g2.drawString(header, headerX, boxY + 75);

        // Draw question
        Laptop.QuizQuestion currentQuestion = laptop.getCurrentQuestion();
        if (currentQuestion != null) {
            // Question area with subtle background
            g2.setColor(new Color(250, 250, 250));
            g2.fillRoundRect(boxX + 20, boxY + 90, boxWidth - 40, 120, 10, 10);
            g2.setColor(new Color(220, 220, 220));
            g2.drawRoundRect(boxX + 20, boxY + 90, boxWidth - 40, 120, 10, 10);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
            g2.setColor(Color.BLACK);

            String questionText = "Question " + (laptop.currentQuestionIndex + 1) + ": " + currentQuestion.getQuestion();
            List<String> wrappedQuestion = wrapText(questionText, boxWidth - 40, g2);

            int textY = boxY + 120;
            for (String line : wrappedQuestion) {
                g2.drawString(line, boxX + 30, textY);
                textY += 30;
            }

            // Draw options
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18f));
            String[] options = currentQuestion.getOptions();

            int optionY = 310;
            for (int i = 0; i < options.length; i++) {
                // Option background
                boolean isSelected = i == laptop.getSelectedOption();
                g2.setColor(isSelected ? new Color(200, 230, 255) : new Color(240, 240, 240));
                g2.fillRoundRect(boxX + 25, optionY + 10, boxWidth - 50, 30, 15, 15);
                g2.setColor(isSelected ? new Color(100, 150, 200) : new Color(200, 200, 200));
                g2.drawRoundRect(boxX + 25, optionY + 10, boxWidth - 50, 30, 15, 15);

                // Option text
                g2.setColor(Color.BLACK);
                g2.drawString((char)(65 + i) + ") " + options[i], boxX + 40, optionY + 30);
                optionY += 40;
            }

//            // Draw feedback if answer was submitted
//            if (laptop.isAnswerSubmitted()) {
//                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20f));
//                if (laptop.submitAnswer()) {
//                    g2.setColor(Color.GREEN);
//                    g2.drawString("Correct! Press ENTER to continue", screenX + 30, screenY + screenHeight - 50);
//                } else {
//                    g2.setColor(Color.RED);
//                    g2.drawString("Incorrect! Try again", screenX + 30, screenY + screenHeight - 50);
//                }
//            }

            // Draw instructions
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16f));
            g2.setColor(new Color(150, 150, 200));
            g2.drawString("Use UP/DOWN to select, ENTER to confirm", screenX + 30, screenY + screenHeight - 95 - boxHeight);
        }

        g2.setClip(null);
    }

    private List<String> wrapText(String text, int maxWidth, Graphics2D g2) {
        List<String> lines = new ArrayList<>();
        FontMetrics fm = g2.getFontMetrics();

        String[] words = text.split(" ");
        String currentLine = words[0];

        for (int i = 1; i < words.length; i++) {
            if (fm.stringWidth(currentLine + " " + words[i]) < maxWidth) {
                currentLine += " " + words[i];
            } else {
                lines.add(currentLine);
                currentLine = words[i];
            }
        }
        lines.add(currentLine);
        return lines;
    }

    public void toggleTitleScreen() {
        commandNumber = commandNumber*(-1);
    }

    public int getCommandNumber() {
        return commandNumber;
    }

    // YOU CURED LILY, SHE GAVE YOU AN A+

}


