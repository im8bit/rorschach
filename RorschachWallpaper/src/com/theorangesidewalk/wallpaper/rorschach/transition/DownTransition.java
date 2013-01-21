package com.theorangesidewalk.wallpaper.rorschach.transition;

import java.util.ArrayList;

import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;

public class DownTransition implements Transition {
	int velocity;

	public void step(ArrayList<Blot> blots) {
	}

	public void finish() {

	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void startUp(ArrayList<Blot> blots) {
		// TODO Auto-generated method stub

	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
