package conservatory;

/**
 * This abstract class holds common functionalities between ShoreBird and WaterFowl class. 
 *
 */
public abstract class AbstractWaterBird extends AbstractBird {
  
  protected String waterBody;
  protected boolean eatFish;
  
  /**
   * Used to get the type of water the bird lives near by.
   * @return water type in string
   * 
   */  
  public String getWaterBody() {
    return this.waterBody;
  }
  
  /**
   * Used to know if the bird eats fish or not.
   * @return if fishes are eaten by the bird in boolean
   */
  public boolean getEatFish() {
    return this.eatFish;
  }

}
