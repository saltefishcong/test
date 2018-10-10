package com.test.Thread;

public class RunnableTest {
	static String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true&useUnicode=true&characterEncoding=UTF-8";
	static String user = "root";
	static String password = "123456";

	public static void main(String[] args) throws Exception {
		Checkpoint checkpoint = new Checkpoint();
		System.out.println("开始比赛!");
		open op = new open(checkpoint);
		for (int i = 0; i < 10; i++) {
			Participants pants = new Participants(checkpoint);
			pants.start();
		}
		Thread.currentThread().sleep(2000);
		op.start();
	}
}

class Checkpoint {

	public synchronized void waitPoint() {
		try {
			System.out.println(Thread.currentThread().getName() + " 到达关卡,开始等待 ");
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 到达终点 ! ");
	}

	public synchronized void notifyPoint() {
		notifyAll();
	}
}

class open extends Thread {

	Checkpoint check;

	public open(Checkpoint check) {
		// TODO Auto-generated constructor stub
		this.check = check;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("关卡开放,比赛继续! ");
		check.notifyPoint();
	}
}

class Participants extends Thread {
	Checkpoint check;

	public Participants(Checkpoint check) {
		// TODO Auto-generated constructor stub
		this.check = check;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		check.waitPoint();
	}
}
