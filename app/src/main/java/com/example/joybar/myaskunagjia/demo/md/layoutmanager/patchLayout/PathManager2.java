package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.graphics.Path;
import android.graphics.PathMeasure;

import java.util.ArrayList;


/**
 * Created by joybar on 2018/11/1.
 */

public class PathManager2 {
	private Path path;
	private ArrayList<Point> points;
	PathMeasure measure;

	public PathManager2(Path path) {
		this.path = path;
		//path：需要测量的path ,forceClosed：是否关闭path
//		pos = new float[2];//点的坐标
//		tan = new float[2];//直角三角形两个的直角边

		measure = new PathMeasure(path, false);
		initPoints();

	}


	/**
	 * item 随路径移动时，获取旋转角度
	 *
	 * @param distance
	 * @return
	 */
	public float getPathMoveDegrees(int distance) {
//		measure.getPosTan(distance, pos, tan);
//		float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
//		return degrees;
		return 0;
	}


	public ArrayList<Point> getPoints() {
		return points;
	}


	public int getLength() {
		return (int) measure.getLength();
	}


	private void initPoints() {

		points = new ArrayList<>(0);
		float[] pos = new float[2];//点的坐标
		float[] tan = new float[2];//直角三角形两个的直角边

		int pointNumber = (int) (measure.getLength() * 2);

		for (int i = 0; i < pointNumber; i++) {
			float distance = i * 2;
			measure.getPosTan(distance, pos, tan);
//			Log.e(CustomLayouManager2ScrollYPatch.TAG, "i = " + i + ", getPatchLength=" + measure.getLength() +
//					"distance=" + distance + ",pos[0]=" + pos[0] + ",pos[1]=" + pos[1]);

			float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
			Point point = new Point();
			point.setIndex(i);
			point.setX(pos[0]);
			point.setY(pos[1]);
			point.setTan(tan);
			point.setDegrees(degrees);
			points.add(point);
		}

	}


}
