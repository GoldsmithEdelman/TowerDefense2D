package data;

public class WaveManager
{
    private float _timeSinceLastWave;
    private float _timeBetweenEnemies;
    private int _waveNumber;
    private int _enemiesPerWave;
    private Enemy _enemyType;
    private Wave _currentWave;
    private int _maxwave = -10;

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
    
    public WaveManager(Enemy enemyType, float timeBetweenEnemies,
            int enemiesPerWave, int maxwave)
    {
        this._enemyType = enemyType;
        this._timeBetweenEnemies = timeBetweenEnemies;
        this._enemiesPerWave = enemiesPerWave;
        this._timeSinceLastWave = 0;
        this._waveNumber = 0;
        this._maxwave = maxwave;
        this._currentWave = null;

        newWave();
    }

    public void update()
    {
        //        to be deleted
        //        if (_currentWave != null) _currentWave.update();
    	if (_maxwave == -10 || _maxwave>0) {
            if (!_currentWave.isWaveCompleted()) {
                _currentWave.update();
            }
            else {
            	newWave();
            	if(!(_maxwave==-10))
            	_maxwave--;
            }
    	}
    
            // System.out.println("Wave is over");
            
    }

    public void newWave()
    {
        _currentWave = new Wave(_enemyType, _timeBetweenEnemies,
                _enemiesPerWave);
        _enemiesPerWave++;
        _timeBetweenEnemies = _timeBetweenEnemies * 0.9f;
        _waveNumber++;
        // testing
        System.out.println("Wave number " + _waveNumber);
    }

    public Wave getCurrentWave()
    {
        return _currentWave;
    }
    
    public String getWaveNumber() {
    	return ""+_waveNumber;
    }
    
    public int getWave() {
    	return _maxwave;
    }
}
