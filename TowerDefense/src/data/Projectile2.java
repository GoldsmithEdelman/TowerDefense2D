package data;

import org.newdawn.slick.opengl.Texture;
import static helpers.Clock.*;
import static helpers.Artist.*;

public class Projectile2
{
    private Texture _texture;
    private float _x;
    private float _y;
    private float _yVel;
    private float _xVel;
    private float _width;
    private float _height;
    private float _speed;
    private int _damage;
    private Enemy _target;
    private boolean _alive;

    public Projectile2(Texture texture, Enemy target, float x, float y, float width, float height, float speed,
            int damage)
    {
    	this._height = height;
    	this._width = width;
        this._texture = texture;
        this._x = x;
        this._y = y;
        this._speed = speed;
        this._damage = damage;
        this._target = target;
        this._xVel = 0f;
        this._yVel = 0f;
        this._alive = true;
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
    	if(_alive) {
    		
    		if(CheckCollision(_x, _y, _width, _height, _target.getX(), _target.getY(), _target.getWidth(), _target.getHeight()) 
    				&& _target.isAlive()){
    			_target.takedamage(_damage);
    			_alive = false;
    			float speed = _target.getSpeed();
    			_target.setSpeed(speed*0.9f);
    		}
            _x += _xVel * delta() * _speed;
            _y += _yVel * delta() * _speed;
            draw();
            
    	}
    }

    public void draw()
    {
        drawRectangleTexture(_texture, _x, _y, 32, 32);
    }
}
