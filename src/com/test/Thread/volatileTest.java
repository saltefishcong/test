package com.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class volatileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		word wo = new word();
		ExecutorService service = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			service.execute(new wordThread("word" + i, wo));
		}
		service.shutdown();
	}

}

class word {
	static volatile int i = 0;

	void nextNum() {
		i++;
	}

	int getI() {
		return i;
	}
}

class wordThread extends Thread {

	word wo;

	String name;

	public wordThread(String name, word wo) {
		// TODO Auto-generated constructor stub
		super(name);
		this.wo = wo;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		wo.nextNum();
		System.out.println(Thread.currentThread().getName() + wo.getI());
	}

}