package puzzle;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 * This is a common puzzle object. It has attributes common to all puzzle types.
 * Common functionality includes adding words, getters and setters, as well as
 * specialized functions such as draw and generate. All puzzle types use this
 * class as the framework.
 * 
 * @author Sad Panda Software
 * @version 3.0
 */
public abstract class Puzzle {
  
  /**
   * flag used for initializing the cells of the puzzle matrix
   */
  protected static final boolean    Initialize = true;
  
  /**
   * flag used for filling in the puzzle matrix with random characters
   */
  protected static final boolean    FillRandom = false;
  
  /** A random number generator */
  private static Random          gen;
  
  /** the word list of PuzzleWords that are in the Puzzle */
  private ArrayList <PuzzleWord> wordList;
  
  /** the list of words to be added to the puzzle */
  private ArrayList <String>     words;
  
  /** the number of words in the puzzle */
  private int                    numWords;
  
  /** the height of the puzzle */
  private int                    height;
  
  /** the width of the puzzle */
  private int                    width;
  
  /** the two-dimensional array used to store arranged letters from PuzzleWords */
  private PuzzleCell [][]        matrix;
  
  /**
   * draws a puzzle
   * 
   * @param g
   *          the graphics to draw to
   */
  public abstract void draw (Graphics2D g);
  
  /** generates a puzzle */
  public abstract void generate ();
  
  /** saves a puzzle */
  public abstract String save ();
  
  /**
   * Generates a string that represents the puzzle as HTML
   * @param isPuzzle flag to generate puzzle or solution
   * @return string represents the puzzle in HTML
   */
  public abstract String export (boolean isPuzzle);
  
  /**
   * adds a word to the puzzle
   * 
   * @param word
   *          a PuzzleWord to add to the puzzle
   * @return true if the word was added, false if the word was not added
   */
  protected abstract boolean addAndValidate (PuzzleWord word);
  
  /**
   * Generates the dimension to be used in the word search matrix
   * 
   * @param list
   * @return an integer specifying the dimension to be used by the Puzzle
   */
  protected abstract int generateDimension (ArrayList <String> list);
  
  /**
   * returns a valid start point for a word by length. Does not check
   * intersections.
   * 
   * @param length
   *          length of the word.
   * @param colSize
   *          number of columns.
   * @param rowSize
   *          number of rows.
   * @return int[] - [0] is the x value, and [1] is the y value.
   */
  protected abstract int [] generatePosition (int length, int colSize, int rowSize, Direction dir);
  
  /**
   * adds a word to the list associated
   * 
   * @param word
   *          a word
   */
  public void addWordToList (String word) {
    words.add (word);
  }
  
  /**
   * removes a word from the list associated
   * 
   * @param word
   *          a word
   */
  public void removeWordFromList (String word) {
    words.remove (word);
  }
  
  /**
   * clears the words from the list associated
   */
  public void clearWordList () {
    words.clear ();
  }
  
  /**
   * gets the specified matrix cell
   * @param r the row
   * @param c the column
   * @return the matrix cell desired
   * @throws ArrayIndexOutOfBoundsException
   */
  public PuzzleCell getMatrixElement (int r, int c) throws ArrayIndexOutOfBoundsException {
    return matrix[r][c];
  }
  
  /**
   * gets the matrix height
   * 
   * @return height of the matrix
   */
  public int getMatrixHeight () {
    return height;
  }
  
  /**
   * gets the matrix height
   * 
   * @return height of the matrix
   */
  public int getMatrixWidth () {
    return width;
  }
  
  /**
   * Returns the number of words in the puzzle.
   * 
   * @return numWords - number of words in the puzzle
   */
  public int getNumWords () {
    return numWords;
  }
  
  /**
   * Returns an array of puzzle words.
   * 
   * @return wordList - a list of PuzzleWords
   */
  public ArrayList <PuzzleWord> getPuzzleWordList () {
    return wordList;
  }
  
  /**
   * Returns an array of words.
   * 
   * @return words - a list of PuzzleWords
   */
  public ArrayList <String> getWordList () {
    return words;
  }
  
  /**
   * sets the list of words associated
   * 
   * @param list
   *          the list to set
   */
  public void setList (ArrayList <String> list) {
    words = list;
  }
  
  /**
   * sets the number of words
   * 
   * @param words
   *          the number of words
   */
  public void setNumWords (int words) {
    numWords = words;
  }
  
  /**
   * sets the matrix width of the puzzle
   * 
   * @param i
   *          the hwidth to set
   */
  public void setMatrixWidth (int i) {
    width = i;
  }
  
  /**
   * sets the matrix height of the puzzle
   * 
   * @param i
   *          the height to set
   */
  public void setMatrixHeight (int i) {
    height = i;
  }
  
