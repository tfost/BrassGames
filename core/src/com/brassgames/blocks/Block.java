package com.brassgames.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.utils.AxisAlignedBoundingBox;

/**
 * A Block is any tile within a level that has any sort of collison, be it
 * a floor, walls, spikes, etc. 
 * @author Tyler
 *
 */
public class Block {
	private static Texture texture; //for now, all have the same texture.
	private AxisAlignedBoundingBox aabb;
	private float width;
	private float height;
	
	//Creates a new Block from two coordinate pairs, the top left and the bottom right corners.
	public Block(int x1, int y1, int x2, int y2) {
		if (this.texture == null) {
			this.texture = new Texture("block.png");
		}
		width = Math.abs(x2 - x1);
		height = Math.abs(y2 - y1);
		
		this.aabb = new AxisAlignedBoundingBox(x1 + width / 2.f, y2 + height / 2.f, width, height);
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(this.texture, this.aabb.getCenter().x - width / 2, this.aabb.getCenter().y - height / 2, width, height);
	}
	
	public AxisAlignedBoundingBox getAABB() {
		return this.aabb;
	}
	
}
