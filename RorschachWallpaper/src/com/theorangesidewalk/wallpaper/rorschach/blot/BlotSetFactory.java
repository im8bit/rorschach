package com.theorangesidewalk.wallpaper.rorschach.blot;

public class BlotSetFactory {
	static BlotSetFactory blotSetFactory;

	public static BlotSetFactory getInstance() {
		if (null == blotSetFactory) {
			blotSetFactory = new BlotSetFactory();
		}

		return blotSetFactory;
	}

	public BlotSet createBlotSet(int offsetX, int centerY, Integer width, Integer height) {
		return new BlotSet(offsetX, centerY, width, height);
	}
}
