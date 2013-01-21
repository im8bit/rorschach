package com.theorangesidewalk.wallpaper.rorschach.transition;

import java.util.ArrayList;

import android.graphics.Color;

import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;

public class FadeOutTransition implements Transition {
	private int colorRGB = 0;
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
				colorRGB++;
				if (colorRGB >= 255) {
					colorRGB = 255;
					finish();
				}
				blot.setColor(Color.rgb(colorRGB, colorRGB, colorRGB));
			}
		}
	}

	public boolean isFinished() {
		return finished;
	}
}
