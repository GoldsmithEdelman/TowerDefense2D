package data.player;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import data.enemy.WaveManager;
import data.field.TileGrid;
import data.field.TileType;
import data.tower.TowerBase;
import data.tower.bank.Bank;
import data.tower.tower1.TowerCannon;
import data.tower.tower2.FreezeTower;
import data.tower.tower3.ThirdTower;
import data.tower.tower5.Tower5;
import helpers.Clock;

import static helpers.Artist.*;

import java.util.ArrayList;
import java.util.Iterator;

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
    private int thirdcost;
    private ArrayList<CrazyMode> _crazyList;
    private boolean _pause = true;
    public Player(TileGrid grid, WaveManager waveManager, int health, Menu menu)
    {
    	_crazyList = new ArrayList<CrazyMode>();
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
        thirdcost = 100;
    }

    public int getPlayerhealth()
    {
        return _playerhealth;
    }

    public void setPlayerhealth()
    {
        this._playerhealth = _playerhealth - 1;
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

    public boolean isTaken(float x, float y)
    {
        for (int i = 0; i < _towerList.size(); i++)
        {
            TowerBase tower = _towerList.get(i);
            if (tower.getX() == x && tower.getY() == y)
            {
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
    	//function/drawing of Towers
        for (TowerBase tower : _towerList)
        {
            tower.update();
            tower.updateEnemyLists(_waveManager.getCurrentWave()
                .getEnemies());
        }

        //Player Health Update
        _playerhealth -= _waveManager.getCurrentWave()
            .getDamageToPlayer();

        mouseInput();

        keyBoardInput();
        
    }

    private void drawCrazyMode() {
		for (CrazyMode e: _crazyList) {
			setTile(TileType.CRAZY ,e.getX(),e.getY());
		}
		
	}
    public void setTile(TileType type, int x, int y) {
        _grid.setTile((int) Math.floor(x),
                (int) Math.floor(y),
                type);
    }

	private void mouseInput() {
        if (Mouse.isButtonDown(0) && !_leftMouseButtonDown)
        {
            int x = (int) Math.floor(Mouse.getX()) / 64;
            int y = (int) Math.floor((HEIGHT - Mouse.getY() - 1)) / 64;
            if (_grid.getTileType(x, y)
                .equals(TileType.Grass)||_grid.getTileType(x, y)
                .equals(TileType.RED))
            {
                String t1 = _menu.getPointer();
                if (!isTaken(x * 64, y * 64))
                {
                    switch (t1)
                    {
                    case "first":
                        if (_menu.getmoney() >= _menu.getkosten())
                        {
                            addCannon();
                            _menu.submoney(_menu.getkosten());
                        }
                        break;
                    case "second":
                        if (_menu.getmoney() >= _menu.getkosten())
                        {
                            addFrezeCannon();
                            _menu.submoney(_menu.getkosten());
                        }
                        break;
                    case "third":
                    	 if (_menu.getmoney() >= thirdcost && _grid.getTileType(x, y)
                                 .equals(TileType.RED))
                         {
                    		 addThirdTower();
                             _menu.submoney(thirdcost);
                             thirdcost = (int) (thirdcost * 3);
                             _menu.updateThirdMoney(thirdcost);
                         }
                    	 break;
                    case "bank":
                    	if(_menu.getmoney() >= _menu.getkosten()) {
                    		addBank();
                    		_menu.submoney(_menu.getkosten());
                    	}
                    	break;
                    case "tower5":
                        if (_menu.getmoney() >= _menu.getkosten())
                        {
                            addtower5();
                            _menu.submoney(_menu.getkosten());
                        }
                        break;
                    }
                    
                }
                else if (isTaken(x * 64, y * 64))
                {
                    switch (t1)
                    {
                    case "delete":
                        deleteTower(x, y);
                        break;
                    case "crazy":
                    	if(!carzyModeCheck(x, y)&& _menu.getmoney() >= _menu.getkosten()) {
                    		crazyMode(x,y);
                    		_menu.submoney(_menu.getkosten());
                    	}
                    	break;
                    }
                }
            }
        }
        _leftMouseButtonDown = Mouse.isButtonDown(0);
    }
    
    private void crazyMode(int x, int y) {
        for (int i = 0; i < _towerList.size(); i++)
        {
            TowerBase tower = _towerList.get(i);
            if (tower.getX() == (x * 64) && tower.getY() == (y * 64))
            {
                _towerList.get(i).crazyMode(true);
                _crazyList.add(new CrazyMode(x, y));
                break;
            }
        }
	}
    
    private boolean carzyModeCheck(int x,int y) {
    	for (int i = 0; i < _crazyList.size(); i++) {
    		if(_crazyList.get(i).getX() == x && _crazyList.get(i).getY() == y) {
    			return true;
    		}
		}
    	return false;
    }

	private void keyBoardInput() {
        while (Keyboard.next())
        {
        	specialKeyBoardInput();
        	normalKeyBoardInput();
        }
    }
    
    private void specialKeyBoardInput(){
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
        if (Keyboard.getEventKey() == Keyboard.KEY_UP
                && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
        {
            Clock.resume();
            _pause = true;
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_DOWN
                && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
        {
            Clock.stop();
            _pause = false;
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE
                && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
        {
            _playerhealth = 0;
        }
        if (Keyboard.getEventKey() == Keyboard.KEY_SPACE
                && Keyboard.getEventKeyState()) //Keyboard.getEventKeyState() gives one per press
        {
            if(_pause) {
            	Clock.stop();
            	_pause = false;
            } else {
            	Clock.resume();
            	_pause = true;
            }
        }
        //Zum Test
        if (Keyboard.getEventKey() == Keyboard.KEY_T
                && Keyboard.getEventKeyState())
        {
            setTile();
        }
    }
    
    //number Input
    private void normalKeyBoardInput() {
        //TowerCannon
        if (Keyboard.getEventKey() == Keyboard.KEY_1)
        {
            _menu.setPointer("first");
        }
        //???Tower
         if (Keyboard.getEventKey() == Keyboard.KEY_2)
        {
            _menu.setPointer("second");
        }
         if (Keyboard.getEventKey() == Keyboard.KEY_3)
        {
            _menu.setPointer("third");
        }
         if (Keyboard.getEventKey() == Keyboard.KEY_4)
        {
            _menu.setPointer("bank");
        }
         if (Keyboard.getEventKey() == Keyboard.KEY_5)
        {
            _menu.setPointer("tower5");
        }
        //DeleteTower
         if (Keyboard.getEventKey() == Keyboard.KEY_0)
        {
            _menu.setPointer("delete");
        }
         
         if (Keyboard.getEventKey() == Keyboard.KEY_9)
        {
            _menu.setPointer("crazy");
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

    private void addCannon()
    {
        _towerList.add(new TowerCannon(quickLoadPngTexture("labyr"),quickLoadPngTexture("ctower1"),
                _grid.getTile(Mouse.getX() / 64,
                        (HEIGHT - Mouse.getY() - 1) / 64),
                10, 1000, _waveManager.getCurrentWave()
                    .getEnemies()));
    }

    private void addFrezeCannon()
    {
        _towerList.add(new FreezeTower(quickLoadPngTexture("Tower2"),quickLoadPngTexture("ctower2"),
                _grid.getTile(Mouse.getX() / 64,
                        (HEIGHT - Mouse.getY() - 1) / 64),
                0, 64 * 3, _waveManager.getCurrentWave()
                    .getEnemies()));
    }
    
    private void addThirdTower()
    {
        _towerList.add(new ThirdTower(quickLoadPngTexture("Turm3"),quickLoadPngTexture("ctower3"),
                _grid.getTile(Mouse.getX() / 64,
                        (HEIGHT - Mouse.getY() - 1) / 64),
                100, 64 * 20, _waveManager.getCurrentWave()
                    .getEnemies()));
    }
    
    private void addBank() {
    	_towerList.add(new Bank(quickLoadPngTexture("bank"),quickLoadPngTexture("ctower4"),  _grid.getTile(Mouse.getX() / 64,
                (HEIGHT - Mouse.getY() - 1) / 64), 1,  _waveManager.getCurrentWave()
                .getEnemies(), _menu));
    }
    
    private void addtower5()
    {
        _towerList.add(new Tower5(quickLoadPngTexture("tower4"),quickLoadPngTexture("ctower5"),
                _grid.getTile(Mouse.getX() / 64,
                        (HEIGHT - Mouse.getY() - 1) / 64),
                12, 64 * 5, _waveManager.getCurrentWave()
                    .getEnemies()));
    }

    private void deleteTower(float x, float y)
    {
        for (int i = 0; i < _towerList.size(); i++)
        {
            TowerBase tower = _towerList.get(i);
            if (tower.getX() == (x * 64) && tower.getY() == (y * 64))
            {
                _towerList.remove(i);
                for (CrazyMode c: _crazyList) {
                	if (c.getX()==x&&c.getY()==y) {
                		_crazyList.remove(c);
                		break;
                	}
				}
                break;
            }
        }
    }

}
