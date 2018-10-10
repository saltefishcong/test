package com.test.Thread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {
	volatile static Integer integer = 0;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ScheduledExecutorService schediled = Executors.newSingleThreadScheduledExecutor();
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				return ++integer;
			}
		};

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("run" + (integer++) + "  " + new Date());
			}
		};
		ScheduledFuture future = schediled.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);

	}

}
