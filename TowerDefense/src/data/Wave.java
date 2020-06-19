package data;

import java.util.ArrayList;

import static helpers.Clock.*;

public class Wave
{
    private float _timeSinceLastSpawn;
    private float _spawnTime;
    private Enemy _enemyType;
    private ArrayList<Enemy> _enemyList;
    private int _enemiesPerWave;
    private boolean _waveCompleted;
    private int _playerlife;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave)
    {
    	this._playerlife = 0;
        this._enemyType = enemyType;
        this._spawnTime = spawnTime;
        this._enemiesPerWave = enemiesPerWave;
        this._timeSinceLastSpawn = 0;
        this._enemyList = new ArrayList<Enemy>();
        this._waveCompleted = false;

        spawn();
    }

    public void update()
    {
        boolean enemiesDead = true;
        if (_enemyList.size() < _enemiesPerWave)
        {
            _timeSinceLastSpawn += delta();
            if (_timeSinceLastSpawn > _spawnTime)
            {
                spawn();
                _timeSinceLastSpawn = 0;
            }
        }
        for (Enemy enemy : _enemyList)
        {
            if (enemy.isAlive())
            {
                enemiesDead = false;
                enemy.update();
                enemy.draw();
                if(enemy.getPlayerDMG()) {
                	_playerlife++;
                }
            }
        }
        if (enemiesDead) _waveCompleted = true;
    }

    private void spawn()
    {
        _enemyList
            .add(new Enemy(_enemyType.getTexture(), _enemyType.getStartTile(),
                    _enemyType.getTileGrid(), 64, 64, _enemyType.getSpeed(), _enemyType.getHealth()));
    }

    public boolean isWaveCompleted()
    {
        return _waveCompleted;
    }

    public ArrayList<Enemy> getEnemies()
    {
        return _enemyList;
    }
    
    public int getPlayerlife() {
    	int x = _playerlife;
    	_playerlife = 0;
    	return x;
    }
}
