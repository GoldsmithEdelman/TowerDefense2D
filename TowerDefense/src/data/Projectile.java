package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public class Projectile
{
    private Texture _texture;
    private float _x;
    private float _y;
    private float _speed;
    private int _damage;

    public Projectile(Texture texture, float x, float y, float speed,
            int damage)
    {
        this._texture = texture;
        this._x = x;
        this._y = y;
        this._speed = speed;
        this._damage = damage;
    }

    public void update()
    {
        _x += delta() * _speed;
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, 32, 32);
    }
}
