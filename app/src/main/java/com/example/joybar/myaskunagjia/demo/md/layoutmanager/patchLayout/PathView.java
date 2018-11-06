package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.Log;
import android.view.View;

/**
 * Created by joybar on 2018/11/5.
 */

public class PathView extends View {

	private static final String TAG = "PathView";
	Path path;

	public PathView(Context context, Path path) {
		super(context);
		this.path = path;

	}

	private void test() {
		PathMeasure measure = new PathMeasure(path, false);
		float[] pos = new float[2];//点的坐标
		float[] tan = new float[2];//直角三角形两个的直角边

		for (int i = 0; i < measure.getLength(); i = i + 100) {
			int distance = i ;
			measure.getPosTan(distance, pos, tan);
			Log.d(TAG, "measure.getLength()=" + measure.getLength()+",distance=" + distance+",X=" + pos[0]+"，Y=" + pos[1]);

		}


	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Paint paint = new Paint();
		paint.setColor(Color.RED);  //设置画笔颜色
		paint.setStyle(Paint.Style.STROKE);//填充样式改为描边
		paint.setStrokeWidth(5);//设置画笔宽度

		test();

		canvas.drawPath(path, paint);

	}
}
