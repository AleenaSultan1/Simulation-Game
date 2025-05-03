/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2025
 *
 * Name: Sebastian Stewart
 * Date: 4/25/25
 * Time: 10:45 PM
 *
 * Project: csci205_final_project
 * Package: org.team12.model.entities
 * Class: EvilPortal
 *
 * Description:
 *
 * ****************************************
 */

package org.team12.model.entities;

import org.team12.controller.GameController;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Table extends Item{

    public Table(GameController gameController) {
        name = "Table";
    }
}