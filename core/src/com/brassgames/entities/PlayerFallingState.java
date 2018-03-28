package com.brassgames.entities;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.blocks.Block;
import com.brassgames.game.World;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class PlayerFallingState implements PlayerState {

	private PlayerState subsequentState;
	
	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		if (keyboard.isKeyJustPressed(Input.Keys.SPACE) ) {
			subsequentState = new PlayerJumpingState();
		}
		
	}

	@Override
	public void update(float delta, Player player, World world) {
		// TODO Auto-generated method stub
		player.setDy(player.getDy() + Constants.GRAVITY);
		player.doWallSensorPhysics(delta);
		Block collidedBlock = player.getDownCollidedBlock(world);
		if (collidedBlock != null) {
			float top = collidedBlock.getAABB().getCenter().y + collidedBlock.getAABB().getRadii().y;
			player.getAABB().setCenter(new Vector2(player.getAABB().getCenter().x, top));
			player.setDy(0);
			
			if (subsequentState == null) {
				player.setState(new PlayerOnGroundState());
			} else {
				player.setState(subsequentState);
			}
		} 
	}

	@Override
	public void enter(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Player player) {
		// TODO Auto-generated method stub
		
	}

}
