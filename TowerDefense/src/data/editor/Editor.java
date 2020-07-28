package data.editor;

import data.field.TileGrid;

public class Editor {
	private EditorActionListner _listner;
	private TileGrid _grid;
	
	public Editor(int[][] map) {
		 _grid = new TileGrid(map);
		 _listner = new EditorActionListner(_grid);
		 System.out.println("Tett2");
	}
	
	public void update() {
		_grid.draw();
		_listner.update();
	}
	
	public boolean getrun() {
		return _listner.getrun();
	}
	

}
