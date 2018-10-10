package com.test.HelloWord;

public class classdemo<T> {

	private String name;

	private T classno;

	private T age;

	private final String on = "123";

	private static double[] book = new double[5];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		classdemo<Integer> demo = new classdemo<Integer>();
		System.out.println(demo.name);
	}

	public T getClassno() {
		return classno;
	}

	public T getAge() {
		return age;
	}

	public void setInfo(String name, T age) throws RuntimeException {
		this.name = name;
		this.age = age;
	}

	public classdemo(String name, T age, T classno) {
		this.name = name;
		this.age = age;
		this.classno = classno;
	}

	public classdemo() {

	}

	private final class pen {
		private T a;
		private final String color = "blue";

		private pen() {
		}

		public void info() {
			System.out.println("pen color is " + color);
		}
	}
}
