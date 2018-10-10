package com.test.Thread;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Exchanger<String> exchanger = new Exchanger<String>();
		cinema cin = new cinema(exchanger, "小");
		cinema cin2 = new cinema(exchanger, "大");
		cin.start();
		cin2.start();
	}

}

class cinema extends Thread {
	Exchanger<String> exchanger;

	String name;

	public cinema(Exchanger exchanger, String name) {
		this.exchanger = exchanger;
		this.name = name;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println(Thread.currentThread().getName() + "  " + name);
			name = exchanger.exchange(name);
			System.out.println(Thread.currentThread().getName() + "  " + name);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
