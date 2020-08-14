package data.test;

import static helpers.Artist.beginSession;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.lwjgl.opengl.Display;

import data.editor.Editor;
import data.field.TileType;
import helpers.Clock;
import helpers.StateManager;
import helpers.StateManager.GameState;

public class EditorTest {
	
	boolean _testlaueft;
	String _aktuellerTest;

	
	@Test
	public void loadTest() {
		_aktuellerTest = "loadTest";
		_testlaueft = true;
		start();
	}
	
	@Test
	public void ausgewaehltTest() {
		_aktuellerTest = "ausgewaehltTest";
		_testlaueft = true;
		start();
	}
	
	@Test
	public void changeTileTest() {
		_aktuellerTest = "changeTileTest";
		_testlaueft = true;
		start();
	}
	
	@Test
	public void saveTest() {
		_aktuellerTest = "saveTest";
		_testlaueft = true;
		start();
		_testlaueft = true;
		start2();
	}
	
	@Test
	public void spawnTest() {
		_aktuellerTest = "spawnTest";
		_testlaueft = true;
		start();
	}
	
	private void start2() {
		beginSession();
        StateManager.setState(GameState.EDITOR);
        StateManager.run();
        while (!Display.isCloseRequested())
        {

            Clock.update(); //always update the clock before drawing enemies
            StateManager.update();
            if ((StateManager.getrun() == false)||_testlaueft == false)
            {
                break;
            }
            loadandsee(StateManager._editor);
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
		
	}
	
	private void loadandsee(Editor ed) {
		assertTrue(ed.getTile(4, 3).equals(TileType.Dirt));
		_testlaueft = false;
	}

	private void start() {
        beginSession();
        StateManager.setState(GameState.EDITOR);
        StateManager.run();
        while (!Display.isCloseRequested())
        {

            Clock.update(); //always update the clock before drawing enemies
            StateManager.update();
            if ((StateManager.getrun() == false)||_testlaueft == false)
            {
                break;
            }
            switchcase();
            Display.update();
            Display.sync(60);
        }
        
        Display.destroy();
        
	}
	
	private void switchcase() {
        switch (_aktuellerTest) {
		case "loadTest":
			runtest(StateManager._editor);
			break;
		case "ausgewaehltTest":
			runtest2(StateManager._editor);
			break;
		case "changeTileTest":
			runtest3(StateManager._editor);
			break;
		case "spawnTest":
			runtest4(StateManager._editor);
			break;
		case "saveTest":
			runtest5(StateManager._editor);
			break;
		default:
			
			break;
		}
	}
	
	
	private void runtest5(Editor ed) {
		//Neutral
		TileType tile= TileType.Grass;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		ed.setTile(4, 3);
		assertTrue(ed.getTile(4, 3).equals(tile));
		
		//Change
		tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		ed.setTile(4, 3);
		assertTrue(ed.getTile(4, 3).equals(tile));
		
		ed.savemap();
		_testlaueft = false;
	}

	private void runtest4(Editor ed) {
		TileType tile= TileType.SPAWN;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		ed.setTile(3, 3);
		assertTrue(ed.getTile(3, 3).equals(tile));
		
		tile= TileType.SPAWN;
		ed.setAusgewaelt(tile);
		ed.setTile(4, 3);
		assertTrue(ed.getTile(4, 3).equals(tile));
		//nur ein Spawn
		assertTrue(!ed.getTile(3, 3).equals(tile));
		//wird zu dirt
		assertTrue(ed.getTile(3, 3).equals(tile.Dirt));
		_testlaueft = false;
	}

	private void runtest3(Editor ed) {
		//Neutral
		TileType tile= TileType.Grass;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		ed.setTile(3, 3);
		assertTrue(ed.getTile(3, 3).equals(tile));
		
		//Change
		tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		ed.setTile(3, 3);
		assertTrue(ed.getTile(3, 3).equals(tile));
		
		//Widerholung
		tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		ed.setTile(3, 3);
		assertTrue(ed.getTile(3, 3).equals(tile));
		
		//Change
		tile= TileType.RED;
		ed.setAusgewaelt(tile);
		ed.setTile(3, 3);
		assertTrue(ed.getTile(3, 3).equals(tile));
		
		//Kann nicht gewechselt werden(Menue Zeile)
		tile= TileType.RED;
		assertFalse(ed.getTile(0, 0).equals(tile));
		ed.setAusgewaelt(tile);
		ed.setTile(3, 3);
		assertFalse(ed.getTile(0, 0).equals(tile));
		_testlaueft = false;
	}

	private void runtest(Editor ed) {
		if (ed.getrun() == true) {
			_testlaueft = false;
		} else {
			fail();
		}
	}
	
	private void runtest2(Editor ed) {
		TileType tile= TileType.Grass;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		 tile= TileType.SPAWN;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		 tile= TileType.RED;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		 tile= TileType.Water;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		 tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		//negativ Test
		 tile= TileType.Menu;
		ed.setAusgewaelt(tile);
		assertFalse(tile.equals(ed.getAusgewaelt()));
		
		 tile= TileType.EXIT;
		ed.setAusgewaelt(tile);
		assertFalse(tile.equals(ed.getAusgewaelt()));
		
		//Wiederholungstest
		 tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		 tile= TileType.Dirt;
		ed.setAusgewaelt(tile);
		assertEquals(ed.getAusgewaelt(), tile);
		
		_testlaueft=false;
	}

	
}
