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
 * Class: Sword
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.states.ItemState;

public class Sword extends Item {

    private int strength;

    public Sword (ItemState status, double interactDistance) {
        super(status, interactDistance);
        this.strength = 5;

    }

    public void attack(Player player) {
        updateStatus();
        calculatePlayerDistance(player);
        if (enableAttack()) {
            inflictDamage();
        }
    }

    public void inflictDamage() {
        EvilGoon.reduceHP();
    }




}


