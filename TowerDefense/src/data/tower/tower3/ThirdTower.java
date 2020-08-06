package data.tower.tower3;

import static helpers.Artist.drawRectangleRotatedTexture;
import static helpers.Artist.drawRectangleTexture;
import static helpers.Artist.quickLoadPngTexture;
import static helpers.Clock.delta;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import data.enemy.Enemy;
import data.field.Tile;
import data.startup.Game;
import data.tower.TowerBase;

public class ThirdTower implements TowerBase {

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
    private ArrayList<ThirdProjectile> _projectiles;
    private ArrayList<Enemy> _enemies;
    private Enemy _target;
	private boolean _targeted;
	private int _secondshot;
    private int _damageNormal;
    private float _firingSpeedNormal;
    private float _rangeNormal;
    private Texture _normalTexture;
    private Texture _crazyTexture;
	
	
	public ThirdTower(Texture baseTexture,Texture crazyTexture, Tile startTile, int damage, int range, ArrayList<Enemy> enemies) {
		this._normalTexture = baseTexture;
		this._crazyTexture = crazyTexture;
		this._range = range;
        this._baseTexture = baseTexture;
        this._cannonTexture = quickLoadPngTexture("Towergun");
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = (int) startTile.getWidth();
        this._height = (int) startTile.getHeight();
        this._damage = damage;
        this._firingSpeed = (float) 10;
        this._timeSinceLastShot = 0;
        this._projectiles = new ArrayList<ThirdProjectile>();
        this._enemies = enemies;
        this._target = getTarget();
        this._targeted = false;
        this._damageNormal = _damage;
        this._rangeNormal = _range;
        this._firingSpeedNormal = _firingSpeed;
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
        _projectiles.add(new ThirdProjectile(quickLoadPngTexture("Ball"),_target,_enemies, _x + Game.TILE_SIZE/2 - Game.TILE_SIZE/4,
                _y + Game.TILE_SIZE/2 - Game.TILE_SIZE/4,32,32, 300, _damage));
    	}
    }
    
    public void updateEnemyLists(ArrayList<Enemy> newList) {
    	_enemies = newList;
        for (ThirdProjectile e: _projectiles) {
        	e.updateEnemyList(newList);
        }
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
        _timeSinceLastShot += 1;
        if (_timeSinceLastShot > _firingSpeed) {
        	if(_secondshot == 1) {
            	shoot();
        	}
        	if(_secondshot == 5) {
            	shoot();
        	}
        	_secondshot++;
        	
        	if (_secondshot >=200) {
        		_timeSinceLastShot = 0;
        		_secondshot = 0;
        	}
        	
        	}
        for (int i = 0; _projectiles.size() > i;i++)
        {
        	
            float x = _projectiles.get(i).getX();
            float y = _projectiles.get(i).getX();
            if(x-_x > _range*3||-(x-_x) > _range*3||y-_y > _range*3||-(y-_y) > _range*3) {
            	_projectiles.remove(i);
            } else {
            	_projectiles.get(i).update();
            }
        }
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_baseTexture, _x, _y, _width, _height);
    }
    
    public float getX() {
    	return _x;
    }
    
    public float getY() {
    	return _y;
    }

	@Override
	public void crazyMode(boolean mode) {
		if(mode) {
			_firingSpeed = _firingSpeedNormal-2;
			_range = _rangeNormal*2;
			_damage = _damageNormal*2;
			_baseTexture = _crazyTexture;
			
		} else  {
			_damage = _damageNormal;
			_range = _rangeNormal;
			_firingSpeed = _firingSpeedNormal;
			_baseTexture = _normalTexture;
		}
	}

}
