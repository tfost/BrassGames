package com.brassgames.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;

//TODO: Ghost code
public class Ghost extends Entity {
	private Texture img;
	private Map<Float, Vector2> positions;
	private float current;

	public Ghost(Map<Float, Vector2> positions) {
		super(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);	//  TODO fix this shit
		this.current = 0;
		this.img = new Texture(Constants.GHOST_TEXTURE);
		this.positions = new HashMap<Float, Vector2>(positions);
		
		System.out.print("map size " + positions.size());	//  TODO remove
	}

	@Override
	public void update(float delta, KeyboardListener keyboard) {
		current = current + delta;
		if(positions.containsKey(current)) {
			Vector2 pos = positions.get(current);
			this.getAABB().setCenter(new Vector2(pos.x, pos.y));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}

	public void resetPos() {
		this.current = 0;		
	}
	

}
