package data.enemy;

import java.awt.List;
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
    private int _ausgleich;
    private ArrayList<Enemy> _enemyTypeList;
    private int[] _enemyIntList;
    private int _customPointer;
    private boolean _customWave = false;
    

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave)
    {
    	this._playerlife = 0;
        this._enemyType = enemyType;
        this._spawnTime = spawnTime;
        this._enemiesPerWave = enemiesPerWave;
        this._timeSinceLastSpawn = 0;
        this._enemyList = new ArrayList<Enemy>();
        this._waveCompleted = false;
        this._ausgleich = 0;
        spawn();
    }
    
    public Wave(ArrayList<Enemy> enemyTypelist, float spawnTime, int[] enemyInt)
    {
    	this._playerlife = 0;
        this._enemyTypeList = enemyTypelist;
        this._spawnTime = spawnTime;
        this._enemiesPerWave = 1;
        this._timeSinceLastSpawn = 0;
        this._enemyList = new ArrayList<Enemy>();
        this._waveCompleted = false;
        this._ausgleich = 0;
        this._enemyIntList = enemyInt;
        this._customPointer = 0;
        this._customWave = true;
        spawn2();
    }
    
    private void spawn2() {
    	if(!(_enemyIntList[_customPointer]==0)) {
    		_enemyType = _enemyTypeList.get(_enemyIntList[_customPointer]-1);
    		_customPointer++;
    		_enemiesPerWave++;
    		spawn();
    		
    	} else {
    		_ausgleich =_enemiesPerWave;
    		
    	}
    }

    public void update()
    {
        boolean enemiesDead = true;
        if(_customWave) {
        	if ((_enemyList.size()) < _enemiesPerWave)
            {
                _timeSinceLastSpawn += delta();
                if (_timeSinceLastSpawn > _spawnTime)
                {
                    spawn2();
                    _timeSinceLastSpawn = 0;
                }
            }
        	
        } else if (_enemyList.size()+_ausgleich < _enemiesPerWave)
        {
            _timeSinceLastSpawn += delta();
            if (_timeSinceLastSpawn > _spawnTime)
            {
                spawn();
                _timeSinceLastSpawn = 0;
            }
        } else if (_customWave) {
        	
        }
        for (int i = 0;  _enemyList.size()>i;i++)
        {
            if (_enemyList.get(i).isAlive())
            {
                enemiesDead = false;
                _enemyList.get(i).update();
                _enemyList.get(i).draw();
                if(_enemyList.get(i).getPlayerDMG()) {
                	_playerlife++;
                }
            } else if (!_enemyList.get(i).isAlive()){
            	_enemyList.remove(_enemyList.get(i));
            	_ausgleich++;
            }
        }
        if (enemiesDead&&_enemyList.size() == 0 && (_ausgleich-_enemiesPerWave)==0 ) {
        	_waveCompleted = true;
        }
        
        //System.out.println(""+_enemyList.size()+enemiesDead+(_ausgleich)+"   "+_enemiesPerWave+"     "+_customPointer);
    }

    private void spawn()
    {
        _enemyList
            .add(new Enemy(_enemyType.getTexture(), _enemyType.getStartTile(),
                    _enemyType.getTileGrid(),_enemyType.getMenu(), 64, 64, _enemyType.getSpeed(), _enemyType.getHealth(), _enemyType.getreward()));
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
