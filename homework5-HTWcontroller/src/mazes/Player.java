package mazes;

import java.awt.Point;


/**
 * Represents an implementation of the game being played.
 */
public class Player implements Game {
  
  private Location curLocation;
  private int numOfArrows;
  private Maze maze;
  
  
  /**
   * Creates a player with default arguments.
   */
  public Player() {
    
    this.curLocation = new Location(new Point(0, 0));
    this.numOfArrows = 0;
    this.maze = null;
    
  }
  
  // It is needed to make sure game options are coming from controller not driver.
  @Override
  public void launchGame(int startLocX, int startLocY, String mazeType, int numOfRows, 
      int numOfCols, int remainingWalls, int numOfArrows, int numOfPits, int numOfBats)
      throws IllegalArgumentException {
    if (startLocX < 0 || startLocY < 0 || numOfRows < 1 || numOfCols < 1 
        || numOfPits < 0 || numOfBats < 0 || remainingWalls < 0 || (! mazeType.equals("room")
        && ! mazeType.equals("wrapped") && ! mazeType.equals("perfect")) 
        || numOfArrows < 1) {
      throw new IllegalArgumentException("Invalid start point!!");
    }
    
    Point newPoint = new Point(startLocX, startLocY);
    Location newLoc = new Location(newPoint);
    this.numOfArrows = numOfArrows;
    
    if (mazeType.equals("perfect")) {
      this.maze = new PerfectMaze(numOfRows, numOfCols, numOfPits, numOfBats);
    } else if (mazeType.equals("room")) {
      this.maze = new RoomMaze(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
    } else {
      this.maze = new WrappedRoomMaze(numOfRows, numOfCols, remainingWalls, numOfPits, numOfBats);
    }
    
    this.curLocation = this.maze.mapLocation(newLoc);
    
    
  }
  
  
  
  /**
   * Represents legal moving directions.
   * 
   */
  enum Direction {
    east, west, north, south;
  }
  
  @Override
  public String getAvailableMoves() {
    StringBuilder nextMoves = new StringBuilder();
    Location newMove;
    
    newMove = this.maze.giveEastMove(this.curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.east.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveWestMove(this.curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.west.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveNorthMove(this.curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.north.toString());
      nextMoves.append("\n");
    }
    
    newMove = this.maze.giveSouthMove(this.curLocation);
    if (newMove != null) {
      nextMoves.append(Direction.south.toString());
      nextMoves.append("\n");
    }
    return nextMoves.toString();
    
  }
  
  
  
  @Override
  public String move(String newDirection)
      throws IllegalArgumentException {
    if (! Direction.east.toString().equals(newDirection) 
        && ! Direction.west.toString().equals(newDirection)
        && ! Direction.north.toString().equals(newDirection) 
        && ! Direction.south.toString().equals(newDirection)) {
      throw new IllegalArgumentException("Illegal direction!!");
    }
    
    StringBuilder message = new StringBuilder();
    
    if (newDirection.equals(Direction.east.toString())) {
      if (this.maze.giveEastMove(curLocation) != null) { 
        this.curLocation = this.maze.giveEastMove(curLocation);
        
      }
    } else if (newDirection.equals(Direction.west.toString())) {
      if (this.maze.giveWestMove(curLocation) != null) { 
        this.curLocation = this.maze.giveWestMove(curLocation);
        
      }
    } else if (newDirection.equals(Direction.north.toString())) {
      if (this.maze.giveNorthMove(curLocation) != null) { 
        this.curLocation = this.maze.giveNorthMove(curLocation);
        
      }
    } else {
      if (this.maze.giveSouthMove(curLocation) != null) { 
        this.curLocation = this.maze.giveSouthMove(curLocation);
        
      }
    }
    // checking if caught by superbat
    if (this.curLocation.getprize().get(0).equalsSuperBat()) {
      int randVal = Integer.valueOf(this.curLocation.getprize().get(0).getMessage());
      if (randVal == 1) {
        // generate random location for the player
        this.curLocation = this.maze.giveRandomLocation();
        message.append("You were grabbed by a superbat");
        
      } else {
        
        message.append("You dodged a superbat.");
      }
    }
    message.append(String.format("%s%s", "Your current location is:", this.curLocation.toString()));
    // Check if caught by Wumpus or Pit, then send game is over.
    if (this.curLocation.getprize().get(1).equalsWumpus()) {
      message.append("You are eaten by a Wumpus! Game Over.");
      return message.toString();
    } else if (this.curLocation.getprize().get(1).equalsBottomlessPit()) {
      message.append("You have fallen in the bottomless pit! Game Over.");
      return message.toString();
    }
    
    message.append(this.maze.ifSmelledSomething(this.curLocation));
    return message.toString();
    
  }
  
  // This is added to handle movement of the arrow.
  @Override
  public String findArrowDestination(String direction, int distance) {
    this.numOfArrows--;
    Location arrowLocation = this.maze.findArrowLocation(this.curLocation, direction, distance);
    if (arrowLocation.getprize().get(1).equalsWumpus()) {
      return "Wumpus killed!! You Won";
    }
    
    if (this.numOfArrows == 0) {
      return "You missed the target! You have no more Arrow! Game Over.";
    }
    return "You missed the target! Good Luck!";
    
  }
  
  @Override
  public String toString() {
    
    return String.format("%s%s\n", "Current Player Location:", this.curLocation.toString());
  }
  
  

}
