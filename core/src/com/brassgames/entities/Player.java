package com.brassgames.entities;

public class Player extends Entity {
	// TODO list of points & method for looping?
	
	public Player() {
	}

	@Override
	public void update(long deltaT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}
	
	public Ghost giveUpTheGhost() {
		return new Ghost();
	}
	
	public boolean isDead() {
		return this.isDead;
	}

}
