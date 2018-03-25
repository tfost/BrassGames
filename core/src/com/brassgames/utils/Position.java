package com.brassgames.utils;

public class Position {
	public final float x;
	public final float y;
	
	public Position(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	public boolean equals(Position other) {
		return this.x == other.x && this.y == other.y;
	}
}
