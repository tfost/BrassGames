package com.brassgames.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.brassgames.entities.Ghost;
import com.brassgames.entities.Player;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;
import com.brassgames.utils.Position;

/**
 * A Level is a fully playable instance of an environment in which the player can explore. 
 * @author Tyler
 *
 */
public class Level {
	
	private Player player;
	private List<Ghost> ghosts;
	
	private KeyboardListener keyboard;
	
	public Level() {
		this.keyboard = new KeyboardListener();
		this.player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
		this.ghosts = new ArrayList<Ghost>();
	}
	
	public void update(float delta) {
		keyboard.poll();
		if(keyboard.isKeyJustPressed(Input.Keys.K)) {
			player.kill();
		} if (player.isDead() && player.hasLanded()) {
			ghosts.add(player.toGhost());
			System.out.println(" / " + player.frames);
			for(Ghost g : ghosts) {
				g.resetPos();
			}
			player = new Player(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);
		}
		player.update(delta, keyboard);
		for(Ghost g : ghosts) {
			g.update(delta, keyboard);
		}
	}
	
	public void render(SpriteBatch batch) {
		this.player.render(batch);
		for(Ghost g : ghosts) {
			g.render(batch);
		}
	}
}
