package com.brassgames.entities;

import com.badlogic.gdx.Input;
import com.brassgames.blocks.Block;
import com.brassgames.game.World;
import com.brassgames.utils.KeyboardListener;

public class PlayerOnGroundState implements PlayerState{

	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		if (keyboard.isKeyJustPressed(Input.Keys.SPACE)) {
			player.setState(new PlayerJumpingState());
			
		} 
	}

	@Override
	public void update(float delta, Player player, World world) {
		Block below = player.getDownCollidedBlock(world);
		if (below == null) {
			System.out.println("FALLING!");
			player.setState(new PlayerFallingState());
		}
		
	}

	@Override
	public void enter(Player player) {
		player.setDy(0);
		System.out.println("Player is on the ground");
	}

	@Override
	public void render(Player player) {
		// TODO Auto-generated method stub
		
	}


}
