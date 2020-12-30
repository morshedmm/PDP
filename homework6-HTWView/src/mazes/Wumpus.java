package mazes;

/**
 * Represents a Wumpus object in the Game.
 * 
 */
public class Wumpus implements CaveObject {
  
  private String message;
  
  /**
   * Constructs a Wumpus object.
   */
  public Wumpus() {
    this.message = "You are eaten by Wumpus";
  }

  @Override
  public String getMessage() {
    
    return this.message;
  }
  
  @Override
  public boolean equalsWumpus() {
    return true;
  }

  @Override
  public boolean equalsBottomlessPit() {
    return false;
  }

  @Override
  public boolean equalsSuperBat() {
    
    return false;
  }
  
  

}
