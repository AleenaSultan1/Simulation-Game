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

    /**
     * Check if the player is hitting any object. Returns the index of the object to process the reaction accordingly
     * @param entity Enemy or Player object
     * @param player returns true if the entity is Player
     * @return index for appropriate reaction
     */
    public int checkObject(Entity entity, boolean player){

        int index = 999;

        // Scan object array
        for(int i = 0; i<gameUI.obj.length; i++){
            if(gameUI.obj[i] != null){
                // Get Entity's hitbox
                entity.hitbox.x = entity.worldX+entity.hitbox.x;
                entity.hitbox.y = entity.worldY+entity.hitbox.y;

                // Get the object's hitbox
                gameUI.obj[i].hitbox.x = gameUI.obj[i].worldX + gameUI.obj[i].hitbox.x;
                gameUI.obj[i].hitbox.y = gameUI.obj[i].worldY + gameUI.obj[i].hitbox.y;

                switch(entity.direction){
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        // If the two rectangles (hitboxes) are intersecting,
                        if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){

                            // DEBUG PRINT
                            System.out.println("UP");

                            // if the item/object is solid, then the player can't pass through it
                            if(gameUI.obj[i].collision){
                                entity.collisionOn = true;
                            }
                            // if the player is touching it, set the index (get the right reaction)
                            if (player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameUI.obj[i].collision){
                                    entity.collisionOn = true;
                                }
                                // if the player is touching it, set the index (get the right reaction)
                                if (player){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "left":
                        entity.hitbox.x -=entity.speed;
                        if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameUI.obj[i].collision){
                                    entity.collisionOn = true;
                                }
                                // if the player is touching it, set the index (get the right reaction)
                                if (player){
                                    index = i;
                                }
                            }
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameUI.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameUI.obj[i].collision){
                                    entity.collisionOn = true;
                                }
                                // if the player is touching it, set the index (get the right reaction)
                                if (player){
                                    index = i;
                                }
                            }
                        }
                        break;
                }
                // return the rectangles (hitboxes) back to its original position
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                gameUI.obj[i].hitbox.x = gameUI.obj[i].hitboxDefaultX;
                gameUI.obj[i].hitbox.y = gameUI.obj[i].hitboxDefaultY;
            }
        }

        return index;
    }
}



