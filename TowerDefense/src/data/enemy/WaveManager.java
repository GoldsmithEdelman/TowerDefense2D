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
    private int _nummerDerAktuellenWave;
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
    	this._nummerDerAktuellenWave = 0;
        this._enemyTypeList = enemyTypeList;
        this._timeBetweenEnemies = timeBetweenEnemies;
        this._WaveIntList = enemiesPerWaveList;
        this._timeSinceLastWave = 0;
        this._waveNumber = 0;
        this._maxwave = enemiesPerWaveList.length;
        this._currentWave = null;
        newWave2();
    }

    /**
     * Updatet die Wave
     */
    public void update()
    {
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

    /**
     * Erstellt eine neue Welle
     */
    public void newWave2()
    {
    	//
    	if(_nummerDerAktuellenWave<_WaveIntList.length) {
        	int[] waveList = _WaveIntList[_nummerDerAktuellenWave];
            _currentWave = new Wave(_enemyTypeList, _timeBetweenEnemies,
            		waveList);
    	}


        _timeBetweenEnemies = _timeBetweenEnemies * 0.9f;
        _waveNumber++;
        _nummerDerAktuellenWave++;
        // testing
        System.out.println("Wave number " + _waveNumber);
    }
    
    /**
     * Erstellt eine neue Welle
     */
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
    
    /**
     * Gibt die aktuelle Wave wieder
     * @return _currentWave
     */
    public Wave getCurrentWave()
    {
        return _currentWave;
    }
    
    /**
     * Gibt die Nummer der Wave wieder
     * @return
     */
    public String getWaveNumber() {
    	return ""+_waveNumber;
    }
    
    /**
     * Gibt die Maximale Anzahl der Wave zurück
     * @return
     */
    public int getWave() {
    	return _maxwave;
    }
}
