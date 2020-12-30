package battle;

import java.util.Collections;
import java.util.List;

/**
 * Abstracts the common functionalities of different dresses.
 * 
 */
public class AbstractDress implements Dress {
  
  protected int attackVal = 0;
  protected int defenseVal = 0;
  protected String name;
  
  /**
   * Constructs a Dress with all possible parameters of a dress.
   * @param name name of the dress in String
   * @param attackVal attack value of the dress in integer
   * @param defenseVal defense value of the dress in integer
   * @throws IllegalArgumentException catches illegal arguments values and throws exception
   */
  public AbstractDress(String name, int attackVal, int defenseVal)
      throws IllegalArgumentException {
    if (attackVal < 0 || defenseVal < 0 || name.equals("") || name.equals(" ")) {
      throw new IllegalArgumentException("Invalid initialization of Dress!!");
    }
    
    this.attackVal = attackVal;
    this.defenseVal = defenseVal;
    this.name = name;
  }
  
  /**
   * Constructs a Dress with only the name of the dress.
   * @param name name of the dress in String
   * @throws IllegalArgumentException catches illegal name value and throws exception
   */
  public AbstractDress(String name)
      throws IllegalArgumentException {
    if (name.equals("") || name.equals(" ")) {
      throw new IllegalArgumentException("Invalid initialization of Dress!!");
    }
    
    this.name = name;
  }
  
  @Override
  public boolean equalsHeadGear(Dress other) {
    return false;
  }
  
  @Override
  public boolean equalsFootWear(Dress other) {
    return false;
  }
  
  @Override
  public boolean equalsHandGear(Dress other) {
    return false;
  }

  @Override
  public int compareTo(Dress o) {
    
    if (this.attackVal > o.getAttackVal()) {
      return 1;
    }
    
    if (this.attackVal < o.getAttackVal()) {
      return -1;
    }
    
    if (this.defenseVal > o.getDefenseVal()) {
      return 1;
    }
    if (this.defenseVal < o.getDefenseVal()) {
      return -1;
    }
    return 0;
  }

  @Override
  public int getAttackVal() {
    return this.attackVal;
  }

  @Override
  public int getDefenseVal() {
    return this.defenseVal;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Dress mergeDress(Dress other) {
    return null;
  }

  @Override
  public List<Dress> sort(List<Dress> newDress) {
    Collections.reverse(newDress);
    return newDress;
  }
  
  @Override
  public boolean equals(Object other) {
    return false;
  }
  
  @Override
  public String toString() {
    return String.format("%s -- defense strength: %d, attack strength: %d",
        //this.getName(), this.getDefenseVal(), this.getAttackVal());
        this.name, this.getDefenseVal(), this.getAttackVal());
  }
  
  @Override
  public int hashCode() {
    return this.attackVal + this.defenseVal;
  }

}
