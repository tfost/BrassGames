package com.brassgames.entities;

public abstract class Entity {
	private int x;
	private int y;
	protected boolean isDead;
	
	public Entity() {
		this.x = 0;
		this.y = 0;
		this.isDead = false;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public abstract void update(long deltaT);
	public abstract void render();
}
