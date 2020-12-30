

import static org.junit.Assert.assertEquals;

import java.awt.Point;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import mazes.GameController;
import mazes.GameViewInterface;
import mazes.Location;
import mazes.Maze;
import mazes.RoomMaze;

/**
 * A JUnit test class for GameController class.
 * 
 */
public class GameControllerTest {

  @Test
  public void testStartWithMock() throws IOException {
    
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("other\n 3\n 5\n 5\n 3\n 3\n 1\n 1\n 1\n 2\n move\neast\n");
    
    StringBuilder log = new StringBuilder();
    Maze model = new MockModel(log);
    GameController controller = new GameController(in, out);
    controller.start(model);
    assertEquals("1.01.01.01.01.01.01.01.0", log.toString());
    
    
  }
  
  @Test
  public void testStartWithMock2() throws IOException {
    
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("other\n 3\n 5\n 5\n 3\n 3\n 1\n 1\n 1\n 2\n shoot\neast\n2\n");
    
    StringBuilder log = new StringBuilder();
    Maze model = new MockModel(log);
    GameController controller = new GameController(in, out);
    controller.start(model);
    assertEquals("1.01.01.0east21.01.0", log.toString());
    
    
  } 
  
  @Test
  public void testWithMockView() {
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("");
    Maze model = new RoomMaze(5, 5, 3, 3, 3);
    GameController controller = new GameController(in, out);
    
    GameViewInterface mockView = new MockView("5", "5", "3", "3", "3", "1", "0", "0", "2");
    
    controller.start(mockView, model);
    assertEquals(1.0, model.giveEastMove(new Location(new Point(0, 0))).getLocationCoord()
        .getX(), 0);
    
  }
  
  @Test
  public void testMultiPlayerWithMock() throws IOException {
    
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("other\n 3\n 5\n 5\n 3\n 3\n 2\n 1\n "
        + "1\n 3\n 0\n 2\n 3\n move\neast\n");
    
    StringBuilder log = new StringBuilder();
    Maze model = new MockModel(log);
    GameController controller = new GameController(in, out);
    controller.start(model);
    assertEquals("1.01.00.02.01.01.01.01.00.02.0", log.toString());
    
    
  }
  

}
