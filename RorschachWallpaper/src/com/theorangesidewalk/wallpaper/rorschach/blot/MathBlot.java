package com.theorangesidewalk.wallpaper.rorschach.blot;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.theorangesidewalk.wallpaper.rorschach.Point;

public class MathBlot implements Blot {
	public Point center = new Point();
	private ArrayList<Point> points = new ArrayList<Point>();
	private Paint paint;
	private Bitmap b;
	private Canvas c;
	private Path path;
	Integer color;

	public MathBlot(Integer width, Integer height) {
		this(0, 0, width, height);
	}

	public MathBlot(Integer centerX, Integer centerY, Integer width, Integer height) {
		b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		c = new Canvas(b);

		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);

		width /= 4;
		height /= 4;

		create(centerX, centerY, width, height, (int) (height * 0.2));

		path = new Path();
		Point pt = points.get(0);
		path.moveTo(center.x + pt.x, center.y + pt.y);

		for (Point point : points) {
			path.lineTo(center.x + point.x, center.y + point.y);
		}

		c.drawPath(path, paint);
	}

	public boolean draw(Canvas c) {
		Paint paint = new Paint();
		ColorFilter colorFilter = new LightingColorFilter(getColor(), 1);
		paint.setColorFilter(colorFilter);
		paint.setAlpha(100);

		c.drawBitmap(b, 0, 0, paint);
		return true;
	}

	private boolean create(int centerX, int centerY, int xHeightMax, int yHeightMax, int ySinMax) {
		points = new ArrayList<Point>();
		center = new Point(centerX, centerY);

		Point p0 = new Point();

		for (int q = 0; q < 360; q += 50) {
			double radians = (q * 0.0174532925);
			Point p1 = new Point();

			int xHeight = (int) (xHeightMax * Math.random());
			int yHeight = (int) (yHeightMax * Math.random());

			p1.x = (int) (xHeight * Math.cos(radians));
			p1.y = (int) (yHeight * Math.sin(radians));

			points.add(new Point(p1.x, p1.y));

			ArrayList<Point> linePoints = new ArrayList<Point>();

			if (q > 0) {
				linePoints = drawCurveAtoB3(p0, p1, ySinMax);
				p0 = linePoints.get(linePoints.size() - 1);
				points.addAll(linePoints);
			} else {
				p0.x = p1.x;
				p0.y = p1.y;
			}

			q += (int) (Math.random() * 30) + 20;

		}

		return true;
	}

	private boolean create1(int centerX, int centerY, int xHeightMax, int yHeightMax, int ySinMax) {
		points = new ArrayList<Point>();
		center = new Point(centerX, centerY);

		Point p0 = new Point();

		for (int q = 0; q < 360; q += 10) {
			double radians = (q * 0.0174532925);
			Point p1 = new Point();

			int xHeight = (int) (xHeightMax);
			int yHeight = (int) (yHeightMax);

			p1.x = (int) (xHeight * Math.cos(radians));
			p1.y = (int) (yHeight * Math.sin(radians));

			points.add(new Point(p1.x, p1.y));

		}

		return true;
	}

	public Point drawCurveAtoB(Canvas c, Point p0, Point p1) {
		Point last = new Point();
		int hSegments = 10;
		float dx = (p1.x - p0.x) / hSegments;
		float da = (float) (Math.PI / hSegments);
		float ymid = (p0.y + p1.y) / 2;
		// float ampl = (p0y - p1.y) / 2;
		float ampl = (p0.y - p1.y);

		Point pt0 = new Point();
		Point pt1 = new Point();

		pt0.x = p0.x;
		pt0.y = p0.y;

		for (int j = 0; j < hSegments + 1; ++j) {

			pt1.x = (int) (p0.x + j * dx);
			pt1.y = (int) (ymid + ampl * Math.cos(da * j));

			c.drawLine(xToScreen(pt0.x), yToScreen(pt0.y), xToScreen(pt1.x), yToScreen(pt1.y), paint);

			pt0.x = pt1.x;
			pt0.y = pt1.y;

			last.x = pt1.x;
			last.y = pt1.y;

		}
		return last;

	}

	static public ArrayList<Point> drawCurveAtoB3(Point p0, Point p1, int ySinMax) {
		ArrayList<Point> points = new ArrayList<Point>();

		int difX = p1.x - p0.x;
		int difY = p1.y - p0.y;
		float p = (float) difY / (float) difX;
		float ang = (float) Math.atan(-(1 / p));

		int hSegments = 15;
		float dx = (float) (difX / hSegments);
		float dy = (float) (difY / hSegments);
		float da = (float) ((Math.PI * 2) / hSegments);

		float ampl = (float) (ySinMax * Math.random() + 5);

		Point pt = new Point();
		pt.x = p0.x;
		pt.y = p0.y;

		points.add(pt);

		for (int j = 0; j < hSegments + 1; ++j) {
			pt = new Point();
			pt.x = (int) (p0.x + j * dx);
			pt.y = (int) (p0.y + j * dy);

			int height = (int) (ampl * Math.sin(da * j));
			Point point1 = new Point(height, 0);
			Point point2 = rotatePoint(point1, (float) ang);
			pt.x += point2.x;
			pt.y += point2.y;

			if (j > 0) {
				points.add(pt);
			}
		}
		return points;
	}

	public static Point rotatePoint(Point p, float ang) {
		Point outPoint = new Point();
		double pt1xCos = p.x * Math.cos(ang);
		double pt1ySin = p.y * Math.sin(ang);
		double pt1xSin = p.x * Math.sin(ang);
		double pt1yCos = p.y * Math.cos(ang);

		outPoint.x = (int) (pt1xCos - pt1ySin);
		outPoint.y = (int) (pt1xSin + pt1yCos);

		return outPoint;
	}

	int xToScreen(int x) {
		return center.x + x;
	}

	int yToScreen(int y) {
		return center.y - y;
	}

	public short[] generateIndex(int vertexNumber) {
		short[] index = new short[vertexNumber + 1];

		int index2 = 0;
		index[index2++] = 0;
		for (short q = 1; q < vertexNumber; q += 1) {
			index[index2++] = q;
		}

		index[index2++] = 1;

		for (int i : index) {
			System.out.println(i);
		}
		return index;
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