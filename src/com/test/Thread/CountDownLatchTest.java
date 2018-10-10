package com.test.Thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CountDownLatch teacherCount = new CountDownLatch(1);
		CountDownLatch studentCount = new CountDownLatch(3);
		CountDownLatch classCount = new CountDownLatch(1);
		ExecutorService service = Executors.newFixedThreadPool(5);
		// 如果线程数量不够5个的话,那么学生任务永远完成不了,导致接下来的教师和科室线程任务一直在队列中无尽的等待
		service.execute(new student2(studentCount, teacherCount));
		service.execute(new student2(studentCount, teacherCount));
		service.execute(new student2(studentCount, teacherCount));

		studentCount.await();
		service.execute(new teacher(classCount, teacherCount));
		classCount.await();
		service.execute(new classroom(teacherCount));
		service.shutdown();
	}

}

class teacher extends Thread {

	CountDownLatch countDownLatch;
	CountDownLatch tcoDownLatch;

	public teacher(CountDownLatch countDownLatch, CountDownLatch tcoDownLatch) {
		this.countDownLatch = countDownLatch;
		this.tcoDownLatch = tcoDownLatch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(" 请准备考试!");
		try {
			Thread.sleep(1000);
			countDownLatch.countDown();
			tcoDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" 考试结束!");
	}
}

class student2 extends Thread {

	CountDownLatch countdownlatch;

	CountDownLatch tcountdownlatch;

	volatile static int i;

	public student2(CountDownLatch countdownlatch, CountDownLatch tcountdownlatch) {
		// TODO Auto-generated constructor stub
		this.countdownlatch = countdownlatch;
		this.tcountdownlatch = tcountdownlatch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("学生" + (++i) + "到达考场");
		countdownlatch.countDown();
		try {
			tcountdownlatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("学生" + (i) + "离开考场");
	}
}

class classroom extends Thread {

	CountDownLatch countDownlarch;

	public classroom(CountDownLatch countDownlarch) {
		// TODO Auto-generated constructor stub
		this.countDownlarch = countDownlarch;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("考试已经结束,清通知学生离开考场！");
		countDownlarch.countDown();
	}

}
