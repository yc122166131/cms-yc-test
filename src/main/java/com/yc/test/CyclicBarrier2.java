package com.yc.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * cyclicBarrier  demo 
 * ���ʵ����� (ͨ�׵�˵)     cyclicBarrier �Ǽ��� await() ������
 *                         countDownLatch �Ǽ��� countDown() ������
 * @author yuanchen
 *
 */
public class CyclicBarrier2 {

	
	 public static void main(String[] args) {
		
		final CyclicBarrier c = new CyclicBarrier(2);
		 
		//�½��� �ӽ���
		new Thread(new Runnable(){
			public void run() {
				try {
					c.await();  // �� countDownLatch��֮ͬ������ :
					// countDownLatch �Ǽ��� countDown�Ĵ����ǲ��Ǵﵽ�˶�ֵ,
					// cyclicBarrier �Ǽ��� await �Ĵ����ǲ��Ǵﵽ�� ��ֵ�����await���������� С�ڶ�ֵ ����Զ����������!
					System.out.println(Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}).start();
		 
		 
		//������
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
