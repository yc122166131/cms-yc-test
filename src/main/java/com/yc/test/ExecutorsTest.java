package com.yc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * �̳߳� Demo
 * @author yuanchen
 *
 */
public class ExecutorsTest {
	
	
	
	public static void main(String[] args) {
			
		
		
			
			//ExecutorService ex1  =  Executors.newFixedThreadPool(3);  //�����̳߳�
		
		
		    //�ɻ����̳߳�   ������̳߳س��ȳ���������Ҫ���������տ����̣߳����޿ɻ��գ����½��̣߳�
		   /*	ExecutorService ex1 = Executors.newCachedThreadPool();   
		
			for(int i = 0 ; i < 10 ; i++){
				ex1.execute(new MyThread1());
			}
			ex1.shutdown();*/
			
		
				
			//��ʱ,�ӳ�ִ������  ==================================================
			//DelayTest();
			
			//�� timer ���ܸ���ǿ��� ÿ��һ��ʱ�����һ�δ����Ĺ���
			//IntervalTest();
			
			//�����߳�
			//SingleThreadPoolTest();
			
			
			
	}

	
	/**
	 *  ����ģʽ (ÿ�� ֻ����һ�� �߳̽�������)
	 *  ͬһʱ��ֻ����һ�� �߳� ���в��� 
	 */
	private static void SingleThreadPoolTest() {
		
		ExecutorService ex4 =  Executors.newSingleThreadExecutor();
		for(int o = 0 ; o < 10 ; o++){
			ex4.execute(new Runnable(){

				public void run() {
					try {
						System.out.println("��ǰ���ڲ��ݵ��߳�����Ϊ :" + Thread.currentThread().getName());
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
	 * �ӳ�  x �� �� ÿ xx �� ִ��һ�� 
	 */
	private static void IntervalTest() {
		
		ScheduledExecutorService ex3 = Executors.newScheduledThreadPool(5);
		ex3.scheduleAtFixedRate(new Runnable(){
			
			public void run() {
					System.out.println("���������");
			}
			
		}, 3, 3, TimeUnit.SECONDS);
		
		ex3.shutdown();
	}


	/**
	 * �ӳ� x �� ���� (���� js �е� setTimeOut(function(){} ));
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
