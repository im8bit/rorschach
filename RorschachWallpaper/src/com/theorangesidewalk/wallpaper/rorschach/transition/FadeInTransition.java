package com.theorangesidewalk.wallpaper.rorschach.transition;

import java.util.ArrayList;

import android.graphics.Color;

import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;

public class FadeInTransition implements Transition {
	private int colorRGB = 255;
	private int color;
	private int velocity;
	private boolean finished;

	public void startUp(ArrayList<Blot> blots) {
		finished = false;
		color = Color.rgb(colorRGB, colorRGB, colorRGB);
		for (Blot blot : blots) {
			blot.setColor(color);
		}
	}

	public void finish() {
		finished = true;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void step(ArrayList<Blot> blots) {
		if (!isFinished()) {
			for (Blot blot : blots) {
				colorRGB--;
				if (colorRGB <= 0) {
					colorRGB = 0;
					finish();
				}
				blot.setColor(Color.rgb(colorRGB, colorRGB, colorRGB));
			}
		}
	}

	public boolean isFinished() {
		return finished;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
