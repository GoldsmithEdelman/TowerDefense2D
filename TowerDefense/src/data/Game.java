package data;

import static helpers.Artist.quickLoadPngTexture;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class Game
{
    private TileGrid _grid;
    private Player _player;
    private WaveManager _waveManager;
    public static final int TILE_SIZE = 64;
    private Menu test;
    private TrueTypeFont _font;
    // to be deleted
    //temporary for testing
    //    TowerCannon tower;

    public Game(int[][] map)
    {
    	test = new Menu();
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(0, 2), _grid, 64, 64, 200,25), 2, 2);
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
        _grid.draw();
        _waveManager.update();
        _player.update();
       
        drawstring(_waveManager.getWaveNumber(),"?",""+_player.getPlayerhealth());
        test.update();
//                  to be deleted
//                tower.update();
    }
    
    public void drawstring(String aktuelleWave, String maxWave, String aktuellesleben) {
    	
    	String s = "Welle: " + aktuelleWave+"/"+maxWave+"    Leben: "+ aktuellesleben ;
    	_font.drawString(0, 0, s);
    	
    }
    
}
