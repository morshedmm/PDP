package mazes;

import java.io.IOException;

/**
 * Represents an interface for game controller that has common methods shared between GUI and 
 * text control.
 * 
 */
public interface GameControllerInterface {
  
  /**
   * Starts the game with model to run in text command mode.
   * @param maze a model to play game of type Maze
   * @throws IOException if inputs are not legal
   */
  public void start(Maze maze) throws IOException;
  
  /**
   * Starts the game with the model and the view to run the game in GUI mode.
   * @param gameView view used to display game status
   * @param maze a model to ply game of type maze
   */
  public void start(GameViewInterface gameView, Maze maze);
  
  /**
   * Ends the game any time a player wants in GUI mode. 
   */
  public void exitProgram();
  
  /**
   * Starts the game in the GUI format after signaled by user from GUI with the game parameters
   * chosen by the player. 
   * @param mazeType type of maze
   * @param remainingWalls walls to remain in String to build the maze for room mazes
   * @param numOfRows number of rows in String
   * @param numOfCols number of columns in String 
   * @param numOfPits number of pits in String
   * @param numOfBats number of bats in String
   * @param view GUI view
   * @param player1StartX start location of player1
   * @param player1StartY start location of player1
   * @param player2StartX start location of player2
   * @param player2StartY start location of player2
   * @param numOfArrows number of arrows each player start with in String
   * @param numOfPlayers number of players to play in String
   * @throws IOException if inputs are not legal
   */
  public void startGame(String mazeType, String remainingWalls, String numOfRows, 
      String numOfCols, String numOfPits, String numOfBats, GameViewInterface view, 
      String player1StartX, String player1StartY, String player2StartX, String player2StartY, 
      String numOfArrows, String numOfPlayers) throws IOException;
  
  /**
   * Handles north move of the player.
   * @throws IOException if output image is not found
   */
  public void moveNorth() throws IOException;
  
  /**
   * Handles south move of the player.
   * @throws IOException if output image is not found
   */
  public void moveSouth() throws IOException;
  
  /**
   * Handles east move of the player.
   * @throws IOException if output image is not found
   */
  public void moveEast() throws IOException;
  
  /**
   * Handles west move of the player.
   * @throws IOException if output image is not found
   */
  public void moveWest() throws IOException;
  
  /**
   * Handles shooting arrow signaled by the player using GUI.
   * @param direction direction in String for shooting the arrow
   * @param distance distance by which the arrow will be shot in String
   * @throws IOException if the output image is not found
   */
  public void shootArrow(String direction, String distance) throws IOException;

}
