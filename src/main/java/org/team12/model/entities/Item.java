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
 * Class: Item
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import javafx.geometry.Point2D;
import org.team12.states.ItemState;

public abstract class Item {
    private ItemState status;
    private double interactDistance;
    private double playerDistance;

    protected Item(ItemState status, double interactDistance) {
        this.status = status;
        this.interactDistance = interactDistance;
        this.playerDistance = Double.MAX_VALUE; // Initialize to large value
    }

    // ... other methods ...

    public double calculatePlayerDistance(Player player) {
        Point2D location = player.getLocation();
        playerDistance = Math.sqrt(location.getX()*location.getX() +
                location.getY()*location.getY());
        return playerDistance;
    }

    public boolean enableAttack() {
        return playerDistance <= interactDistance;
    }

    public void updateStatus() {
        status = ItemState.INTERACTABLE;

    }

    public ItemState getStatus() {
        return status;
    }
}