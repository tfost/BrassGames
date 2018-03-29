package com.brassgames.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Level level;
	
	private final int TICKS_PER_SECOND = 50;
	private final float TIME_PER_FRAME = 1.f / TICKS_PER_SECOND;
	private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	private final int MAX_FRAMESKIP = 10;
	long next_tick; 
	int loops;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		level = new Level();
		next_tick = System.currentTimeMillis();
		loops = 0;
		startSecond = System.currentTimeMillis();
	}
	
	private int framesPerSecond = 0;
	private long startSecond = 0;
	
	@Override
	//Game loop, renders in a Constant game speed with maximum FPS. 
	public void render () { //while game is running:
		framesPerSecond++;
		long currTime = System.currentTimeMillis();
		if (currTime - startSecond > 1000) {
			System.out.println(framesPerSecond + " fps");
			framesPerSecond = 0;
		}
		loops = 0;
		while (System.currentTimeMillis() > next_tick && loops < MAX_FRAMESKIP) {
			level.update(TIME_PER_FRAME);
			next_tick += SKIP_TICKS;
			loops++;
		}
		loops = 0;
		next_tick = System.currentTimeMillis();
		
		// render
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		level.render(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
