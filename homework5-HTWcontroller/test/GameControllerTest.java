

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import mazes.Game;
import mazes.GameController;

/**
 * A JUnit test class for GameController class.
 * 
 */
public class GameControllerTest {

  @Test
  public void testStartWithMock() throws IOException {
    
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("room\n 2\n 1\n 1\n 3\n 3\n 3\n 3\n 1\n move\neast\n");
    
    StringBuilder log = new StringBuilder();
    Game model = new MockModel(log);
    GameController controller = new GameController(in, out);
    controller.start(model);
    assertEquals("Input: 1 1 room 3 3 2 3 3 1 east", log.toString());
    
    
  }
  
  @Test
  public void testStartWithMock2() throws IOException {
    
    StringBuffer out = new StringBuffer();
    Reader in = new StringReader("room\n 2\n 1\n 1\n 3\n 3\n 3\n 3\n 1\n shoot\neast\n2\n");
    
    StringBuilder log = new StringBuilder();
    Game model = new MockModel(log);
    GameController controller = new GameController(in, out);
    controller.start(model);
    assertEquals("Input: 1 1 room 3 3 2 3 3 1 east 2", log.toString());
    
    
  }

}
