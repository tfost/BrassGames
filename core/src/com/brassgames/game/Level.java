package com.brassgames.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.entities.Ghost;
import com.brassgames.entities.Player;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

/**
 * A Level is a fully playable instance of an environment in which the player can explore. 
 * @author Tyler
 *
 */
public class Level {
	
	private Player player;
	private Ghost ghost;
	
	private KeyboardListener keyboard;
	
	public Level() {
		this.keyboard = new KeyboardListener();
		this.player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
	}
	
	public void update(float delta) {
		keyboard.poll();
		if(keyboard.isKeyJustPressed(Input.Keys.K)) {
			ghost = player.toGhost();
			player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
		}	
		player.update(delta, keyboard);
		if(ghost != null) {
			ghost.update(delta, keyboard);
		}
	}
	
	public void render(SpriteBatch batch) {
		this.player.render(batch);
		if(ghost != null) {
			this.ghost.render(batch);
		}
	}
	
}
