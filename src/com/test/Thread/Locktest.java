package com.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locktest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lock lock = new ReentrantLock();
		Bank bank = new Bank(lock);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 3; i++) {
			for (int o = 0; o < 5; o++) {
				executorService.execute(new customero(bank));
			}
			executorService.execute(new vip(bank, lock));
			executorService.execute(new vip(bank, lock));
			executorService.execute(new vip(bank, lock));
			new Bad(bank, lock).start();
		}
		executorService.shutdown();
	}
}

class Bank {
	Integer number = 0;
	Lock lock;

	public Bank(Lock lock) {
		this.lock = lock;
	}

	public void setNumber() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " 获取单号 " + number++);
			Thread.sleep(10);
			System.out.println("普通用户取款完成!  " + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.unlock();
	}
}

class customero extends Thread {
	Bank bank;

	public customero(Bank bank) {
		// TODO Auto-generated constructor stub
		this.bank = bank;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		bank.setNumber();
	}
}

class vip extends Thread {
	Bank bank;
	Lock lock;

	public vip(Bank bank, Lock lock) {
		this.bank = bank;
		this.lock = lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (lock.tryLock()) {
			System.out.println("vip 会员先取款  " + Thread.currentThread().getName() + "   " + (bank.number++));
			try {
				Thread.sleep(2000);
				System.out.println("vip 会员插队完成! " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		} else {
			System.out.println("vip 会员不能插队 " + Thread.currentThread().getName());
		}
	}
}

class Bad extends Thread {
	Bank bank;
	Lock lock;

	public Bad(Bank bank, Lock lock) {
		this.bank = bank;
		this.lock = lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			lock.lockInterruptibly();
			System.out.println("坏人开始取款!  " + Thread.currentThread().getName() + "  " + (bank.number++));
			Thread.sleep(2000);
			System.out.println("坏人取款完成!  " + Thread.currentThread().getName());
			lock.unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("坏人插不了队 !  " + Thread.currentThread().getName());
		}
	}
}