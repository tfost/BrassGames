package com.brassgames.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.blocks.Block;
import com.brassgames.game.World;
import com.brassgames.utils.AxisAlignedBoundingBox;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class Player extends Entity {
	
	//Physics variables that apply to all states of a player
	private float dx;
	private float dy;
	
	//Determines what state the player is in. 
	private PlayerState state;
	
	// Wall sensors, to determine if we are moving into a wall or not. 
	private AxisAlignedBoundingBox wallAboveSensor;
	private AxisAlignedBoundingBox wallBelowSensor;
	private AxisAlignedBoundingBox wallLeftSensor;
	private AxisAlignedBoundingBox wallRightSensor;
	
	//Graphics Variables
	private Texture img;
	
	//Ghost Creation Variables
	private float current;
	public int frames;
	private Map<Float, Vector2> positions;
			
	/**
	 * Creates a new Instance of a Player
	 */
	public Player(float x, float y) {
		super(x, y);
		this.dx = 0;
		this.dy = 0;
		this.img = new Texture(Constants.PLAYER_TEXTURE);
		
		this.state = new PlayerOnGroundState();
		
		//Wall Sensor Initialization
		//Starts centereed around the character, but is fixed to be int he proper spot after UpdateWallSensors is called. 
		this.wallBelowSensor = new AxisAlignedBoundingBox(x, y, Constants.PLAYER_WIDTH + 2, 4); //TODO: NO MAGIC NUMBERS
		this.wallAboveSensor = new AxisAlignedBoundingBox(x, y, Constants.PLAYER_WIDTH + 2, 4);
		this.wallRightSensor = new AxisAlignedBoundingBox(x, y, 4, Constants.PLAYER_HEIGHT + 2);
		this.wallLeftSensor = new AxisAlignedBoundingBox(x, y, 4, Constants.PLAYER_HEIGHT + 2);
	    this.updateWallSensors(); //Set to right position
		
		//Ghost initialization
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
		} else { // Slow down player if no button is pressed, or stop them if moving slow enough.
			if (this.getDx() < -Constants.PLAYER_ACCELERATION) {
				this.setDx(this.getDx() +  Constants.PLAYER_ACCELERATION);
			} else if (this.getDx() > Constants.PLAYER_ACCELERATION) {
				this.setDx(this.getDx() - Constants.PLAYER_ACCELERATION);
			} else if (Math.abs(this.getDx()) < Constants.PLAYER_ACCELERATION) {
				this.setDx(0);
			}
			this.dx = 0;
		}
	}
	
	/**
	 * Determines if there is a block beneath the player that is colliding with its wall sensors.
	 * @return the Block beneath the player, if there is one. Otherwise, returns null
	 */
	protected Block getDownCollidedBlock(World world) {
		return world.getCollidingBlock(this.wallBelowSensor);
	}
	
	/**
	 * @see getDownCollidedBlock
	 */
	protected Block getUpCollidedBlock(World world) {
		return world.getCollidingBlock(this.wallAboveSensor);
	}
	
	/**
	 * @see getDownCollidedBlock
	 */
	protected Block getLeftCollidedBlock(World world) {
		return world.getCollidingBlock(this.wallLeftSensor);
	}
	
	/**
	 * @see getDownCollidedBlock
	 */
	protected Block getRightCollidedBlock(World world) {
		return world.getCollidingBlock(this.wallRightSensor);
	}
	
	/**
	 * Does Moves wall sensors to see where we would be if we applied physics as they are as of right now
	 * Can be used to see if the player would collide if their velocities didn't change.
	 * @param delta
	 */
	protected void doWallSensorPhysics(float delta) {
		Vector2 vel = new Vector2(this.dx, this.dy);

		this.wallAboveSensor.setCenter(this.wallAboveSensor.getCenter().add(vel));
		this.wallBelowSensor.setCenter(this.wallBelowSensor.getCenter().add(vel));
		this.wallLeftSensor.setCenter(this.wallLeftSensor.getCenter().add(vel));
		this.wallRightSensor.setCenter(this.wallRightSensor.getCenter().add(vel));

	}
	
	/**
	 * Runs physics simulation on the players master AABB. 
	 * @param delta
	 */
	private void doPhysics(float delta) {
		float prevX = this.getAABB().getCenter().x;
		Vector2 vel = new Vector2();
		vel.x = this.dx;
		vel.y = this.dy;
		
		this.getAABB().setCenter(this.getAABB().getCenter().add(vel));
		//System.out.println("x:" + (this.getAABB().getCenter().x - prevX));
	}
	
	
	
	@Override
	public void update(float delta, KeyboardListener keyboard, World world) {
		this.updateWallSensors();
		this.handleInput(keyboard);
		this.state.handleInput(delta, this, keyboard);
		
		this.state.update(delta, this, world);
		this.doPhysics(delta);
		

		updateGhosts(delta);
	}
	
	private void updateWallSensors() {
		
		float halfWidth = this.getAABB().getRadii().x;
		float halfHeight = this.getAABB().getRadii().y;
		this.wallBelowSensor.setCenter(this.getAABB().getCenter().add(new Vector2(0, -halfHeight)));
		this.wallAboveSensor.setCenter(this.getAABB().getCenter().add(new Vector2(0, halfHeight)));
		this.wallLeftSensor.setCenter(this.getAABB().getCenter().add(new Vector2(-halfWidth, 0)));
		this.wallRightSensor.setCenter(this.getAABB().getCenter().add(new Vector2(halfWidth, 0 )));
	}
	
	private void updateGhosts(float delta) {
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
		this.updateWallSensors();
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
		return this.getAABB().getCenter();
	}
	
	public boolean hasLanded() {
		return this.state.getClass().equals(com.brassgames.entities.PlayerOnGroundState.class);
	}
}
