package com.joybar.appcommponentlib.router3;

/**
 * Created by joybar on 04/11/2017.
 */

public class Rule3 {

	private String module = "";
	private String path = "";
	private Class classz = null;

	public Rule3(String module, String path) {
		this.module = module;
		this.path = path;
	}

	public Rule3(String module, String path, Class classz) {
		this.module = module;
		this.path = path;
		this.classz = classz;
	}

	@Override
	public int hashCode() {
		return module.hashCode() + path.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this) {
			return true;
		}
		if (obj instanceof Rule3) {
			Rule3 rule = (Rule3) obj;
			return (module.equals(rule.module) && path.equals(rule.path) && module.equals(rule.module));
		}
		return super.equals(obj);
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Class getClassz() {
		return classz;
	}

	public void setClassz(Class classz) {
		this.classz = classz;
	}
}
