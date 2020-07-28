package data.enemy;

import static helpers.Artist.drawRectangleTexture;
import static helpers.Clock.delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import data.field.Tile;
import data.field.TileGrid;
import data.player.Menu;

/**
 * 
 * Getting enemies on the screen 
 * @author 
 *
 */
public class Enemy
{
    private float _x;
    private float _y;
    private int _width;
    private int _height;
    private int _health;
    private int _currentCheckpoint;
    private float _speed;
    private Texture _texture;
    private Tile _startTile;
    private TileGrid _grid;
    private boolean _first = true; //temporary fix for clock settings
    private boolean _alive = true;
    private boolean _playerdmg = false;
    private Menu _menu;
    private int _reward;

    private ArrayList<CheckPoint> _checkpoints;
    private int[] _directions;

    public Enemy(Texture texture, Tile startTile, TileGrid grid, Menu menu,int width,
            int height, float speed, int health, int reward)
    {
    	this._reward = reward;
    	this._menu = menu;
        this._texture = texture;
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = width;
        this._height = height;
        this._speed = speed;
        this._grid = grid;
        this._health = health;
        this._checkpoints = new ArrayList<CheckPoint>();
        this._directions = new int[2];
        this._directions[0] = 0; // x direction
        this._directions[1] = 0; // y direction
        _directions = findNextDirection(_startTile);
        this._currentCheckpoint = 0;
        populateCheckpointList();
    }

    public void update()
    {
        if (_first)
            _first = false; //ignores calculating delta on a first update of the game
        else
        {
            //            to be deleted
            //            if (pathContinues()) _x += delta() * _speed; //if path continues then move forward
            if (checkpointReached())
            {
                if (_currentCheckpoint + 1 == _checkpoints.size()) {
                	
                
                    death();
                    _playerdmg = true;
                }
                else
                    _currentCheckpoint++; //if check point reached move to the next one
            }
            else
            {
                _x += delta() * _checkpoints.get(_currentCheckpoint)
                    .getX() * _speed;
                _y += delta() * _checkpoints.get(_currentCheckpoint)
                    .getY() * _speed;
            }
        }
        // to be deleted
        //
        //            _x += delta() * _directions[0] * _speed;
        //            _y += delta() * _directions[1] * _speed;
    }

    private boolean checkpointReached()
    {
        boolean reached = false;

        Tile tile = _checkpoints.get(_currentCheckpoint)
            .getTile();
        // check if position reached a tile within a variance of 3 (arbitrary) 
        if (_x > tile.getX() - 3 && _x < tile.getX() + 3 && _y > tile.getY() - 3
                && _y < tile.getY() + 3)
        {
            reached = true;
            _x = tile.getX();
            _y = tile.getY();
        }

        return reached;
    }

    private void populateCheckpointList()
    {
        _directions = findNextDirection(_startTile);
        _checkpoints.add(findNextCheckpoint(_startTile, _directions));

        int counter = 0;
        boolean carryon = true;
        while (carryon)
        {
            int[] currentDirection = findNextDirection(_checkpoints.get(counter)
                .getTile());
            // check if a next direction/checkpoint exists, end after 20 checkpoints (arbitrary) 
            if (currentDirection[0] == 2 || counter == 20)
            {
                carryon = false;
            }
            else
            {
                _directions = findNextDirection(_checkpoints.get(counter)
                    .getTile());
                _checkpoints.add(findNextCheckpoint(_checkpoints.get(counter)
                    .getTile(), _directions));
            }
            counter++;
        }
    }

