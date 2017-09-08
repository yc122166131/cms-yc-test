package com.yc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 Demo
 * @author yuanchen
 *
 */
public class ExecutorsTest {
	
	
	
	public static void main(String[] args) {
			
		
		
			
			//ExecutorService ex1  =  Executors.newFixedThreadPool(3);  //定长线程池
		
		
		    //可缓存线程池   （如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程）
		   /*	ExecutorService ex1 = Executors.newCachedThreadPool();   
		
			for(int i = 0 ; i < 10 ; i++){
				ex1.execute(new MyThread1());
			}
			ex1.shutdown();*/
			
		
				
			//定时,延迟执行任务  ==================================================
			//DelayTest();
			
			//比 timer 功能更加强大的 每隔一段时间进行一次触发的功能
			//IntervalTest();
			
			//单例线程
			//SingleThreadPoolTest();
			
			
			
	}

	
	/**
	 *  单例模式 (每次 只能有一个 线程进行消费)
	 *  同一时间只允许一个 线程 进行操作 
	 */
	private static void SingleThreadPoolTest() {
		
		ExecutorService ex4 =  Executors.newSingleThreadExecutor();
		for(int o = 0 ; o < 10 ; o++){
			ex4.execute(new Runnable(){

				public void run() {
					try {
						System.out.println("当前正在操纵的线程名称为 :" + Thread.currentThread().getName());
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
		ex4.shutdown();
		
	}



	/**
	 * 延迟  x 秒 后 每 xx 秒 执行一次 
	 */
	private static void IntervalTest() {
		
		ScheduledExecutorService ex3 = Executors.newScheduledThreadPool(5);
		ex3.scheduleAtFixedRate(new Runnable(){
			
			public void run() {
					System.out.println("是你的名字");
			}
			
		}, 3, 3, TimeUnit.SECONDS);
		
		ex3.shutdown();
	}


	/**
	 * 延迟 x 秒 处理 (类似 js 中的 setTimeOut(function(){} ));
	 */
	private static void DelayTest() {
		ScheduledExecutorService ex2 = Executors.newScheduledThreadPool(5);
		ex2.schedule(new Runnable(){

			public void run() {
				System.out.println(" delay 3 seconds!");
			}
			
		}, 3, TimeUnit.SECONDS);		
		
		ex2.shutdown();
	}
	
	
	

}





class MyThread1 implements Runnable{

	public void run() {
		System.out.println(Thread.currentThread().getName()+" come in !");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
