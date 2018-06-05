package com.test.Comparable;

public abstract class Shape<T> implements Comparable<T> { // 图形类

	@Override
	public int compareTo(T o) {
		// TODO Auto-generated method stub
		if (o instanceof Shape) { // 判断传进的对象是否为Shape的子类
			Shape shape = (Shape) o; // 进行类型转换
			System.out.println(this.getArea() + "  " + this);
			System.out.println(shape.getArea() + "  " + shape);
			if (this.getArea() > shape.getArea()) {
				// 判断当前对象(调用compareTo方法的对象)的面积是否大于传递对象(用于和当前对象比较大小的对象)的图形面积
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}

	public abstract double getArea(); // 定义获取面积抽象方法,由继承的子类实现
}
