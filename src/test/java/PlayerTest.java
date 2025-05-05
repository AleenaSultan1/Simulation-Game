/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/4/25
 * Time: 9:47 PM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: PlayerTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.controller.CollisionController;
import org.team12.controller.InputController;
import org.team12.model.Map;
import org.team12.model.entities.Player;
import org.team12.model.Items.Sword;

public class PlayerTest {
    Player player;
    Map map;

    @BeforeEach
    public void setUp() {
        map = new Map("/map/dungeonMap.txt", false);
        InputController inputController = new InputController();
        CollisionController collisionController = new CollisionController(map);
        player = new Player(inputController, collisionController, 6);
        map.setPlayer(player);
        map.setCollisionController(collisionController);
    }

    @Test
    public void testInitialHP() {
        assertEquals(6, player.getHP());
    }


    @Test
    public void testDamageReducesHP() {
        int initialHP = player.getHP();
        player.setHP(initialHP - 2);
        assertEquals(initialHP - 2, player.getHP());
    }

    @Test
    public void testAttackRangeExists() {
        assertNotNull(player.getAttackRange());
    }
}

