package com.brassgames.game;

import java.util.ArrayList;
import java.util.List;

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
	private List<Ghost> ghosts;
	
	private World world;
	
	private KeyboardListener keyboard;
	
	public Level() {
		this.keyboard = new KeyboardListener();
		this.world = new World();
		this.player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
		this.ghosts = new ArrayList<Ghost>();
	}
	
	public void handleInput() {
		keyboard.poll();
		if(keyboard.isKeyJustPressed(Input.Keys.K)) {
			player.kill();
		} 
		if (keyboard.isKeyJustPressed(Input.Keys.P)) { // purge ghosts
			ghosts.clear();
		}
	}
	
	public void update(float delta) {
		this.handleInput();
		if (player.isDead() && player.hasLanded()) {	//TODO: define this better
			ghosts.add(player.toGhost());
			System.out.println(" / " + player.frames);
			for(Ghost g : ghosts) {
				g.resetPos();
			}
			player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
		}
		player.update(delta, keyboard, world);
		for(Ghost g : ghosts) {
			g.update(delta, keyboard, world);
		}
	}
	
	public void render(SpriteBatch batch) {
		this.player.render(batch);
		this.world.render(batch);
		for(Ghost g : ghosts) {
			g.render(batch);
		}
	}
}
