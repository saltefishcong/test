package com.test.Thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		CyclicBarrier cBarrier = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("打开关卡!");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread().getName() + " 到达");
				try {
					cBarrier.await();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 继续前进");
			}
		};
		Thread thread = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		Thread thread3 = new Thread(runnable);
		Thread thread4 = new Thread(runnable, "thread4");
		executorService.execute(thread);
		executorService.execute(thread2);
		executorService.execute(thread3);
		cBarrier.reset();
		executorService.execute(thread4);
		executorService.execute(thread3);
		executorService.execute(thread2);
		executorService.shutdown();
	}

}
