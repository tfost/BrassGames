package com.brassgames.entities;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.brassgames.utils.Constants;
import com.brassgames.utils.KeyboardListener;
import com.brassgames.utils.Position;

public class Ghost extends Entity {
	private Texture img;
	private Map<Float, Position> positions;
	private float current;

	public Ghost(Map<Float, Position> positions) {
		super(Constants.PLAYER_DEFAULT_X, Constants.PLAYER_DEFAULT_Y);	//  TODO fix this shit
		this.current = 0;
		this.img = new Texture(Constants.PLAYER_TEXTURE);
		this.positions = new HashMap<Float, Position>(positions);
		
		System.out.println("map size " + positions.size());
	}

	@Override
	public void update(float delta, KeyboardListener keyboard) {
		current = current + delta;
		if(positions.containsKey(current)) {
			Position position = positions.get(current);
			this.getAABB().setCenter(new Vector2(position.x, position.y));
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(img, this.getAABB().getCenter().x, this.getAABB().getCenter().y);
	}
	

}
