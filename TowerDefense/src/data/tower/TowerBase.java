package data.tower;

import java.util.ArrayList;

import data.enemy.Enemy;

public interface TowerBase {
	
	/**
	 * 
	 * @param newList
	 */
	public void updateEnemyLists(ArrayList<Enemy> newList);
	
	/**
	 * 
	 */
	public void update();
	
	/**
	 * 
	 */
	public void draw();
	
	/**
	 * 
	 * @return
	 */
	public float getX();
	
	/**
	 * 
	 * @return
	 */
	public float getY();
}
