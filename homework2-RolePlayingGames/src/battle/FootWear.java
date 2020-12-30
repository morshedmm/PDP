package battle;

/**
 * Represents a dress type named Foot Wear.
 */
public class FootWear extends AbstractDress {
  
  /**
   * Initializes the attributes of Foot Wear Dress.
   * @param name name of the dress in String
   * @param attackVal attack value of the dress in integer
   * @param defenseVal defense value of the dress in integer
   */
  public FootWear(String name, int attackVal, int defenseVal) {
    super(name, attackVal, defenseVal);
  }
  
  @Override
  public boolean equalsFootWear(Dress other) {
    return true;
  }
  
  @Override
  public boolean equals(Object other) {
    if (other instanceof AbstractDress) {
      AbstractDress tempDress = (AbstractDress) other;
      return tempDress.equalsFootWear(this);
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
    
    return new FootWear(thisDressContents[0] + ", " + otherDressContents[0] + " " 
        + otherDressContents[1], this.attackVal + other.getAttackVal(), this.defenseVal 
        + other.getDefenseVal());
  }
  
  @Override
  public int hashCode() {
    return 100 * this.attackVal + 10 * this.defenseVal;
  }


}
