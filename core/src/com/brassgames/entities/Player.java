package com.brassgames.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.utils.AxisAlignedBoundingBox;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class Player extends Entity {
	// TODO list of points & method for looping?
	
	//Physics variables that apply to all states of a player
	private float dx;
	private float dy;

	
	//Determines what state the player is in. 
	private PlayerState state;
	
	private AxisAlignedBoundingBox aboveSensor;
	private AxisAlignedBoundingBox belowSensor;
	private AxisAlignedBoundingBox leftSensor;
	private AxisAlignedBoundingBox rightSensor;
	
	//Graphics Variables
	private Texture img;
	
	/**
	 * Creates a new Instance of a Player
	 */
	public Player() {
		super(250, 0);
		this.dx = 0;
		this.dy = 0;
		this.img = new Texture(Constants.PLAYER_TEXTURE);
		
		this.state = new PlayerOnGroundState();
	}
	
	
	
	/**
	 * Sets variables to handle input, assuming perfect timesteps. 
	 * @param keyboard the Keyboard listener
	 */
	private void handleInput(KeyboardListener keyboard) {
		
		if (keyboard.isKeyPressed(Input.Keys.A)) {
			this.setDx(this.getDx() - Constants.PLAYER_ACCELERATION);
		} else if (keyboard.isKeyPressed(Input.Keys.D)) {
			this.setDx(this.getDx() + Constants.PLAYER_ACCELERATION);

		} else {
			this.dx = 0;
		}
	

	}
	
	//Returns the y position on top of a tile that we should be if we collide with a tile, or
	//NaN otherwise. 
	private float hasGround(Vector2 oldPos, Vector2 position, Vector2 vel) {
		Vector2 center = this.getAABB().getCenter();
		Vector2 bottomLeft = center.sub(this.getAABB().getRadii()).add(new Vector2(1, -1)); // one pixel on either side inside and below the AABB.
		Vector2 bottomRight = center.sub(this.getAABB().getRadii()).add(new Vector2(-1, -1));
		
	}
	
	
	private void doPhysics(float delta) {
		//TODO: https://www.gamasutra.com/blogs/DanielFineberg/20150825/244650/Designing_a_Jump_in_Unity.php
		
		Vector2 vel = new Vector2();
		vel.x = this.dx;
		vel.y = this.dy;
		
		this.getAABB().setCenter(this.getAABB().getCenter().add(vel));
	}
	
	@Override
	public void update(float delta, KeyboardListener keyboard) {
		this.handleInput(keyboard);
		this.state.handleInput(delta, this, keyboard);

		this.state.update(delta, this);
		this.doPhysics(delta);	
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	
	public Ghost toGhost() {
		return null;
	}
	
	public boolean isDead() {
		return this.isDead;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
		state.enter(this);
	}


	/**
	 * @return the dx
	 */
	public float getDx() {
		return dx;
	}


	/**
	 * @param dx the dx to set
	 */
	public void setDx(float dx) {
		if (dx < -Constants.PLAYER_WALK_SPEED) {
			dx = -Constants.PLAYER_WALK_SPEED;
		}
		if (dx > Constants.PLAYER_WALK_SPEED) {
			dx = Constants.PLAYER_WALK_SPEED;
		}
		this.dx = dx;
		
	}


	/**
	 * @return the dy
	 */
	public float getDy() {
		return dy;
	}


	/**
	 * @param dy the dy to set
	 */
	public void setDy(float dy) {
		if (dy < Constants.TERMINAL_VELOCITY) {
			dy = Constants.TERMINAL_VELOCITY;
		}
		this.dy = dy;
		
	}
	
}
