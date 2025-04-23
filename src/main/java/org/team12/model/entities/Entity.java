/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/18/25
 * Time: 3:33 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model
 * Class: Entity
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

public abstract class Entity {
    protected int HP;
    protected boolean state; //true=alive
    protected int xCoordinate;
    protected int yCoordinate;

    public Entity(int x, int y, int hp) {
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.HP = hp;
        this.state = true; // alive by default
    }

    public abstract boolean attack();
    public abstract boolean move();
    public abstract boolean spawn();

    public boolean survivalStatus() {
        return this.state && this.HP > 0;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public int getHP() {
        return HP;
    }

    public void takeDamage(int damage) {
        this.HP -= damage;
        if (this.HP <= 0) {
            this.state = false;
        }
    }

}
