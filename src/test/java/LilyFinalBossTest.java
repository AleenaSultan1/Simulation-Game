/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:09 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: LilyFinalBossTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.team12.model.entities.LilyFinalBoss;
import org.team12.states.EnemyStatus;

public class LilyFinalBossTest {
    LilyFinalBoss lily;

    @BeforeEach
    public void setUp() {
        lily = new LilyFinalBoss(10, 10);
    }

    @Test
    public void testStartsPeaceful() {
        assertEquals(EnemyStatus.PEACEFUL, lily.getState());
    }

    @Test
    public void testCureChangesState() {
        lily.getCured();
        assertEquals(EnemyStatus.CURED, lily.getState());
    }
}

