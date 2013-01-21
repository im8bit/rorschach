package com.theorangesidewalk.wallpaper.rorschach.blot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

public class ChaosBlot implements Blot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double CHILD_RADIUS_DECREASE_X = 0.5;
	private static final double CHILD_RADIUS_DECREASE_Y = 0.5;
	private Paint paint;
	private Bitmap b;
	private Canvas c;
	private Integer color = Color.BLACK;

	public ChaosBlot(Integer width, Integer height) {
		this(0, 0, width, height);
	}

	public ChaosBlot(Integer centerX, Integer centerY, Integer width, Integer height) {
		b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		c = new Canvas(b);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);

		// createCircle(canvas, 0, 0, 200, 200, 50, 50, 0.5, 4, 0, Math.PI / 4, 2);
		// createCircle(canvas, 0, 0, 200, 200, 50, 50, 0.5, 0.5, 1, 4, 0, 0, 2);

		Integer startRadiusX = (int) ((width / 5) * Math.random() * 2);
		Integer startRadiusY = (int) ((height / 5) * Math.random() * 2);
		create(0, 0, centerX, centerY, startRadiusX, startRadiusY, 0.5, 0.5, 0.7, 4, 0, 0, 5);

		for (int q = 0; q < 3; q++) {
			create(0, 0, centerX, centerY, startRadiusX, startRadiusY, 0.5, 0.5, 0.7, 4, 0, 0, 7);
		}
	}

	public boolean draw(Canvas c) {
		Paint paint = new Paint();
		ColorFilter colorFilter = new LightingColorFilter(getColor(), 1);
		paint.setColorFilter(colorFilter);
		paint.setAlpha(100);

		c.drawBitmap(b, 0, 0, paint);

		return true;
	}

	private void create(int parentCenterX, int parentCenterY, int centerX, int centerY, int radiusX, int radiusY, double childRadiusDecreaseX,
			double childRadiusDecreaseY, double randomChildChance, int finalStep, int currentStep, double angle, int sideCount) {
		if (currentStep > finalStep) {
			return;
		}

		// g2.drawOval(centerX, centerY, 2, 2);

		int x, y;
		int childX, childY;
		int childRadiusX = radiusX;
		int childRadiusY = radiusY;

		double splatStart = -Math.PI;
		double splatEnd = Math.PI;

		if (currentStep > 0) {
			splatStart = -Math.PI;
			splatEnd = 0;
		}

		int oldX = -radiusX;
		int oldY = 0;
		int rotatedX = (int) (oldX * Math.cos(angle) - oldY * Math.sin(angle));
		int rotatedY = (int) (oldX * Math.sin(angle) + oldY * Math.cos(angle));
		oldX = centerX + rotatedX;
		oldY = centerY + rotatedY;

		double rotationAngle = angle;

		for (double q = splatStart; q < splatStart + (Math.PI * 2); q += Math.PI / sideCount) {
			x = (int) (Math.cos(q) * radiusX);
			y = (int) (Math.sin(q) * radiusY);
			rotatedX = (int) (x * Math.cos(rotationAngle) - y * Math.sin(rotationAngle));
			rotatedY = (int) (x * Math.sin(rotationAngle) + y * Math.cos(rotationAngle));
			x = centerX + rotatedX;
			y = centerY + rotatedY;

			c.drawLine(oldX, oldY, x, y, paint);

			fillTriangle(oldX, oldY, x, y, centerX, centerY);

			oldX = x;
			oldY = y;

			// Recursion ends here in order to avoid some math
			if (currentStep <= finalStep && Math.random() < randomChildChance) {
				if (0 == currentStep || (q >= (splatStart + (Math.PI / 2)) && q <= (splatEnd + (Math.PI / 2)))) {
					// childX = (int) (-childRadiusX * childRadiusDecreaseX); //Set vertex of child same as vertex of parent
					// childX = (int) (childRadiusX * childRadiusDecreaseX * Math.random() * 1.2); //Random amount from vertex to vertex to inside
					// How inside is child shape respect to parent (0 for the center to be on the vertex of parent)
					childX = (int) (childRadiusX * childRadiusDecreaseX * Math.random());
					childY = 0;
					rotatedX = (int) (childX * Math.cos(q + rotationAngle) - childY * Math.sin(q + rotationAngle));
					rotatedY = (int) (childX * Math.sin(q + rotationAngle) + childY * Math.cos(q + rotationAngle));
					childX = x - rotatedX;
					childY = y - rotatedY;

					create(centerX, centerY, childX, childY, (int) (childRadiusX * childRadiusDecreaseX), (int) (childRadiusY * childRadiusDecreaseY),
							childRadiusDecreaseX, childRadiusDecreaseY, 0.5, finalStep, currentStep + 1, q + rotationAngle, sideCount);
				}
			}

		}

		int finalX = (int) (Math.cos(splatStart) * radiusX);
		int finalY = (int) (Math.sin(splatStart) * radiusY);
		rotatedX = (int) (finalX * Math.cos(rotationAngle) - finalY * Math.sin(rotationAngle));
		rotatedY = (int) (finalX * Math.sin(rotationAngle) + finalY * Math.cos(rotationAngle));
		finalX = centerX + rotatedX;
		finalY = centerY + rotatedY;

		c.drawLine(oldX, oldY, finalX, finalY, paint);
		fillTriangle(oldX, oldY, finalX, finalY, centerX, centerY);
	}

	private void fillTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
		int[] xPoints = new int[3];
		int[] yPoints = new int[3];

		xPoints[0] = x1;
		xPoints[1] = x2;
		xPoints[2] = x3;

		yPoints[0] = y1;
		yPoints[1] = y2;
		yPoints[2] = y3;

		Path path = new Path();
		// path.moveTo(-(center.x + pt.x), center.y + pt.y);
		path.moveTo(x1, y1);
		// path.lineTo(-(center.x + point.x), center.y + point.y);
		path.lineTo(x2, y2);
		path.lineTo(x3, y3);
		path.lineTo(x1, y1);

		c.drawPath(path, paint);
	}

	public boolean drawMirrored(Canvas c) {
		// TODO Auto-generated method stub
		return false;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

}