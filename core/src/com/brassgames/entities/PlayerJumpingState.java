package com.brassgames.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.blocks.Block;
import com.brassgames.game.World;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class PlayerJumpingState implements PlayerState{
	
	private short jumpTimer;
	private short MAX_JUMP_FRAMES;
	
	public PlayerJumpingState() {
		MAX_JUMP_FRAMES = 20;
		jumpTimer = 0;
	}
	
	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		if (keyboard.isKeyReleased(Input.Keys.SPACE)) {
			player.setState(new PlayerFallingState());
		} 
		
	}

	@Override
	public void update(float delta, Player player, World world) {
		if (jumpTimer <= MAX_JUMP_FRAMES) {//still holding jump button!
			float proportionCompleted = (float) jumpTimer / (float) MAX_JUMP_FRAMES;
			Vector2 jumpVector = new Vector2(0, Constants.PLAYER_JUMP_VEL).lerp(Vector2.Zero, proportionCompleted);
			player.setDy(jumpVector.y);
			jumpTimer++;
			
			//Stop jump if it would cause us to crash!
			player.doWallSensorPhysics(delta);
			Block upBlock = player.getUpCollidedBlock(world);
			if (upBlock != null) { 
				player.setState(new PlayerFallingState());
			}
			
		} else { // ran out of jump time!
			player.setState(new PlayerFallingState());	
		
		}
		
	}

	@Override
	public void enter(Player player) {
		System.out.println("Player started a jump");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Player player) {
		// TODO Auto-generated method stub
		
	}

}
