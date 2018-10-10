package com.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new threadFactory("A"));
		executorService.execute(new thread());
		executorService.execute(new thread());
		executorService = Executors.newSingleThreadExecutor(new threadFactory("B"));
		executorService.execute(new thread());
		executorService.execute(new thread());
		executorService.shutdown();
	}

}

class thread extends Thread {

	volatile static int i;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName() + "  " + (++i));
	}
}

class threadFactory implements ThreadFactory {
	String name;

	public threadFactory(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		return new Thread(r, name);
	}

}
