package data.editor;

import data.field.Tile;
import data.field.TileGrid;
import data.field.TileType;

public class EditorArtist {
	private Tile _ausgewahlt;
	private Tile[] _menubar;
	private int _x,_y;
	
	public EditorArtist(int x, int y) {
		_menubar = new Tile[x];
		for (int i = 0; i < _menubar.length; i++) {
			_menubar[i] = new Tile(i*64, 0, 64, 64, TileType.EditorBar);
		}
		_x=0;
		_y=y;
		_ausgewahlt = new Tile(_x, _y, 64, 64, TileType.EditorAusgewahlt);
		
	}
	
	/**
	 * Zeichnet "ausgewaelt" auf ein ausgewaeltes Feld
	 * @param x
	 * @param y
	 */
	public void select(int x, int y) {
		System.out.println("x"+ x + " y"+y);
		_ausgewahlt = new Tile(x*64, y*64, 64, 64, TileType.EditorAusgewahlt);
	}
	
	/**
	 * Zeichnet die Menue Umrandung und ausgewaelt
	 */
	public void update() {
		for (int i = 0; i < _menubar.length; i++) {
			_menubar[i].draw();
		}
		_ausgewahlt.draw();
	}
	
	
}
