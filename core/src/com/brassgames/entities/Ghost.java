package com.brassgames.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.game.World;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

/**
 * A Ghost is an instance of a player that has died, but relives it's previous life multiple times. 
 * It contains all elements of a run through a level to recreate it identically and efficiently. 
 * @author Tyler
 *
 */

public class Ghost extends Entity {
	private Texture img; // The sprite of this Ghost.
	private Map<Float, Vector2> positions; // All positions that this ghost will take. 
	private float current; // ???
	
	/**
	 * Creates a new Ghost from a given Map(???) of positions. 
	 * @param positions the previous positions of a past ghost.
	 */
	public Ghost(Map<Float, Vector2> positions) {
		super(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);	//  TODO fix this shit
		this.current = 0;
		this.img = new Texture(Constants.GHOST_TEXTURE);
		this.positions = new HashMap<Float, Vector2>(positions);
		
		System.out.print("map size " + positions.size());	//  TODO remove
	}

	@Override
	public void update(float delta, KeyboardListener keyboard, World world) {
		current = current + delta;
		if(positions.containsKey(current)) {
			Vector2 pos = positions.get(current);
			this.getAABB().setCenter(new Vector2(pos.x, pos.y));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	
	/**
	 * Resets this ghost
	 * TODO: This could be global as well, so we reset the player at the same time as the character and rename it reset?
	 */
	public void resetPos() {
		this.current = 0;		
	}
	

}
