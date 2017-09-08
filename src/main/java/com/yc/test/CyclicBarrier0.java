package com.yc.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 * CyclicBarrier  first simple demo 
 * @author yuanchen
 *
 */
public class CyclicBarrier0 {

	
	public static CyclicBarrier cy = new CyclicBarrier(2);
	
	
	public static void main(String[] args) {
	
		
			Thread t1 = new Thread(new Runnable(){
				public void run() {
						try {
							System.out.println("进来了1");
							cy.await();   //不论是 CountDownLatch 还是 CyclicBarrier 都是 阻塞线程 ,因为 一个要等 countDown到齐,一个要等await 方法总数凑齐。！
						} catch (InterruptedException e) {
							//e.printStackTrace();
						} catch (BrokenBarrierException e) {
							//e.printStackTrace();
						}
				}
			});
			
			try {
				t1.start();
				System.out.println("进来了2");
				t1.interrupt();   // 这里进行了线程中断操作
				cy.await();
			} catch (Exception e) {
				System.out.println(cy.isBroken());  // 当阻塞线程是否被中断 , 调用 isBroken() 可以进行得知
				//e.printStackTrace();
			} 
		
			
		
	}
	
	
	
	
	
}
