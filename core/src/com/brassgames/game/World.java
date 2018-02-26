package com.brassgames.game;

import java.util.List;

import com.brassgames.utils.AxisAlignedBoundingBox;

/**
 * A World is a representation of the game world in which the player exists. 
 * @author Tyler
 *
 */
public class World {

	//TODO: Use a quadtree to improve object lookup instead of a list. 
	List<AxisAlignedBoundingBox> world;
	
	/**
	 * Creates a list of bounding boxes to represent the world from an array of tiles.
	 * @param tiles
	 */
	public World(int[][] tiles) {
		
	}
	
	
	
}
