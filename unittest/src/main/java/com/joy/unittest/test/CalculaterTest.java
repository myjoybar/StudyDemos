package com.joy.unittest.test;

import junit.framework.Assert;

/**
 * Created by joybar on 2018/12/11.
 * https://blog.csdn.net/qq_17766199/article/details/78243176
 *
 */

public class CalculaterTest {
	Calculater calculater = new Calculater();

	@org.junit.Test
	public void testAdd() {
		int a = 1;
		int b = 2;

		int result = calculater.add(a, b);

		Assert.assertEquals(result, 4); // 验证result==3，如果不正确，测试不通过
	}
}


