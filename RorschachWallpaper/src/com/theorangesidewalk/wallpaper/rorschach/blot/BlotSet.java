package com.theorangesidewalk.wallpaper.rorschach.blot;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.theorangesidewalk.wallpaper.rorschach.transition.Transition;

public class BlotSet {
	Integer centerX;
	Integer centerY;

	ArrayList<Blot> blots = new ArrayList<Blot>();
	ArrayList<Transition> transitions = new ArrayList<Transition>();	
	boolean finished;

	public BlotSet(int offsetX, int centerY, int width, int height) {		
		finished = false;
		this.centerX = centerX;
		this.centerY = centerY;

		/*Transition transition = TransitionFactory.createTransition();
		transitions.add(transition);
		transition = TransitionFactory.createColorTransition();
		transitions.add(transition);*/

		create(width, height);
	}

	private void create(int width, int height) {
		//int y = (int) (q + (Math.random() * (height * 0.15)));
		//int x = (int) ((Math.random() * (width * 0.3)) + (width * 0.1));

		int sizeX = (int) ((Math.random() * (width * 0.6)) + (width * 0.2));
		int sizeY = (int) ((Math.random() * (height * 0.6)) + (height * 0.2));

		Blot blot = BlotFactory.getInstance().createBlotType(sizeX, sizeY, 1);

		blots.add(blot);

		/*for (Transition transition : transitions) {
			transition.startUp(blots);
		}*/
	}

	public void draw(Canvas c) {
		for (Blot blot : blots) {
			blot.draw(c);
			blot.drawMirrored(c);
		}
	}

	/*public void stepTransitions() {
		for (Transition transition : transitions) {
			transition.step(blots);
			if (transition.isFinished()) {
				finish();
			}
		}
	}

	public void finish() {
		finished = true;
	}

	public boolean isFinished() {
		return finished;
	}*/

}
