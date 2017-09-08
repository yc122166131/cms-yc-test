package com.yc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * �ź��� (semaphore demo)
 * @author yuanchen
 *
 */
public class SemaphoreTest {
	
	public static void main(String[] args) {
		
		int num = 2;  
		PrintQueue pq = new PrintQueue(num);
		/*for(int i = 0 ; i < 5 ; i ++){
			new Thread(new PrintMachine(pq)).start();  // ���� ���ʵ� ������ PrintQueue  ��ͬһ����
		}*/
		
		ExecutorService exs =  Executors.newCachedThreadPool();
		for(int i = 0 ; i < 5 ;i++){
			exs.execute(new PrintMachine(pq));
		}
		exs.shutdown();
	}
	
	
}


/**
 * ������ ������ƽ̨�� ���� Semaphore �ź���
 * OK?
 * @author yuanchen
 *
 */
class PrintQueue{
	
	private Semaphore semaphore;
	public PrintQueue( int num ){
		this.semaphore = new Semaphore(num);  //���ܶ��ٽ��������������������������㴫������Num
	}
	
	public void printJob(){
		try {
			semaphore.acquire();  //��ȡ���
			System.out.println(Thread.currentThread().getName()+"   :  "+System.currentTimeMillis());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();  //�ͷ����
		}
	}
	
}


class PrintMachine implements Runnable{

	private PrintQueue printQueue;
		
	public PrintMachine(PrintQueue printQueue){
		this.printQueue = printQueue;
	}
	
	public void run() {
		printQueue.printJob();
	}
	
}


