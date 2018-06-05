package com.test.Comparable;

/**
 * ClassName: Circle.java
 * 
 * @author cong
 * @date 2018年6月4日
 * @param <T>
 */
public class Circle<T> extends Shape<T> { // 圆形类
	/** 圆形半径 */
	private double radius;

	public Circle(double radius) { // 构造方法传进构造圆形的半径
		// TODO Auto-generated constructor stub
		this.radius = radius;
	}

	@Override
	public double getArea() { // 返回圆形类的面积
		// TODO Auto-generated method stub
		return radius * Math.PI;
	}

}
