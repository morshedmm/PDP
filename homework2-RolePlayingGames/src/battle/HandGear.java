package battle;

/**
 * Represents a dress of type Hand Gear.
 */
public class HandGear extends AbstractDress {
  
  /**
   * Initializes the attributes of hand Gear Dress.
   * @param name name of the dress in String
   * @param attackVal attack value of the dress in integer
   * @throws IllegalArgumentException if negative value of attack is passed
   */
  public HandGear(String name, int attackVal)
      throws IllegalArgumentException {
    super(name);
    if (attackVal < 0) {
      throw new IllegalArgumentException("Invalid initialization of Dress!!");
    }
    
    this.attackVal = attackVal;
  }
  
  @Override
  public boolean equalsHandGear(Dress other) {
    return true;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractDress) {
      AbstractDress tempDress = (AbstractDress) other;
      return tempDress.equalsHandGear(this);
    }
    return false;
  }
  
  @Override
  public Dress mergeDress(Dress other)
      throws IllegalArgumentException {
    if (! this.equals(other)) {
      throw new IllegalArgumentException("Dress types are not the same");
    }
    String[] thisDressContents = this.toString().split(" ");
    String[] otherDressContents = other.toString().split(" ");
    
    return new HandGear(thisDressContents[0] + ", " + otherDressContents[0] + " " 
        + otherDressContents[1], this.attackVal + other.getAttackVal());
  }
  
  @Override
  public int hashCode() {
    return 100 * this.attackVal;
  }

}
