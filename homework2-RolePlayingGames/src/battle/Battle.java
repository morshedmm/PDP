package battle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a battle. Initiates the battle, assigns dresses based on rules, declares winner
 * in the end.
 * 
 */
public class Battle {
  
  private static final int maxNumOfDresses = 10;
  private static final int maxNumOfCharacters = 2;
  private int numOfDresses;
  private int numOfCharacters;
  private List<Character> characterList = new ArrayList<Character>();
  private List<Dress> headGearList = new ArrayList<Dress>();
  private List<Dress> handGearList = new ArrayList<Dress>();
  private List<Dress> footWearList = new ArrayList<Dress>();
  
  /**
   * Initializes a Battle.
   */
  public Battle() {
    this.numOfCharacters = 0;
    this.numOfDresses = 0;
  }
  
  /**
   * How many dresses are there now, that is given.
   * @return number of dresses in integer
   */
  public int getNumOfDresses() {
    return this.numOfDresses;
  }
  
  /**
   * How many characters are there now, that is given.
   * @return number of characters in integer
   */
  public int getNumOfCharacters() {
    return this.numOfCharacters;
  }
  
  /**
   * Gives the list of he characters.
   * @return character list
   */
  public List<Character> getCharacterList() {
    return this.characterList;
  }
  
  /**
   * Adds a character for the battle.
   * @param name name of the character
   * @param attackVal maximum attack value that character can acquire 
   * @param defenseVal maximum defense value that character an acquire
   * @throws IllegalStateException checks if more characters are being added than the limit
   */
  public void addCharacter(String name, int attackVal, int defenseVal)
      throws IllegalStateException {
    if (this.numOfCharacters == maxNumOfCharacters) {
      throw new IllegalStateException("Can not add more characters!!");
    }
    
    characterList.add(new Character(name, attackVal, defenseVal));
    this.numOfCharacters++;
  }
  
  
  /**
   * Adds a headgear to the available list.
   * @param name name of the headgear
   * @param defenseVal defense value of headgear
   * @throws IllegalStateException if illegal arguments are passed, exception is thrown
   */
  public void addHeadGear(String name, int defenseVal)
      throws IllegalStateException {
    if (this.numOfDresses == maxNumOfDresses) {
      throw new IllegalStateException("Can not add more Dresses!!");
    }
    this.headGearList.add(new HeadGear(name, defenseVal));
    Collections.reverse(this.headGearList);
    this.numOfDresses++;
  }
  
  /**
   * Adds a hand gear to the available list.
   * @param name name of the hand gear
   * @param attackVal attack value of hand gear
   * @throws IllegalStateException if illegal arguments are passed, exception is thrown
   */
  public void addHandGear(String name, int attackVal)
      throws IllegalStateException {
    if (this.numOfDresses == maxNumOfDresses) {
      throw new IllegalStateException("Can not add more Dresses!!");
    }
    this.handGearList.add(new HandGear(name, attackVal));
    Collections.reverse(this.handGearList);
    this.numOfDresses++;
  }
  
  /**
   * Adds a Foot Wear to the available list.
   * @param name name of the foot wear
   * @param attackVal attack value of the foot wear
   * @param defenseVal defense value of the foot wear
   * @throws IllegalStateException if illegal arguments are passed, exception is thrown
   */
  public void addFootWear(String name, int attackVal, int defenseVal)
      throws IllegalStateException {
    if (this.numOfDresses == maxNumOfDresses) {
      throw new IllegalStateException("Can not add more Dresses!!");
    }
    this.footWearList.add(new FootWear(name, attackVal, defenseVal));
    Collections.sort(this.footWearList);
    this.numOfDresses++;
  }
  
  private Character findCharacterFromName(String name) {
    if (this.characterList.get(0).getName() == name) {
      return this.characterList.get(0);
    }
    return this.characterList.get(1);
  }
  
