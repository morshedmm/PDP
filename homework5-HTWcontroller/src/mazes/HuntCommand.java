package mazes;

/**
 * Represents a Command for the game that wraps up any command.
 * 
 */
public interface HuntCommand {
  
  /**
   * Executes the command on the player.
   * @param player a Game object on whom command is applied
   * @return results after the command is executed in string
   */
  public String execute(Game player);

}
