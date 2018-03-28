package com.brassgames.entities;

import com.badlogic.gdx.Input;
import com.brassgames.game.World;
import com.brassgames.utils.KeyboardListener;

public class PlayerOnGroundState implements PlayerState{

	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		if (keyboard.isKeyJustPressed(Input.Keys.SPACE) || keyboard.isKeyPressed(Input.Keys.SPACE)) {
			player.setState(new PlayerJumpingState());
			
		} 
	}

	@Override
	public void update(float delta, Player player, World world) {
		
		
	}

	@Override
	public void enter(Player player) {
		player.setDy(0);
	}

	@Override
	public void render(Player player) {
		// TODO Auto-generated method stub
		
	}


}