  /**
   * One dress is assigned to a character according to the priority of the dresses.
   * @param characterName the player who wants the dress to be assigned
   */
  public void assignDress(String characterName) {
    Character character = this.findCharacterFromName(characterName);
    int lastidxFootWear = this.footWearList.size() - 1;
    if (character.getTotalDresses() == 4) {
      character.addDress(this.headGearList.get(0));
      this.headGearList.remove(0);
    } else if (character.getTotalDresses() == 0) {
      if (this.footWearList.get(lastidxFootWear).compareTo(this.handGearList.get(0)) == -1) {
        character.addDress(this.handGearList.get(0));
        this.handGearList.remove(0);
      } else {
        character.addDress(this.footWearList.get(lastidxFootWear));
        this.footWearList.remove(lastidxFootWear);
      }      
    }  else if (character.getTotalDresses() == 2) {
      if (character.getDressList().get(0).equalsFootWear(character.getDressList().get(0))) {
        character.addDress(this.handGearList.get(0));
        this.handGearList.remove(0);
      } else {
        character.addDress(this.footWearList.get(lastidxFootWear));
        this.footWearList.remove(lastidxFootWear);
      }
    } else {
      if (character.getDressList().get(character.getDressList().size() - 1)
          .equalsFootWear(character.getDressList().get(character.getDressList().size() - 1))) {
        Dress newDress = character.getDressList().get(character.getDressList().size() - 1)
            .mergeDress(this.footWearList.get(lastidxFootWear)); 
        character.swapDress(newDress, "FootWear");
        this.footWearList.remove(lastidxFootWear);
        
      } else {
        Dress newDress = character.getDressList().get(character.getDressList().size() - 1)
            .mergeDress(this.handGearList.get(0));
        character.swapDress(newDress, "HandGear");
        this.handGearList.remove(0);
      }
      
    }   
    
  }
  
  /**
   * Prints the character's name, dress name, attack and defense values of each dress.
   * @param name name of the character
   * @return String with info
   * @throws IllegalArgumentException if that character is not included yet
   */
  public String characterInfo(String name)
      throws IllegalArgumentException {
    if (! name.equals(this.characterList.get(0).getName()) 
        && ! name.equals(this.characterList.get(1).getName())) {
      throw new IllegalArgumentException("Not a valid character!!");
    }
    
    Character character;
    List<String> description = new ArrayList<String>();
    if (name.equals(this.characterList.get(0).getName())) {
      character = this.characterList.get(0);
    } else {
      character = this.characterList.get(1);
    }
    
    for (int i = 0; i < character.getDressList().size(); i++) {
      description.add(character.getDressList().get(i).toString());
    }
    
    return description.toString();
  }
  
  private int getTotalAttackVal(Character character) {
    int totalVal = 0;
    for (int i = 0; i < character.getDressList().size(); i++) {
      totalVal += character.getDressList().get(i).getAttackVal();
    }
    return totalVal;
  }
  
  private int getTotalDefenseVal(Character character) {
    int totalVal = 0;
    for (int i = 0; i < character.getDressList().size(); i++) {
      totalVal += character.getDressList().get(i).getDefenseVal();
    }
    return totalVal;
  }
  
  /**
   * Prints the winner in the battle.
   * @return String with the information of winner
   * @throws IllegalStateException If not enough characters are present for the battle yet
   */
  public String printWinner()
      throws IllegalStateException {
    if (this.characterList.size() < 2) {
      throw new IllegalStateException("Not enough characters yet!!");
    }
    int attackVal1 = this.getTotalAttackVal(this.characterList.get(0));
    int attackVal2 = this.getTotalAttackVal(this.characterList.get(1));
    int defenseVal1 = this.getTotalDefenseVal(this.characterList.get(0));
    int defenseVal2 = this.getTotalDefenseVal(this.characterList.get(1));
    
    if (attackVal2 - defenseVal1 < attackVal1 - defenseVal2) {
      return String.format("%s Won!!", this.characterList.get(0).getName());
    } else if (attackVal2 - defenseVal1 > attackVal1 - defenseVal2) {
      return String.format("%s Won!!", this.characterList.get(1).getName());
    }
    
    return String.format("Tie!!");
  }

}
