package com.example.joybar.myaskunagjia.demo.md.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.View;

/**
 * Created by joybar on 2018/11/2.
 */

public class PathPainter  extends View {

	private PathMeasure mPathMeasure;
	private Path mPath;
	private Paint mPaint;

	public PathPainter(Context context) {
		super(context);
		mPathMeasure = new PathMeasure();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
		mPath = new Path();
		mPath.addCircle(400, 400, 100, Path.Direction.CW);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
}