    private CheckPoint findNextCheckpoint(Tile start, int[] direction)
    {
        Tile next = null;
        CheckPoint checkpoint = null;

        boolean found = false;
        int counter = 1;
        // keep going in the same direction if the tiles are the same type 
        while (!found)
        {
            if (start.getXTile()
                    + _directions[0] * counter == _grid.getTilesWidth()
                    || start.getYTile() + _directions[1] * counter == _grid
                        .getTilesHeight()
                    || start.getType() != _grid
                        .getTile(start.getXTile() + _directions[0] * counter,
                                start.getYTile() + _directions[1] * counter)
                        .getType())
            {
                found = true; // checkpoint found
                counter -= 1; // decrement the counter to find tile before new tiletype
                next = _grid.getTile(
                        start.getXTile() + _directions[0] * counter,
                        start.getYTile() + _directions[1] * counter);
            }
            counter++;
        }
        checkpoint = new CheckPoint(next, _directions[0], _directions[1]);
        return checkpoint;
    }

    /**
     * what direction to go next
     *  
     * @param start
     * @return
     */
    private int[] findNextDirection(Tile start)
    {
        int[] direction = new int[2];
        Tile aboveTile = _grid.getTile(start.getXTile(), start.getYTile() - 1); // above ->  -1
        Tile rightTile = _grid.getTile(start.getXTile() + 1, start.getYTile());
        Tile downTile = _grid.getTile(start.getXTile(), start.getYTile() + 1);
        Tile leftTile = _grid.getTile(start.getXTile() - 1, start.getYTile());

        if (start.getType() == aboveTile.getType() && _directions[1] != 1)
        {
            direction[0] = 0;
            direction[1] = -1;
        }
        else if (start.getType() == rightTile.getType() && _directions[0] != -1)
        {
            direction[0] = 1;
            direction[1] = 0;
        }
        else if (start.getType() == leftTile.getType() && _directions[0] != 1)
        {
            direction[0] = -1;
            direction[1] = 0;
        }
        else if (start.getType() == downTile.getType() && _directions[1] != -1)
        {
            direction[0] = 0;
            direction[1] = 1;
        }
        else
        {
            direction[0] = 2;
            direction[1] = 2;
        }

        return direction;
    }

    //    to be deleted
    //    private boolean pathContinues()
    //    {
    //        boolean answer = true;
    //
    //        Tile currentTile = _grid.getTile((int) (_x / 64), (int) (_y / 64)); // (int) cast removes decimals; /64 takes us to an actual tile
    //        Tile nextTile = _grid.getTile((int) (_x / 64) + 1, (int) (_y / 64)); //one tile to the right
    //
    //        if (currentTile.getType() != nextTile.getType()) answer = false;
    //
    //        return answer;
    //    }

    private void death()
    {
        _alive = false;
        _menu.addmoney(_reward);
    }

    /**
     * show the stuff on the screen
     */
    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, _width, _height);
    }

    public float getX()
    {
        return _x;
    }

    public void setX(float x)
    {
        this._x = x;
    }

    public float getY()
    {
        return _y;
    }

    public void setY(float y)
    {
        this._y = y;
    }

    public int getWidth()
    {
        return _width;
    }

    public void setWidth(int width)
    {
        this._width = width;
    }

    public int getHeight()
    {
        return _height;
    }

    public void setHeight(int height)
    {
        this._height = height;
    }

    public int getHealth()
    {
        return _health;
    }

    public void setHealth(int health)
    {
        this._health = health;
    }

    public float getSpeed()
    {
        return _speed;
    }

    public void setSpeed(float speed)
    {
        this._speed = speed;
    }

    public Texture getTexture()
    {
        return _texture;
    }

    public void setTexture(Texture texture)
    {
        this._texture = texture;
    }

    public Tile getStartTile()
    {
        return _startTile;
    }

    public void setStartTile(Tile startTile)
    {
        this._startTile = startTile;
    }

    public boolean isFirst()
    {
        return _first;
    }

    public void setFirst(boolean first)
    {
        this._first = first;
    }

    public TileGrid getTileGrid()
    {
        return _grid;
    }

    public boolean isAlive()
    {
        return _alive;
    }
    
    public void takedamage(int amount) {
    	_health = _health - amount;
    	if (_health<=0) {
    		death();
    	}
    }
    
    public boolean getPlayerDMG() {
    	return _playerdmg;
    }
    
    public int getreward(){
    	return _reward;
    }
    
    public Menu getMenu() {
    	return _menu;
    }
}
