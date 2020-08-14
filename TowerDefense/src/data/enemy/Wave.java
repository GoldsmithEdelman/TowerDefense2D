package data.enemy;

import static helpers.Clock.delta;

import java.util.ArrayList;

public class Wave
{
    private float _timeSinceLastSpawn;
    private float _spawnTime;
    private Enemy _enemyType;
    private ArrayList<Enemy> _enemyList;
    private boolean _waveCompleted;
    private boolean _customWave = false;
    private int _damageToPlayer;
    private ArrayList<Enemy> _enemyTypeList;
    private int[] _enemyIntList;
    private int _nummberOfEnemys;
    private int _deadEnemys;
    private int _EnemyNummber;

    public Wave(Enemy enemyType, float spawnTime, int enemiesPerWave)
    {
        this._damageToPlayer = 0;
        this._enemyType = enemyType;
        this._spawnTime = spawnTime;
        this._nummberOfEnemys = enemiesPerWave;
        this._timeSinceLastSpawn = 0;
        this._enemyList = new ArrayList<Enemy>();
        this._waveCompleted = false;
        spawn();
    }

    public Wave(ArrayList<Enemy> enemyTypelist, float spawnTime, int[] enemyInt)
    {
        this._damageToPlayer = 0;
        this._enemyTypeList = enemyTypelist;
        this._spawnTime = spawnTime;
        this._timeSinceLastSpawn = 0;
        this._enemyList = new ArrayList<Enemy>();
        this._waveCompleted = false;
        this._enemyIntList = enemyInt;
        this._customWave = true;
        this._nummberOfEnemys = enemyInt.length;
        spawn2();
    }

    private void spawnNewEnemy()
    {
        if (_customWave)
        {
            if (_EnemyNummber < _nummberOfEnemys)
            {
                _timeSinceLastSpawn += delta();
                if (_timeSinceLastSpawn > _spawnTime)
                {
                    spawn2();
                    _timeSinceLastSpawn = 0;
                }
            }

        }
        else if ((_EnemyNummber < _nummberOfEnemys - 1) && !_customWave)
        {
            _timeSinceLastSpawn += delta();
            if (_timeSinceLastSpawn > _spawnTime)
            {
                spawn();
                _timeSinceLastSpawn = 0;
                _EnemyNummber++;
            }
        }
    }

    private void spawn()
    {
        _enemyList.add(new Enemy(_enemyType.getTexture(),
                _enemyType.getStartTile(), _enemyType.getTileGrid(),
                _enemyType.getMenu(), 64, 64, _enemyType.getSpeed(),
                _enemyType.getHealth(), _enemyType.getreward(),
                _enemyType.getDMG(), _enemyType.getCanBeSlowed()));
    }

    private void spawn2()
    {
        if (_EnemyNummber < _nummberOfEnemys)
        {
            System.out.println("_nummberOfEnemys:  " + _nummberOfEnemys);
            System.out.println("_EnemyNummber:  " + _EnemyNummber);
            System.out
                .println("_enemyIntList.length:  " + _enemyIntList.length);
            _enemyType = _enemyTypeList.get(_enemyIntList[_EnemyNummber] - 1);
            System.out.println("W");
            _EnemyNummber++;
            spawn();

        }
    }

    public void update()
    {
        //erzeugt einen neuen Feind
        spawnNewEnemy();

        //Update Drawing/Status
        updateEnemysStatus();

        //Wave Zuende???
        if ((_nummberOfEnemys == _deadEnemys) && _customWave)
        {
            _waveCompleted = true;
        }
        else if (!_customWave && (_deadEnemys == _nummberOfEnemys))
        {
            _waveCompleted = true;
        }
        //    	System.out.println(_nummberOfEnemys+ " dead  "+_deadEnemys);

    }

    private void updateEnemysStatus()
    {
        for (int i = 0; _enemyList.size() > i; i++)
        {
            if (_enemyList.get(i)
                .isAlive())
            {
                _enemyList.get(i)
                    .update();
                _enemyList.get(i)
                    .draw();
                if (_enemyList.get(i)
                    .getPlayerDMGCheck())
                {
                    _damageToPlayer = _enemyList.get(i)
                        .getDMG();
                }
            }
            else if (!_enemyList.get(i)
                .isAlive())
            {
                _enemyList.remove(_enemyList.get(i));
                _deadEnemys++;
            }
        }
    }

    /**
     * Gibt den Status der Wave wieder. True = Es gibt keine Feinde mehr die besiegt werden muessen
     * @return boolean
     */
    public boolean isWaveCompleted()
    {
        return _waveCompleted;
    }

    /**
     * Gibt eine ArrayList der Feinde wieder (Aktueller Sichtberreich der Tuerme)
     * @return ArrayList<Enemy>
     */
    public ArrayList<Enemy> getEnemies()
    {
        return _enemyList;
    }

    /**
     * Gibt an wie viel Leben der Spieler gerade verloren hat.
     * @return int
     */
    public int getDamageToPlayer()
    {
        int x = _damageToPlayer;
        _damageToPlayer = 0;
        return x;
    }
}
