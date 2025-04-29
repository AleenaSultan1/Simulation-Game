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
import org.team12.model.Map;

public class CollisionController {

    GameController gameController;
    Map map;
    // Constructor for a collision controller
    public CollisionController(GameController gameController) {
        this.gameController = gameController;
        this.map = gameController.map;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX= entity.worldX + entity.hitbox.x;
        int entityRightWorldX = entity.worldX+entity.hitbox.x+entity.hitbox.width;
        int entityTopWorldY = entity.worldY + entity.hitbox.y;
        int entityBottomWorldY = entity.worldY + entity.hitbox.y+entity.hitbox.height;

        int entityLeftCol = entityLeftWorldX/gameController.tileSize;
        int entityRightCol = entityRightWorldX/gameController.tileSize;
        int entityTopRow = entityTopWorldY /gameController.tileSize;
        int entityBottomRow = entityBottomWorldY /gameController.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY-entity.speed)/gameController.tileSize;
                tileNum1 = gameController.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameController.map.mapTileNum[entityRightCol][entityTopRow];
                if(gameController.map.tile[tileNum1].isObstacle || gameController.map.tile[tileNum2].isObstacle){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY+entity.speed)/gameController.tileSize;
                tileNum1 = gameController.map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gameController.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gameController.map.tile[tileNum1].isObstacle || gameController.map.tile[tileNum2].isObstacle){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX-entity.speed)/gameController.tileSize;
                tileNum1 = gameController.map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gameController.map.mapTileNum[entityLeftCol][entityBottomRow];
                if(gameController.map.tile[tileNum1].isObstacle || gameController.map.tile[tileNum2].isObstacle){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX+entity.speed)/gameController.tileSize;
                tileNum1 = gameController.map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gameController.map.mapTileNum[entityRightCol][entityBottomRow];
                if(gameController.map.tile[tileNum1].isObstacle || gameController.map.tile[tileNum2].isObstacle){
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
        for(int i = 0; i<gameController.map.obj.length; i++){
            if(gameController.map.obj[i] != null){
                // Get Entity's hitbox
                entity.hitbox.x = entity.worldX+entity.hitbox.x;
                entity.hitbox.y = entity.worldY+entity.hitbox.y;

                // Get the object's hitbox
                gameController.map.obj[i].hitbox.x = gameController.map.obj[i].worldX + gameController.map.obj[i].hitbox.x;
                gameController.map.obj[i].hitbox.y = gameController.map.obj[i].worldY + gameController.map.obj[i].hitbox.y;

                switch(entity.direction){
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        // If the two rectangles (hitboxes) are intersecting,
                        if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){

                            // DEBUG PRINT
                            System.out.println("UP");

                            // if the item/object is solid, then the player can't pass through it
                            if(gameController.map.obj[i].collision){
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
                        if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameController.map.obj[i].collision){
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
                        if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameController.map.obj[i].collision){
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
                        if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                            // If the two rectangles (hitboxes) are intersecting,
                            if(entity.hitbox.intersects(gameController.map.obj[i].hitbox)){
                                // if the item/object is solid, then the player can't pass through it
                                if(gameController.map.obj[i].collision){
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
                gameController.map.obj[i].hitbox.x = gameController.map.obj[i].hitboxDefaultX;
                gameController.map.obj[i].hitbox.y = gameController.map.obj[i].hitboxDefaultY;
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        // Scan object array
        for(int i = 0; i<target.length; i++){
            if(target[i] != null){
                // Get Entity's hitbox
                entity.hitbox.x = entity.worldX+entity.hitbox.x;
                entity.hitbox.y = entity.worldY+entity.hitbox.y;

                // Get the object's hitbox
                target[i].hitbox.x = target[i].worldX + target[i].hitbox.x;
                target[i].hitbox.y = target[i].worldY + target[i].hitbox.y;

                switch(entity.direction){
                    case "up":
                        entity.hitbox.y -= entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                            entity.collisionOn = true;
                            index = i; // Set index to the current target
                        }
                        break;
                    case "down":
                        entity.hitbox.y += entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                            entity.collisionOn = true;
                            index = i; // Set index to the current target
                        }
                        break;
                    case "left":
                        entity.hitbox.x -=entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                            entity.collisionOn = true;
                            index = i; // Set index to the current target
                        }
                        break;
                    case "right":
                        entity.hitbox.x += entity.speed;
                        if(entity.hitbox.intersects(target[i].hitbox)){
                            entity.collisionOn = true;
                            index = i; // Set index to the current target
                        }
                        break;
                }
                // return the rectangles (hitboxes) back to its original position
                entity.hitbox.x = entity.hitboxDefaultX;
                entity.hitbox.y = entity.hitboxDefaultY;
                target[i].hitbox.x = target[i].hitboxDefaultX;
                target[i].hitbox.y = target[i].hitboxDefaultY;
            }
        }

        return index;
    }

    public void checkPlayer(Entity entity) {
        entity.hitbox.x = entity.worldX+entity.hitbox.x;
        entity.hitbox.y = entity.worldY+entity.hitbox.y;

        // Get the object's hitbox
        gameController.player.hitbox.x = gameController.player.worldX + gameController.player.hitbox.x;
        gameController.player.hitbox.y = gameController.player.worldY + gameController.player.hitbox.y;

        switch(entity.direction){
            case "up":
                entity.hitbox.y -= entity.speed;
                // If the two rectangles (hitboxes) are intersecting,
                if(entity.hitbox.intersects(gameController.player.hitbox)){

                    // DEBUG PRINT
                    System.out.println("UP");
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entity.hitbox.y += entity.speed;
                if(entity.hitbox.intersects(gameController.player.hitbox)){
                    // If the two rectangles (hitboxes) are intersecting,
                    if(entity.hitbox.intersects(gameController.player.hitbox)){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "left":
                entity.hitbox.x -=entity.speed;
                if(entity.hitbox.intersects(gameController.player.hitbox)){
                    // If the two rectangles (hitboxes) are intersecting,
                    if(entity.hitbox.intersects(gameController.player.hitbox)){
                        entity.collisionOn = true;
                    }
                }
                break;
            case "right":
                entity.hitbox.x += entity.speed;
                if(entity.hitbox.intersects(gameController.player.hitbox)){
                    // If the two rectangles (hitboxes) are intersecting,
                    if(entity.hitbox.intersects(gameController.player.hitbox)){
                        entity.collisionOn = true;
                    }
                }
                break;
        }
        // return the rectangles (hitboxes) back to its original position
        entity.hitbox.x = entity.hitboxDefaultX;
        entity.hitbox.y = entity.hitboxDefaultY;
        gameController.player.hitbox.x = gameController.player.hitboxDefaultX;
        gameController.player.hitbox.y = gameController.player.hitboxDefaultY;
    }

    public void checkHitBoxIntersection(){

    }
}


