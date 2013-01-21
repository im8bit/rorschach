package com.theorangesidewalk.wallpaper.rorschach;

public class Point {
	public Integer x;
	public Integer y;

	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String toString() {
		return "X: " + x.toString() + " Y: " + y.toString();
	}
}