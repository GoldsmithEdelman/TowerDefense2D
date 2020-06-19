package data;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;

public class Player
{
    private TileGrid _grid;
    private TileType[] _types;
    private int _index;
    private WaveManager _waveManager;
    private ArrayList<TowerCannon> _towerList;
    private boolean _leftMouseButtonDown = false;

    public Player(TileGrid grid, WaveManager waveManager)
    {
        this._grid = grid;
        this._types = new TileType[3];
        this._types[0] = TileType.Dirt;
        this._types[1] = TileType.Grass;
        this._types[2] = TileType.Water;
        this._index = 0;

        this._waveManager = waveManager;
        this._towerList = new ArrayList<TowerCannon>();

    }

    /**
     * input from the mouse
     */

    public void setTile()
    {
        _grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
                _types[_index]);
    }

    
    public boolean isTaken(float x, float y) {
    	for (int i = 0; i < _towerList.size(); i++) {
    		TowerCannon tower = _towerList.get(i);
			if (tower.getX() == x && tower.getY() == y) {
				return true;
			}
		}
    	return false;
    }

    /**
     * updates on click or press 
     */

    public void update()
    {
        for (TowerCannon tower : _towerList)
        {
            tower.update();
            tower.updateEnemyLists(_waveManager.getCurrentWave().getEnemies());
        }
        
        
        

        // mouse input
        if (Mouse.isButtonDown(0) && !_leftMouseButtonDown)
        {
        	int x = (int) Math.floor(Mouse.getX())/64;
        	int y = (int) Math.floor((HEIGHT - Mouse.getY() - 1))/64;
        	if(_grid.getTileType(x, y).equals(TileType.Grass)) {
        		if(!isTaken( x*64, y*64)) {
                	_towerList.add(new TowerCannon(
                            quickLoadPngTexture("cannonbase"), _grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY()-1)/64),10,
                            1000, _waveManager.getCurrentWave()
                                .getEnemies()));
        		}
        	}

            //setTile();
        }
        
        _leftMouseButtonDown = Mouse.isButtonDown(0);
        
        
        // keyboard input
        while (Keyboard.next())
        {
            if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT
                    && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
            {
                Clock.changeMultiplier(0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_LEFT
                    && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
            {
            	Clock.changeMultiplier(-0.2f);
            }
            if (Keyboard.getEventKey() == Keyboard.KEY_T
                    && Keyboard.getEventKeyState())
            {
            	setTile();
            }
        }
    }

    private void moveIndex()
    {
        _index++;
        if (_index > _types.length - 1)
        {
            _index = 0;
        }
    }

}
