package com.theorangesidewalk.wallpaper.rorschach.transition;


public class TransitionFactory {
	public static final Transition UP = new UpTransition();
	public static final Transition DOWN = new DownTransition();
	public static final Transition OUT = new OutTransition();
	public static final Transition IN = new InTransition();

	private TransitionFactory() {

	}

	public static Transition createTransition() {
		Transition transition;
		int randomTransition = (int) (Math.random() * 3);
		int velocity = (int) (Math.random() * 3) + 1;

		switch (randomTransition) {
		case 0:
			transition = new UpTransition();
			break;
		case 1:
			transition = new DownTransition();
			break;
		case 2:
			transition = new OutTransition();
			break;
		default:
			transition = new InTransition();
		}

		transition.setVelocity(velocity);
		return transition;
	}

	public static Transition createColorTransition() {
		Transition transition;
		int velocity = (int) (Math.random() * 5) + 1;
		int randomTransition = (int) (Math.random() * 2);

		switch (randomTransition) {
		case 0:
			transition = new FadeInTransition();
			break;
		default:
			transition = new FadeOutTransition();
		}

		transition.setVelocity(velocity);
		return transition;
	}

}
