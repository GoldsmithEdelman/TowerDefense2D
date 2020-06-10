package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

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

    public Enemy(Texture texture, Tile startTile, int width, int height,
            float speed)
    {
        this._texture = texture;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = width;
        this._height = height;
        this._speed = speed;
    }

    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, _width, _height);
    }
}
