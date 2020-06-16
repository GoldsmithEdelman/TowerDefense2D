package data;

public class WaveManager
{
    private float _timeSinceLastWave;
    private float _timeBetweenEnemies;
    private int _waveNumber;
    private int _enemiesPerWave;
    private Enemy _enemyType;
    private Wave _currentWave;

    public WaveManager(Enemy enemyType, float timeBetweenEnemies,
            int enemiesPerWave)
    {
        this._enemyType = enemyType;
        this._timeBetweenEnemies = timeBetweenEnemies;
        this._enemiesPerWave = enemiesPerWave;
        this._timeSinceLastWave = 0;
        this._waveNumber = 0;

        this._currentWave = null;

        newWave();
    }

    public void update()
    {
        //        to be deleted
        //        if (_currentWave != null) _currentWave.update();
        if (!_currentWave.isWaveCompleted())
            _currentWave.update();
        else
            // System.out.println("Wave is over");
            newWave();
    }

    public void newWave()
    {
        _currentWave = new Wave(_enemyType, _timeBetweenEnemies,
                _enemiesPerWave);
        _waveNumber++;
        // testing
        System.out.println("Wave number " + _waveNumber);
    }

    public Wave getCurrentWave()
    {
        return _currentWave;
    }
}