  /**
   * Sets a puzzle cell in the matrix
   * @param r the row
   * @param c the column
   * @param cell the cell you wish to set
   */
  public void setMatrixElement (int r, int c,PuzzleCell cell) {
    matrix[r][c] = cell;
  }

  /**
   * builds a matrix based on the assigned height and width of the puzzle
   */
  public void buildMatrix () {
    matrix = new PuzzleCell [height][width];
    for (int r = 0; r < height; ++r)
      for (int c = 0; c < width; ++c)
        matrix[r][c] = new PuzzleCell();
  }
  
  /**
   * sets the PuzzleCell matrix
   * 
   * @param cells
   *          the matrix to set
   */
  
  protected void setMatrix (PuzzleCell [][] cells) {
    if (cells != null) {
      int i = cells.length, j = cells[0].length;
      matrix = new PuzzleCell [i] [j];
      for (int r = 0; r < i; r++) {
        for (int c = 0; c < j; c++) {
          matrix[r][c] = cells[r][c];
        }
      }
    } else {
      matrix = null;
    }
  }
  
  /**
   * sets the word list
   * 
   * @param words
   *          the list of words to set
   */
  public void setWordList (ArrayList <PuzzleWord> words) {
    wordList = words;
  }
  
  /**
   * Builds a popup window that shows the progress of the puzzle generation
   * 
   * @param popup
   *          the popup dialog
   * @param bar
   *          the progress bar
   * @param text
   *          the title of the window
   */
  protected void buildPopup (JDialog popup, JProgressBar bar) {
    bar.setValue (0);
    bar.setStringPainted (true);
    popup.setTitle ("Generating...");
    popup.setLocation (400, 350);
    popup.add (bar);
    popup.pack ();
    popup.setVisible (true);
    popup.setAlwaysOnTop (true);
  }
  
  /**
   * fills the matrix with random characters or spaces
   * 
   * @param length
   * @param fillBlank
   *          flag; true if blanks are desired, false if random chars are
   *          desired
   */
  protected void fillMatrix (boolean fillBlank) {
    for (int r = 0; r < matrix.length; r++) {
      for (int c = 0; c < matrix[0].length; c++) {
        if (fillBlank) {
          matrix[r][c] = new PuzzleCell ();
        } else if (!matrix[r][c].hasCharacter ()) {
          matrix[r][c].addRandomChar ();
        }
      }
    }
  }
  
  /**
   * Generates a random direction.
   * 
   * @return Direction - any of the 8 directions a word can be oriented.
   */
  protected Direction generateDirection (int max) {
    int num = (int) (max * Math.random ());
    return (Direction.values ()[num]);
  }
  
  protected int getColumnChange (Direction dir) {
    int dC = 0;
    switch (dir) {
      case NORTHEAST:
        dC = 1;
        break;
      case EAST:
        dC = 1;
        break;
      case SOUTHEAST:
        dC = 1;
        break;
      case NORTHWEST:
        dC = -1;
        break;
      case WEST:
        dC = -1;
        break;
      case SOUTHWEST:
        dC = -1;
        break;
    }
    return dC;
  }
  
  /**
   * A Singleton object used by any instance of puzzle as a number generator
   * 
   * @return a random number generator
   */
  protected Random getNumberGenerator () {
    if (gen == null) {
      gen = new Random ();
    }
    return gen;
  }
  
  protected int getRowChange (Direction dir) {
    int dR = 0;
    switch (dir) {
      case NORTHWEST:
        dR = -1;
        break;
      case NORTH:
        dR = -1;
        break;
      case NORTHEAST:
        dR = -1;
        break;
      case SOUTHWEST:
        dR = 1;
        break;
      case SOUTH:
        dR = 1;
        break;
      case SOUTHEAST:
        dR = 1;
        break;
    }
    return dR;
  }
  
  /**
   * 
   * @param length
   *          the dimension of the matrix
   */
  protected void initializeMatrix (int length) {
    setMatrix (new PuzzleCell [length] [length]);
    setMatrixWidth (length);
    setMatrixHeight (length);
    fillMatrix (Initialize);
  }
  
  /**
   * resets the attributes of the puzzle
   * 
   */
  protected void reset () {
    setMatrix (null);
    setWordList (null);
    setNumWords (0);
    setMatrixHeight (0);
    setMatrixWidth (0);
  }
  
  /**
   * updates the progress bar to a specified value
   * 
   * @param bar
   *          the progress bar
   * @param val
   *          the value to set to the progress bar
   * @param s
   *          the string to output
   */
  protected void updateProgressBar (JProgressBar bar, int val, String s) {
    bar.setValue (val);
    if (s != null) {
      bar.setString (s);
      bar.setStringPainted (true);
    }
    bar.paintImmediately (0, 0, 600, 100);
  }
}
