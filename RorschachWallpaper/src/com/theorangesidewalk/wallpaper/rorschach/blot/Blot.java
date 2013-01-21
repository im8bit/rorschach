package com.theorangesidewalk.wallpaper.rorschach.blot;

import android.graphics.Canvas;

public interface Blot {
	boolean draw(Canvas c);

	boolean drawMirrored(Canvas c);

	void setColor(Integer color);
	
	Integer getColor();
}
