package com.brassgames.utils;

/**
 * Various constant definitions, all located here such that they can be changed in one place.
 * @author Tyler
 *
 */
public class Constants {

	// Player Display Constants
	public static final int PLAYER_WIDTH = 16;
	public static final int PLAYER_HEIGHT = 32;
	public static final float PLAYER_DEFAULT_X = 250;
	public static final float PLAYER_DEFAULT_Y = 0;
	public static final String PLAYER_TEXTURE = "supahman.png";

	//Player Physics Constants
	public static final float PLAYER_MASS = 1.0f;
	public static final float PLAYER_JUMP_VEL = 20.f;
	public static final float PLAYER_WALK_SPEED = 9.0f;
	private static final int FRAMES_TO_ACCELERATE = 5;
	public static final float PLAYER_ACCELERATION = PLAYER_WALK_SPEED / FRAMES_TO_ACCELERATE; //amount to accellerate per frame such that we reach player_walk_speed within frames_to_accellerate.
	public static final float TERMINAL_VELOCITY = -25.f;

	
	//World Constants
	public static final float GRAVITY = -2;
	public static final String GHOST_TEXTURE = "ghostythenoman.png";
}
