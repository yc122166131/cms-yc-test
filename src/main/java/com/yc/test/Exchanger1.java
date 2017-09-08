package com.yc.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Exchanger1 {

		
		public static void main(String[] args) {
			Exchanger<List<Integer>> exgr = new Exchanger<List<Integer>>();
			
			for(int i = 0 ; i < 4 ; i++){
				try {
					new Thread(new Producer(exgr)).start();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	
	
}

class Producer implements Runnable{
	
	Exchanger<List<Integer>>  exchanger = null ;
	List<Integer> list = new ArrayList<Integer>();
	
	public Producer(Exchanger<List<Integer>> exgr){
		this.exchanger = exgr;
	}
	
	public void run() {
		Random rand = new Random();
		list.add(rand.nextInt(100));
		list.add(rand.nextInt(100));
		list.add(rand.nextInt(100));
		list.add(rand.nextInt(100));
		list.add(rand.nextInt(100));
		
		System.out.println(Thread.currentThread().getName()+ "交换前的 数据为 : ");
		print();
		try {
			list = exchanger.exchange(list);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+ "交换后 为 : ");
		print();
	}

	private void print() {
		synchronized (Producer.class) {
			for(int i = 0 ; i < list.size() ; i++){
				System.out.println(list.get(i));
			}
		}
	}
	
}
