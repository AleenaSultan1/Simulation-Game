/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 4/24/25
 * Time: 1:40 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.view
 * Class: AssetSetter
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.view;

import org.team12.controller.GameController;
import org.team12.model.entities.Enemy;
import org.team12.model.entities.LilyFinalBoss;

public class AssetSetter {
    GameController gameController;

    public AssetSetter(GameController gameController) {
        this.gameController = gameController;
    }

    public void setEnemy() {
        gameController.map.enemies[0] = new Enemy(gameController);
        gameController.map.enemies[0].worldX = gameController.tileSize * 21;
        gameController.map.enemies[0].worldY = gameController.tileSize * 21;

        gameController.map.enemies[1] = new Enemy(gameController);
        gameController.map.enemies[1].worldX = gameController.tileSize * 21;
        gameController.map.enemies[1].worldY = gameController.tileSize * 21;
    }

//    public void setLilyFinalBoss() {
//        gameController.lilyFinalBoss = new LilyFinalBoss(gameController);
//        gameController.lilyFinalBoss.worldX = gameController.tileSize*7;
//        gameController.lilyFinalBoss.worldY = gameController.tileSize*5;
//    }
}
