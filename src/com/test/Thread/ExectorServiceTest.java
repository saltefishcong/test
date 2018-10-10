package com.test.Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExectorServiceTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newFixedThreadPool(3);
		Future<Integer> future = executor.submit(new call());
		boolean flag = future.cancel(false);
		System.out.println(flag + "  " + future.isDone() + "  " + future.isCancelled());
		executor.shutdown();
		if (!future.isCancelled())
			System.out.println(future.get());
	}

}

class thoead extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("等待时间太久,被中断 !");
		}
		System.out.println(Thread.currentThread().getName());
	}
}

class call implements Callable<Integer> {
	volatile static Integer i = 0;

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		synchronized (call.class) {
			i++;
			System.out.println(Thread.currentThread().getName() + "  " + i);
			return i;
		}
	}
}
