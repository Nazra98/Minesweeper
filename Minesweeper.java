import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * This is the class MineSweeper and it handles the functionality of the main game.
 * @author Lauren Scott / Modified by Nazra Sajida 
 * @version 1.0 May 2024 
 */
public class Minesweeper {
    //This array stores the board currently displayed in the game
    private String[][] gameBoard;
    //This stores the players moves and guesses
    private Slot[][] playerBoard;
    //This scanner is used to read the game and level files
    private Scanner reader;
    //This will be the size of the game
    private int gameSize;
    //This is the level file,changable for easy and hard
    private String level = "Levels/em1.txt";
    //This is the number of lives the player has 
    private int lives = 3;
    /**
     * This is the constructor for the class MineSweeper 
     */
    public Minesweeper() {
        try {
            reader = new Scanner(new File(level));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        gameSize = calculateGameSize();
        gameBoard = new String[gameSize][gameSize];
        playerBoard = new Slot[gameSize][gameSize];
        readLevelFile();
    }
    
    /**
     * This method gets the entire set of moves in the game
     * @return the set of moves
     */
    public Slot[][] getMoves() {
        return playerBoard;
    }
    
    /**
     * This method gets an individual cell's state
     * @param row - the row of the move
     * @param col - the column of the move
     * @return The state of that cell
     */
    public String getIndividualMove(int row, int col) {
        return playerBoard[row][col].getState();
    }
    
    /**
     * This method reads the game size from the file 
     * @return the size of the game board 
     */
    public int calculateGameSize() {
        return Integer.parseInt(reader.next());
    }
    
    /**
     * This method provides access to the gameSize from other classes
     * @return the size of the game board 
     */
    public int getGameSize() {
        return gameSize;
    }
    
    /**
     * This method provides access to the lives from other classes
     * @return the number of lives
    */
    public int getLives() {
        return lives;
    }
    
    /**
     * This method reads the level file to populate the game
     * @return The moves stored in the file
     */
    public String[][] readLevelFile() {
        while (reader.hasNext()) {
            int row =Integer.parseInt(reader.next());
            int col =Integer.parseInt(reader.next());
            String move = reader.next();
            
            gameBoard[row][col] = move;
            playerBoard[row][col] = new Slot(row,col,"?");
        }
        return gameBoard;
    }
    
    /**
     * This method checks whether the game has been won
     * @return whether the game has been won
     */ 
    public String checkWin(){
        if (lives !=0 ) {
        for (int i = 0; i<gameSize; i++) {
            for (int c = 0; c <gameSize; c++) {
                if (!playerBoard[i][c].getState().equals(gameBoard[i][c])) {
                    return "continue";
                }
            }
        }
            return "won";
        } else {
            return "lives";
        }
    }
    
    /**
     * This method allows a user to make a move in the game
     * @param row - the row of the move
     * @param col - the column of the move
     * @param number - the number they are wishing to enter in the cell
     * @return a message indicating the results of the move 
     */
    public String makeMove(String row, String col, String guess){
        int enteredRow = Integer.parseInt(row);
        int enteredCol = Integer.parseInt(col);
        if (guess.equals("M") && gameBoard[enteredRow][enteredCol].equals("M")){
            playerBoard[enteredRow][enteredCol].setState("M");        
        } else if (guess.equals("G")) {
            if (gameBoard[enteredRow][enteredCol].equals("M") ) {
                lives -= 1;
                playerBoard[enteredRow][enteredCol].setState("M");
                return "You have lost one life. \nNew life total: " + lives;
            } else if (gameBoard[enteredRow][enteredCol].equals("-")){
                playerBoard[enteredRow][enteredCol].setState("-");
            } else {
                playerBoard[enteredRow][enteredCol].setState(gameBoard[enteredRow][enteredCol]);
            }
            return "You have survived this time";
        } else {
            return "Unsuccessful - this was not a mine";
        }
        return "You have successfully found a mine";
    }
    
    /**
     * This method gets the current state of an individual cell
     * @param row - the row of the cell
     * @param col - the column of the cell
     * @return the state of the cell
     */
    public String getCellState(int row, int col) {
        return playerBoard[row][col].getState();
    }
}//end of class Minesweeper
