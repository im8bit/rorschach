package com.theorangesidewalk.wallpaper.rorschach.blot;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.theorangesidewalk.wallpaper.rorschach.transition.Transition;

public class BlotGroup {
	Integer centerX;
	Integer centerY;

	ArrayList<BlotSet> blotSets = new ArrayList<BlotSet>();
	ArrayList<Transition> transitions = new ArrayList<Transition>();

	public BlotGroup(Integer centerX, Integer centerY, Integer width, Integer height) {
		this.centerX = centerX;
		this.centerY = centerY;
		int offsetX = (width / 2) - 100;

		BlotSet blotSet = BlotSetFactory.getInstance().createBlotSet(offsetX, centerY, width, height);
		blotSets.add(blotSet);
	}

	public void draw(Canvas c) {
		for (BlotSet blotSet : blotSets) {
			blotSet.draw(c);
		}
	}
}
