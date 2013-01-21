package com.theorangesidewalk.wallpaper.rorschach.transition;

import java.util.ArrayList;

import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;

public class OutTransition implements Transition {
	private int velocity;
	private boolean finished;

	public void finish() {
		finished = true;
	}

	public void step(ArrayList<Blot> blots) {
		for (Blot blot : blots) {
			// blot.expand(velocity);
		}
	}

	public void startUp(ArrayList<Blot> blots) {
	}

	public boolean isFinished() {
		return finished;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getVelocity() {
		return velocity;
	}

}
