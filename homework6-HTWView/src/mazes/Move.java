package mazes;

/**
 * Represents a Move command which moves player from current location to given direction.
 * 
 */
public class Move implements HuntCommand {
  
  private String direction;
  
  /**
   * Creates a move command to move the player.
   * @param direction direction to which move will be attempted in string
   */
  public Move(String direction)
      throws IllegalArgumentException {
    
    if (! direction.equals("east") && ! direction.equals("west") && ! direction.equals("north")
        && ! direction.equals("south")) {
      throw new IllegalArgumentException("Illegal direction!");
    }
    this.direction = direction;
  }

  @Override
  public String execute(GameControllerHelper gameContollerHelper, Player player) {
    
    return gameContollerHelper.move(this.direction, player);

  }

}
