/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/29/25
 * Time: 2:26 AM
 *
 * Project: csci205_final_project
 * Package: org.team12.controller
 * Class: AssetController
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.controller;

import org.team12.model.entities.EvilGoon;
import org.team12.model.entities.Sword;
import org.team12.model.entities.Table;

/**
 * Used to spawn in objects, items, and enemies
 */
public class AssetController {
    GameController gameController;

    public AssetController(GameController gameController) {
        this.gameController = gameController;
    }

    // Spawns objects in the map
    public void setObjects(){
        gameController.map.obj[0] = new Sword(gameController);
        gameController.map.obj[0].worldX = 27 * gameController.tileSize;
        gameController.map.obj[0].worldY = 19 * gameController.tileSize;

        gameController.map.obj[1] = new Table(gameController);
        gameController.map.obj[1].worldX = 3* gameController.tileSize;
        gameController.map.obj[1].worldY = 3 * gameController.tileSize;
    }

    public void setMonsters(){
        gameController.map.monster[0] = new EvilGoon(gameController);
        gameController.map.monster[0].worldX = 20 * gameController.tileSize;
        gameController.map.monster[0].worldY = 20 * gameController.tileSize;
        gameController.map.monster[1] = new EvilGoon(gameController);
        gameController.map.monster[1].worldX = 21 * gameController.tileSize;
        gameController.map.monster[1].worldY = 21 * gameController.tileSize;
    }
}

