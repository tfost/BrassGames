package com.brassgames.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class Player extends Entity {
	
	private float dx;
	private float dy;
	private Texture img;
	private PlayerState state;
	
	//TODO: Ghost Code
	private float current;
	public int frames;
	private Map<Float, Vector2> positions;
			
	public Player(float x, float y) {
		super(x, y);
		this.dx = 0;
		this.dy = 0;
		this.img = new Texture(Constants.PLAYER_TEXTURE);
		
		this.state = new PlayerOnGroundState();
		
		//TODO: Ghost code
		this.current = 0;
		this.positions = new HashMap<Float, Vector2>();
		this.frames = 0;
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
	
	
	
	//TODO: https://www.gamasutra.com/blogs/DanielFineberg/20150825/244650/Designing_a_Jump_in_Unity.php
	//TODO: apply gravity.
	private void doPhysics(float delta) {
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
		
		//TODO: Ghost code
		current += delta;
		Vector2 pos = new Vector2(this.getAABB().getCenter().x, this.getAABB().getCenter().y);
		positions.put(current, pos);
		frames++;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	
	public Ghost toGhost() {
		return new Ghost(positions);
	}
	
	public boolean isDead() {
		return super.isDead;
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



	public void kill() {
		super.isDead = true;
	}
	
	public Vector2 getPosition() {
		return new Vector2(this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	
	public boolean hasLanded() {
		return this.state.getClass().equals(com.brassgames.entities.PlayerOnGroundState.class) ||
				this.state.getClass().equals(com.brassgames.entities.PlayerMovingState.class);
	}
}
