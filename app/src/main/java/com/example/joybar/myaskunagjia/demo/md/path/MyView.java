package com.example.joybar.myaskunagjia.demo.md.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by joybar on 2018/11/2.
 */

public class MyView extends View {

	Context m_context;

	public MyView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		m_context = context;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//设置画笔基本属性
		Paint paint=new Paint();
		paint.setAntiAlias(true);//抗锯齿功能
		paint.setColor(Color.RED);  //设置画笔颜色
		paint.setStyle(Paint.Style.FILL);//设置填充样式   Style.FILL/Style.FILL_AND_STROKE/Style.STROKE
		paint.setStrokeWidth(5);//设置画笔宽度
		paint.setShadowLayer(10, 15, 15, Color.GREEN);//设置阴影
		canvas.drawRGB(255, 255,255);
		canvas.drawCircle(190, 200, 150, paint);
	}
}
