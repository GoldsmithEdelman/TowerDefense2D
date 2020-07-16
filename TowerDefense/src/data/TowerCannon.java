package data;

import static helpers.Artist.*;
import static helpers.Clock.*;

import java.lang.annotation.Target;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class TowerCannon implements TowerBase
{
    private float _range;
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
    private boolean _targeted;

    public TowerCannon(Texture baseTexture, Tile startTile, int damage,
            int range, ArrayList<Enemy> enemies)
    {
        this._range = range;
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
        this._targeted = false;
    }

    private Enemy getTarget()
    {
        Enemy close = null;
        float distance = 100000;
        for (Enemy e : _enemies)
        {
            if (isInRange(e) && findDistance(e) < distance)
            {
                distance = findDistance(e);
                close = e;
            }
        }
        if (close != null)
        {
            _targeted = true;
        }
        return close;
    }

    private boolean isInRange(Enemy enemy)
    {
        float x = Math.abs(enemy.getX() - _x);
        float y = Math.abs(enemy.getY() - _y);
        if (x < _range && y < _range)
        {
            return true;
        }
        return false;
    }

    private float findDistance(Enemy enemy)
    {
        float x = Math.abs(enemy.getX() - _x);
        float y = Math.abs(enemy.getY() - _y);
        return x + y;
    }

    private float getAngle()
    {
        if (_target != null)
        {
            double angleTemp = Math.atan2(_target.getY() - _y,
                    _target.getX() - _x);
            return (float) Math.toDegrees(angleTemp) - 90;
        }
        return 0;
    }

    private void shoot()
    {
        if (_target != null)
        {
            _projectiles.add(new Projectile(quickLoadPngTexture("pokeball"),
                    _target, _x + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4,
                    _y + Game.TILE_SIZE / 2 - Game.TILE_SIZE / 4, 32, 32, 900,
                    10));
            _timeSinceLastShot = 0;
        }
    }

    public void updateEnemyLists(ArrayList<Enemy> newList)
    {
        _enemies = newList;
    }

    public void update()
    {
        if (!_targeted)
        {
            _target = getTarget();
        }

        if (_target == null || _target.isAlive() == false)
        {
            _targeted = false;
        }
        _timeSinceLastShot += delta();
        if (_timeSinceLastShot > _firingSpeed) shoot();
        for (int i = 0; _projectiles.size() > i; i++)
        {

            float x = _projectiles.get(i)
                .getX();
            float y = _projectiles.get(i)
                .getX();
            if (x - _x > _range * 3 || -(x - _x) > _range * 3
                    || y - _y > _range * 3 || -(y - _y) > _range * 3)
            {
                _projectiles.remove(i);
            }
            else
            {
                _projectiles.get(i)
                    .update();
            }
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

    public float getX()
    {
        return _x;
    }

    public float getY()
    {
        return _y;
    }
}
