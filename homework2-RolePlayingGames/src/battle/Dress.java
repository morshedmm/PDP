package battle;

import java.util.List;

/**
 * Represents a dress in the battle.
 * 
 */
public interface Dress extends Comparable<Dress> {
  
  /**
   * Provides the attack value of the dress.
   * @return giving attack value of the dress in integer
   */
  public int getAttackVal();
  
  /**
   * Provides the defense value of the dress.
   * @return giving defense value of the dress in integer
   */
  public int getDefenseVal();
  
  /**
   * Provides the name of the dress.
   * @return giving name of the dress in integer
   */
  public String getName();
  
  /**
   * Two dresses of the same type are merged and the new dress is returned.
   * @return Dress that is created after the merger
   */
  public Dress mergeDress(Dress other);
  
  /**
   * Compares this dress with the Dress sent to compare based on certain conditions.
   * @return an integer based on which dress has larger attribute
   */
  public int compareTo(Dress o);
  
  /**
   * Used for sorting a list of dresses.
   * @param newDress unsorted list of provided dress list
   * @return sorted list of provided dress list
   */
  public List<Dress> sort(List<Dress> newDress);
  
  /**
   * Checks for the equality of two Dresses.
   * @param other Dress to check with
   * @return boolean to tell if the dresses are equal
   */
  @Override
  public boolean equals(Object other);
  
  /**
   * Checks if a dress is of type HeadGear.
   * @param other the dress need to be checked
   * @return boolean value
   */
  public boolean equalsHeadGear(Dress other);
  
  /**
   * Checks if a dress is of type Foot Wear.
   * @param other the dress need to be checked
   * @return boolean value
   */
  public boolean equalsFootWear(Dress other);
  
  /**
   * Checks if a dress is of type Hand Gear.
   * @param other the dress need to be checked
   * @return boolean value
   */
  public boolean equalsHandGear(Dress other);
  
  @Override
  public int hashCode();

}
