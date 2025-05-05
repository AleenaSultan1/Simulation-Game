/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Radley Le
 * Date: 5/5/25
 * Time: 12:59 AM
 *
 * Project: csci205_final_project
 * Package: PACKAGE_NAME
 * Class: MagicDustTest
 *
 * Description:
 *
 * ****************************************
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.team12.model.Items.MagicDust;
import org.team12.model.entities.LilyFinalBoss;
import org.team12.states.EnemyStatus;

import static org.junit.jupiter.api.Assertions.*;

public class MagicDustTest {

    private MagicDust magicDust;
    private LilyFinalBoss lily;

    @BeforeEach
    public void setUp() {
        magicDust = new MagicDust();
        lily = new LilyFinalBoss(10, 100);
    }

    @Test
    public void testCureLilyChangesState() {
        assertNotEquals(EnemyStatus.CURED, lily.getState());
        magicDust.getCured(lily);
        assertEquals(EnemyStatus.CURED, lily.getState());
    }

    @Test
    public void testGetSpriteNotNull() {
        assertNotNull(magicDust.getSprite());
    }
}

