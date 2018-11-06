package com.example.joybar.myaskunagjia.demo.md.layoutmanager.patchLayout;

/**
 * Created by joybar on 2018/11/5.
 */

public class Point {
	float x;
	float y;
	int index;
	float[] tan;
	float degrees;


	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public float[] getTan() {
		return tan;
	}

	public void setTan(float[] tan) {
		this.tan = tan;
	}

	public float getDegrees() {
		return degrees-90;
	}

	public void setDegrees(float degrees) {
		this.degrees = degrees;
	}
}
