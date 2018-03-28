package com.brassgames.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.blocks.Block;

/**
 * A World is a representation of the game world in which the player exists. It contains
 * Methods to access and query features about a given world. 
 * @author Tyler
 *
 */
public class World {

	//TODO: Use a quadtree to improve object lookup instead of a list?
	
	//
	List<Block> blocks;
	
	/**
	 * Creates a list of bounding boxes to represent the world from an array of tiles.
	 * @param tiles
	 */
	public World() {
		//tbd: width and height are arbitrary.
		//this.world = new ArrayList<>();
		this.blocks = new ArrayList<>();
		this.blocks.add(new Block(50, 100, 100, 50));
		
		this.blocks.add(new Block(400, 180, 600, 100));
		this.blocks.add(new Block(-1000, -1, 1000, -100));

	}
	
	
	public void render(SpriteBatch batch) {
		for (Block b : this.blocks) {
			b.render(batch);
		}
	}
	
	
	
}
