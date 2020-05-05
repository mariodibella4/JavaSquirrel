/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariodibellaprojectfinal;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author mario
 */
public class Squirrel extends Entity implements Moveable {

    private static int pointsCollected;
    private static int totalNutsEaten;
    private static char symbol;
    private static int row;
    private static int column;
    private static Squirrel squirrel;
    private ArrayList<Nut> nuts = new ArrayList();

    private Squirrel(char symbol, int row, int column, int pointsCollected, int totalNutsEaten) {
        super(symbol, row, column);
        this.pointsCollected = pointsCollected;
        this.totalNutsEaten = totalNutsEaten;
        this.symbol = '@';
        this.row = row;
        this.column = column;
    }
    public static Squirrel getInstanceOfSquirrel() {

        if (!Maze.doesTheSquirrelAlreadyExist()) {
            squirrel = new Squirrel(symbol, row, column, pointsCollected, totalNutsEaten);
            return squirrel;
        }
        return squirrel;
    }
    public Nut fillNutArray() {
        int k = 0;
        for (int i = 0; i < Maze.getMaxMazeRows(); i++) {
            for (int j = 0; j < Maze.getMaxMazeCol(); j++) {
                char a = Maze.getMazeEntity(i, j).getSymbol();
                char b = Maze.getMazeEntity(i, j).getSymbol();
                if (a == 'P' || b == 'A') {
                    nuts.add((Nut) Maze.getMazeEntity(i, j));
                    k++;
                }
            }
        }
        return null;
    }
    public void wasNutFound() {
        for (int i = 0; i < nuts.size(); i++) {
            int col = nuts.get(i).getCol();
            int roW = nuts.get(i).getRow();
            int nutr = nuts.get(i).getNutraPts();
            String name = nuts.get(i).getName();

            if (column == col && row == roW) {
                nuts.remove(i);
                ++totalNutsEaten;
                pointsCollected = pointsCollected + nutr;
                System.out.println("Squirrelly ate a yummy " + name + " and gained " + nutr + " points." + Maze.ANSI_GREEN + "(Total: " + pointsCollected + " points)" + Maze.ANSI_RESET);
            }
        }
    }
    @Override
    public void create() {
        System.out.println("Enter where you would Squirrelly to be placed:" + Maze.ANSI_BLUE + "row,column" + Maze.ANSI_RESET);
        Scanner keyboard = new Scanner(System.in);

        while (keyboard.hasNextLine()) {
            String input = keyboard.nextLine();
            if (input.equalsIgnoreCase("quit")) {
                break;
            }

            if (wasInputValidFormat(input)) {
                String[] tokens = input.split(",");
                String rowString = tokens[0].trim();
                String colString = tokens[1].trim();

                if (canTheSquirrelBePlaced(rowString, colString)) {
                    placeSquirrelInMaze(rowString, colString);
                    return;
                }
            }
        }
        keyboard.close();
    }

    public boolean wasInputValidFormat(String input) {
        String[] tokens = input.split(",");
        if (tokens.length == 2) {
            return true;

        } else {
            System.out.println(Maze.ANSI_RED + "Invalid format. Try again... Example: 8,5" + Maze.ANSI_RESET);
        }
        return false;
    }

    public boolean canTheSquirrelBePlaced(String rowString, String colString) {
        if (wasProperInputGiven(rowString, colString)) {

            return true;

        } else {
            System.out.println(Maze.ANSI_RED + "Please do not enter Negative numbers." + Maze.ANSI_RESET);
        }
        return false;
    }

    public void placeSquirrelInMaze(String rowString, String colString) {
        int inRow = Integer.parseInt(rowString);
        int inCol = Integer.parseInt(colString);

        if (Maze.available(inRow, inCol)) {
            row = inRow;
            column = inCol;
            Entity made = getInstanceOfSquirrel(); //getInstanceOfSquirrel(symbol, inRow, inCol, pointsCollected, totalNutsEaten);
            Maze.setMazeEntity(made);
            return;
        }
        if (Maze.outOfBounds(inRow, inCol)) {
            System.out.println(Maze.ANSI_RED + "Oops. Squirrely is out of bounds! Try again! " + Maze.ANSI_RESET + Maze.ANSI_BLUE + "Hint:Row:0-19,Col:0-49" + Maze.ANSI_RESET);

        }
        if (!Maze.available(inRow, inCol)) {
            System.out.println(Maze.ANSI_RED + "Oops. Squirrely can't be placed in the walls. Try again! " + Maze.ANSI_RESET + Maze.ANSI_BLUE + "Hint:Row:0-19,Col:0-49" + Maze.ANSI_RESET);

        }
    }

    public boolean wasProperInputGiven(String rowString, String colString) {
        return wasNegativeValueInputted(rowString)
                && wasNegativeValueInputted(colString)
                && numberOrNotInputted(rowString)
                && numberOrNotInputted(colString);
    }

    public boolean wasNegativeValueInputted(String input) {
        if (input.charAt(0) == '-') {
            return false;
        }
        return true;
    }

    public boolean numberOrNotInputted(String input) {
        Scanner scan = new Scanner(input);

        if (scan.hasNextInt()) {
            return true;
        }
        if (!scan.hasNextInt()) {
            System.out.println("Please make sure to enter in numbers!");
        }
        return false;
    }

    @Override
    public void move(char direction) {
        if (checkChar(direction)) {

            if ((direction == 'w')) {
                moveUp(row, column);
            }
            if (direction == 's') {
                moveDown(row, column);
            }
            if (direction == 'a') {
                moveLeft(row, column);
            }
            if (direction == 'd') {
                moveRight(row, column);

            }
        }
    }

    public void moveUp(int row, int column) {
        int rowUp = row - 1;
        if (Maze.available(rowUp, column)) {
            replaceOldPosition(row, column);
            this.row = rowUp;
            updatePositionInEntityMaze(rowUp, column);
        }
    }

    public void moveDown(int row, int column) {
        int rowDown = row + 1;
        if (Maze.available(rowDown, column)) {
            replaceOldPosition(row, column);
            this.row = rowDown;
            updatePositionInEntityMaze(rowDown, column);
        }
    }

    public void moveLeft(int row, int column) {
        int colLeft = column - 1;
        if (Maze.available(row, colLeft)) {
            replaceOldPosition(row, column);
            this.column = colLeft;
            updatePositionInEntityMaze(row, colLeft);
        }
    }

    public void moveRight(int row, int column) {
        int colRight = column + 1;
        if (Maze.available(row, colRight)) {
            replaceOldPosition(row, column);
            this.column = colRight;
            updatePositionInEntityMaze(row, colRight);
        }
    }

    public void updatePositionInEntityMaze(int row, int column) {
        Squirrel a = new Squirrel(symbol, row, column, pointsCollected, totalNutsEaten);
        Maze.setMazeEntity(a);
    }

    public void replaceOldPosition(int row, int column) {
        Wall white = new Wall(' ', row, column);
        Maze.setMazeEntity(white);
    }

    public boolean checkChar(char it) {
        if (it == 'w' || it == 's' || it == 'd' || it == 'a' || it == 'W' || it == 'S' || it == 'D' || it == 'A') {
            return true;
        }
        System.out.println("Incorrect input. 'w'-Squirrelly moves up, 's'-Squirrelly moves down, 'a'-Squirrelly moves left, 'd'-Squirrelly moves right. ");
        return false;
    }

    public int getPointsCollected() {
        return pointsCollected;
    }

    public void setRow(int row) {
        this.row = row;

    }
}
