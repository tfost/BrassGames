package com.brassgames.entities;

import com.badlogic.gdx.math.Vector2;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

public class PlayerFallingState implements PlayerState {

	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, Player player) {
		// TODO Auto-generated method stub
		float dy = player.getDy() + Constants.GRAVITY;
		if (player.getAABB().getCenter().y + dy < 0) {
			player.getAABB().setCenter(new Vector2(player.getAABB().getCenter().x, 0));
			dy = 0;
			
			player.setState(new PlayerOnGroundState());
		} else {
			player.setDy(dy);
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
