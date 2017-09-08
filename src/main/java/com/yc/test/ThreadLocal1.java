package com.yc.test;

public class ThreadLocal1 {

	
	
	
	public static void main(String[] args) {
		
		TheadLocal1 th  = new TheadLocal1();
		
		for(int i = 0 ; i < 5 ; i++){
			new Thread(th,"y-"+i).start();
		}
	}
	
}


class TheadLocal1 implements Runnable{

	
	ThreadLocal tlo = new ThreadLocal();
	public void run() {
		
		int randomNum =  (int)(Math.random() * 100);
		tlo.set(randomNum);
		System.out.println(Thread.currentThread().getName() + " 往里面设置了:  " + randomNum );
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		System.out.println(Thread.currentThread().getName() + " 取出了:  "  + tlo.get());
		
	}
	
}
