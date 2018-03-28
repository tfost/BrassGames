package com.brassgames.entities;

import com.brassgames.game.World;
import com.brassgames.utils.KeyboardListener;

/**
 * A PlayerState is a state that the player could be in. It can handle input for itself
 * @author Tyler
 *
 */
public interface PlayerState {
	
	/**Handles input in this state.
	 * 
	 * @see Entity.render
	 * @param delta
	 * @param player
	 * @param keyboard
	 */
	public void handleInput(float delta, Player player, KeyboardListener keyboard);
	
	/**
	 * Updates the plaeyr in this state.
	 * @param delta
	 * @param player
	 * @see Entity.render
	 */
	public void update(float delta, Player player, World world);
	
	/**
	 * Should be called once when a state is switched to this one. 
	 * @param player
	 * @see Entity.render
	 */
	public void enter(Player player);
	
	/**
	 * Draws the player in this state.
	 * @see Entity.render
	 * @param player
	 */
	public void render(Player player);

}
