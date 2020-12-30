package mazes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player who is playing on the maze.
 * 
 */
public class Player {
  
  private Location startLocation;
  private Location curLocation;
  private Location destiLocation;
  private Maze maze;
  private StringBuilder savedPrizes = new StringBuilder();
  private double totalGold = 0;
  private List<List<Integer>> visited = new ArrayList<List<Integer>>();
  
  /**
   * Creates a player when there is no destination.
   * @param startLocX x coordinate of staring location 
   * @param startLocY y coordinate of starting location
   * @param maze maze where player is playing
   * @throws IllegalArgumentException if parameters are not legal
   */
  public Player(int startLocX, int startLocY, Maze maze)
      throws IllegalArgumentException {
    if (startLocX < 0 || startLocY < 0 || maze == null) {
      throw new IllegalArgumentException("Invalid start point!!");
    }
    
    Point newPoint = new Point(startLocX, startLocY);
    Location newLoc = new Location(newPoint);
    this.maze = maze;
    this.startLocation = this.maze.mapLocation(newLoc);
    this.curLocation = this.startLocation;
    this.destiLocation = null;
    
    
  }
  
  /**
   * Creates a player when there is a destination.
   * @param startLocX x coordinate of staring location
   * @param startLocY y coordinate of starting location
   * @param destiLocX x coordinate of destination location
   * @param destiLocY y coordinate of destination location
   * @param maze where player will play
   * @throws IllegalArgumentException if arguments are not legal
   */
  public Player(int startLocX, int startLocY, int destiLocX, int destiLocY, Maze maze)
      throws IllegalArgumentException {
    if (startLocX < 0 || startLocY < 0 || destiLocX < 0 || destiLocY < 0 || maze == null) {
      throw new IllegalArgumentException("Invalid start requests!!");
    }
    
    Point newPoint = new Point(startLocX, startLocY);
    Location newLoc = new Location(newPoint); 
    this.maze = maze;
    this.startLocation = this.maze.mapLocation(newLoc);
    this.curLocation = this.startLocation;
    Point newPoint2 = new Point(destiLocX, destiLocY); 
    Location newLoc2 = new Location(newPoint2);
    this.destiLocation = this.maze.mapLocation(newLoc2);
    
    
  }
  
  /**
   * Represents legal moving directions.
   * 
   */
  enum Direction {
    east, west, north, south;
  }
  
  private List<Location> getNextMoves() {
    List<Location> nextMoves = new ArrayList<Location>();
    Location newMove;
    
    newMove = this.maze.giveEastMove(this.curLocation);
    if (newMove != null && this.visited.get((int) newMove.getLocationCoord()
        .getX()).get((int) newMove.getLocationCoord().getY()) == 0) {
      nextMoves.add(newMove);
    }
    
    newMove = this.maze.giveWestMove(this.curLocation);
    if (newMove != null && this.visited.get((int) newMove.getLocationCoord()
        .getX()).get((int) newMove.getLocationCoord().getY()) == 0) {
      nextMoves.add(newMove);
    }
    
    newMove = this.maze.giveNorthMove(this.curLocation);
    if (newMove != null && this.visited.get((int) newMove.getLocationCoord()
        .getX()).get((int) newMove.getLocationCoord().getY()) == 0) {
      nextMoves.add(newMove);
    }
    
    newMove = this.maze.giveSouthMove(this.curLocation);
    if (newMove != null && this.visited.get((int) newMove.getLocationCoord()
        .getX()).get((int) newMove.getLocationCoord().getY()) == 0) {
      nextMoves.add(newMove);
    }
    
    return nextMoves;
    
  }
  
  private boolean ifTravelledAll() {
    for (int idx = 0; idx < this.visited.size(); idx++) {
      for (int jdx = 0; jdx < this.visited.get(idx).size(); jdx++) {
        if (visited.get(idx).get(jdx) == 0) {
          return false;
        }
      }
    }
    return true;
  }
  
