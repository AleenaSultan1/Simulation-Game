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
 * Class: MagicDust
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.states.ItemState;

public class MagicDust extends Item {
    private LilyFinalBoss boss;
    public MagicDust() {
        this.boss = new LilyFinalBoss();
    }

    public boolean getCured() {
        if (super.isCloseEnough()) {
            boss.getCured();
            return true;
        }
        return false;
    }
    
}
