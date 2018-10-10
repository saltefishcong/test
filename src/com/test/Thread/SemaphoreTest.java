package com.test.Thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(5);
		Semaphore semaphore = new Semaphore(5);
		track tr = new track(semaphore);
		for (int i = 0; i < 20; i++)
			service.execute(new student(tr));

		service.shutdown();
	}
}

class student extends Thread {
	volatile static Integer i = 0;
	track tr;

	public student(track tr) {
		// TODO Auto-generated constructor stub
		this.tr = tr;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int num = 0;
		synchronized (student.class) {
			num = i;
			i++;
		}
		int x = tr.gettark();
		System.out.println("学生  " + num + "获取跑道    " + x);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tr.releasetark(x);
		System.out.println("学生  " + num + "释放跑道    " + x);
	}

}

class track {
	Semaphore semaphore;

	static volatile boolean[] flag = new boolean[5];

	public track(Semaphore semaphore) {
		// TODO Auto-generated constructor stub
		this.semaphore = semaphore;
	}

	public Integer gettark() {
		for (int i = 0; i < flag.length; i++) {
			synchronized (flag) {
				if (flag[i] == false) {
					flag[i] = true;
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return i;
				}
			}
		}
		return null;
	}

	public void releasetark(int i) {
		synchronized (flag) {
			flag[i] = false;
			semaphore.release();
		}
	}

}
