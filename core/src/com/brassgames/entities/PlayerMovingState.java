package com.brassgames.entities;

import com.brassgames.utils.KeyboardListener;

@SuppressWarnings("unused")
public class PlayerMovingState implements PlayerState{
	private boolean jumping;
	private boolean falling;	
	
	public PlayerMovingState() {
		jumping = false;
		falling = false;
	}
	
	@Override
	public void handleInput(float delta, Player player, KeyboardListener keyboard) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta, Player player) {
		// TODO Auto-generated method stub
		
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
