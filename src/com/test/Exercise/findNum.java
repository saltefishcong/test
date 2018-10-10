package com.test.Exercise;

public class findNum<T> extends Nums<T> {

	private double num;

	public findNum(double num) {
		// TODO Auto-generated constructor stub
		this.num = num;
	}

	@Override
	public Double getNum() {
		// TODO Auto-generated method stub
		return num * 3.14;
	}

}

abstract class Nums<T> implements Comparator<T> {

	@Override
	public int compare(T o, T x) {
		// TODO Auto-generated method stub
		if (o instanceof Nums && x instanceof Nums) {
			Nums num = (Nums) o;
			Nums num2 = (Nums) x;
			if (num2.getNum() > num.getNum()) {
				return 1;
			} else {
				return -1;
			}
		}
		return 0;
	}

	public abstract Double getNum();
}

interface Comparator<T> {
	int compare(T o, T x);
}
