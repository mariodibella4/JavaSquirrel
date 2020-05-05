/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariodibellaprojectfinal;

import java.util.Arrays;

/**
 *
 * @author mario
 */
public class Nut extends Entity {

    final private int TOTALNUTS = 5;
    private String name;
    private int nutrionalPoints;
    private char symbol;
    private int row;
    private int col;

    public Nut(char symbol, int row, int column, int nutrionalPoints, String name) {
        super(symbol, row, column);
        this.name = name;
        this.symbol = symbol;
        this.nutrionalPoints = nutrionalPoints;
    }
    
    @Override
    public void create() {
        Nut nuts[] = new Nut[TOTALNUTS];
        int[] randomRows = new int[TOTALNUTS];
        int[] randomCols = new int[TOTALNUTS];
        int randomNumberForProbability;
        int randomRow;
        int randomCol;

        for (int i = 0; i < TOTALNUTS; i++) {
            randomNumberForProbability = generateRandomInt(Maze.getMaxMazeRows());
            randomRow = generateRandomInt(Maze.getMaxMazeRows());
            randomCol = generateRandomInt(Maze.getMaxMazeCol());
            randomRows[i] = randomRow;
            randomCols[i] = randomCol;

            if (isItAPeanut(randomNumberForProbability, randomRow, randomCol)) {

                Maze.setMazeEntity(generateAPeanut('P', randomRow, randomCol, 10, "Peanut"));
                nuts[i] = generateAPeanut('P', randomRow, randomCol, 10, "Peanut");

                if (checkToSeeIfANutIsAlreadyAtTheNewNutsLocation(nuts, randomRows, randomCols))
                {
                    --i;
                }
            } else if (isItAnAlmond(randomNumberForProbability, randomRow, randomCol)) {
                
                Maze.setMazeEntity(generateAnAlmond('A', randomRow, randomCol, 5, "Almond"));
                nuts[i] = generateAnAlmond('A', randomRow, randomCol, 5, "Almond");
               
                if (checkToSeeIfANutIsAlreadyAtTheNewNutsLocation(nuts, randomRows, randomCols)) {
                    --i;
                }
            } else {
                --i;
            }
        }
    }
    public int generateRandomInt(int number) {
        return (int) (Math.random() * (number));
    }
     public boolean isItAPeanut(int numberForProbability, int row, int col) {
        return numberForProbability < 10
                && 0 <= numberForProbability
                && Maze.available(row, col)
                && Maze.getMazeEntity(row, col).getSymbol() != '@';
    }
    public boolean isItAnAlmond(int numberForProbability, int row, int col) {
        return numberForProbability < 20
                && 10 <= numberForProbability
                && Maze.available(row, col)
                && Maze.getMazeEntity(row, col).getSymbol() != '@';
    }
    public Peanut generateAPeanut(char symbol, int row, int col, int nutrionalValue, String name) {
        return new Peanut(symbol, row, col, nutrionalValue, name);
    }
    public Almond generateAnAlmond(char symbol, int row, int col, int nutrionalValue, String name) {
        return new Almond(symbol, row, col, nutrionalValue, name);
    }
    public boolean checkToSeeIfANutIsAlreadyAtTheNewNutsLocation(Nut[] nuts, int[] targetrow, int[] targetcol) {
        for (int i = 0; i < nuts.length; i++) {
            int a = nuts[i].row;
            int b = nuts[i].col;
            int c = targetrow[i];
            int d = targetcol[i];

            return a == c && b == d;
        }
        return false;
    }
    
    public int getNutraPts() {
        return nutrionalPoints;
    }

    public String getName() {
        return name;
    }
}

