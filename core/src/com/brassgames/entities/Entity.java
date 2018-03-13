package com.brassgames.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.utils.AxisAlignedBoundingBox;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public abstract class Entity {
	
	private AxisAlignedBoundingBox aabb;
	
	protected boolean isDead;
	
	public Entity(float x, float y) {
		this.aabb = new AxisAlignedBoundingBox(x, y, Constants.PLAYER_WIDTH, Constants.PLAYER_HEIGHT);
		this.isDead = false;
	}
	
	protected AxisAlignedBoundingBox getAABB() {
		return this.aabb;
	}

	
	public abstract void update(float delta, KeyboardListener keyboard);
	
	public abstract void render(SpriteBatch batch);
}
