package com.yc.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {

	
		public static void main(String[] args) {
				
				/**
				 *  Callable ��ʵ��һ���ӿ� ���� FutureTask �� , �ٷŵ� new Thread ��
				 *  Runnable ����ʵ��һ���ӿ� ֱ�ӷ��� new Thread ��
				 *  ������  д��һ (ֻ�Ƿ����˶���~)
				 */
			   /* Callable<Integer> callable = new Callable<Integer>(){

					public Integer call() throws Exception {
						Integer randomNum = new Random().nextInt(100);
						System.out.println(randomNum);
						return randomNum;
					}
			    	
			    };
			    
			    
			    FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);
			    new Thread(futureTask).start();
			    
			    try {
					Thread.sleep(5000); //5������ , ģ��һЩ�����ӳ� ~
					System.out.println(futureTask.get());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			    
			    
			    
			    /**
			     * д���� 
			     */
			    test1();
			    
			
		}

		@SuppressWarnings("unchecked")
		private static void test1() {
				FutureTask<Integer> future = new FutureTask(new Callable<Integer>(){
					public Integer call() throws Exception {
						return new Random().nextInt(1000);
					}
				});
				
				new Thread(future).start();
				
				try {
					Thread.sleep(1000); 
					System.out.println(Thread.currentThread().getName()+"======="+future.get());  //ִ����Ϻ� �� ��ȡ �߳�return �� ֵ
				} catch (Exception e) {
					e.printStackTrace();
				}
					
		}
		
		
		  /* new Runnable(){
				public void run() {
				}
		    };*/
	
}