  private void travel() {
    if (this.ifTravelledAll()) {
      return;
    }
    
    // Checks if this location is already visited.
    if (this.visited.get((int) this.curLocation.getLocationCoord()
        .getX()).get((int) this.curLocation.getLocationCoord().getY()) == 1) {
      this.totalGold += this.curLocation.getprize().getPrizeValue();
      this.totalGold = this.totalGold * this.curLocation.getprize().getFactor();
      
      this.savedPrizes.append(this.curLocation.toString());
      this.savedPrizes.append(", Gold: ");
      this.savedPrizes.append(this.totalGold);
      this.savedPrizes.append("\n");
      return;
    }
    
    this.visited.get((int) this.curLocation.getLocationCoord().getX())
        .set((int) this.curLocation.getLocationCoord().getY(), 1);
    
    // Collects or looses the gold.
    this.totalGold += this.curLocation.getprize().getPrizeValue();
    this.totalGold = this.totalGold * this.curLocation.getprize().getFactor();
    
    this.savedPrizes.append(this.curLocation.toString());
    this.savedPrizes.append(", Gold: ");
    this.savedPrizes.append(this.totalGold);
    this.savedPrizes.append("\n");
    
    
    // Checking if there is a valid destination and player has reached there
    if (this.destiLocation != null) {
      if (this.destiLocation.equals(this.curLocation)) {
        for (int idx = 0; idx < this.visited.size(); idx++) {
          for (int jdx = 0; jdx < this.visited.get(idx).size(); jdx++) {
            this.visited.get(idx).set(jdx, 1);
          }
        }
        return;
      }
    }
    
    // Roaming is complete.
    List<Location> itsNextMoves = this.getNextMoves();
    if (itsNextMoves.size() == 0) {
      return;
    }
    
    // Moves to the neighboring locations.
    Location savedLocation = this.curLocation;
    for (Location eachLoc: itsNextMoves) {
      this.curLocation = eachLoc;
      travel();
    }
    
    if (this.ifTravelledAll()) {
      return;
    }
    this.curLocation = savedLocation;
    this.totalGold = this.totalGold * this.curLocation.getprize().getFactor();
    
    this.savedPrizes.append(this.curLocation.toString());
    this.savedPrizes.append(", Gold: ");
    this.savedPrizes.append(this.totalGold);
    this.savedPrizes.append("\n");
    return;
    
    
  }
  
  /**
   * Makes the player move.
   * @param numOfRows number of rows of the maze where player moves
   * @param numOfCols number of columns of the maze where player moves
   * @throws IllegalArgumentException if dimensions are not legal
   */
  public void moveAll(int numOfRows, int numOfCols)
      throws IllegalArgumentException {
    if (numOfRows <= 0 || numOfCols <= 0) {
      throw new IllegalArgumentException("Illegal dimensions!!");
    }
    
    // Initialize visited list to make sure there is no cycle during travel
    for (int idx = 0; idx < numOfCols; idx++) {
      visited.add(new ArrayList<Integer>());
      for (int jdx = 0; jdx < numOfRows; jdx++) {
        visited.get(idx).add(0);
      }
    }
    
    this.travel();  
    
  }
  
  /**
   * Used by the player to move in a certain direction.
   * @param newDirection direction to move to
   * @return boolean whether move is successful or not
   * @throws IllegalArgumentException if direction is not legal
   */
  public boolean move(String newDirection)
      throws IllegalArgumentException {
    if (! Direction.east.toString().equals(newDirection) 
        && ! Direction.west.toString().equals(newDirection)
        && ! Direction.north.toString().equals(newDirection) 
        && ! Direction.south.toString().equals(newDirection)) {
      throw new IllegalArgumentException("Illegal direction!!");
    }
    
    if (newDirection.equals(Direction.east.toString())) {
      if (this.maze.giveEastMove(curLocation) != null) { 
        this.curLocation = this.maze.giveEastMove(curLocation);
        return true;
      }
    } else if (newDirection.equals(Direction.west.toString())) {
      if (this.maze.giveWestMove(curLocation) != null) { 
        this.curLocation = this.maze.giveWestMove(curLocation);
        return true;
      }
    } else if (newDirection.equals(Direction.north.toString())) {
      if (this.maze.giveNorthMove(curLocation) != null) { 
        this.curLocation = this.maze.giveNorthMove(curLocation);
        return true;
      }
    } else {
      if (this.maze.giveSouthMove(curLocation) != null) { 
        this.curLocation = this.maze.giveSouthMove(curLocation);
        return true;
      }
    }
    return false;
    
  }
  
  @Override
  public String toString() {
    return this.savedPrizes.toString();
  }
  
  

}
