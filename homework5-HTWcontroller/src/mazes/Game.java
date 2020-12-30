package mazes;

/**
 * Represents the Game model to play the game. It provides all the options to start
 * and proceed the game.
 * 
 */
public interface Game {
  
  /**
   * Launches the game after user's input from the command line.
   * @param startLocX starting X location for player in integer
   * @param startLocY starting Y location for player in integer
   * @param mazeType type of the maze in String
   * @param numOfRows number of Rows of the maze in integer
   * @param numOfCols number of columns of the maze in integer
   * @param remainingWalls number of remaining walls of the maze in integer
   * @param numOfArrows number of arrows play start the game with in integer
   * @param numOfPits number of Pits present in the game in integer
   * @param numOfBats number of bats present in the game in integer
   */
  public void launchGame(int startLocX, int startLocY, String mazeType, int numOfRows, 
      int numOfCols, int remainingWalls, int numOfArrows, int numOfPits, int numOfBats);
  
  /**
   * Gives the available moves from player's current position.
   * @return a string with all available moves
   */
  public String getAvailableMoves();
  
  /**
   * Moves the player from the current location to the specified direction.
   * @param newDirection direction in String where the player wants to move
   * @return status of the new location in terms of danger after the move in string
   */
  public String move(String newDirection);
  
  /**
   * Finds if the arrow killed the wumpus or failed to do so after arrow is thrown.
   * @param direction direction in string where arrow is thrown
   * @param distance how many caves away the arrow is aimed in string
   * @return a string to tell if the arrow was successful
   */
  public String findArrowDestination(String direction, int distance);
  
  

}
