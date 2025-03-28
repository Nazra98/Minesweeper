/**
 * Assign
 * This class handles the creation of all moves in the game
 * @author Lauren Scott / Modified by Nazra Sajida 
 * @version 1.0 May 2024
 */

public class Assign {
   //The row and column being assigned
    private int col, row;
    //The game 
    private Minesweeper game;
    //2D Array to store the game's moves
    Slot[][] moves;
   
    /**
     * Constructor for Assign class.
     * This gets the total number of moves and calls methods to determine the row that will be filled, and to set the state of the slot being assigned.
     * @param game - the game
     * @param col - the column the user has selected
     * @param row - the row the user has selected 
     * @param number - a string value that determines whether it is a player/computer move
     */
    public Assign(Minesweeper game, int row,int col, String number){
        this.game = game;
        this.col = col;
        this.row = row;
        assignMove(number);
    }
    
    /**
     * assignMove
     * This method assigns the move to the game
     * @param player a String value to determine whether it is a computer/player move
     */
    public void assignMove(String number){
        moves[row][col].setState(number);
    }
    
    /**
     * getRow
     * This method returns the current row value for this move. It allows another element of the program to access this move's current row.
     * @return the row value
     */
    public int getRow() {
        return row;
    }
}//End of class Assign
