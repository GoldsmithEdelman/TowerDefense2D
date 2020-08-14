package data.tower.bank;

import static helpers.Artist.drawRectangleTexture;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import data.enemy.Enemy;
import data.field.Tile;
import data.player.Menu;
import data.tower.TowerBase;

public class Bank implements TowerBase
{

    private float _x;
    private float _y;
    private int _width;
    private int _height;
    private Texture _baseTexture;
    @SuppressWarnings("unused")
    private Tile _startTile;
    private ArrayList<Enemy> _enemies;
    private Menu _menu;
    private float _moneyPerEnemeyDead;
    private ArrayList<Enemy> _allenemies;
    private float _moneyPerEnemeyDeadNormal;
    private Texture _normalTexture;
    private Texture _crazyTexture;

    public Bank(Texture baseTexture, Texture crazyTexture, Tile startTile,
            float moneyPerEnemeyDead, ArrayList<Enemy> enemies, Menu menu)
    {
        this._normalTexture = baseTexture;
        this._crazyTexture = crazyTexture;
        this._allenemies = new ArrayList<Enemy>();
        this._menu = menu;
        this._moneyPerEnemeyDead = moneyPerEnemeyDead;
        this._baseTexture = baseTexture;
        this._startTile = startTile;
        this._x = startTile.getX();
        this._y = startTile.getY();
        this._width = (int) startTile.getWidth();
        this._height = (int) startTile.getHeight();
        this._enemies = enemies;
        this._moneyPerEnemeyDeadNormal = _moneyPerEnemeyDead;
    }

    public void updateEnemyLists(ArrayList<Enemy> newList)
    {

        _enemies = newList;
        for (Enemy e : _enemies)
        {
            if (!_allenemies.contains(e) && e.isAlive())
            {
                _allenemies.add(e);
            }
        }
        for (int i = 0; i < _allenemies.size(); i++)
        {
            Enemy enemy = _allenemies.get(i);
            if (!enemy.isAlive())
            {
                _menu.addmoney((int) _moneyPerEnemeyDead);
                _allenemies.remove(i);
            }
        }
    }

    public void update()
    {
        draw();
    }

    public void draw()
    {
        drawRectangleTexture(_baseTexture, _x, _y, _width, _height);
    }

    public float getX()
    {
        return _x;
    }

    public float getY()
    {
        return _y;
    }

    @Override
    public void crazyMode(boolean mode)
    {
        if (mode)
        {
            _moneyPerEnemeyDead = _moneyPerEnemeyDead * 5;
            _baseTexture = _crazyTexture;
        }
        else
        {
            _moneyPerEnemeyDead = _moneyPerEnemeyDeadNormal;
            _baseTexture = _normalTexture;
        }
    }
}
