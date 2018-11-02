package com.example.joybar.myaskunagjia.demo.md.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by joybar on 2018/11/2.
 */

public class PathPainter  extends View {

	private PathMeasure mPathMeasure;
	private Path mPath;
	private Paint mPaint;


	private float mAnimatorValue = 0;

	private Path mDst;
	private float mLength;

	public PathPainter(Context context) {
		super(context);

	}

	public PathPainter(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPathMeasure = new PathMeasure();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
		mPath = new Path();
		mPath.addCircle(400, 400, 100, Path.Direction.CW);

		mDst = new Path();


		mPathMeasure.setPath(mPath, true);
		mLength = mPathMeasure.getLength();



		final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				mAnimatorValue = (float) valueAnimator.getAnimatedValue();
				invalidate();
			}
		});
		valueAnimator.setDuration(2000);
		valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
		valueAnimator.start();


	}

	public PathPainter(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//canvas.drawPath(mPath, mPaint);

		mDst.reset();
		// 硬件加速的BUG
		mDst.lineTo(0,0);

		float stop = mLength * mAnimatorValue;
		//float start = 0;
		float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
		//截取 mPath 中的一段 到 mDst 中
		mPathMeasure.getSegment(start, stop, mDst, true);
		canvas.drawPath(mDst, mPaint);

	}
}
