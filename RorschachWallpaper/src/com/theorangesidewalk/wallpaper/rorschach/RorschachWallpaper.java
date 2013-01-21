package com.theorangesidewalk.wallpaper.rorschach;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.theorangesidewalk.wallpaper.rorschach.blot.BlotFactory;
import com.theorangesidewalk.wallpaper.rorschach.blot.Blot;
import com.theorangesidewalk.wallpaper.rorschach.blot.BlotGroup;
import com.theorangesidewalk.wallpaper.rorschach.blot.BlotGroupFactory;

public class RorschachWallpaper extends WallpaperService {

	private final Handler mHandler = new Handler();
	ArrayList<BlotGroup> blotGroups = new ArrayList<BlotGroup>();

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public Engine onCreateEngine() {
		return new RorschachEngine();
	}

	class RorschachEngine extends Engine {
		private int mCenterX;
		private int mCenterY;

		private final Runnable threadRorschach = new Runnable() {
			public void run() {
				drawFrame();
			}
		};
		private boolean mVisible;
		private Integer mHeight;
		private Integer mWidth;

		RorschachEngine() {
			drawFrame();
		}

		@Override
		public void onCreate(SurfaceHolder surfaceHolder) {
			super.onCreate(surfaceHolder);
		}

		@Override
		public void onDestroy() {
			super.onDestroy();
			mHandler.removeCallbacks(threadRorschach);
		}

		@Override
		public void onVisibilityChanged(boolean visible) {
			mVisible = visible;

			if (visible) {
				drawFrame();
			} else {
				mHandler.removeCallbacks(threadRorschach);
			}
		}

		@Override
		public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			super.onSurfaceChanged(holder, format, width, height);

			mWidth = width;
			mHeight = height;
			mCenterX = width / 2;
			mCenterY = height / 2;

			blotGroups.clear();

			BlotGroup blotGroup = BlotGroupFactory.getInstance().createBlotGroup(mCenterX, mCenterY, mWidth, mHeight);
			blotGroups.add(blotGroup);
			
		}

		@Override
		public void onSurfaceCreated(SurfaceHolder holder) {
			super.onSurfaceCreated(holder);
		}

		@Override
		public void onSurfaceDestroyed(SurfaceHolder holder) {
			super.onSurfaceDestroyed(holder);
			mVisible = false;
			mHandler.removeCallbacks(threadRorschach);
		}

		void drawFrame() {
			final SurfaceHolder holder = getSurfaceHolder();

			Canvas c = null;
			try {
				c = holder.lockCanvas();
				if (c != null) {
					// draw something
					drawFrame(c);
					// drawTouchPoint(c);
				}
			} finally {
				if (c != null)
					holder.unlockCanvasAndPost(c);
			}

			// Reschedule the next redraw
			mHandler.removeCallbacks(threadRorschach);
			if (mVisible) {
				mHandler.postDelayed(threadRorschach, 1000 / 1000);
			}
		}

		/*
		 * Draw a wireframe cube by drawing 12 3 dimensional lines between adjacent corners of the cube
		 */
		void drawFrame(Canvas c) {
			c.save();
			// c.translate(mCenterX, mCenterY);
			Paint paint = new Paint();
			paint.setColor(Color.rgb(255, 255, 255));
			paint.setStyle(Paint.Style.FILL);
			c.drawRect(-mCenterX, -mCenterY, mCenterX, mCenterY, paint);
			c.drawRect(0, 0, mWidth, mHeight, paint);

			for (BlotGroup blotGroup : blotGroups) {
				// inkblot.updateDraw(c, mCenterX, mCenterY);
				blotGroup.draw(c);
			}

			c.restore();
		}
	}
}
