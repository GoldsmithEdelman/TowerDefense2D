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
    private ArrayList<TowerBase> _towerList;
    private boolean _leftMouseButtonDown = false;
    private int _playerhealth;
    private Menu _menu;

    public Player(TileGrid grid, WaveManager waveManager,int health, Menu menu)
    {
    	_playerhealth = health;
        this._grid = grid;
        this._types = new TileType[4];
        this._types[0] = TileType.Dirt;
        this._types[1] = TileType.Grass;
        this._types[2] = TileType.Water;
        this._types[3] = TileType.Menu;
        this._index = 0;
        this._menu = menu;
        this._waveManager = waveManager;
        this._towerList = new ArrayList<TowerBase>();

    }
    
    
    
    
    

    public int getPlayerhealth() {
		return _playerhealth;
	}






	public void setPlayerhealth() {
		this._playerhealth = _playerhealth -1;
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
    		TowerBase tower = _towerList.get(i);
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
        for (TowerBase tower : _towerList)
        {
            tower.update();
            tower.updateEnemyLists(_waveManager.getCurrentWave().getEnemies());
        }
        
        
        _playerhealth-= _waveManager.getCurrentWave().getPlayerlife();

        // mouse input
        if (Mouse.isButtonDown(0) && !_leftMouseButtonDown)
        {
        	int x = (int) Math.floor(Mouse.getX())/64;
        	int y = (int) Math.floor((HEIGHT - Mouse.getY() - 1))/64;
        	if(_grid.getTileType(x, y).equals(TileType.Grass)) {
        		if(!isTaken( x*64, y*64)) {
        			int t1 = _menu.getPointer();
        			switch(t1) {
        				case 1:
        					addCannon();
        					break;
        				case 2:
        					addFrezeCannon();
        			}
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
            
            //TowerCannon
            if(Keyboard.getEventKey() == Keyboard.KEY_1) {
            	_menu.setPointer(1);
            }
            //???Tower
            if(Keyboard.getEventKey() == Keyboard.KEY_2) {
            	_menu.setPointer(2);
            }
            //DeleteTower
            if(Keyboard.getEventKey() == Keyboard.KEY_0) {
            	_menu.setPointer(10);
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
    
    private void addCannon() {
    	_towerList.add(new TowerCannon(
                quickLoadPngTexture("cannonbase"), _grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY()-1)/64),10,
                1000, _waveManager.getCurrentWave()
                    .getEnemies()));
    }
    
    private void addFrezeCannon() {
    	_towerList.add(new FreezeTower(
                quickLoadPngTexture("Tower2"), _grid.getTile(Mouse.getX() / 64, (HEIGHT - Mouse.getY()-1)/64),0,
                64*3, _waveManager.getCurrentWave()
                    .getEnemies()));
    }

}
