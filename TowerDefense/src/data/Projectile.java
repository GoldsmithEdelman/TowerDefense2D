package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public class Projectile
{
    private Texture _texture;
    private float _x;
    private float _y;
    private float _yVel;
    private float _xVel;
    private float _speed;
    private int _damage;
    private Enemy _target;

    public Projectile(Texture texture, Enemy target, float x, float y, float speed,
            int damage)
    {
        this._texture = texture;
        this._x = x;
        this._y = y;
        this._speed = speed;
        this._damage = damage;
        this._target = target;
        this._xVel = 0f;
        this._yVel = 0f;
        getdirection();
    }
    
    public void getdirection() {
    	float totalAllowedMovement = 1.0f;
    	float xDistanceFromTarget = Math.abs(_target.getX()-_x + Game.TILE_SIZE/2 - Game.TILE_SIZE/4); 
    	float yDistanceFromTarget = Math.abs(_target.getY()-_y + Game.TILE_SIZE/2 - Game.TILE_SIZE/4);
    	float totalDistanceFromTarget = xDistanceFromTarget + yDistanceFromTarget;
    	float xPercentOfMovment = xDistanceFromTarget / totalDistanceFromTarget;
    	_xVel = xPercentOfMovment;
    	_yVel = totalAllowedMovement - xPercentOfMovment;
    	if (_target.getX()< _x) {
    		_xVel *=-1;
    	}
    	if (_target.getY()< _y) {
    		_yVel *=-1;
    	}
    	
    	
    }
    
    public void update()
    {
        _x += _xVel * delta() * _speed;
        _y += _yVel * delta() * _speed;
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, 32, 32);
    }
}
