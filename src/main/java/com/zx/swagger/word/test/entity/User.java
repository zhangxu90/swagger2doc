package com.zx.swagger.word.test.entity;

public class User {
	private String name;
	private int age;
	private Inside inside;

	public Inside getInside() {
		return inside;
	}

	public void setInside(Inside inside) {
		this.inside = inside;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
