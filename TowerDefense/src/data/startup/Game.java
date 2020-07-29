package data.startup;

import static helpers.Artist.quickLoadPngTexture;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.newdawn.slick.TrueTypeFont;

import data.enemy.Enemy;
import data.enemy.WaveManager;
import data.field.TileGrid;
import data.player.Menu;
import data.player.Player;

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
    private String _LevelName;
    private String _MaxWave;
    // to be deleted
    //temporary for testing
    //    TowerCannon tower;

    //Endlose Mode
    public Game(int[][] map)
    {
    	int spawnX = 0;
    	int spawnY = 2;
        test = new Menu();
        _grid = new TileGrid(map);
        
        File file = new File("customLevel");
    	if(!file.exists()) {
    		//nothing
    	} 
    	else {
    		try {
    			FileReader fr = new FileReader(file.getAbsolutePath());
    			BufferedReader br = new BufferedReader(fr);
    			
    			String str;
    			try {
    				ArrayList<String[]> table = new ArrayList<String[]>();
    				while ((str=br.readLine())!=null) {
    					String[] parts = str.split("-");
    					table.add(parts);
    				}
    				System.out.println(_grid.getTilesHeight()+" test");
    				if(table.size() == _grid.getTilesHeight()||table.get(0).length == _grid.getTilesWidth()) {
    					for (int i = 0; i < table.size(); i++) {
							for (int j = 0; j < table.get(i).length; j++) {
								System.out.println(i +"/"+ j);
								switch (table.get(i)[j]) {
								case "Grass":
									map[i+1][j] =    0;
									break;
								case "Water":
									map[i+1][j] =    1;
									break;
								case "SPAWN":
									map[i+1][j] =    2;
									spawnY = i+1;
									spawnX = j;
									break;
								case "Dirt":
									map[i+1][j] =    2;
									break;

								default:
									break;
								}
								
							}
						}
    					_grid = new TileGrid(map);
    				}
    				
    			} catch (IOException e) {
    				System.out.println("Err222");
    				e.printStackTrace();
    			}
    		} catch (FileNotFoundException e) {
    			System.out.println("Err222");
    			e.printStackTrace();
    		}
    	}
    	
        
        
        
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(spawnX, spawnY), _grid, test, 64, 64, 200, 25, 10), 2, 2);
        _player = new Player(_grid, _waveManager, 3, test);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
        _font = new TrueTypeFont(awtFont, false);
        _MaxWave = "?";

    }

    //Wave Mode mit einnen WaveManager
    public Game(int[][] map, int maxwaves, int x, int y)
    {
        _MaxWave = "?";
        test = new Menu();
        _grid = new TileGrid(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid, test, 64, 64, 200, 25, 10), 2, 2,
                maxwaves);
        _player = new Player(_grid, _waveManager, 2, test);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
        _font = new TrueTypeFont(awtFont, false);

    }

 

    //custom Wave
    public Game(int[][] map, int[][] wavemap, int x, int y, String s)
    {
        _MaxWave = "" + wavemap.length;
        test = new Menu();
        _grid = new TileGrid(map);
        ArrayList<Enemy> EnemyTypes = new ArrayList<Enemy>();
        EnemyTypes.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid, test, 64, 64, 100, 25, 10));
        EnemyTypes.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid, test, 64, 64, 200, 25, 10));
        EnemyTypes.add(new Enemy(quickLoadPngTexture("gegner"),
                _grid.getTile(x, y), _grid, test, 64, 64, 200, 1000, 100));
        _waveManager = new WaveManager(EnemyTypes, 2, wavemap);
        _LevelName = s;
        _player = new Player(_grid, _waveManager, 2, test);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
        _font = new TrueTypeFont(awtFont, false);

    }

    public Game(int[][] map, int[][] wavemap, int x, int y, int[][] wavemap2,
            int x2, int y2, String s)
    {
        _MaxWave = "" + wavemap.length;
        test = new Menu();
        _grid = new TileGrid(map);
        //EnemyListe + Ort1
        ArrayList<Enemy> EnemyTypes1 = new ArrayList<Enemy>();
        EnemyTypes1.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid, test, 64, 64, 100, 25, 10));
        EnemyTypes1.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x, y), _grid, test, 64, 64, 200, 25, 10));
        EnemyTypes1.add(new Enemy(quickLoadPngTexture("gegner"),
                _grid.getTile(x, y), _grid, test, 64, 64, 200, 1000, 100));
        //EnemyListe + Ort2
        ArrayList<Enemy> EnemyTypes2 = new ArrayList<Enemy>();
        EnemyTypes2.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x2, y2), _grid, test, 64, 64, 100, 25, 10));
        EnemyTypes2.add(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(x2, y2), _grid, test, 64, 64, 200, 25, 10));
        EnemyTypes2.add(new Enemy(quickLoadPngTexture("gegner"),
                _grid.getTile(x2, y2), _grid, test, 64, 64, 200, 1000, 100));

        ArrayList<Enemy> EnemyTypes = new ArrayList<Enemy>();
        for (int i = 0; i < EnemyTypes1.size(); i++)
        {
            EnemyTypes.add(EnemyTypes1.get(i));
        }
        for (int i = 0; i < EnemyTypes2.size(); i++)
        {
            EnemyTypes.add(EnemyTypes2.get(i));
        }

        int[][] wavemapzusammen = new int[wavemap.length][];
        for (int i = 0; i < wavemap.length; i++)
        {
            wavemapzusammen[i] = new int[wavemap[i].length
                    + wavemap2[i].length];
            int k = 0;
            for (int j = 0; j < wavemap[i].length; j = j + 2)
            {
                wavemapzusammen[i][j] = wavemap[i][k];

                if (wavemap2[i].length + 1 > j)
                {
                    if (!(wavemap2[i][k] == 0))
                        wavemapzusammen[i][j + 1] = wavemap2[i][k]
                                + EnemyTypes1.size();
                }
                else
                {
                    wavemapzusammen[i][j + 1] = 0;
                }
                k++;

            }
        }

        _waveManager = new WaveManager(EnemyTypes, 1, wavemapzusammen);

        _LevelName = s;
        _player = new Player(_grid, _waveManager, 2, test);
        Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
        _font = new TrueTypeFont(awtFont, false);

    }

    public void update()
    {

        // order is important; grid first then towers on top
        if (_player.getPlayerhealth() > 0)
        {
            _grid.draw();
            _waveManager.update();
            if (_waveManager2 != null)
            {
                _waveManager2.update();
            }
            _player.update();

            drawstring(_waveManager.getWaveNumber(),
                    "" + _player.getPlayerhealth() + "       Geld: "
                            + test.getmoney());
            test.update();
        }
        else
        {
            run = false;
        }

        //Wenn der Spieler alle Waves �berstanden hat
        if (_waveManager.getWave() == 0)
        {
            run = false;
            writedata();
        }

        //                  to be deleted
        //                tower.update();
    }

    public void drawstring(String aktuelleWave, String aktuellesleben)
    {

        String s = "Welle: " + aktuelleWave + "/" + _MaxWave + "    Leben: "
                + aktuellesleben;
        _font.drawString(0, 0, s);

    }

    public boolean getrun()
    {
        return run;
    }

    public void writedata()
    {
        File file = new File("data");
        ArrayList<String> list = readdata();
        try
        {
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            PrintWriter pw = new PrintWriter(fw);
            boolean level = true;
            for (int i = 0; i < list.size(); i++)
            {
                pw.println(list.get(i));
                if (list.get(i)
                    .equals(_LevelName))
                {
                    level = false;
                }

            }
            if (level)
            {
                pw.println(_LevelName);
            }
            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("Err201");
            e.printStackTrace();
        }

    }

    public ArrayList<String> readdata()
    {
        ArrayList<String> list = new ArrayList<String>();
        File file = new File("data");
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        try
        {
            FileReader fr = new FileReader(file.getAbsolutePath());
            BufferedReader br = new BufferedReader(fr);

            String str;
            try
            {

                while ((str = br.readLine()) != null)
                {
                    list.add(str);
                }
            }
            catch (IOException e)
            {
                System.out.println("Err111");
                e.printStackTrace();
            }
        }
        catch (FileNotFoundException e)
        {

        }
        return list;
    }

}
