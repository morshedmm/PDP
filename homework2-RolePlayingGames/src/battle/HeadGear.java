package battle;

/**
 * Represents a dress of type Head Gear.
 */
public class HeadGear extends AbstractDress {
  
  /**
   * Initializes the attributes of head Gear Dress.
   * @param name name of the dress in String
   * @param defenseVal defenseVal defense value of the dress in integer
   * @throws IllegalArgumentException if negative value of defense is passed
   */
  public HeadGear(String name, int defenseVal)
      throws IllegalArgumentException {
    super(name);
    if (defenseVal < 0) {
      throw new IllegalArgumentException("Invalid initialization of Dress!!");
    }
    
    this.defenseVal = defenseVal;
  }
  
  @Override
  public boolean equalsHeadGear(Dress other) {
    return true;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractDress) {
      AbstractDress tempDress = (AbstractDress) other;
      return tempDress.equalsHeadGear(this);
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return 10 * this.defenseVal;
  }

}
