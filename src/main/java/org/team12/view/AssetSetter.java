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

import org.team12.model.entities.Enemy;
import org.team12.model.entities.LilyFinalBoss;

public class AssetSetter {
    GameUI gameUI;

    public AssetSetter(GameUI gameUI) {
        this.gameUI = gameUI;
    }

    public void setEnemy() {
        gameUI.enemy[0] = new Enemy(gameUI);
        gameUI.enemy[0].worldX = gameUI.tileSize * 9;
        gameUI.enemy[0].worldY = gameUI.tileSize * 7;

        gameUI.enemy[1] = new Enemy(gameUI);
        gameUI.enemy[1].worldX = gameUI.tileSize * 7;
        gameUI.enemy[1].worldY = gameUI.tileSize * 5;
    }

    public void setLilyFinalBoss() {
        gameUI.lilyFinalBoss = new LilyFinalBoss(gameUI);
        gameUI.lilyFinalBoss.worldX = gameUI.tileSize*7;
        gameUI.lilyFinalBoss.worldY = gameUI.tileSize*5;
    }
}
