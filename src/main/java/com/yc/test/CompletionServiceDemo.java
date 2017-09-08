package com.yc.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ��Ƚ��� Callable �� Future ���ܸ��� ���ܸ�ǿ��(��������ʹ�� (ComletionService ��� �ӿ� (jdk8 ��ǰ�İ汾��õ� api ))
 * �첽async ���߳� ִ����� �첽���з��� (ִ���� ����ȴ� ֱ�ӷ��� ) 
 * ������������� ����  take().get() �� �ڲ�ʵ�ֵ� һ�� �������� (����ȴ�ֱ�ӷ��� )
 * ��һ���� ������ͨ�� Future<T> ��ȫ��ִ�����֮�� �ٽ��з��� ��һ�����߳���������sleep �������������һ��ȴ���ϲŽ��д���
 * �������Ǳ����� take().get() ��� forѭ�����л�ȡ (��Ч !) 
 * @author yuanchen
 *
 */
public class CompletionServiceDemo {

	
	
	
	public static void main(String[] args) {
		
		long start  = System.currentTimeMillis();
		//  CompletionService �ӿ�  =  new CompletionExecutorService( Executors.newFixedThreadPool(num) );
		//  �� jdk8 ֮ǰ ��� ExecutorService ��ʵ�� Ч������ߵ� (��˾���ã�)
		ExecutorService exs =  Executors.newFixedThreadPool(5);
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(exs);
		List<Future<Integer>> futureList = new  ArrayList<Future<Integer>>();
		
		//���ڱ��� �鼯�� �� ���� 
		List<Integer> list = new ArrayList<Integer>();
		
		try{
			int countTake = 10 ;
			for(int i = 0 ; i < countTake ; i++){
				futureList.add(cs.submit(new TaskCompletion(i+1)));
			}
			
			BlockCarry(cs,countTake,list);  //  (�Ƽ�)  ����ȡ���ݸ���Ч��;  CompletionService.take().get();
			
			
			//futureListTest(futureList);  // ��� ����ȵ� �߳�ȫ������ ����ȡ (���Ƽ�)
			
			System.out.println("��ʱ �� "  + (System.currentTimeMillis() - start )/1000 + "��" );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			exs.shutdown();	  //�̳߳� �ر� 
		}
		
	}

	
	
	

	/**
	 * ������   : ʹ���ڲ���������   completionService.take().get(); (����Ч��)
	 * =========================================================================
	 * 
	 * 		�߳� pool-1-thread-4 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 4   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 1   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 6   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-3 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 3   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-3 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 9   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-2 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 2   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 4 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 8   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-4 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 7   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 1 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-3 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 10   ʱ��Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 6 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 3 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 9 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 2 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 8 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 7 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�����Ѿ���ȡ����ֵ  : 10 ʱ�� Ϊ: Tue Sep 05 17:06:18 CST 2017
			�߳� pool-1-thread-5 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 5   ʱ��Ϊ: Tue Sep 05 17:06:23 CST 2017
			�����Ѿ���ȡ����ֵ  : 5 ʱ�� Ϊ: Tue Sep 05 17:06:23 CST 2017
	 * @param cs           CompletionService 
	 * @param countTake    ѭ������ 
	 * @param list         �鼯�ļ���
	 */
	private static void BlockCarry(CompletionService<Integer> cs, int countTake, List<Integer> list) {
		//����2.ʹ���ڲ��������е�take()  ����Ҫ�Ŷ� ������ ������  
		for(int i = 0 ; i < countTake ; i++){
			Integer result;
			try {
				result = cs.take().get();
				list.add(result);  //��ÿ���߳� ִ����Ϻ�� ���� ���� �鼯 
				System.out.println("�����Ѿ���ȡ����ֵ  : " + result  + " ʱ�� Ϊ: " +  new Date());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	
	
	/**
	 * ����1  
	 * =======================================
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 1   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-4 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 4   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			1
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 6   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-3 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 3   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-2 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 2   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-3 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 9   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-2 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 10   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-1 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 8   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�߳� pool-1-thread-4 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 7   ʱ��Ϊ: Tue Sep 05 17:15:25 CST 2017
			�����Ѿ���ȡ����ֵ : 1 ʱ��Ϊ : Tue Sep 05 17:15:25 CST 2017
			=======================================
			2
			�����Ѿ���ȡ����ֵ : 2 ʱ��Ϊ : Tue Sep 05 17:15:25 CST 2017
			=======================================
			3
			�����Ѿ���ȡ����ֵ : 3 ʱ��Ϊ : Tue Sep 05 17:15:25 CST 2017
			=======================================
			4
			�����Ѿ���ȡ����ֵ : 4 ʱ��Ϊ : Tue Sep 05 17:15:25 CST 2017
			=======================================
			�߳� pool-1-thread-5 ==== ���� �Ѿ�ִ�� ִ�е�ֵ Ϊ �� 5   ʱ��Ϊ: Tue Sep 05 17:15:30 CST 2017
			5
			�����Ѿ���ȡ����ֵ : 5 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
			=======================================
			6
			�����Ѿ���ȡ����ֵ : 6 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
			=======================================
			7
			�����Ѿ���ȡ����ֵ : 7 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
			=======================================
			8
			�����Ѿ���ȡ����ֵ : 8 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
			=======================================
			9
			�����Ѿ���ȡ����ֵ : 9 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
			=======================================
			10
			�����Ѿ���ȡ����ֵ : 10 ʱ��Ϊ : Tue Sep 05 17:15:30 CST 2017
	 * @param futureList
	 */
	
	private static void futureListTest(List<Future<Integer>> futureList) {
		/**
		 * (����1)   future���ύʱ���صģ�����queue���������ύ˳�򣬻�ȡ���
		 */
	   for(Future<Integer> future : futureList ){
			try {
				System.out.println("=======================================");
				System.out.println(future.get());
				System.out.println("�����Ѿ���ȡ����ֵ : "+ future.get() + " ʱ��Ϊ : "+ new Date());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
}


class TaskCompletion implements Callable<Integer>{
	
	private Integer i ; 
	
	public TaskCompletion(Integer i ){
		this.i = i;
	}
	
	public Integer call() throws Exception {
		
		if(this.i == 5){
			TimeUnit.SECONDS.sleep(5);  // ��� ������ ����� �� ����5��
		}
		System.out.println("�߳� "+ Thread.currentThread().getName() +" ==== " +  "���� �Ѿ�ִ�� " + "ִ�е�ֵ Ϊ �� " + i + "   ʱ��Ϊ: " + new Date());
		return this.i;
	}
	
	
	
	
}