package data.editor;

import static helpers.Artist.HEIGHT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import data.field.TileGrid;
import data.field.TileType;

public class EditorActionListner {
	private TileGrid _grid;
	private TileType[] _types;
	private boolean _leftMouseButtonDown = false;
	private TileType _ausgewaelt;
	private boolean _run;
	private EditorArtist _art;

	public EditorActionListner(TileGrid grid) {
        _grid = grid;
        _run = true;
        _ausgewaelt = TileType.Grass;
        setTile(TileType.SPAWN, 0, 2);
        buildEditor();
        _art = new EditorArtist(_grid.getTilesWidth(), 0);
	}
	
	public void update() {
		if (Mouse.isButtonDown(0) && !_leftMouseButtonDown)
        {
			//Test
			savemap();
			//
			
            int x = (int) Math.floor(Mouse.getX()) / 64;
            int y = (int) Math.floor((HEIGHT - Mouse.getY() - 1)) / 64;
            
            TileType spawn =  TileType.SPAWN;
            if(y==0) {
            	TileType menu =  TileType.Menu;
            	TileType exit =  TileType.EXIT;
            	
            	if(!menu.equals(_grid.getTileType(x, y)) && !exit.equals(_grid.getTileType(x, y))) {
            		_ausgewaelt = _grid.getTileType(x, y);
            		_art.select(x, y);
            	} else if (exit.equals(_grid.getTileType(x, y))) {
            		_run = false;
            	} else  {
            		
            		
            	}
            	
            } 
            else {
            	System.out.println("x:" + x+ "y:" + y);
            	if(_ausgewaelt.equals(spawn)) {
            		deletespawn();
            	}
            	if(!_grid.getTileType(x, y).equals(spawn)) {
            		setTile();
            	}
            }
            
            
        }
		_art.update();
		_leftMouseButtonDown = Mouse.isButtonDown(0);
	}
	
    public void setTile()
    {
        _grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
                _ausgewaelt);
    }
    
    public void setTile(TileType type, int x, int y) {
        _grid.setTile((int) Math.floor(x),
                (int) Math.floor(y),
                type);
    }
    
    public boolean getrun() {
    	return _run;
    }
    
    public void deletespawn() {
    	System.out.println("Tile");
    	for (int x = 0; x < _grid.getTilesWidth(); x++) {
        	for (int y = 1; y < _grid.getTilesHeight(); y++) {
    			if(_ausgewaelt.equals(_grid.getTileType(x, y))) {
    				setTile(TileType.Dirt, x,y);
    			}
    		}
		}
    }
    
    public void savemap() {
    	 File file = new File("customLevel");
         try
         {
             FileWriter fw = new FileWriter(file.getAbsolutePath());
             PrintWriter pw = new PrintWriter(fw);
             boolean level = true;
         	for (int y = 1; y < _grid.getTilesHeight(); y++) {
            	for (int x = 0; x < _grid.getTilesWidth(); x++) {
            		pw.print(_grid.getTileType(x, y).name()+ "-");
        		}
            	pw.println();
    		}
             pw.close();
         }
         catch (IOException e)
         {
             System.out.println("Err201");
             e.printStackTrace();
         }
    }
    
    public void buildEditor() {
    	for (int i = 0; i < _grid.getTilesWidth(); i++) {
    		setTile(TileType.Menu, i,0);
		}
    	setTile(TileType.Grass ,0,0);
    	setTile(TileType.Dirt ,1,0);
    	setTile(TileType.SPAWN ,2,0);
    	setTile(TileType.Water ,3,0);
    	setTile(TileType.EXIT ,_grid.getTilesWidth()-1,0);
    }

}
