package com.brassgames.utils;

import com.badlogic.gdx.math.Vector2;

/**
 * An AABB is an Axis Aligned BoundingBox: a box that keeps track of a center and half sizes
 * of a given box. 
 * @author Tyler
 *
 */
public class AxisAlignedBoundingBox {
	
	private Vector2 center; // x, y positions of the center of this bouding box.
	private Vector2 radii; // width/2, height/2
	
	//creates a new AABB from center x and y, width, and height.
	public AxisAlignedBoundingBox(float x, float y, float width, float height) {
		this.center = new Vector2(x, y);
		this.radii = new Vector2(width / 2.f, height / 2.f);
	}
	
	//Returns if this AABB collides with another AABB.
	public boolean overlaps(AxisAlignedBoundingBox other) {
		if (Math.abs(center.x - other.center.x)  > radii.x + other.radii.x) {
			return false;
		} 
		if (Math.abs(center.y - other.center.y) > radii.x + other.radii.y) {
			return false;
		}
		return true;
	}
	
	public Vector2 getCenter() {
		return this.center;
		
	}
	
	public Vector2 getRadii() {
		return this.radii;
	}
	
	public void setCenter(Vector2 center) {
		this.center = center;
	}
}
