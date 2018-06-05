package com.test.Comparable;

/**
 * 
 * ClassName: Rectangle.java
 * 
 * @author cong
 * @date 2018年6月4日
 * @param <T>
 */
public class Rectangle<T> extends Shape<T> { // 长方形类
	/** 长方形的宽度 */
	private double width;
	/** 长方形的高度 */
	private double height;

	public Rectangle(double width, double height) { // 构造长方形传进宽度和高度
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() { // 返回长方形的面积
		// TODO Auto-generated method stub
		return width * height;
	}

}
