package data;

import static helpers.Artist.quickLoadPngTexture;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Game
{
    private TileGrid _grid;
    private Player _player;
    private WaveManager _waveManager;
    private WaveManager _waveManager2;
    public static final int TILE_SIZE = 64;
    private Menu test;
    private TrueTypeFont _font;
    private boolean run = true;
    // to be deleted
    //temporary for testing
    //    TowerCannon tower;
    
    //Endlose Mode
    public Game(int[][] map)
    {
    	test = new Menu();
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(0, 2), _grid,test, 64, 64, 200,25,10), 2, 2);
        _player = new Player(_grid, _waveManager,15,test);
    	Font awtFont = new Font("Times New Roman", Font.BOLD,30);
    	 _font = new TrueTypeFont(awtFont, false); 
        //        to be deleted
        //        tower = new TowerCannon(quickLoadPngTexture("cannonbase"),
        //                _grid.getTile(8, 7), 20);

    }
    
    //Wave Mode mit einnen WaveManager
    public Game(int[][] map, int maxwaves, int x, int y)
    {
    	test = new Menu();
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid,test, 64, 64, 200,25,10), 2, 2,maxwaves);
        _player = new Player(_grid, _waveManager,15,test);
    	Font awtFont = new Font("Times New Roman", Font.BOLD,30);
    	 _font = new TrueTypeFont(awtFont, false); 
        //        to be deleted
        //        tower = new TowerCannon(quickLoadPngTexture("cannonbase"),
        //                _grid.getTile(8, 7), 20);

    }
    
    //Wave Mode mit zwei WaveManager
    public Game(int[][] map, int maxwaves, int x, int y, int x2,int y2)
    {
    	test = new Menu();
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid,test, 64, 64, 200,25,10), 2, 2,maxwaves);
        _waveManager2 = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x2, y2), _grid,test, 64, 64, 200,25,10), 2, 2,maxwaves);
        _player = new Player(_grid, _waveManager,15,test);
    	Font awtFont = new Font("Times New Roman", Font.BOLD,30);
    	 _font = new TrueTypeFont(awtFont, false); 
        //        to be deleted
        //        tower = new TowerCannon(quickLoadPngTexture("cannonbase"),
        //                _grid.getTile(8, 7), 20);

    }

    public void update()
    {
    	
        // order is important; grid first then towers on top
    	if(_player.getPlayerhealth()>0 ) {
        _grid.draw();
        _waveManager.update();
        if (_waveManager2 != null) {
        	_waveManager2.update();
        }
        _player.update();
       
        drawstring(_waveManager.getWaveNumber(),"?",""+_player.getPlayerhealth() + "       Geld: " + test.getmoney());
        test.update();
        } else {
        	run = false;
        }
    	
    	//Wenn der Spieler alle Waves Überstanden hat
    	if(_waveManager.getWave() == 0) {
    		run = false;
    	}
    	
//                  to be deleted
//                tower.update();
    }
    
    public void drawstring(String aktuelleWave, String maxWave, String aktuellesleben) {
    	
    	String s = "Welle: " + aktuelleWave+"/"+maxWave+"    Leben: "+ aktuellesleben ;
    	_font.drawString(0, 0, s);
    	
    }
    
    public boolean getrun() {
    	return run;
    }
    
}
