/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/24/25
 * Time: 11:24 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: CollisionController
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.model.entities.Entity;
import org.team12.view.GameUI;

public class CollisionController {

    GameUI gameUI;
    // Constructor for a collision controller
    public CollisionController(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX= entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX+entity.hitbox.x+entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y+entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX/gameUI.tileSize;
        int entityRightCol = entityRightWorldX/gameUI.tileSize;
        int entityTopRow = entityTopWorldY /gameUI.tileSize;
        int entityBottomRow = entityBottomWorldY /gameUI.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY-entity.speed)/gameUI.tileSize;
                tileNum1 = gameUI.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameUI.map.mapTileNum[entityRightCol][entityTopRow];
                if(gameUI.map.tile[tileNum1].isObstacle == true || gameUI.map.tile[tileNum2].isObstacle == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY+entity.speed)/gameUI.tileSize;
                tileNum1 = gameUI.map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gameUI.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gameUI.map.tile[tileNum1].isObstacle == true || gameUI.map.tile[tileNum2].isObstacle == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-entity.speed)/gameUI.tileSize;
                tileNum1 = gameUI.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameUI.map.mapTileNum[entityLeftCol][entityBottomRow];
                if(gameUI.map.tile[tileNum1].isObstacle == true || gameUI.map.tile[tileNum2].isObstacle == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX+entity.speed)/gameUI.tileSize;
                tileNum1 = gameUI.map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gameUI.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gameUI.map.tile[tileNum1].isObstacle == true || gameUI.map.tile[tileNum2].isObstacle == true){
                    entity.collisionOn = true;
                }
                break;
        }





    }
}


