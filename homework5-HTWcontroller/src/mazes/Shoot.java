package mazes;

/**
 * Represents a Shoot command which shoots an arrow from current location to given direction
 * and by given number of cave numbers if valid.
 * 
 */
public class Shoot implements HuntCommand {
  
  private String direction;
  private int numOfCaves;
  
  /**
   * Creates a shoot command to move an arrow to given direction and by number of caves.
   * @param direction direction to which arrow will be thrown in string
   * @param numOfCaves number of caves the arrow will be attempted in integer
   */
  public Shoot(String direction, int numOfCaves) {
    
    this.direction = direction;
    this.numOfCaves = numOfCaves;
  }

  @Override
  public String execute(Game player) {
    
    return player.findArrowDestination(direction, numOfCaves);

  }

}
