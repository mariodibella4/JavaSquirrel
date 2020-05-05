/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariodibellaprojectfinal;

/**
 *
 * @author mario
 */
public class Almond extends Nut{
    private int nutrionalPoints;
    private String name;
    private char symbol;
    public Almond(char symbol, int row, int column,int nutrionalPoints,String name) {
        super(symbol, row, column,nutrionalPoints,name);
        this.symbol='A';
        this.name="almond";
        this.nutrionalPoints=5;
    }
}
