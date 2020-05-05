/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariodibellaprojectfinal;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *
 * @author mario
 */
public class HungrySquirrelGame {


    public static void main(String[] args) throws FileNotFoundException {

        Maze.create("Maze.txt");
        Maze.createStartingImg("squirrelly.txt");
        Maze.promptEnterKey();
        Maze.display();

        Squirrel squirrelly = Squirrel.getInstanceOfSquirrel();
        System.out.println("Squirrelly the squirrel is getting ready for winter. ");
        System.out.println("Squirrelly needs to collect the nuts or he will surely die!");
        squirrelly.create();
        Maze.display();

        Nut almondOrPeanut = new Nut('a', 0, 0, 0, "heyya");
        almondOrPeanut.create();
        Maze.display();
        squirrelly.fillNutArray();

        System.out.println("w moves up,s moves down,a moves left,d moves right. Follow each input with ENTER key on the keyboard.");
        try (Scanner keyboard = new Scanner(System.in)) {
            while (keyboard.hasNextLine()) {
                String input = keyboard.next();
                if (input.equalsIgnoreCase("quit")) {
                    break;
                }
                char c = input.charAt(0);
                squirrelly.move(c);
                Maze.display();
                squirrelly.wasNutFound();

                if (!Maze.nutsExist()) {
                    System.out.println("Squirrelly is now ready for winter. Total points: " + squirrelly.getPointsCollected());
                    System.out.println("Thank you for playing!");
                    break;
                }
            }
        }
    }

}
