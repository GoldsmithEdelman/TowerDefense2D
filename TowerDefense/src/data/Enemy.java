package data;

import static helpers.Artist.drawRectangleTexture;
import static helpers.Clock.delta;

import org.newdawn.slick.opengl.Texture;

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
    private float _speed;
    private Texture _texture;
    private Tile _startTile;
    private TileGrid _grid;

    private boolean _first = true; //temporary fix for clock settings

    public Enemy(Texture texture, Tile startTile, TileGrid grid, int width,
            int height, float speed)
    {
        this._texture = texture;
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = width;
        this._height = height;
        this._speed = speed;
        this._grid = grid;
    }

    public void update()
    {
        if (_first)
            _first = false; //ignores calculating delta on a first update of the game
        else
        {
            if (pathContinues()) _x += delta() * _speed; //if path continues then move forward
        }
    }

    private boolean pathContinues()
    {
        boolean answer = true;

        Tile currentTile = _grid.getTile((int) (_x / 64), (int) (_y / 64)); // (int) cast removes decimals; /64 takes us to an actual tile
        Tile nextTile = _grid.getTile((int) (_x / 64) + 1, (int) (_y / 64)); //one tile to the right

        if (currentTile.getType() != nextTile.getType()) answer = false;

        return answer;
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

}
