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

import data.editor.Editor;
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
    private Menu _shop;
    private TrueTypeFont _font;
    private boolean run = true;
    private String _LevelName;
    private String _MaxWave;
    private int spawnX, spawnY;

    /**
     * Ladet eine Karte und erstellt daraus ein Level. Beinhaltet {Eine art von Feinden, SpawnPoint(0/2), Editor-Karte(>Karte), Endlosse Wellen}
     * 
     * @param map
     * @ensure map != null
     * 
     */
    public Game(int[][] map)
    {
        assert map != null : "Vorbedingung verletzt: map ist null";

        spawnX = 0;
        spawnY = 2;
        _shop = new Menu();
        _grid = new TileGrid(map);
        //L�dt die Editor-Karte
        loadEditorMap(map);
        _waveManager = new WaveManager(new Enemy(quickLoadPngTexture("enemy1"),
                _grid.getTile(spawnX, spawnY), _grid, _shop, 64, 64, 200, 25,
                10, 1, true), 2, 2);
        _player = new Player(_grid, _waveManager, 50, _shop);
        _MaxWave = "?";
        createFond();
       
    }

    /**
     * Ladet die Editor-Karte in das aktuelle Grid und setzt den Spawn neu, wenn diese existiert.
     * @param map
     */
    private void loadEditorMap(int[][] map)
    {
        File file = new File("customLevel");
        if (!file.exists())
        {
            //nothing
        }
        else
        {
            try
            {
                FileReader fr = new FileReader(file.getAbsolutePath());
                BufferedReader br = new BufferedReader(fr);
                String str;
                try
                {
                    ArrayList<String[]> table = new ArrayList<String[]>();
                    while ((str = br.readLine()) != null)
                    {
                        String[] parts = str.split("-");
                        table.add(parts);
                    }
                    if (table.size() == _grid.getTilesHeight()
                            || table.get(0).length == _grid.getTilesWidth())
                    {
                        for (int i = 0; i < table.size(); i++)
                        {
                            for (int j = 0; j < table.get(i).length; j++)
                            {
                                switch (table.get(i)[j])
                                {
                                case "Grass":
                                    map[i + 1][j] = 0;
                                    break;
                                case "Water":
                                    map[i + 1][j] = 1;
                                    break;
                                case "SPAWN":
                                    map[i + 1][j] = 2;
                                    spawnY = i + 1;
                                    spawnX = j;
                                    break;
                                case "Dirt":
                                    map[i + 1][j] = 2;
                                    break;
                                case "RED":
                                    map[i + 1][j] = 5;
                                    break;

                                default:
                                    break;
                                }
                            }
                        }
                        _grid = new TileGrid(map);
                    }
                }
                catch (IOException e)
                {
                    System.out.println("Err loadEditorMap(int[][] map) 1");
                    e.printStackTrace();
                }
            }
            catch (FileNotFoundException e)
            {
                System.out.println("Err loadEditorMap(int[][] map) 2");
                e.printStackTrace();
            }
            
        }
        
    }

    /**
     * Erstellt ein Spiel mit einer Karte
     * @param map
     * @param wavemap
     * @param x
     * @param y
     * @param s
     */
    public Game(int[][] map, int[][] wavemap, int x, int y, String s,
            int playerhealt)
    {
        _MaxWave = "" + wavemap.length;
        _shop = new Menu();
        _grid = new TileGrid(map);
        ArrayList<Enemy> EnemyTypes = createEnemyTypes(x, y);
        _waveManager = new WaveManager(EnemyTypes, 2, wavemap);
        _LevelName = s;
        _player = new Player(_grid, _waveManager, playerhealt, _shop);
        createFond();
    }

    /**
     * Erstellt ein Spiel mit einer Karte und zwei SpawnPoints
     * @param map
     * @param wavemap
     * @param x
     * @param y
     * @param wavemap2
     * @param x2
     * @param y2
     * @param s
     */
    public Game(int[][] map, int[][] wavemap, int x, int y, int[][] wavemap2,
            int x2, int y2, String s, int playerhealt)
    {
        //Kopie von wavemap2 da die Static ist
        int[][] wavemap3 = wavemap2.clone();
        wavemap2 = wavemap3;

        wavemap3 = wavemap.clone();
        wavemap = wavemap3;
        _MaxWave = "" + wavemap.length;
        _shop = new Menu();
        _grid = new TileGrid(map);
        //EnemyListe + Ort1
        ArrayList<Enemy> EnemyTypes1 = createEnemyTypes(x, y);
        //EnemyListe + Ort2
        ArrayList<Enemy> EnemyTypes2 = createEnemyTypes(x2, y2);

        //Eine Liste der Feine
        ArrayList<Enemy> EnemyTypes = new ArrayList<Enemy>();
        for (int i = 0; i < EnemyTypes1.size(); i++)
        {
            EnemyTypes.add(EnemyTypes1.get(i));
        }
        for (int i = 0; i < EnemyTypes2.size(); i++)
        {
            EnemyTypes.add(EnemyTypes2.get(i));
        }

        //Update der Nummer der Feinde in WaveMap2
        for (int i = 0; i < wavemap2.length; i++)
        {
            for (int j = 0; j < wavemap2[i].length; j++)
            {
                wavemap2[i][j] = wavemap2[i][j] + EnemyTypes1.size();
            }
        }

        //Kombination
        int[][] wavemapzusammen = waveMapCombine(wavemap, wavemap2);

        _waveManager = new WaveManager(EnemyTypes, 1, wavemapzusammen);

        _LevelName = s;
        _player = new Player(_grid, _waveManager, playerhealt, _shop);
        createFond();

    }

    private int[][] waveMapCombine(int[][] wavemap, int[][] wavemap2)
    {
        int groeße = 0;
        if (wavemap.length == wavemap2.length)
        {
            groeße = wavemap.length;
        }
        else if (wavemap.length >= wavemap2.length)
        {
            int[][] neu = new int[wavemap.length][];
            for (int i = 0; i < wavemap2.length; i++)
            {
                neu[i] = wavemap2[i];

            }
            wavemap2 = neu;
            groeße = wavemap.length;
        }
        else
        {
            return waveMapCombine(wavemap2, wavemap);
        }
        int[][] wavemapzusammen = new int[groeße][];
        for (int i = 0; i < wavemapzusammen.length; i++)
        {
            wavemapzusammen[i] = combine(wavemap[i], wavemap2[i]);
        }
        return wavemapzusammen;

    }

    private int[] combine(int[] wavemap, int[] wavemap2)
    {
        int laenge1 = 0;
        int laenge2 = 0;
        if (wavemap != null)
        {
            laenge1 = wavemap.length;
        }
        if (wavemap2 != null)
        {
            laenge2 = wavemap2.length;
        }

        int groeße = laenge1 + laenge2;
        int[] newWavemap = new int[groeße];
        int z1 = 0;
        int z2 = 0;
        if (laenge2 > laenge1)
        {
            return combine(wavemap2, wavemap);
        }
        // wavemap>wavemap2
        for (int i = 0; i < newWavemap.length; i++)
        {
            if (i % 2 == 1 && z1 < laenge2)
            {
                newWavemap[i] = wavemap2[z1];
                z1++;
            }
            else
            {
                newWavemap[i] = wavemap[z2];
                z2++;
            }
        }
        return newWavemap;
    }

    private ArrayList<Enemy> createEnemyTypes(int x, int y)
    {
        ArrayList<Enemy> EnemyTypes1 = new ArrayList<Enemy>();
        EnemyTypes1
            .add(new Enemy(quickLoadPngTexture("enemy1"), _grid.getTile(x, y),
                    _grid, _shop, 64, 64, 100, 25, 10, 1, true));
        EnemyTypes1
            .add(new Enemy(quickLoadPngTexture("enemy1"), _grid.getTile(x, y),
                    _grid, _shop, 64, 64, 200, 25, 10, 1, true));
        EnemyTypes1
            .add(new Enemy(quickLoadPngTexture("gegner"), _grid.getTile(x, y),
                    _grid, _shop, 64, 64, 200, 300, 100, 3, true));
        EnemyTypes1.add(new Enemy(quickLoadPngTexture("shieldenemy"),
                _grid.getTile(x, y), _grid, _shop, 64, 64, 200, 1000, 100, 0,
                false));
        return EnemyTypes1;
    }

    private void createFond()
    {
        Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
        _font = new TrueTypeFont(awtFont, false);
    }

    /**
     * Updatet alle Update Methoden, �berpr�ft ob das Spiel zu Ende ist
     */
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
                            + _shop.getmoney());
            _shop.update();
        }
        //Wenn der Spieler kein Leben mehr hat
        else
        {
            run = false;
        }
        //Wenn der Spieler alle Waves ueberstanden hat
        if (_waveManager.getWave() == 0)
        {
            run = false;
            writedata();
        }
    }

    /**
     * Zeichnet einen String oben links mit der aktuellen Welle und Leben des Spielers
     * @param aktuelleWave
     * @param aktuellesleben
     */
    public void drawstring(String aktuelleWave, String aktuellesleben)
    {
        String s = "Welle: " + aktuelleWave + "/" + _MaxWave + "    Leben: "
                + aktuellesleben;
        _font.drawString(0, 0, s);
    }

    /**
     * Gibt den Status des Spiels wieder
     * @return
     */
    public boolean getrun()
    {
        return run;
    }

    /**
     * Schreibt den Namen des Levels in einer Datei "data" 
     */
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

    /**
     * Liest alle Level Namen in der Datei "data" und gibt diese als ArrayList<String> wieder.
     * @return ArrayList<String>
     */
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
