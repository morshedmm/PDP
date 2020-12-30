

import static org.junit.Assert.assertEquals;

import mazes.Maze;
import mazes.PerfectMaze;
import mazes.Player;
import mazes.RoomMaze;
import mazes.WrappedRoomMaze;
import org.junit.Before;
import org.junit.Test;


/**
 * A JUnit test class for Player class.
 * 
 */
public class PlayerTest {
  
  private Player player;
  private Player player1;
  
  /**
   * Sets up the testing.
   */
  @Before
  public void setUp() {
    Maze myPerfectMaze = new PerfectMaze(2, 2);
    player = new Player(0, 0, myPerfectMaze);
    player1 = new Player(0, 0, 1, 1, myPerfectMaze);
    
    
  }

  @Test
  public void testMoveAll() {
    player.moveAll(2, 2);
    assertEquals("0.0,0.0, Gold: 0.0\n0.0,1.0, Gold: 10.0\n1.0,1.0, Gold: 10.0\n1.0,0.0, "
        + "Gold: 10.0\n", player.toString());
  }
  
  @Test
  public void testMoveAll2() {
    player1.moveAll(2, 2);
    assertEquals("0.0,0.0, Gold: 0.0\n0.0,1.0, Gold: 10.0\n1.0,1.0, "
        + "Gold: 10.0\n", player1.toString());
  }
  
  @Test
  public void testMove() {
    assertEquals(false, player1.move("east"));
    assertEquals(true, player1.move("north"));
    assertEquals(true, player1.move("west"));
    assertEquals(true, player1.move("east"));
    assertEquals(true, player1.move("south"));
    assertEquals(true, player1.move("south"));
  }
  
  @Test
  public void testRoomMazeCreatedWallsCount() {
    assertEquals("10", new RoomMaze(3, 3, 2).toString());
  }
  
  @Test
  public void testWrappedRoomMazeCreatedWallsCount() {
    assertEquals("16", new WrappedRoomMaze(3, 3, 2).toString());
  }
  
  @Test
  public void testPerfectMazeCreatedWallsCount() {
    assertEquals("8", new PerfectMaze(3, 3).toString());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord1() {
    Maze myPerfectMaze = new PerfectMaze(2, 2);
    new Player(-1, 0, myPerfectMaze);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord2() {
    Maze myPerfectMaze = new PerfectMaze(2, 2);
    new Player(0, -1, myPerfectMaze);
  }
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord3() {
    Maze myPerfectMaze = new PerfectMaze(2, 2);
    new Player(0, 0, -1, 0, myPerfectMaze);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidCoord4() {
    Maze myPerfectMaze = new PerfectMaze(2, 2);
    new Player(0, 0, 0, -1, myPerfectMaze);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMove() {
    player1.move("left");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMoveAll() {
    player1.moveAll(-1, 0);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIfInvalidMoveAll2() {
    player1.moveAll(0, -1);
  }

}
