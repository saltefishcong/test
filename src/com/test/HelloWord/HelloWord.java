package com.test.HelloWord;

public class HelloWord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class A {

	int x, y;

	public A(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return x * 3 + y * 4;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		// if (this == obj) {
		// System.out.println(this + " " + obj + " 相等 ");
		// return true;
		// }
		// if (obj == null) {
		// System.out.println(obj + " 等于空");
		// return false;
		// }
		// if (getClass() != obj.getClass()) {
		// System.out.println(obj + "的类 和 " + this + " 的类不相等 ");
		// return false;
		// }
		// A a = (A) obj;
		return true;
	}
}
