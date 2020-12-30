import mazes.Game;

/**
 * Represents a Mock model with no active processing to test controller class in isolation.
 * 
 */
public class MockModel implements Game {
  
  private StringBuilder log;
  
  /**
   * Creates a mock model object.
   * @param log saves the contents sent in string
   */
  public MockModel(StringBuilder log) {
    this.log = log;
    
  }

  @Override
  public void launchGame(int startLocX, int startLocY, String mazeType, int numOfRows,
      int numOfCols, int remainingWalls, int numOfArrows, int numOfPits, int numOfBats) {
    log.append("Input: " + startLocX + " " + startLocY + " " + mazeType + " " + numOfRows
        + " " + numOfCols + " " + remainingWalls + " " + numOfArrows + " " + numOfPits
        + " " + numOfBats);
    

  }

  @Override
  public String getAvailableMoves() {
    return "";
  }

  @Override
  public String move(String newDirection) {
    log.append(" " + newDirection);
    return "";
  }

  @Override
  public String findArrowDestination(String direction, int distance) {
    log.append(" " + direction + " " + distance);
    return "";
  }

}
