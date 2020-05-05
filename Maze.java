/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariodibellaprojectfinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/**
 *
 * @author mario
 */
public class Maze {

    private static final int MAXMAZEROWS = 20;
    private static final int MAXMAZECOL = 50;
    private static Entity maze[][] = new Entity[MAXMAZEROWS][MAXMAZECOL];
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    static void create(String filename) throws FileNotFoundException {
        String maze2[] = new String[MAXMAZEROWS];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((br.readLine()) != null) {
                for (int i = 0; i < MAXMAZEROWS; i++) {
                    maze2[i] = br.readLine();//reads the whole row placing it in 1D ray
                    for (int j = 0; j < MAXMAZECOL; j++) {
                        char c = maze2[i].charAt(j);//reads each char in the row
                        Entity w = new Wall(c, i, j);//creates both *walls* and white walls
                        maze[i][j] = w;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    static void createStartingImg(String filename) throws FileNotFoundException {
        String maze2[] = new String[MAXMAZEROWS];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((br.readLine()) != null) {
                for (int i = 0; i < MAXMAZEROWS; i++) {
                    maze2[i] = br.readLine();//reads the whole row placing it in 1D ray
                    System.out.println(maze2[i]);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static Entity getMazeEntity(int row, int col) {
        return maze[row][col];
    }

    public static void setMazeEntity(Entity obj) {
        int a=obj.getRow();
        int b=obj.getCol();
        maze[a][b] = obj;
    }

    public static void promptEnterKey() {
        System.out.println("Are You Ready to Guide Squirrelly to Find the Nuts?");
        System.out.println("\u001B[34mPRESS \"ENTER\" to Begin the Hunt!\u001B[0m");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void display() {
        char dis[][] = new char[MAXMAZEROWS][MAXMAZECOL];
        for (int i = 0; i < MAXMAZEROWS; i++) {
            for (int j = 0; j < MAXMAZECOL; j++) {
                char b;
                b = maze[i][j].getSymbol();

                dis[i][j] = b;

            }
        }
        for (int i = 0; i < MAXMAZEROWS; i++) {
            System.out.println(dis[i]);
        }
    }

    public static boolean outOfBounds(int row, int col) {
        return row < 0 || row >= MAXMAZEROWS || col < 0 || col >= MAXMAZECOL;
    }

    public static int getMaxMazeRows() {
        return MAXMAZEROWS;
    }

    public static int getMaxMazeCol() {
        return MAXMAZECOL;
    }

    public static boolean available(int row, int col) {
        Entity avail = maze[row][col];
        return avail.getSymbol() != '*';
    }

    public static boolean nutsExist() {
        for (int i = 0; i < MAXMAZEROWS; i++) {
            for (int j = 0; j < MAXMAZECOL; j++) {
                if (maze[i][j].getSymbol() == 'P' || maze[i][j].getSymbol() == 'A') {
                    return true;
                }
            }

        }
        return false;
    }
    public static boolean doesTheSquirrelAlreadyExist() {
        for (int i = 0; i < MAXMAZEROWS; i++) {
            for (int j = 0; j < MAXMAZECOL; j++) {
                if (maze[i][j].getSymbol() == '@' ) {
                    return true;
                }
            }

        }
        return false;
    }

}
