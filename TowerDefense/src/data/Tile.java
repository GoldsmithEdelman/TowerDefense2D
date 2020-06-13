package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Tile
{
    private float _x;
    private float _y;
    private float _width;
    private float _height;
    private Texture _texture;
    private TileType _type;

    public Tile(float x, float y, float width, float height, TileType type)
    {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
        this._type = type;
        this._texture = quickLoadPngTexture(type._textureName);
    }

    /**
     * drawing tiles faster
     */
    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, _width, _height);
    }

    /**
     * short-cut to create getters and setters
     * Source -> Generate Getters and Setters -> Select All -> Generate
     */

    public float getX()
    {
        return _x;
    }

    public int getXTile()
    {
        return (int) _x / 64;
    }

    public void set_x(float x)
    {
        this._x = x;
    }

    public float getY()
    {
        return _y;
    }

    public int getYTile()
    {
        return (int) _y / 64;
    }

    public void setY(float y)
    {
        this._y = y;
    }

    public float getWidth()
    {
        return _width;
    }

    public void setWidth(float width)
    {
        this._width = width;
    }

    public float getHeight()
    {
        return _height;
    }

    public void setHeight(float height)
    {
        this._height = height;
    }

    public Texture getTexture()
    {
        return _texture;
    }

    public void setTexture(Texture texture)
    {
        this._texture = texture;
    }

    public TileType getType()
    {
        return _type;
    }

    public void setType(TileType type)
    {
        this._type = type;
    }

}
