package com.test.Thread;

public class PC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		letterShared shared = new letterShared();
		new Producer(shared).start();
		new comsumer(shared).start();
	}

}

class letterShared {

	private char c;

	private boolean writeable = true;

	synchronized void setSharedChar(char c) {
		while (!writeable) {
			try {
				System.out.println(Thread.currentThread().getName() + "  " + c);
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		this.c = c;
		this.writeable = false;
		notify();
	}

	synchronized char getSharedChar() {
		while (writeable) {
			try {
				System.out.println(Thread.currentThread().getName() + "  " + c);
				wait();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		this.writeable = true;
		notify();
		return c;
	}
}

class Producer extends Thread {

	private letterShared shared;

	public Producer(letterShared shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			synchronized (shared) {
				shared.setSharedChar(ch);
				System.out.println("Producer 生产了 字母 " + ch);
			}
		}
	}
}

class comsumer extends Thread {

	private letterShared shared;

	public comsumer(letterShared shared) {
		this.shared = shared;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		char a;
		do {
			synchronized (shared) {
				a = shared.getSharedChar();
				System.out.println("消费者 获取了 字母  " + a);
			}
		} while (a != 'Z');
	}

}