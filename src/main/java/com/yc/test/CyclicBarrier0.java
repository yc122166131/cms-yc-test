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
							System.out.println("������1");
							cy.await();   //������ CountDownLatch ���� CyclicBarrier ���� �����߳� ,��Ϊ һ��Ҫ�� countDown����,һ��Ҫ��await �����������롣��
						} catch (InterruptedException e) {
							//e.printStackTrace();
						} catch (BrokenBarrierException e) {
							//e.printStackTrace();
						}
				}
			});
			
			try {
				t1.start();
				System.out.println("������2");
				t1.interrupt();   // ����������߳��жϲ���
				cy.await();
			} catch (Exception e) {
				System.out.println(cy.isBroken());  // �������߳��Ƿ��ж� , ���� isBroken() ���Խ��е�֪
				//e.printStackTrace();
			} 
		
			
		
	}
	
	
	
	
	
}
