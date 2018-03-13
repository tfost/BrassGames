package com.brassgames.entities;

import com.brassgames.utils.KeyboardListener;

/**
 * A PlayerState is a state that the player could be in. It can handle input for itself
 * @author Tyler
 *
 */
public interface PlayerState {
	
	// Handles input in this state.
	public void handleInput(float delta, Player player, KeyboardListener keyboard);
	
	//Updates the plaeyr in this state.
	public void update(float delta, Player player);
	
	//Should be called once when a state is switched to this one. 
	public void enter(Player player);
	
	//Draws the player in this state.
	public void render(Player player);

}
