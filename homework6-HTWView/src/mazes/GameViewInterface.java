package mazes;

import java.io.IOException;

/**
 * Represents a game view interface to expose generic functionalities of GUI. 
 * 
 */
public interface GameViewInterface {
  
  /**
   * Creates a field to play the game.
   * @param numOfRows number of rows in integer
   * @param numOfCols number of columns in integer
   */
  public void createField(int numOfRows, int numOfCols);
  
  /**
   * Sets the features of the GUI.
   * @param f controller object
   */
  public void setFeatures(GameController f);
  
  /**
   * Updates the tags of the players.
   * @param x coordinate of player's location in integer
   * @param y coordinate of player's location in integer
   */
  public void updateGuiPlayerTag(int x, int y);
  
  /**
   * Updates the images of the GUI.
   * @param x x coordinate in integer
   * @param y y coordinate in integer
   * @param newDirections string representation of update instructions
   * @param id player id to use in integer
   * @throws IOException if the images are not available
   */
  public void updateGuiImage(int x, int y, String newDirections, int id) throws IOException;
  
  /**
   * Updates GUI images for a tunnel.
   * @param x x coordinate in integer
   * @param y y coordinate in integer
   * @param newDirections string to parse the directions
   * @throws IOException if the images are not available
   */
  public void updateGuiImageTunnel(int x, int y, String newDirections) throws IOException;
  
  /**
   * Resets the focus of the GUI for keyboard key strokes.
   */
  public void resetFocus();
  
  /**
   * Toggles the color of player tag to show active player.
   * @param idx id if player in integer
   */
  public void toggleColor(int idx);

}
