package data.editor;

import static helpers.Artist.HEIGHT;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.lwjgl.input.Mouse;

import data.field.Tile;
import data.field.TileGrid;
import data.field.TileType;

/**
 * Action listeners for the editor 
 */

public class EditorActionListner
{
    private TileGrid _grid;
    private boolean _leftMouseButtonDown = false;
    private TileType _ausgewaelt;
    private boolean _run;
    private EditorArtist _art;

    public EditorActionListner(TileGrid grid)
    {
        _grid = grid;
        _run = true;
        _ausgewaelt = TileType.Grass;
        File file = new File("customLevel");
        if (!file.exists())
        {
            setTile(TileType.SPAWN, 0, 2);
        }

        if (true)
        {
            boolean missingSpawn = true;
            for (int x = 0; x < _grid.getTilesWidth(); x++)
            {
                for (int y = 1; y < _grid.getTilesHeight(); y++)
                {
                    if (TileType.SPAWN.equals(_grid.getTileType(x, y)))
                    {
                        missingSpawn = false;
                    }
                }
            }

            if (missingSpawn)
            {
                setTile(TileType.SPAWN, 0, 1);
            }
        }

        buildEditor();
        _art = new EditorArtist(_grid.getTilesWidth(), 0);
    }

    /**
     * Prueft den Standort der Maus und ob mit der geklickt wurde.
     */
    public void update()
    {
        if (Mouse.isButtonDown(0) && !_leftMouseButtonDown)
        {
            int x = (int) Math.floor(Mouse.getX()) / 64;
            int y = (int) Math.floor((HEIGHT - Mouse.getY() - 1)) / 64;

            TileType spawn = TileType.SPAWN;
            //Zeile 0 Editor Menue
            if (y == 0)
            {
                TileType menu = TileType.Menu;
                TileType exit = TileType.EXIT;

                if (!menu.equals(_grid.getTileType(x, y))
                        && !exit.equals(_grid.getTileType(x, y))
                        && !TileType.Save.equals(_grid.getTileType(x, y)))
                {
                	changeAusgewaelt(_grid.getTileType(x, y));
                    _art.select(x, y);
                }
                else if (exit.equals(_grid.getTileType(x, y)))
                {
                    _run = false;
                }
                else if (TileType.Save.equals(_grid.getTileType(x, y)))
                {
                    savemap();
                }
            }
            else
            {
                //Nur ein Spawn darf existieren
                if (_ausgewaelt.equals(spawn))
                {
                    deletespawn();
                }
                if (!_grid.getTileType(x, y)
                    .equals(spawn))
                {
                    setTile();
                }
            }

        }
        _art.update();
        _leftMouseButtonDown = Mouse.isButtonDown(0);
    }

    /**
     * Veraendert das Feld wo die Maus ist zu dem ausgewaehlten Typ
     */
    public void setTile()
    {
        _grid.setTile((int) Math.floor(Mouse.getX() / 64),
                (int) Math.floor((HEIGHT - Mouse.getY() - 1) / 64),
                _ausgewaelt);
    }

    /**
     * Veraendert ein bestimmtes Feld
     * @param type
     * @param x
     * @param y
     */
    public void setTile(TileType type, int x, int y)
    {
        _grid.setTile((int) Math.floor(x), (int) Math.floor(y), type);
    }
    
    /**
     * Gibt das Feld an der Position x, y zurueck
     * @param x
     * @param y
     * @return 
     */
    public TileType getTile(int x, int y) {
    	return _grid.getTile(x, y).getType();
    }
    
    /**
     * Aendert den ausgewhalten TileTyp
     * @param typ
     */
    public void changeAusgewaelt(TileType typ) {
    	if (	typ.equals(TileType.Dirt)
    			||typ.equals(TileType.SPAWN)
    			||typ.equals(TileType.Water)
    			||typ.equals(TileType.Grass)
    			||typ.equals(TileType.RED)
    			) {
    		_ausgewaelt = typ;
		} 
    }
    
    /**
     * Gibt das Ausgewealte TileTyp zurueck
     * @return
     */
    public TileType getAusgewaelt() {
    	return _ausgewaelt;
    }
    
    /**
     * Setzt den ausgewahlten TileType in der Map
     * @param x
     * @param y
     */
    public void setTile(int x, int y) {
    	if (!(y == 0)) {
        	if(_ausgewaelt.equals(TileType.SPAWN)) {
        		deletespawn();
        		setTile(_ausgewaelt, x, y);
        	} else {
        		setTile(_ausgewaelt, x, y);
        	}
		}
    }

    /**
     * Gibt den Status des Editors zurueck
     * @return run
     */
    public boolean getrun()
    {
        return _run;
    }

    private void deletespawn()
    {
        for (int x = 0; x < _grid.getTilesWidth(); x++)
        {
            for (int y = 1; y < _grid.getTilesHeight(); y++)
            {
                if (_ausgewaelt.equals(_grid.getTileType(x, y)))
                {
                    setTile(TileType.Dirt, x, y);
                }
            }
        }
    }

    /**
     * Saves custom map
     */
    public void savemap()
    {
        File file = new File("customLevel");
        try
        {
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            PrintWriter pw = new PrintWriter(fw);
            for (int y = 1; y < _grid.getTilesHeight(); y++)
            {
                for (int x = 0; x < _grid.getTilesWidth(); x++)
                {
                    pw.print(_grid.getTileType(x, y)
                        .name() + "-");
                }
                pw.println();
            }
            pw.close();
        }
        catch (IOException e)
        {
            System.out.println("Err201");
            e.printStackTrace();
        }
    }

    private void buildEditor()
    {
        for (int i = 0; i < _grid.getTilesWidth(); i++)
        {
            setTile(TileType.Menu, i, 0);
        }
        setTile(TileType.Grass, 0, 0);
        setTile(TileType.Dirt, 1, 0);
        setTile(TileType.SPAWN, 2, 0);
        setTile(TileType.Water, 3, 0);
        setTile(TileType.RED, 4, 0);
        setTile(TileType.Save, _grid.getTilesWidth() - 2, 0);
        setTile(TileType.EXIT, _grid.getTilesWidth() - 1, 0);
    }

}
