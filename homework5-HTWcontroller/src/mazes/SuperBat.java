package mazes;

import java.util.Random;

/**
 * Represents a SuperBat object.
 * 
 */
public class SuperBat implements CaveObject {
  
  private String message;
  
  /**
   * Creates a super bat object to be used in the game.
   */
  public SuperBat() {
    
    int num = this.generateNumbers(123456) % 2;
    this.message = String.valueOf(num);
  }
  
  /**
   * Creates a super bat object to be used in the game with random number
   * to create random decision made by bat to grab a player or not.
   */
  public SuperBat(int randNumber) {
    
    int num = this.generateNumbers(randNumber) % 2;
    this.message = String.valueOf(num);
  }
  
  private int generateNumbers(int seed) {
    
    Random r = new Random();
    r.setSeed(seed);
    return r.nextInt(100);
  }

  @Override
  public String getMessage() {
    return this.message;
    
  }

  @Override
  public boolean equalsWumpus() {
    
    return false;
  }

  @Override
  public boolean equalsBottomlessPit() {
    
    return false;
  }

  @Override
  public boolean equalsSuperBat() {
    
    return true;
  }

}
