package com.brassgames.utils;

import com.badlogic.gdx.Gdx;

/**
 * A Wrapper around GdxInput that keeps track of which keys are currently pressed at any given time.
 * This wrapper ensures that the state of the keys at the beginning of a frame is the same as at the end.
 * @author Tyler
 *
 */
public class KeyboardListener {

	/*
	 * The various states of each button. A button could be:
	 * 	Released: It is not currently being pressed
	 * 	JustPressed: The button was not pressed in the previous frame, but is being
	 * 				pressed for the first time in this frame.
	 *  Pressed: The button is currently being pressed, but was pressed the frame before.
	 */
	private enum ButtonState{RELEASED, PRESSED, JUSTPRESSED};
	
	private ButtonState[] buttons; // current state of buttons 0-255
	
	/**
	 * Creates a new KeyboardListener
	 */
	public KeyboardListener() {
		this.buttons= new ButtonState[255]; 
	}
	
	/**
	 * Polls the state of each key. Should be called only once per frame, otherwise
	 * undefined behaviour might occur with the state of keypresses changing in a 
	 * single frame.
	 */
	public void poll() {
		for (int i = 0; i < this.buttons.length; i++) {
			if (Gdx.input.isKeyJustPressed(i)) {
				this.buttons[i] = ButtonState.JUSTPRESSED;
			} else if (Gdx.input.isKeyPressed(i)) {
				this.buttons[i] = ButtonState.PRESSED;
			} else {
				this.buttons[i] = ButtonState.RELEASED;
			}
		}
	}
	
	/**
	 * @param key the key to be inspected. Assumes 0 <= key <= 255.
	 * @return whether a key is just pressed
	 */
	public boolean isKeyJustPressed(int key) {
		return this.buttons[key] == ButtonState.JUSTPRESSED;
	}
	
	/**
	 * @param key the key to be inspected. Assumes 0 <= key <= 255.
	 * @return whether a key is released
	 */
	public boolean isKeyPressed(int key) {
		return this.buttons[key] == ButtonState.PRESSED;
	}
	
	/**
	 * @param key the key to be inspected. Assumes 0 <= key <= 255.
	 * @return whether a key is released
	 */
	public boolean isKeyReleased(int key) {
		return this.buttons[key] == ButtonState.RELEASED;
	}
}













