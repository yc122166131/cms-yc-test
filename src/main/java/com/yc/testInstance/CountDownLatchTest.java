package com.yc.testInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;


/**
 * 1. countDownLatch ��Ӧ�� 
 * 2. ArrayList ��  Vector �ڲ���ͬһ������ʱ��� �̰߳�ȫ����
 * @author yuanchen
 *
 */
public class CountDownLatchTest {
	
	public static void main(String[] args) {
		
		CountDownLatch countDownLatch = new CountDownLatch(1000); //ǹҪ��1000��
		List<Object> list = new ArrayList<Object>();       //����<= 1000*100  (�̲߳���ȫ,��Թ���һ������)
		//List<Object> list = new Vector<Object>();        //ʼ������Ϊ 1000*100  (�̰߳�ȫ,��Թ���һ������)
		
		int LOOPCOUNT = 1000;
		
		for(int i = 0 ; i < LOOPCOUNT ; i++){
			Thread th1 = new Thread(new MyThread(list,countDownLatch));
	//Thread th1 = new Thread(new MyThread(new ArrayList<Object>() , countDownLatch)); //ÿ���̲߳��ٶ���һ������,����ÿ�ζ�newһ���µ�
			th1.start();
		}
		
		try {
			countDownLatch.await();   //�ȴ�������ϣ�
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


