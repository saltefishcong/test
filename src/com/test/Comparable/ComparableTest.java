package com.test.Comparable;

public class ComparableTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shape[] shape = { new Circle<Double>(2.0), new Rectangle<>(2.0, 3.0), new Circle<Double>(4.0) };
		Rectangle[] rectangles = { new Rectangle<Double>(100, 2), new Rectangle<Double>(1, 2) };
		System.out.println(ComparableUtils.findMaxs(rectangles) + " ");
	}
}
