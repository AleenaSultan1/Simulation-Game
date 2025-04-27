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
    protected ItemState status;
    protected double interactDistance;
    protected double playerDistance;

    protected Item() {
        this.status = ItemState.INTERACTABLE;
    }

    public void pickUp() {
        status = ItemState.UNINTERACTABLE;
        }

    public ItemState getStatus() {
        return status;
    }

}