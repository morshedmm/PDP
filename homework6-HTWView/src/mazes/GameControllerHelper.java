package mazes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import java.awt.Point;

/**
 * Creates a class to wrap all functionalities needed to proceed with the game.
 * 
 */
public class GameControllerHelper {
  
  private Maze maze;
  private List<Location> visited = new ArrayList<Location>();
  
  /**
   * Constructs a GameController object with the maze provided. 
   * @param maze a Maze object to play the game on
   */
  public GameControllerHelper(Maze maze) {
    this.maze = maze;
  }
  
  /**
   * Changes the maze with options passed.
   * @param mazeParams all maze parameters for the game
   * @throws IllegalArgumentException if any invalid argument is passed to create the maze
   */
  
  public void createGame(Map<String, String> mazeParams) 
      throws IllegalArgumentException {
    if (Integer.parseInt(mazeParams.get("numOfRows")) < 1 
        || Integer.parseInt(mazeParams.get("numOfCols")) < 1 
        || Integer.parseInt(mazeParams.get("numOfPits")) < 0 
        || Integer.parseInt(mazeParams.get("numOfBats")) < 0 
        || Integer.parseInt(mazeParams.get("remainingWalls")) < 0 
        || (! mazeParams.get("mazeType").equals("room")
        && ! mazeParams.get("mazeType").equals("wrapped") 
        && ! mazeParams.get("mazeType").equals("perfect")
        && ! mazeParams.get("mazeType").equals("other"))) {
      throw new IllegalArgumentException("Invalid start point!!");
    }

    int numOfRows = Integer.valueOf(mazeParams.get("numOfRows"));
    int numOfCols = Integer.valueOf(mazeParams.get("numOfCols"));
    int remainingWalls = Integer.valueOf(mazeParams.get("remainingWalls"));
    int numOfPits = Integer.valueOf(mazeParams.get("numOfPits"));
    int numOfBats = Integer.valueOf(mazeParams.get("numOfBats"));
    
    if (mazeParams.get("mazeType").equals("perfect")) {
      this.maze = new PerfectMaze(numOfRows, numOfCols, numOfPits, numOfBats);
    } else if (mazeParams.get("mazeType").equals("room")) {
      this.maze = new RoomMaze(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
    } else if (mazeParams.get("mazeType").equals("wrapped")) {
      this.maze = new WrappedRoomMaze(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
    }
    
  }
  
  /**
   * Represents legal moving directions.
   * 
   */
  enum Direction {
    east, west, north, south;
  }
  
  /**
   * Moves the player from the current location to the specified direction.
   * @param player player for whom moves are being requested 
   * @return status of the new location in terms of danger after the move in string
   */
  public String getAvailableMoves(Player player) {
    Location curLocation = player.giveCurLocation();
    StringBuilder nextMoves = new StringBuilder();
    Location newMove;
    
    newMove = this.maze.giveEastMove(curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.east.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveWestMove(curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.west.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveNorthMove(curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.north.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveSouthMove(curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.south.toString());
      nextMoves.append("\n");
    }
    //
    this.getTunnel();
    
    return nextMoves.toString();
    
  }
  
  /**
   * Moves the player from the current location to the specified direction.
   * @param newDirection direction in String where the player wants to move
   * @return status of the new location in terms of danger after the move in string
   * @throws IllegalArgumentException if direction is not legal
   */
  public String move(String newDirection, Player player)
      throws IllegalArgumentException {
    if (! Direction.east.toString().equals(newDirection) 
        && ! Direction.west.toString().equals(newDirection)
        && ! Direction.north.toString().equals(newDirection) 
        && ! Direction.south.toString().equals(newDirection)) {
      throw new IllegalArgumentException("Illegal direction!!");
    }
    StringBuilder message = new StringBuilder();
    Location curLocation = player.giveCurLocation();
    //
    this.resetTunnel();
    //
    
    if (newDirection.equals(Direction.east.toString())) {
      if (this.maze.giveEastMove(curLocation) != null) { 
        curLocation = this.maze.giveEastMove(curLocation);
        
      }
    } else if (newDirection.equals(Direction.west.toString())) {
      if (this.maze.giveWestMove(curLocation) != null) { 
        curLocation = this.maze.giveWestMove(curLocation);
        
      }
    } else if (newDirection.equals(Direction.north.toString())) {
      if (this.maze.giveNorthMove(curLocation) != null) { 
        curLocation = this.maze.giveNorthMove(curLocation);
        
      }
    } else {
      if (this.maze.giveSouthMove(curLocation) != null) { 
        curLocation = this.maze.giveSouthMove(curLocation);
        
      }
    }
    this.visited = this.resetTunnel();
    // checking if caught by superbat
    if (curLocation.getprize().get(0).equalsSuperBat()) {
      int randVal = Integer.valueOf(curLocation.getprize().get(0).getMessage());
      if (randVal == 1) {
        // generate random location for the player
        message.append(String.valueOf(curLocation.getLocationCoord().getX()) + " " 
            + String.valueOf(curLocation.getLocationCoord().getY()) + " " 
            + "You were grabbed by a superbat ");
        curLocation = this.maze.giveRandomLocation();
        
      } else {
        
        message.append("You dodged a superbat ");
      }
    }
    message.append(String.format("%s%s", "Your current location is:", curLocation.toString()));
    // Check if caught by Wumpus or Pit, then send game is over.
    if (curLocation.getprize().get(1).equalsWumpus()) {
      message.append("You are eaten by a Wumpus! Game Over.");
      player.setCurLocation(curLocation);
      return message.toString();
    } else if (curLocation.getprize().get(1).equalsBottomlessPit()) {
      message.append("You have fallen in the bottomless pit! Game Over.");
      player.setCurLocation(curLocation);
      return message.toString();
    }
    
    player.setCurLocation(curLocation);
    message.append(this.maze.ifSmelledSomething(curLocation));
    return message.toString();
    
  }
  
  /**
   * Finds if the arrow killed the wumpus or failed to do so after arrow is thrown.
   * @param direction direction in string where arrow is thrown
   * @param distance how many caves away the arrow is aimed in string
   * @param player player for whom shoot is requested
   * @return a string to tell if the arrow was successful
   */
  public String findArrowDestination(Player player, String direction, int distance) {
    //numOfArrows--;
    Location curLocation = player.giveCurLocation();
    int numOfArrows = player.giveNumOfArrows();
    Location arrowLocation = this.maze.findArrowLocation(curLocation, direction, distance);
    if (arrowLocation.getprize().get(1).equalsWumpus()) {
      player.setCurLocation(arrowLocation);
      return "Wumpus killed!! You Won";
    }
    
    if (numOfArrows == 0) {
      return "You missed the target! You have no more Arrow! Game Over.";
    }
    return "You missed the target! Good Luck!";
    
  }
  
  
  public List<Location> getTunnel() {
    return this.visited;
  }
  
  public List<Location> resetTunnel() {
    return this.maze.giveTunnelLocations();
  }
  
  public void resetGame() {
    this.maze = null;
  }

}
