package com.example.joybar.myaskunagjia.demo.md.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by joybar on 2018/11/2.
 */

public class PathPainter1 extends View {

	private PathMeasure mPathMeasure;
	private Path mPath;
	private Paint mPaint;


	private float mAnimatorValue = 0;

	private float mLength;


	private float[] pos;
	private float[] tan;


	public PathPainter1(Context context) {
		super(context);

	}

	public PathPainter1(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPathMeasure = new PathMeasure();
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(5);
		mPath = new Path();
		mPath.addCircle(400, 400, 100, Path.Direction.CW);



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
		valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
		valueAnimator.start();


		pos = new float[2];//点的坐标
		tan = new float[2];//直角三角形两个的直角边


	}

	public PathPainter1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//canvas.drawPath(mPath, mPaint);




		//获取在动画某一个时刻点的坐标及正切值
		mPathMeasure.getPosTan(mLength * mAnimatorValue,pos,tan);

		float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//		Log.e("degrees","&&&"+degrees+"--->"+Math.atan2(tan[1], tan[0])+"--->tan[1]= "+tan[1]+"---tan[0]= "+tan[0]+"---pos[0] " +
//				"="+pos[0]+"---pos[1] ="+pos[1]);


		for (int i = 0; i < mPathMeasure.getLength(); i = i + 100) {
			int distance = i ;
			mPathMeasure.getPosTan(distance, pos, tan);
			Log.d("degrees", "measure.getLength()=" + mPathMeasure.getLength()+",distance=" + distance+",X=" + pos[0]+"，Y=" + pos[1]);

		}


		canvas.save();
		canvas.translate(-300, -300);//将坐标系移动到定点

		canvas.translate(getWidth()/2, getHeight()/2);//将坐标系移动到控件的中心位置

		canvas.drawPath(mPath, mPaint); //画了一个圆

		canvas.drawCircle(pos[0], pos[1], 10, mPaint);//在路径的点上绘制一个小圆

		canvas.rotate(degrees);//将画布旋转 此时坐标系也跟着旋转
		canvas.drawLine(0, -200, 100, -200, mPaint);//绘制一段长度为100的正切线 200是圆的半径
		canvas.restore();


	}
}
