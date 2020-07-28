package data.enemy;

import java.util.ArrayList;

public class WaveManager
{
    private float _timeSinceLastWave;
    private float _timeBetweenEnemies;
    private int _waveNumber;
    private int _enemiesPerWave;
    private Enemy _enemyType;
    private ArrayList<Enemy> _enemyTypeList;
    private Wave _currentWave;
    private int _maxwave = -10;
    private int[][] _WaveIntList;
    private int _customPointer;
    private boolean _customWave = false;
    
    //Normale Endlose Wave
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
    
    //Normale Wave
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
    
    //Custom Wave
    public WaveManager(ArrayList<Enemy> enemyTypeList, float timeBetweenEnemies,
            int[][] enemiesPerWaveList)
    {
    	this._customWave = true;
    	this._customPointer = 0;
        this._enemyTypeList = enemyTypeList;
        this._timeBetweenEnemies = timeBetweenEnemies;
        this._WaveIntList = enemiesPerWaveList;
        this._timeSinceLastWave = 0;
        this._waveNumber = 0;
        this._maxwave = enemiesPerWaveList.length;
        this._currentWave = null;

        newWave2();
    }

    public void update()
    {
        //        to be deleted
        //        if (_currentWave != null) _currentWave.update();
    	if (_maxwave == -10 || _maxwave>=0) {
            if (!_currentWave.isWaveCompleted()) {
                _currentWave.update();
            }
            else if (_customWave){
            	if(_maxwave>0) {
                	newWave2();
                	if(!(_maxwave==-10))
                	_maxwave--;
            	}
            }
            else{
            	newWave();
            	if(!(_maxwave==-10))
            	_maxwave--;
            }
    	}
    
            // System.out.println("Wave is over");
            
    }

    public void newWave2()
    {
    	if(_customPointer<_WaveIntList.length) {
        	int[] waveList = _WaveIntList[_customPointer];
            _currentWave = new Wave(_enemyTypeList, _timeBetweenEnemies,
            		waveList);
    	}


        _timeBetweenEnemies = _timeBetweenEnemies * 0.9f;
        _waveNumber++;
        _customPointer++;
        // testing
        System.out.println("Wave number " + _waveNumber);
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
