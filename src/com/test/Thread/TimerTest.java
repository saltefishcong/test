package com.test.Thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TimerTest test = new TimerTest();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i <= 19; i++) {
					for (int j = 0; j < i; j++) {
						System.out.print(" ");
					}
					System.out.println("*");
				}
				synchronized (test) {
					test.notifyAll();
				}
				cancel();
			}
		};
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 19; i > 0; i--) {
					for (int j = 0; j < i; j++) {
						System.out.print(" ");
					}
					System.out.println("*");
				}
				synchronized (test) {
					test.notifyAll();
				}
				cancel();
			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 0);
		synchronized (test) {
			test.wait();
		}
		timer.schedule(task2, 0);
		synchronized (test) {
			test.wait();
		}
		timer.cancel();
	}

}
