package com.brassgames.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.game.World;
import com.brassgames.utils.AxisAlignedBoundingBox;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

/**
 * An Entity is a character in the world, be it the player, ghosts, or enemies. They can move around throughout the world. 
 * @author Tyler
 *
 */
public abstract class Entity {
	
	private AxisAlignedBoundingBox aabb; // The Bounding Box surrounding this entity.
	
	protected boolean isDead; //Whether an entity is dead or not
	
	/**
	 * Creates a new Entity at a specified point in world coordinates.
	 * @param x horizontal position
	 * @param y vertical position
	 */
	public Entity(float x, float y) {
		this.aabb = new AxisAlignedBoundingBox(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		this.isDead = false;
	}
	
	/**
	 * 
	 * @return The bounding box over this character.
	 */
	protected AxisAlignedBoundingBox getAABB() {
		return this.aabb;
	}

	/**
	 * Updates a character each frame.
	 * @param delta the amount of time that has passed between frames (in seconds)
	 * @param keyboard the Keyboard Listener to be used to run an update.
	 */
	public abstract void update(float delta, KeyboardListener keyboard, World world);
	
	/**
	 * Draws this Entity to the screen.
	 * @param batch the active SpriteBatch.
	 */
	public abstract void render(SpriteBatch batch);
}
