package mazes;

import java.awt.Point;


/**
 * Represents an implementation of the game being played.
 */
public class Player {
  
  private Location curLocation;
  private int numOfArrows;
  
  
  
  /**
   * Creates a player with default arguments.
   */
  public Player() {
    
    this.curLocation = new Location(new Point(0, 0));
    this.numOfArrows = 0;
    
    
  }
  
  /**
   * Creates a player with starting position given.
   * @param startLocX X coordinate of player's starting location
   * @param startLocY Y coordinate of player's starting location
   */
  public Player(int startLocX, int startLocY, int numOfArrows)
      throws IllegalArgumentException {
    if (startLocX < 0 || startLocY < 0 || numOfArrows < 1) {
      throw new IllegalArgumentException("Illegal player conditions!");
    }
    
    this.curLocation = new Location(new Point(startLocX, startLocY));
    this.numOfArrows = numOfArrows;
    
    
  }
  
  public Location giveCurLocation() {
    return this.curLocation;
  }
  
  public int giveNumOfArrows() {
    this.numOfArrows--;
    return this.numOfArrows;
  }
  
  public void setCurLocation(Location newLoc) {
    this.curLocation = newLoc;
  }
  
  @Override
  public String toString() {
    
    return String.format("%s%s\n", "Current Player Location:", this.curLocation.toString());
  } 
  
  

}
