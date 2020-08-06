package data.editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.field.TileGrid;

public class Editor {
	private EditorActionListner _listner;
	private TileGrid _grid;
	
	public Editor(int[][] map) {
		 _grid = new TileGrid(map);
		 oldmap(map);
		 _listner = new EditorActionListner(_grid);
	}
	
	/**
	 * Zeichnet das Grid
	 */
	public void update() {
		_grid.draw();
		_listner.update();
	}
	
	/**
	 * Gibt den Status des Editors wieder
	 * @return
	 */
	public boolean getrun() {
		return _listner.getrun();
	}
	
	/**
	 * Laedt die alte bearbeite Karte, wenn die Karte vorhanden ist
	 * @param map
	 */
	private void oldmap(int[][] map) {
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
									map[i+1][j] =    4;
									
									break;
								case "Dirt":
									map[i+1][j] =    2;
									break;
								case "RED":
									map[i+1][j] =    5;
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
	}
    	

}