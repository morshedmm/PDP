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
  public Move(String direction) {
    
    this.direction = direction;
  }

  @Override
  public String execute(Game player) {
    
    return player.move(this.direction);

  }

}
