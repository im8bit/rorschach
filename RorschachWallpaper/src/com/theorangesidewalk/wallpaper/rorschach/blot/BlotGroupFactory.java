package com.theorangesidewalk.wallpaper.rorschach.blot;

public class BlotGroupFactory {
	static BlotGroupFactory blotGroupFactory;

	public static BlotGroupFactory getInstance() {
		if (null == blotGroupFactory) {
			blotGroupFactory = new BlotGroupFactory();
		}

		return blotGroupFactory;
	}

	public BlotGroup createBlotGroup(Integer centerX, Integer centerY, Integer width, Integer height) {		
		return new BlotGroup(centerX, centerY, width, height);
	}
}
