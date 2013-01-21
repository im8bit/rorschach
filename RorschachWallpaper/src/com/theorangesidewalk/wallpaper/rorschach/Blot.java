package com.theorangesidewalk.wallpaper.rorschach;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.theorangesidewalk.wallpaper.rorschach.blot.BlotSet;

public class Blot {
	public static final int MAX_INK = 10;
	private static final int GROUP_COUNT = 3;

	ArrayList<BlotSet> groups = new ArrayList<BlotSet>();

	public void draw(Canvas c) {
		for (BlotSet blotGroup : groups) {
			blotGroup.draw(c);
		}
	}

	/*
	public void updateDraw(Canvas c, int width, int height) {
		@SuppressWarnings("unchecked")
		ArrayList<BlotSet> newGroups = (ArrayList<BlotSet>) groups.clone();
		for (BlotSet blotGroup : groups) {
			blotGroup.stepTransitions();
			if (blotGroup.isFinished()) {
				newGroups.remove(blotGroup);
				BlotSet newBlotGroup = new BlotSet(width, height);
				newGroups.add(newBlotGroup);
			}
		}
		groups = newGroups;
	}*/

	/*public void create(int width, int height) {
		for (int q = 0; q < GROUP_COUNT; q++) {
			BlotSet blotSet = new BlotSet(width, height);
			groups.add(blotSet);
		}
	}*/
}