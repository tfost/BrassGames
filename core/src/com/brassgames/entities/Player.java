package com.brassgames.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class Player extends Entity {
	// TODO list of points & method for looping?
	
	private float dx;
	private float dy;
	private Texture img;
	private boolean isJumping;
			
	public Player() {
		super(250, 0);
		this.dx = 0;
		this.dy = 0;
		this.img = new Texture(Constants.PLAYER_TEXTURE);
		this.isJumping = false;
	}
	
	
	private void jump() {
		if (!this.isJumping) {
			this.isJumping = true;
			dy = Constants.PLAYER_JUMP_VEL;
		}
		
	}
	
	/**
	 * Sets variables to handle input, assuming perfect timesteps. 
	 * @param keyboard the Keyboard listener
	 */
	private void handleInput(KeyboardListener keyboard) {
		if (keyboard.isKeyPressed(Input.Keys.A)) {
			this.dx = -Constants.PLAYER_WALK_SPEED;
		} else if (keyboard.isKeyPressed(Input.Keys.D)) {
			this.dx = Constants.PLAYER_WALK_SPEED;
		} else {
			this.dx = 0;
		}
		if (keyboard.isKeyJustPressed(Input.Keys.SPACE)) {
			jump();
		}
	}
	
	
	private void doPhysics(long delta) {
		//TODO: https://www.gamasutra.com/blogs/DanielFineberg/20150825/244650/Designing_a_Jump_in_Unity.php
		if (this.isJumping) {
			dy -= 2;
			if (this.getAABB().getCenter().y  + dy < 0) {
				Vector2 newCenter = new Vector2();
				newCenter.x = this.getAABB().getCenter().x;
				this.getAABB().setCenter(newCenter);
				this.isJumping = false;
				this.dy = 0;
			}
		}
		
		Vector2 vel = new Vector2();
		vel.x = this.dx;
		vel.y = this.dy;
		
		this.getAABB().setCenter(this.getAABB().getCenter().add(vel));
	}
	
	@Override
	public void update(long delta, KeyboardListener keyboard) {
		this.handleInput(keyboard);
		this.doPhysics(delta);	
		
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	
	public Ghost giveUpTheGhost() {
		return null;
	}
	
	public boolean isDead() {
		return this.isDead;
	}

}
