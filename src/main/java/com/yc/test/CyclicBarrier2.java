package com.yc.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * cyclicBarrier  demo 
 * 本质的区别 (通俗的说)     cyclicBarrier 是计算 await() 的总数
 *                         countDownLatch 是计算 countDown() 的总数
 * @author yuanchen
 *
 */
public class CyclicBarrier2 {

	
	 public static void main(String[] args) {
		
		final CyclicBarrier c = new CyclicBarrier(2);
		 
		//新建的 从进程
		new Thread(new Runnable(){
			public void run() {
				try {
					c.await();  // 与 countDownLatch不同之处在于 :
					// countDownLatch 是计算 countDown的次数是不是达到了定值,
					// cyclicBarrier 是计算 await 的次数是不是达到了 定值，如果await触发的总数 小于定值 将永远不会往下走!
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();
		 
		 
		//主进程
		try {
			c.await();
			System.out.println(Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	 
	 
	
	
	
	
}
