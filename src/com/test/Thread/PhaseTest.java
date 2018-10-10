package com.test.Thread;

import java.util.concurrent.Semaphore;

public class PhaseTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Semaphore semaphore = new Semaphore(1);
		Semaphore semaphore2 = new Semaphore(0);
		Shared shared = new Shared();
		new Producer2(shared, semaphore, semaphore2).start();
		new comsumer2(shared, semaphore, semaphore2).start();
	}
}

class Shared {

	private char c;

	void setSharedChar(char c) {
		this.c = c;
	}

	char getSharedChar() {
		return c;
	}
}

class Producer2 extends Thread {

	private Shared shared;

	Semaphore semaphore;

	Semaphore semaphore2;

	public Producer2(Shared shared, Semaphore semaphore, Semaphore semaphore2) {
		this.shared = shared;
		this.semaphore = semaphore;
		this.semaphore2 = semaphore2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			shared.setSharedChar(ch);
			System.out.println("Producer 生产了 字母 " + ch);
			semaphore2.release();
		}
	}
}

class comsumer2 extends Thread {

	private Shared shared;

	Semaphore semaphore;

	Semaphore semaphore2;

	public comsumer2(Shared shared, Semaphore semaphore, Semaphore semaphore2) {
		this.shared = shared;
		this.semaphore = semaphore;
		this.semaphore2 = semaphore2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		char a;
		do {
			try {
				semaphore2.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a = shared.getSharedChar();
			System.out.println("消费者 获取了 字母  " + a);
			semaphore.release();
		} while (a != 'Z');
	}

}
