package com.theorangesidewalk.wallpaper.rorschach.blot;

public class BlotFactory {
	private Integer width;
	private Integer height;
	private Integer centerX;
	private Integer centerY;
	static BlotFactory blotFactory;

	private BlotFactory() {

	}

	public static BlotFactory getInstance() {
		if (null == blotFactory) {
			blotFactory = new BlotFactory();
		}

		return blotFactory;
	}

	public Blot createBlotType(int width, int height, Integer blotType) {
		switch (blotType) {
		case 0:
			return new MathBlot(width, height);
		}
		return new ChaosBlot(width, height);
	}

	public Blot createRandomBlot(int width, int height) {
		return createBlotType(width, height, (int) (2 * Math.random()));
	}
}
