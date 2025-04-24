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

public class LilyFinalBoss extends Entity {

    private int hostilityArea;

    public LilyFinalBoss(int x, int y, int hp, int hostilityArea, GameUI gameUI) {
        super(x, y, hp);
        this.hostilityArea = hostilityArea;
    }

    public void updateState(EnemyStatus newState) {
        if (super.enemyStatus.canTransitionTo(newState)) {
            super.enemyStatus = newState;
        }
    }
}


