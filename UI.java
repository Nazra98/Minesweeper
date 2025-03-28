import java.util.Scanner;
import java.util.Stack;
import java.io.*;
import java.io.IOException;
/**
 * This class provides a text based user interface for the player to interact with the game
 * @author Lauren Scott/ Modified by Nazra Sajida (W21005067)
 * @version 1.0 May 2024 
 */
public class UI {
    //This is the game model
    private Minesweeper thegame;
    //This is the users choice from the menu
    private String menuChoice;
    //This scanner is used to read the terminal
    private Scanner reader;
    //This stack is used to undo the player moves 
    private Stack<String[]> undoStack;
    //This folder is used to save the player moves 
    private String saveFolder = "Save/yourFileName.txt";
    /**
     * Constructor for the class UI
     */
    public UI() {
        thegame = new Minesweeper();
        reader = new Scanner(System.in);
        menuChoice="";
        undoStack = new Stack();
        while(!menuChoice.equalsIgnoreCase("Q")&&thegame.checkWin().equals("continue")) {
            displayGame();
            menu();
            menuChoice = getChoice();
        }
        if (thegame.checkWin().equals("won") ){
            winningAnnouncement();
        } else if (thegame.checkWin().equals("lives") ){
            livesAnnouncement();
        }
    }

    /**
     * Method that outputs an announcement when the user has won the game
     */
    public void winningAnnouncement() {
        System.out.println("\nCongratulations, you solved the level");
    }
    
    /**
     * Method that outputs an announcement when the user has lost due to lack of lives
     */
    public void livesAnnouncement() {
        System.out.println("\nYou have ran out of lives, the game is over");
    }
    
    /**
     * Method that displays the game for the user to play
     */
    public void displayGame() {
        //boardmoves = thegame.getMoves();
        System.out.print("\n\nCol");
        for (int r = 0; r < thegame.getGameSize(); r++) {
            System.out.print(r + " ");
        }
        for (int i = 0; i < thegame.getGameSize(); i++) {
            System.out.print("\nRow  " + i);
            for (int c = 0; c < thegame.getGameSize() ; c++) {
                System.out.print(" "+thegame.getCellState(i,c));
            }
        }
    }

    /**
     * Method that displays the menu to the user
     */
    public void menu() {
        System.out.println("\nPlease select an option: \n"
            + "[M] Flag a mine\n"
            + "[G] Guess a square\n"
            + "[S] save game\n"
            + "[L] load saved game\n"
            + "[U] undo move\n"
            + "[C] clear game\n"
            + "[Q] quit game\n");
    }

    /**
     * Method that gets the user's choice from the menu and conducts the activities
     * accordingly
     * @return the choice the user has selected
     */
    public String getChoice() {
        String choice = reader.next();
        if (choice.equalsIgnoreCase("M") || choice.equalsIgnoreCase("G")) {
            System.out.print("Which row is the cell you wish to flag?  ");
            String row = reader.next();
            System.out.print("Which colum is the cell you wish to flag?  ");
            String col = reader.next();
            String[] userEntry = new String [] {row,col};
            undoStack.add(userEntry);
            System.out.print(thegame.makeMove(row, col, choice));
        } else if (choice.equalsIgnoreCase("S")) {
            saveGame();
        } else if (choice.equalsIgnoreCase("U")) {
            undoMove();
        } else if (choice.equalsIgnoreCase("L")) {
            loadGame();
        } else if (choice.equalsIgnoreCase("C")) {
            clearGame();
        } else if (choice.equalsIgnoreCase("Q")) {
            System.exit(0);
        }
        return choice;
    }

    /**
     * saveGame 
     * This method saves the current game state to a file, including the moves made so far in the game, and the number of lives remaining 
     * @returns the user and computers saved moves 
     */
    public void saveGame() {
        try {
            //create a save folder 
            FileWriter file = new FileWriter(saveFolder);
            //create a file in the save folder 
            PrintWriter writer = new PrintWriter (file,true);
            Slot[][] playerMoves = thegame.getMoves();
            //save lives in the game 
            int lives = thegame.getLives();
            //converts the data type integer into a string 
            String livesString = Integer.toString(lives);
            writer.write(livesString);
            //Create a space and a new line for lives 
            writer.write("\r\n");
            for (String[] previousMove : undoStack) {
                String previousRow = previousMove[0];
                String previousCol = previousMove[1];
                int previousRowNum = Integer.parseInt(previousRow);
                int previousColNum = Integer.parseInt(previousCol);
                String state = playerMoves[previousRowNum][previousColNum].getState();
                writer.write(previousRow);
                writer.write(" ");
                writer.write(previousCol);
                writer.write(" ");
                writer.write(state);
                writer.write("\r\n");
            }
            //Print the message game saved 
            System.out.println("Game Saved");
            writer.close();       
         }    catch (IOException ex) {
            ex.printStackTrace();
         }
        }
        
    /**
     * This method undo the user move in the game 
     */
    public void undoMove() {
        if (undoStack.empty()) {
           //There is no data in the stack 
           System.out.println("Move undone");
     }   else {
           //previousMove = ["0","1"]
           String[] previousMove = undoStack.pop ();
           //previousRow = "0"
           String previousRow = previousMove[0];
           //previousCol = "1"
           String previousCol = previousMove[1];
           //To access Slots, the data must be converted, previous RowNum = 0
           int previousRowNum = Integer.parseInt(previousRow);
           //previousColNum = 1
           int previousColNum = Integer.parseInt(previousCol);
           Slot[][] playerMoves = thegame.getMoves();
           //Sets the original state of the game 
           playerMoves[previousRowNum][previousColNum].setState("?");
           //Prints the message "move undone"
           System.out.println("Move undone");
     }
    }

    /**
     * This method will load the game for the User. 
     * 
     */
    public void loadGame() {
        thegame=new Minesweeper();
        try {
            //create a file called 'save'
            File folder = new File (saveFolder);
            //scanner reader, reads the folder 
            Scanner reader = new Scanner (folder);
            //represents the lives in the game 
            String stringLives = reader.next();
            //interger is converted to a string 
            int lives = Integer.parseInt(stringLives);
            //print out messsage 'Loaded lives is and the number of lives left'
            System.out.println("Loaded lives is" + lives);
        }catch (IOException ex) {
        }
    }

    /**
     * This method clears the game for the User. 
     * @Param returns a message 
     */
    public void clearGame() {
        thegame = new Minesweeper();
        undoStack = new Stack();
        //Print out message 'game cleared'
        System.out.println("Game Cleared");
    }

    /**
     * The main method within the Java application. 
     * It's the core method of the program and calls all others.
     */
    public static void main(String args[]) {
        UI thisUI = new UI();
    }
}//end of class UI