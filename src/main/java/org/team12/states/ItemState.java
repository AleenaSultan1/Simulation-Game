/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:37 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.states
 * Class: ItemState
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.states;

/**
 * Represents the interaction states of game items.
 */
public enum ItemState {
    /** The item can be interacted with by the player. */
    INTERACTABLE,

    /** The item cannot be interacted with by the player. */
    UNINTERACTABLE;
}