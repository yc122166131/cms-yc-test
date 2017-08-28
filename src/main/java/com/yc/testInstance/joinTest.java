package com.yc.testInstance;

/**
 * join 多线程实例
 * @author yuanchen
 *
 */
public class joinTest {

	public static void main(String[] args) throws InterruptedException {

		
		final Thread thread3 = new Thread(new Runnable() {
			
			public void run() {
				for(int j = 0 ; j < 3 ; j++){
					System.out.println("3333333333333");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
	    final Thread thread4 = new Thread(new Runnable() {
			
			public void run() {
				try {
					thread3.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for(int i = 0 ; i < 5 ; i ++){
					System.out.println("444444444");	
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		
	    
		
	    
		Thread thread2 = new Thread(new Runnable() {
			
			public void run() {
				try {
					thread4.join();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				for(int i = 0 ; i < 5 ; i ++){
					System.out.println("2222222222222");	
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
					
			}
		});
		
		
		thread3.start();
		thread2.start();
		thread4.start();
		
		
	/*	thread2.join();
		System.out.println("main0 ======:");
		//Thread.sleep(1000);
		System.out.println("main1 ======:");
*/		
		//我们现在希望的是 t2 在 t3 前面执行 ,  t4在t3 前面执行 但在 t2 后面执行
		//即 优先级( t2 > t4 > t3 ) a > c > b
		
		
		
	}

}



