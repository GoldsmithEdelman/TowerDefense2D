package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon
{
    private float _x;
    private float _y;
    private float _timeSinceLastShot;
    private float _firingSpeed;
    private int _width;
    private int _height;
    private int _damage;
    private Texture _baseTexture;
    private Texture _cannonTexture;
    private Tile _startTile;
    private ArrayList<Projectile> _projectiles;
    private ArrayList<Enemy> _enemies;
    private Enemy _target;
    private float _angle;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage,
            ArrayList<Enemy> enemies)
    {
        this._baseTexture = baseTexture;
        this._cannonTexture = quickLoadPngTexture("cannongun");
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = (int) startTile.getWidth();
        this._height = (int) startTile.getHeight();
        this._damage = damage;
        this._firingSpeed = (float) 3;
        this._timeSinceLastShot = 0;
        this._projectiles = new ArrayList<Projectile>();
        this._enemies = enemies;
        this._target = getTarget();
        this._angle = getAngle();
    }

    private Enemy getTarget()
    {
        return _enemies.get(0);
    }

    private float getAngle()
    {
        double angleTemp = Math.atan2(_target.getY() - _y, _target.getX() - _x);
        return (float) Math.toDegrees(angleTemp) - 90;
    }

    private void shoot()
    {
        _projectiles.add(new Projectile(quickLoadPngTexture("bullet"),_target, _x + Game.TILE_SIZE/2 - Game.TILE_SIZE/4,
                _y + Game.TILE_SIZE/2 - Game.TILE_SIZE/4, 900, 10));
        _timeSinceLastShot = 0;

    }

    public void update()
    {
        _timeSinceLastShot += delta();
        if (_timeSinceLastShot > _firingSpeed) shoot();
        for (Projectile projectile : _projectiles)
        {
            projectile.update();
        }
        _angle = getAngle();
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_baseTexture, _x, _y, _width, _height);
        drawRectangleRotatedTexture(_cannonTexture, _x + 32, _y, 10, 50,
                _angle);
    }
}
