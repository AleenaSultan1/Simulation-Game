/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:34 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: LilyFinalBoss
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.states.EnemyStatus;
import org.team12.view.GameUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class LilyFinalBoss extends Enemy {
    GameUI gameUI;

    public LilyFinalBoss(int hp, int hostilityArea) {
        super(hp, hostilityArea);
    }

    public void getCured() {
            super.setEnemyState(EnemyStatus.CURED);
        }

    }
