import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 * A simple GUI for the Minesweeper game
 * Provides a Graphical User Interface with buttons for game interactions 
 * @Nazra Sajida (W21005067)
 * @version 1.0 May 2024 
 */
public class GUI extends JFrame implements ActionListener {
    //This is used to create panel in the frame 
    private JPanel infoPanel = new JPanel();
    //This is used to create panel in the frame 
    private JPanel buttonPanel = new JPanel();
    //This is used to create grids in the frame 
    private JPanel gridPanel = new JPanel();
    //This is used to create a text field in the frame 
    private JTextField infoField = new JTextField(15);
    //This is used for the grid 
    private JButton [][] gridButtons;
    //This is used to specify the grid size 
    private int gridsize = 5;
    //This is used to create the undo button
    private JButton undoButton = new JButton("Undo");
    //This is used to create the save button
    private JButton saveButton = new JButton("Save");
    //This is used to create the clear button
    private JButton clearButton= new JButton("Clear");
    //This is used to create the Load button
    private JButton loadButton = new JButton("Load");
    //This is used to create the Toggle button
    private JButton toggleButton = new JButton("Toggle");
    //This is used to create the Label for lives 
    private JLabel livesLabel = new JLabel("Lives: 3");
    //This is used to create the Label for mode 
    private JLabel modeLabel = new JLabel("Mode: Flag");
    //This is used to implement observer observable this has been commented out 
    //private ObservableClass observable;
    /**
     * Constructor to create and display the frame 
     */
    public GUI() {
        super("MineSweeper");
        //observable = new ObservableClass();
        makeFrame();
    }
    
    /**
     * The method that constructs the GUI Frame. 
     */
    private void makeFrame() {
        setLayout(new BorderLayout());
        //Create a live label in the GUI frame 
        infoPanel.add(livesLabel);
        //Create a mode label in the GUI frame 
        infoPanel.add(modeLabel);
        //Create a info field in the GUI frame
        infoPanel.add(infoField);
        //Info field located North 
        add(infoPanel,BorderLayout.NORTH);
        //Create grid buttons 
        gridButtons = new JButton[gridsize][gridsize];
        //Set the layout of the Grid 
        gridPanel.setLayout (new GridLayout(gridsize, gridsize));
        makeGridPanel();
        //Center the window in the center 
        add(gridPanel, BorderLayout.CENTER);
        //Create undo button 
        buttonPanel.add(undoButton);
        //Create save button
        buttonPanel.add(saveButton);
        //Create clear button
        buttonPanel.add(clearButton);
        //Create load button
        buttonPanel.add(loadButton);
        //Create toggle button 
        buttonPanel.add(toggleButton);
        //Undo button for action listener 
        undoButton.addActionListener(this);
        //Save button for action listener
        saveButton.addActionListener(this);
        //Clear button for action listener
        clearButton.addActionListener(this);
        //Load button for action listener 
        loadButton.addActionListener(this);
        //Toggle button for action listener
        toggleButton.addActionListener(this);
        add(buttonPanel, BorderLayout.EAST);
        showFrame();
    }
    
    /**
     * Sets up the main layout of the frame 
     */
    private void showFrame()
    {
        //Default operation to close the JFrame 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create title 'MineSweeper'
        setTitle("MineSweeper");
        //Set the colour of the background to white 
        getContentPane().setBackground(Color.WHITE);
        //Set the preferred size of the frame 
        infoPanel.setPreferredSize(new Dimension(200,200));
        buttonPanel.setPreferredSize(new Dimension(200,200));
        //Correctly sizes the frame 
        pack();
        //Centers the frame 
        setLocationRelativeTo(null);
        //Observing panel 
        //ObservingPanel panel2 = new ObservingPanel(observable);
        //observable.addObserver(panel2);
        //setLayout(new GridLayout(1,2));
        //add(panel2);
        setVisible(true);
    }
    
    /**
     * Add action listener to the buttons, once the buttons are clicked the
     * specified actions are performed 
     * @param evt the action event from the button
     */
    
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        if  (source==undoButton) {
            infoField.setText("Undo action performed");
        } else if (source == saveButton) {
            infoField.setText ("Save action performed");
        } else if (source == clearButton) {
            infoField.setText ("Clear action performed");
        } else if (source == loadButton) {
            infoField.setText ("Load action performed");
        } else if (source == toggleButton) {
            infoField.setText ("Toggle action performed");
        }
    }
    
    /**
     * Create grid panels with buttons 
     */
    public void makeGridPanel () {
        // create and add grid butttons by using loop
        for (int row = 0; row < gridsize; row++){
            for (int col = 0; col < gridsize; col++){
                JButton button = new JButton ("?");
                gridPanel.add(button);
                gridButtons[row][col] = button;
            }
        }
    }
}//end of GUI Class