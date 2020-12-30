import java.io.IOException;
import mazes.GameController;
import mazes.GameViewInterface;

/**
 * Represents a mock view for testing the controller's GUI interaction.
 * 
 */
public class MockView implements GameViewInterface {
  
  private String numOfRows;
  private String numOfCols;
  private String remainingWalls;
  private String numOfPits;
  private String numOfBats;
  private String numOfPlayers;
  private String startLocX;
  private String startLocY;
  private String numOfArrows;
  
  /**
   * Creates a mock view to enable controller testing.
   * @param numOfRows number of rows in string
   * @param numOfCols number of columns in string
   * @param remainingWalls number of remaining walls in string
   * @param numOfPits number of pits in string
   * @param numOfBats number of bats in string
   * @param numOfPlayers number of players in string
   * @param startLocX X start location of player
   * @param startLocY Y start location of player
   * @param numOfArrows number of arrows in string
   */
  public MockView(String numOfRows, String numOfCols, String remainingWalls, String numOfPits,
      String numOfBats, String numOfPlayers, String startLocX, String startLocY, 
      String numOfArrows) {
    this.numOfRows = numOfRows;
    this.numOfCols = numOfCols;
    this.numOfBats = numOfBats;
    this.numOfPits = numOfPits;
    this.numOfPlayers = numOfPlayers;
    this.startLocX = startLocX;
    this.startLocY = startLocY;
    this.numOfArrows = numOfArrows;
    this.remainingWalls = remainingWalls;
        
  }

  @Override
  public void createField(int numOfRows, int numOfCols) {
    return;

  }

  @Override
  public void setFeatures(GameController f) {
    try {
      f.startGame("room", remainingWalls, numOfRows, numOfCols, numOfPits, numOfBats, this, 
          startLocX, startLocY, "0", "0", numOfArrows, numOfPlayers);
    } catch (IOException e1) {
      
      e1.printStackTrace();
    }
    
    try {
      f.moveEast();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      f.moveWest();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      f.moveNorth();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void updateGuiPlayerTag(int x, int y) {
    return;

  }

  @Override
  public void updateGuiImage(int x, int y, String newDirections, int id) throws IOException {
    return;

  }

  @Override
  public void updateGuiImageTunnel(int x, int y, String newDirections) throws IOException {
    return;

  }

  @Override
  public void resetFocus() {
    return;

  }

  @Override
  public void toggleColor(int idx) {
    return;

  }

}
