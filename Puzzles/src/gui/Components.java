package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import shared.ProgramConstants;

/**
 * The components of the GUI
 * @author Sad Panda Software
 * @version 2.0
 *
 */
public class Components {
	
  /**
   * Toolbars in the interface
   */
  private static JToolBar toolBar, leftBar, rightBar;
	
  /**
   * Various panels used in the interface
   */
  private static JPanel dropDownPanel, buttonPanel, sidebarPanel;
	
  /**
   * The actual drop down menu in the dropDownPanel
   */
  private static JComboBox dropDown;
	
  /**
   * Label for the Word List
   */
  private static JLabel wordListLabel;
	
  /**
   * The output panel
   */
  private static OutputPanel outputPanel;
	
	/**
   * An empty JLabel 
	 */
  private static final JLabel EMPTY_LABEL = new JLabel(" ");
	
  /**
   * Strings used for labelling various buttons and controls
   */
  private static final String NEW = "New", OPEN = "Open", SAVE = "Save",
			EXPORT = "Export", QUIT = "Quit", HELP = "Help",
			GENERATE = "Generate";
	
  /**
   * Area for storing the word list
   */
	public static MutableList wordList;
	
  /**
   * Text area for inputting words
   */
  public static JTextField wordField;
	
	/**
   * Returns the name of the currently selected drop down item 
   * @return String
	 */
  public static String getSelectedPuzzleOption () {
		return ((String)(dropDown.getSelectedItem()));
	}
	
	/**
   * Returns the currently inputted word in uppercase 
   * @return String
	 */
  public static String getWordFieldText() {
		return (wordField.getText().toUpperCase());
	}
	
	/**
   * Sets the output panel 
   * @param p - OutputPanel
	 */
  public static void setOutputPanel (OutputPanel p) {
	  outputPanel = p;
	}
	
	/**
   * Returns the output panel 
   * @return OutputPanel
	 */
  public static OutputPanel getOutputPanel () {
	  return outputPanel;
	}
	
	/**
   * Builds the side panel 
   * @return JPanel
	 */
  public static JPanel buildSidebar() {
		wordField = new JTextField();
		Buttons.addWordToList = new JButton ("Add New Word");
		Buttons.removeWordFromList = new JButton("Remove Selected Word");
		Buttons.clearList = new JButton ("Clear Word List");
		wordListLabel = new JLabel("Word List");
		wordListLabel.setHorizontalAlignment (JLabel.CENTER);
		
		buttonPanel = new JPanel (new GridLayout(4,1,5,5));
		buttonPanel.add(wordField);
		buttonPanel.add(Buttons.addWordToList);
		buttonPanel.add(Buttons.removeWordFromList);
		buttonPanel.add(Buttons.clearList);
		
		wordList = new MutableList();
		JScrollPane scrollPane = new JScrollPane(wordList);
		
		sidebarPanel = new JPanel (new BorderLayout(5, 5));
		sidebarPanel.add(wordListLabel, BorderLayout.NORTH);
		sidebarPanel.add(buttonPanel, BorderLayout.SOUTH);
		sidebarPanel.add(scrollPane, BorderLayout.CENTER);
		
		return sidebarPanel;
	}
	
	/**
   * Builds the toolbar 
   * @return JToolBar
	 */
  public static JToolBar buildToolbar() {
		toolBar = new JToolBar();
		leftBar = new JToolBar();
		rightBar = new JToolBar();

		toolBar.setLayout(new BorderLayout());
		leftBar.setLayout(new GridLayout(1, 6));
		rightBar.setLayout(new BorderLayout());

		leftBar.setFloatable(false);
		rightBar.setFloatable(false);
		leftBar.setRollover(true);
		rightBar.setRollover(true);

		String[] s = { ProgramConstants.WORD_SEARCH, ProgramConstants.CROSSWORD };
		dropDown = new JComboBox(s);
		dropDownPanel = new JPanel(new GridLayout(3, 1));
		dropDownPanel.add(EMPTY_LABEL);
		dropDownPanel.add(EMPTY_LABEL);
		dropDownPanel.add(dropDown);

		leftBar.add(generateButton(NEW));
		leftBar.add(generateButton(OPEN));
		leftBar.add(generateButton(SAVE));
		leftBar.add(generateButton(EXPORT));
		leftBar.add(generateButton(QUIT));
		leftBar.add(generateButton(HELP));

		rightBar.add(dropDownPanel, BorderLayout.CENTER);
		rightBar.add(generateButton(GENERATE), BorderLayout.EAST);

		toolBar.add(leftBar, BorderLayout.WEST);
		toolBar.add(rightBar, BorderLayout.EAST);

		toolBar.setSize(toolBar.getWidth() * 2, toolBar.getHeight() * 2);
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		
		return toolBar;
	}
	
	/**
   * Generates a button given its name 
   * @param name - String
   * @return JButton
	 */
  private static JButton generateButton(String name) {
	  
		JButton button;
		button = new JButton(name, new ImageIcon(name.toLowerCase() + ".png"));
		button.setVerticalTextPosition(SwingConstants.BOTTOM);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setRolloverEnabled(true);
		button.setFocusable(false);

		//Assign to the correct value
		if (name.equals(NEW))
      Buttons.newButton = button;
    else if (name.equals(OPEN))
      Buttons.openButton = button;
    else if (name.equals(SAVE))
      Buttons.saveButton = button;
    else if (name.equals(EXPORT))
      Buttons.exportButton = button;
    else if (name.equals(QUIT))
      Buttons.quitButton = button;
    else if (name.equals(HELP))
      Buttons.helpButton = button;
    else if (name.equals(GENERATE))
      Buttons.generateButton = button;
		
		return (button);
	}

	/**
   * Buttons used in the interface 
   * @author Sad Panda Software
   * @version 2.0
	 */
  public static class Buttons {
		
    /**
     * The buttons
     */
    public static JButton newButton, openButton, saveButton, exportButton,
				quitButton, helpButton, generateButton, addWordToList, removeWordFromList, clearList;
		
		/**
     * Adds action listeners to the buttons
     * @param listener - ActionListener
		 */
    public static void addActionListener (ActionListener listener) {
			newButton.addActionListener(listener);
			openButton.addActionListener(listener);
			saveButton.addActionListener(listener);
			exportButton.addActionListener(listener);
			quitButton.addActionListener(listener);
			helpButton.addActionListener(listener);
			generateButton.addActionListener(listener);
			addWordToList.addActionListener(listener);
			removeWordFromList.addActionListener(listener);
			clearList.addActionListener(listener);
			wordField.addActionListener (listener);
		}
	}
}
