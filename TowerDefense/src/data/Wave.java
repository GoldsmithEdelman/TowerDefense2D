package data;

import java.util.ArrayList;

import static helpers.Clock.*;

public class Wave
{
    private float _timeSinceLastSpawn;
    private float _spawnTime;
    private Enemy _enemyType;
    private ArrayList<Enemy> _enemyList;

    public Wave(float spawnTime, Enemy enemyType)
    {
        this._enemyType = enemyType;
        this._spawnTime = spawnTime;
        _timeSinceLastSpawn = 0;
        _enemyList = new ArrayList<Enemy>();
    }

    public void update()
    {
        _timeSinceLastSpawn += delta();
        if (_timeSinceLastSpawn > _spawnTime)
        {
            spawn();
            _timeSinceLastSpawn = 0;
        }
        for (Enemy enemy : _enemyList)
        {
            enemy.update();
            enemy.draw();
        }
    }

    private void spawn()
    {
        _enemyList.add(new Enemy(_enemyType.getTexture(),
                _enemyType.getStartTile(), 64, 64, _enemyType.getSpeed()));
    }
}
