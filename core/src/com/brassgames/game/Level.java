package com.brassgames.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.entities.Player;
import com.brassgames.utils.KeyboardListener;

/**
 * A Level is a fully playable instance of an environment in which the player can explore. 
 * @author Tyler
 *
 */
public class Level {
	
	private Player player;
	
	private World world;
	
	private KeyboardListener keyboard;
	
	public Level() {
		this.keyboard = new KeyboardListener();
		this.player = new Player();
		this.world = new World();
	}
	
	public void update(float delta) {
		keyboard.poll();
		player.update(delta, keyboard);
	}
	
	public void render(SpriteBatch batch) {
		this.player.render(batch);
		this.world.render(batch);
	}
	
}
