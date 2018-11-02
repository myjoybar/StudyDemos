package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

import android.graphics.Path;
import android.graphics.PathMeasure;


/**
 * Created by joybar on 2018/11/1.
 */

public class PathManager {
	Path path;
	PathMeasure measure;

	public PathManager(Path path) {
		this.path = path;
		//path：需要测量的path ,forceClosed：是否关闭path
		measure = new PathMeasure(path, false);
	}

	/**
	 * 获取路径长度
	 * @return
	 */
	float getPatchLength() {
		return measure.getLength();
	}

	/**
	 * item 随路径移动时，获取旋转角度
	 * @param distance
	 * @return
	 */
	float getPathMoveDegrees(int distance) {
		float[] pos =  new float[2];
		float[] tan =  new float[2];
		measure.getPosTan(distance, pos, tan);
		float degrees = (float) (Math.atan2(tan[1], tan[0]) * 180.0 / Math.PI);
		return degrees;
	}

}
