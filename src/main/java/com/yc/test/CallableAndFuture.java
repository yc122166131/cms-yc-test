package com.yc.test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableAndFuture {

	
		public static void main(String[] args) {
				
				/**
				 *  Callable 是实现一个接口 放在 FutureTask 中 , 再放到 new Thread 中
				 *  Runnable 都是实现一个接口 直接放在 new Thread 中
				 *  下面是  写法一 (只是分离了而已~)
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
					Thread.sleep(5000); //5秒后接收 , 模拟一些操作延迟 ~
					System.out.println(futureTask.get());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
			    
			    
			    
			    /**
			     * 写法二 
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
					System.out.println(Thread.currentThread().getName()+"======="+future.get());  //执行完毕后 的 获取 线程return 的 值
				} catch (Exception e) {
					e.printStackTrace();
				}
					
		}
		
		
		  /* new Runnable(){
				public void run() {
				}
		    };*/
	
}
