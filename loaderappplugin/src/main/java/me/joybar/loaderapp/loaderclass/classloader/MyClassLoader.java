package me.joybar.loaderapp.loaderclass.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by joybar on 2017/8/15.
 */

public class MyClassLoader  extends ClassLoader {

	private String mClassPath; // Class存放的的目录

	public MyClassLoader(String classPath) {
		mClassPath = classPath;
	}

	@Override
	public Class<?> findClass(String name) {

		File clsFile = new File(mClassPath, getClassName(name));

		FileInputStream fis = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] b = null;
		try {
			fis = new FileInputStream(clsFile);

			int data = 0;

			while ((data = fis.read()) != -1) {
				baos.write(data);
			}
			b = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (baos != null) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		// 上面是基本的数据流操作, 把Class文件转换成二进制流

		return defineClass(name, b, 0, b.length);
	}

	/**
	 * 获取Class的完整文件名
	 *
	 * @param name
	 * @return
	 */
	private String getClassName(String name) {
		String clsName = null;

		if (name != null) {
			int lastIndexOf = name.lastIndexOf(".");
			if (lastIndexOf == -1) {
				clsName = name + ".class";
			} else {
				clsName = name.substring(lastIndexOf + 1) + ".class";
			}
		}

		return clsName;
	}

}
