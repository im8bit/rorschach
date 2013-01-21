package com.theorangesidewalk.wallpaper.rorschach.transition;

import java.util.ArrayList;

import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;

public interface Transition {
	public void finish();

	public void setVelocity(int velocity);

	public void startUp(ArrayList<Blot> blots);

	public boolean isFinished();

	public void step(ArrayList<Blot> blots);

}
