package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;

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

    private boolean _first = true; //temporary fix for clock settings

    public Enemy(Texture texture, Tile startTile, int width, int height,
            float speed)
    {
        this._texture = texture;
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = width;
        this._height = height;
        this._speed = speed;
    }

    public void update()
    {
        if (_first)
            _first = false; //ignores calculating delta on a first update of the game
        else
            _x += delta() * _speed;
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

}
