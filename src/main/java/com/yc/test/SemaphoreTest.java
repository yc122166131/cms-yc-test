package com.yc.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量 (semaphore demo)
 * @author yuanchen
 *
 */
public class SemaphoreTest {
	
	public static void main(String[] args) {
		
		int num = 2;  
		PrintQueue pq = new PrintQueue(num);
		/*for(int i = 0 ; i < 5 ; i ++){
			new Thread(new PrintMachine(pq)).start();  // 最终 访问的 公共类 PrintQueue  是同一个！
		}*/
		
		ExecutorService exs =  Executors.newCachedThreadPool();
		for(int i = 0 ; i < 5 ;i++){
			exs.execute(new PrintMachine(pq));
		}
		exs.shutdown();
	}
	
	
}


/**
 * 必须在 公共的平台上 建立 Semaphore 信号量
 * OK?
 * @author yuanchen
 *
 */
class PrintQueue{
	
	private Semaphore semaphore;
	public PrintQueue( int num ){
		this.semaphore = new Semaphore(num);  //不管多少进程想进来，最大容量反正就是你传进来的Num
	}
	
	public void printJob(){
		try {
			semaphore.acquire();  //获取许可
			System.out.println(Thread.currentThread().getName()+"   :  "+System.currentTimeMillis());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			semaphore.release();  //释放许可
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


