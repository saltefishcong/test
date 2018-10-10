package com.test.Thread;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadWriteLock lock = new ReentrantReadWriteLock();
		Lock rLock = lock.readLock();
		Lock wLock = lock.writeLock();
		Mobile mobile = new Mobile(rLock, wLock);
		new userRead(rLock, "小明", mobile).start();
		new userRead(rLock, "小红", mobile).start();
		new userRead(rLock, "小风", mobile).start();
		new userRead(rLock, "大哥", mobile).start();
		new userWrite(wLock, "小明", mobile, 1.0).start();
		new userWrite(wLock, "小弟", mobile, 1.0).start();
		new userRead(rLock, "小弟", mobile).start();
		new userWrite(wLock, "小红", mobile, 1.0).start();
		new userRead(rLock, "A", mobile).start();
		new userRead(rLock, "小明", mobile).start();
		new userRead(rLock, "小红", mobile).start();
		new userRead(rLock, "小弟", mobile).start();
	}

}

class Mobile {

	volatile static double[] callchargs = { 10.01, 22.3, 31.9, 47, 26.5, 30, 49, 3.5, 0.6 };
	static String[] user = { "小明", "小红", "小风", "大哥", "小弟", "A", "B", "C", "D" };

	private Lock lock;

	private Lock lock2;

	public Mobile(Lock lock, Lock lock2) {
		// TODO Auto-generated constructor stub
		this.lock = lock;
		this.lock2 = lock2;
	}

	public double readCall(String username) {
		lock.lock();
		double chargs = 0;
		for (int i = 0; i < user.length; i++) {
			if (username.equals(user[i])) {
				chargs = callchargs[i];
			}
		}
		lock.unlock();
		return chargs;
	}

	public void addChargs(double money, String username) {
		for (int i = 0; i < user.length; i++) {
			if (username.equals(user[i])) {
				lock2.lock();
				callchargs[i] += money;
				lock2.unlock();
			}
		}
	}

	public void lessChargs(double money, String username) {
		for (int i = 0; i < user.length; i++) {
			if (username.equals(user[i])) {
				lock2.lock();
				callchargs[i] -= money;
				lock2.unlock();
			}
		}
	}
}

class userRead extends Thread {
	Lock lock;

	String name;

	Mobile mobile;

	public userRead(Lock lock, String name, Mobile mobile) {
		// TODO Auto-generated constructor stub
		super(name);
		this.lock = lock;
		this.name = name;
		this.mobile = mobile;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		lock.lock();
		double money = mobile.readCall(name);
		lock.unlock();
		System.out.println(name + " 的话费还剩下 " + money + "  元!");
	}
}

class userWrite extends Thread {

	Lock lock;

	String name;

	Mobile mobile;

	double money;

	public userWrite(Lock lock, String name, Mobile mobile, double money) {
		// TODO Auto-generated constructor stub
		super(name);
		this.lock = lock;
		this.name = name;
		this.mobile = mobile;
		this.money = money;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int x = new Random().nextInt(3);
		lock.lock();
		if (x < 2) {
			mobile.lessChargs(money, name);
			System.out.println(name + "  使用了 " + money + "  话费!");
		} else {
			mobile.addChargs(money, name);
			System.out.println(name + " 充值了 " + money + "  话费!");
		}
		lock.unlock();
	}
}
