package com.yc.test;

import java.util.concurrent.CountDownLatch;

/**
 * 第二个countDownLatch 的demo 
 * @author yuanchen
 *
 */
public class countDownLatch1 {

	
	public static void main(String[] args) {
		final CountDownLatch  begin = new CountDownLatch(1) ; 
		final CountDownLatch  end  = new CountDownLatch(10);
		
		try {
			for(int i = 0 ; i < 10 ; i++){
				 new Thread(new Player(begin,end)).start();
			}
			Thread.sleep(5000); 
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		begin.countDown();  
		System.out.println("game Start ~~~~~~");
		try {
			end.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("game over !");
	}
	
	
}


class Player implements Runnable{

	private CountDownLatch begin ;
	private CountDownLatch end;
	
	public Player( CountDownLatch begin, CountDownLatch end){
		this.begin = begin;
		this.end = end;
	}
	
	public void run() {
		synchronized (Player.class) {  //锁对象, (给对象加把锁 ,一次 只允许一次访问)
			try {
				begin.await();
				System.out.println(Thread.currentThread().getName()+" :: arrived !" );
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				end.countDown();
			}
		}
		
	}
	
}
