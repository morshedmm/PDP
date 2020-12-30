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
  public Shoot(String direction, int numOfCaves) 
      throws IllegalArgumentException {
    if (! direction.equals("east") && ! direction.equals("west") && ! direction.equals("north")
        && ! direction.equals("south")) {
      throw new IllegalArgumentException("Illegal direction!");
    }
    
    if (numOfCaves < 0) {
      throw new IllegalArgumentException("Illegal distance!");
    }

    this.direction = direction;
    this.numOfCaves = numOfCaves;
  }

  @Override
  public String execute(GameControllerHelper gameContollerHelper, Player player) {
    
    return gameContollerHelper.findArrowDestination(player, direction, numOfCaves);

  }

}
