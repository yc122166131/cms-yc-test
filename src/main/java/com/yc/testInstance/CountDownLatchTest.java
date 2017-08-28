package com.yc.testInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;


/**
 * 1. countDownLatch 的应用 
 * 2. ArrayList 和  Vector 在操纵同一个对象时候的 线程安全问题
 * @author yuanchen
 *
 */
public class CountDownLatchTest {
	
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1000); //枪要响1000次
		List<Object> list = new ArrayList<Object>();       //总数<= 1000*100  (线程不安全,针对共享一个对象)
		//List<Object> list = new Vector<Object>();        //始终总数为 1000*100  (线程安全,针对共享一个对象)
		
		int LOOPCOUNT = 1000;
		
		for(int i = 0 ; i < LOOPCOUNT ; i++){
			Thread th1 = new Thread(new MyThread(list,countDownLatch));
	//Thread th1 = new Thread(new MyThread(new ArrayList<Object>() , countDownLatch)); //每个线程不再独享一个对象,而是每次都new一个新的
			th1.start();
		}
		
		try {
			countDownLatch.await();   //等待抢响完毕！
			System.out.println("end================");
			System.out.println(list.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}


class MyThread implements Runnable{
	
	private CountDownLatch countDownLatch;
	private List<Object> list ;
	
	public MyThread( List<Object> list,CountDownLatch countDownLatch){
		this.countDownLatch = countDownLatch;
		this.list = list;
	}
	
	public void run() {
			for(int i = 0 ; i < 100; i++){
				list.add(new Object());
			}
			//System.out.println(list.size());
			countDownLatch.countDown();
	}
	
}


