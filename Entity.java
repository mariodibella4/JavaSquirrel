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
public abstract class Entity {
    
   private char symbol;
   private int row;
   private int column;
   
    
    Entity(char symbol,int row, int column){
        this.symbol=symbol;
        this.row=row;
        this.column=column;
        
    }
    
    public abstract void create();

    public char getSymbol()
    {
        return this.symbol;
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public int getCol()
    {
        return this.column;
    }
    
}

