package conservatory;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents an aviary which is the home for birds.
 *
 */
public final class Aviary {
  
  private int locationX;
  private int locationY;
  private int id;
  private int maxNumOfBirds;
  private ArrayList<Bird> birdsList = new ArrayList<Bird>();
  private HashMap<String, Integer> foodReserve = new HashMap<String, Integer>();
  
  /**
   * This function sets up the parameters when an aviary is created. Caller gets new aviary by it. 
   * @param locationX x coordinate in integer
   * @param locationY y coordinate in integer
   * @param id id of the aviary in integer
   * @param maxNumOfBirds maximum number of birds can live in integer
   * @throws IllegalArgumentException if locations, id or number of birds are negative it throws
   *         exception
   */
  public Aviary(int locationX, int locationY, int id, int maxNumOfBirds)
      throws IllegalArgumentException {
    if (locationX < 0 || locationY < 0 || id < 0 || maxNumOfBirds < 0) {
      throw new IllegalArgumentException("Negative values are not valid for Aviary parameters");
    }
    
    this.locationX = locationX;
    this.locationY = locationY;
    this.id = id;
    this.maxNumOfBirds = maxNumOfBirds;
  }
 
  /**
   * This function gives the X location of aviary.
   * @return an integer representing x location
   */
  public int getLocationX() {
    return this.locationX;
  }
 
  /**
   * This function gives the Y location of aviary.
   * @return an integer representing y location
   */
  public int getLocationY() {
    return this.locationY;
  }
 
  /**
   * This function gives the ID of the aviary.
   * @return an integer representing ID
   */
  public int getId() {
    return this.id;
  }

  /**
   * This functions gives the maximum number of birds it can host.
   * @return an integer representing number of maximum birds
   */
  public int getMaxNumOfBirds() {
    return this.maxNumOfBirds;
  }
 
  /**
   * Updating the food required for this aviary if a new bird is assigned.
   * @param foodName name of the food in string
   * @param foodQuantity quantity needed for new bird in integer
   * @throws IllegalArgumentException throws exception food name is not of one of the assumed type
   */
  private void updateFoodReserve(String foodName, int foodQuantity)
      throws IllegalArgumentException {
    if (! foodName.equals("berries") && ! foodName.equals("seeds") && ! foodName.equals("fruit") 
        && ! foodName.equals("insects") && ! foodName.equals("other birds")
        && ! foodName.equals("eggs") && ! foodName.equals("small mammals") 
        && ! foodName.equals("fish") && ! foodName.equals("buds") && ! foodName.equals("larvae")
        && ! foodName.equals("nuts") && ! foodName.equals("aquatic invertebrates")
        && ! foodName.equals("vegetation")) {
      throw new IllegalArgumentException("The name of the food is not allowed to add!!");
    }
   
    if (this.foodReserve.containsKey(foodName)) {
      this.foodReserve.put(foodName, (foodQuantity + this.foodReserve.get(foodName)));
    }  else {
      this.foodReserve.put(foodName, foodQuantity);
    }
  }
 
  /**
   * Giving the food requirements of this aviary.
   * @return quantity of food in integer
   * @throws IllegalArgumentException checking if food name is valid 
   */
  public HashMap<String, Integer> getFoodQuantity() {
  
    return foodReserve;
  }

  /**
  * Trying to assign a bird in this aviary if all conditions are met. Caller has successful bird
  * entry by this method.
  * @param bird any bird class
  * @return true or false based on success or failure of assignment
  */
  public boolean assignBird(Bird bird) {
   
    /*
     * Can not assign in this aviary if it is already full.
     */
    if (this.maxNumOfBirds == this.birdsList.size()) {
      return false;
    }
   
    /*
     * Checking if this bird is either Prey, Fightless or Waterfowl and assignable in this aviary.
     */
    if (bird.toString().equals("class conservatory.Prey")) {
      for (Bird curBird : this.birdsList) {
        if (curBird.toString().equals("class conservatory.Fightless") 
            || curBird.toString().equals("class conservatory.WaterFowl")) {
          return false;
        }
      }
    }
   
    if (bird.toString().equals("class conservatory.Fightless")) {
      for (Bird curBird : this.birdsList) {
        if (curBird.toString().equals("class conservatory.Prey") 
            || curBird.toString().equals("class conservatory.WaterFowl")) {
          return false;
        }
      }
    }
   
    if (bird.toString().equals("class conservatory.WaterFowl")) {
      for (Bird curBird : this.birdsList) {
        if (curBird.toString().equals("class conservatory.Fightless") 
            || curBird.toString().equals("class conservatory.Prey")) {
          return false;
        }
      }
    }
   
    /*
     * As this bird can be assigned in this aviary, food requirements will be added now.
     */
   
    String[] foodArray = bird.getFoodNeeded().split(" ");
   
    /*
     * Invalid foodArry if number of elements are not even.
     */
    if (foodArray.length % 2 != 0) {
      return false;
    }
   
    int idx = 0;
    while (idx < foodArray.length) {
      this.updateFoodReserve(foodArray[idx], Integer.parseInt(foodArray[idx + 1]));
      idx += 2;
    }
    this.birdsList.add(bird);
    return true;
  }
 
  /**
   * This function returns the array list containing the birds in this aviary.
   * @return an arraylist of birds 
   */
  public ArrayList<Bird> getBirdsList() {
    return this.birdsList;
  }
 
}
