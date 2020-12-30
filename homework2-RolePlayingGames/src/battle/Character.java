package battle;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the battle. 
 *
 */
public class Character {
  
  private int defenseVal;
  private int attackVal;
  private String name;
  private List<Dress> dressList = new ArrayList<Dress>();
  private int numOfHeadGear = 0;
  private int numOfFootWear = 0;
  private int numOfHandGear = 0;
  private int addeddefenseVal = 0;
  private int addedAttackVal = 0;
  private static final int maxNumOfDresses = 5;
  
  /**
   * Initializes the character parameters.
   * @param name character's name in String 
   * @param attackVal character's maximum attack value in integer
   * @param defenseVal character's maximum defense value in integer
   * @throws IllegalArgumentException if any values are not legal
   */
  public Character(String name, int attackVal, int defenseVal)
      throws IllegalArgumentException {
    if (defenseVal < 0 || attackVal < 0 || name.equals("")) {
      throw new IllegalArgumentException("Illegal values");
    }
    this.defenseVal = defenseVal;
    this.attackVal = attackVal;
    this.name = name;
  }
  
  /**
   * Adds a dress to the character.
   * @param newDress the dress to be added
   * @throws IllegalArgumentException if dress limit is reach already
   */
  public void addDress(Dress newDress)
      throws IllegalArgumentException {
    if (this.numOfFootWear + this.numOfHandGear + this.numOfHeadGear == maxNumOfDresses) {
      throw new IllegalStateException("Dress list is full!!");
    }
    if (this.addedAttackVal + newDress.getAttackVal() > this.attackVal 
        || this.addeddefenseVal + newDress.getDefenseVal() > this.defenseVal) {
      throw new IllegalStateException("Target already reached!!");
    }
    if (newDress.equalsHeadGear(newDress)) {
      if (numOfHeadGear == 1) {
        throw new IllegalStateException("HeadGear list is full!!");
      }
      dressList.add(newDress);
      numOfHeadGear++;
    }
    if (newDress.equalsHandGear(newDress)) {
      if (numOfHandGear == 2) {
        throw new IllegalStateException("HandGear list is full!!");
      }
      dressList.add(newDress);
      numOfHandGear++;
    }
    if (newDress.equalsFootWear(newDress)) {
      if (numOfFootWear == 2) {
        throw new IllegalStateException("FootWear list is full!!");
      }
      dressList.add(newDress);
      numOfFootWear++;
    }
    this.addedAttackVal += newDress.getAttackVal();
    this.addeddefenseVal += newDress.getDefenseVal();
  }
  
  /**
   * Swaps the previous dress with newly merged dress.
   * @param newDress the dress that is replacing
   * @param kindOfDress what kind of dress is being swapped
   */
  public void swapDress(Dress newDress, String kindOfDress) {
    this.dressList.set(this.dressList.size() - 1, newDress);
    if (kindOfDress .equals("FootWear")) {
      this.numOfFootWear++;
    } else {
      this.numOfHandGear++;
    }
  }
  
  /**
   * Gives the defense value of the character.
   * @return defense value in integer.
   */
  public int getDefenseVal() {
    return this.defenseVal;
  }
  
  /**
   * Gives the attack value of the character.
   * @return attack value of the character
   */
  public int getAttackVal() {
    return this.attackVal;
  }
  
  /**
   * Gives the list of the dresses.
   * @return list with the dresses
   */
  public List<Dress> getDressList() {
    return this.dressList;
  }
  
  /**
   * Gives the name of the character.
   * @return name in String
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Gives how many total dresses already added.
   * @return an integer representing total dresses so far
   */
  public int getTotalDresses() {
    return this.numOfFootWear + this.numOfHandGear + this.numOfHeadGear;
  }

}
