package data;

import static helpers.Artist.drawRectangleRotatedTexture;
import static helpers.Artist.drawRectangleTexture;
import static helpers.Artist.quickLoadPngTexture;
import static helpers.Clock.delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public class FreezeTower extends TowerCannon {

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
    private ArrayList<Projectile2> _projectiles;
    private ArrayList<Enemy> _enemies;
    private Enemy _target;
    private float _angle;
	private boolean _targeted;
	
	
	public FreezeTower(Texture baseTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		super(baseTexture, startTile, damage, range, enemies);
    	this._range = range;
        this._baseTexture = baseTexture;
        this._cannonTexture = quickLoadPngTexture("Towergun");
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = (int) startTile.getWidth();
        this._height = (int) startTile.getHeight();
        this._damage = damage;
        this._firingSpeed = (float) 3;
        this._timeSinceLastShot = 0;
        this._projectiles = new ArrayList<Projectile2>();
        this._enemies = enemies;
        this._target = getTarget();
        this._angle = getAngle();
        this._targeted = false;
	}
	
    private Enemy getTarget()
    {
        Enemy close = null;
        float distance = 100000;
        for (Enemy e: _enemies) {
        	if(isInRange(e) && findDistance(e)<distance) {
        		distance = findDistance(e);
        		close = e;
        	}
        }
        if(close != null) {
        	_targeted = true;
        }
        return close;
    }
    
    private boolean isInRange(Enemy enemy) {
    	float x = Math.abs(enemy.getX()-_x);
    	float y = Math.abs(enemy.getY()-_y);
    	if(x < _range && y < _range) {
    		return true;
    	}
    	return false;
    }
    
    private float findDistance(Enemy enemy) {
    	float x = Math.abs(enemy.getX()-_x);
    	float y = Math.abs(enemy.getY()-_y);
    	return x +y;
    }

    private float getAngle()
    {
    	if(_target != null) {
            double angleTemp = Math.atan2(_target.getY() - _y, _target.getX() - _x);
            return (float) Math.toDegrees(angleTemp) - 90;
    	}
        return 0;
    }

    private void shoot()
    {
    	if(_target != null) {
        _projectiles.add(new Projectile2(quickLoadPngTexture("Ball"),_target, _x + Game.TILE_SIZE/2 - Game.TILE_SIZE/4,
                _y + Game.TILE_SIZE/2 - Game.TILE_SIZE/4,32,32, 900, _damage));
        _timeSinceLastShot = 0;
    	}
    }
    
    public void updateEnemyLists(ArrayList<Enemy> newList) {
    	_enemies = newList;
    }
    
    public void update()
    {	_target = getTarget();
    	if (!_targeted) {
    		_target = getTarget();
    	}
    	
    	if (_target == null || _target.isAlive() == false)
    	{
    		_targeted = false;
    	}
        _timeSinceLastShot += delta();
        if (_timeSinceLastShot > _firingSpeed) shoot();
        for (Projectile2 projectile : _projectiles)
        {
            projectile.update();
        }
        _angle = getAngle();
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_baseTexture, _x, _y, _width, _height);
        drawRectangleRotatedTexture(_cannonTexture, _x + 28, _y+18, 8, 25,
                _angle);
    }
    
    public float getX() {
    	return _x;
    }
    
    public float getY() {
    	return _y;
    }

}
