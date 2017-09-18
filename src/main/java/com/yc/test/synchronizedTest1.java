package com.yc.test;


/**
 * synchronized 锁对象 和 锁区块  
 * 
 * 区分 synchronized(this) 和 synchronized(xxx.Class) 
 * 前者 是 同一个 对象 ,
 * 后者 是 每次都是 一个新的 对象  new Thread(new MySynThread())
 * @author yuanchen
 *
 */
public class synchronizedTest1 {

			public static void main(String[] args) {
					
					//==================================================================
					//	synchronized (MySynThread.class)  锁对象 
				
				    /*	int i = 0 ;
				    for(i = 0;i<5;i++){
				    	new Thread(new MySynThread()).start();;
				    }*/
				
				
				
					
					//================================================================
					//synchronized (this)      锁区块 
				
					/*Runnable th1 = new MyThread();
					for(int i = 0 ; i < 5 ; i++){
						new Thread(th1,"A"+i).start();;
					}*/
			
				
				
			}
	
	
}



class MySynThread implements Runnable {

	public void run() {
		synchronized (MySynThread.class) {
			try {
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*	public void run() {
			synchronized (this) {
				 try {
					Thread.sleep(3000);
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	}*/
	
}