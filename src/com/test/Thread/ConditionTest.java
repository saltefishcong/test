package com.test.Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Shared2 shared = new Shared2(lock, condition);
		new Producer3(shared, lock).start();
		new comsumer3(shared, lock).start();
	}

}

class Shared2 {

	private char c;

	final Lock lockl;

	final Condition condition;

	volatile static boolean flag = true;

	public Shared2(Lock lockl, Condition condition) {
		// TODO Auto-generated constructor stub
		this.lockl = lockl;
		this.condition = condition;
	}

	void setSharedChar(char c) {
		lockl.lock();
		while (!flag) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.c = c;
		flag = false;
		condition.signal();
		lockl.unlock();
	}

	char getSharedChar() {
		lockl.lock();
		while (flag) {
			try {
				condition.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		flag = true;
		condition.signal();
		lockl.unlock();
		return c;
	}
}

class Producer3 extends Thread {

	private Shared2 shared;

	final Lock lock;

	public Producer3(Shared2 shared, Lock lock) {
		this.shared = shared;
		this.lock = lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (char ch = 'A'; ch <= 'Z'; ch++) {
			lock.lock();
			shared.setSharedChar(ch);
			System.out.println("Producer 生产了 字母 " + ch);
			lock.unlock();
		}
	}
}

class comsumer3 extends Thread {

	private Shared2 shared;

	final Lock lock;

	public comsumer3(Shared2 shared, Lock lock) {
		this.shared = shared;
		this.lock = lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		char a;
		do {
			lock.lock();
			a = shared.getSharedChar();
			System.out.println("消费者 获取了 字母  " + a);
			lock.unlock();
		} while (a != 'Z');
	}

}
