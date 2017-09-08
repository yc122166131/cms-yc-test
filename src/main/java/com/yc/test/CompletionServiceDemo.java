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
 * 相比较于 Callable 和 Future 性能更好 功能更强大(所以我们使用 (ComletionService 这个 接口 (jdk8 以前的版本最好的 api ))
 * 异步async 子线程 执行完毕 异步进行返回 (执行完 无需等待 直接返回 ) 
 * 我下面介绍两个 方法  take().get() 是 内部实现的 一个 阻塞队列 (无需等待直接返回 )
 * 另一个是 就是普通的 Future<T> 会全部执行完毕之后 再进行返回 ，一旦子线程中有休眠sleep 则后面的任务必须一起等待完毕才进行处理！
 * 所以我们必须用 take().get() 这个 for循环进行获取 (高效 !) 
 * @author yuanchen
 *
 */
public class CompletionServiceDemo {

	
	
	
	public static void main(String[] args) {
		
		long start  = System.currentTimeMillis();
		//  CompletionService 接口  =  new CompletionExecutorService( Executors.newFixedThreadPool(num) );
		//  在 jdk8 之前 这个 ExecutorService 的实践 效率是最高的 (公司会用！)
		ExecutorService exs =  Executors.newFixedThreadPool(5);
		CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(exs);
		List<Future<Integer>> futureList = new  ArrayList<Future<Integer>>();
		
		//用于保存 归集后 的 数据 
		List<Integer> list = new ArrayList<Integer>();
		
		try{
			int countTake = 10 ;
			for(int i = 0 ; i < countTake ; i++){
				futureList.add(cs.submit(new TaskCompletion(i+1)));
			}
			
			BlockCarry(cs,countTake,list);  //  (推荐)  这样取数据更加效率;  CompletionService.take().get();
			
			
			//futureListTest(futureList);  // 这个 必须等到 线程全部跑完 才能取 (不推荐)
			
			System.out.println("用时 ： "  + (System.currentTimeMillis() - start )/1000 + "秒" );
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			exs.shutdown();	  //线程池 关闭 
		}
		
	}

	
	
	

	/**
	 * 方法二   : 使用内部阻塞队列   completionService.take().get(); (更加效率)
	 * =========================================================================
	 * 
	 * 		线程 pool-1-thread-4 ==== 任务 已经执行 执行的值 为 ： 4   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 1   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 6   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-3 ==== 任务 已经执行 执行的值 为 ： 3   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-3 ==== 任务 已经执行 执行的值 为 ： 9   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-2 ==== 任务 已经执行 执行的值 为 ： 2   时间为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 4 时间 为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 8   时间为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-4 ==== 任务 已经执行 执行的值 为 ： 7   时间为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 1 时间 为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-3 ==== 任务 已经执行 执行的值 为 ： 10   时间为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 6 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 3 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 9 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 2 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 8 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 7 时间 为: Tue Sep 05 17:06:18 CST 2017
			任务已经获取返回值  : 10 时间 为: Tue Sep 05 17:06:18 CST 2017
			线程 pool-1-thread-5 ==== 任务 已经执行 执行的值 为 ： 5   时间为: Tue Sep 05 17:06:23 CST 2017
			任务已经获取返回值  : 5 时间 为: Tue Sep 05 17:06:23 CST 2017
	 * @param cs           CompletionService 
	 * @param countTake    循环次数 
	 * @param list         归集的集合
	 */
	private static void BlockCarry(CompletionService<Integer> cs, int countTake, List<Integer> list) {
		//方法2.使用内部阻塞队列的take()  不需要排队 处理完 立马返回  
		for(int i = 0 ; i < countTake ; i++){
			Integer result;
			try {
				result = cs.take().get();
				list.add(result);  //将每个线程 执行完毕后的 数据 进行 归集 
				System.out.println("任务已经获取返回值  : " + result  + " 时间 为: " +  new Date());
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	
	
	/**
	 * 方法1  
	 * =======================================
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 1   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-4 ==== 任务 已经执行 执行的值 为 ： 4   时间为: Tue Sep 05 17:15:25 CST 2017
			1
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 6   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-3 ==== 任务 已经执行 执行的值 为 ： 3   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-2 ==== 任务 已经执行 执行的值 为 ： 2   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-3 ==== 任务 已经执行 执行的值 为 ： 9   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-2 ==== 任务 已经执行 执行的值 为 ： 10   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-1 ==== 任务 已经执行 执行的值 为 ： 8   时间为: Tue Sep 05 17:15:25 CST 2017
			线程 pool-1-thread-4 ==== 任务 已经执行 执行的值 为 ： 7   时间为: Tue Sep 05 17:15:25 CST 2017
			任务已经获取返回值 : 1 时间为 : Tue Sep 05 17:15:25 CST 2017
			=======================================
			2
			任务已经获取返回值 : 2 时间为 : Tue Sep 05 17:15:25 CST 2017
			=======================================
			3
			任务已经获取返回值 : 3 时间为 : Tue Sep 05 17:15:25 CST 2017
			=======================================
			4
			任务已经获取返回值 : 4 时间为 : Tue Sep 05 17:15:25 CST 2017
			=======================================
			线程 pool-1-thread-5 ==== 任务 已经执行 执行的值 为 ： 5   时间为: Tue Sep 05 17:15:30 CST 2017
			5
			任务已经获取返回值 : 5 时间为 : Tue Sep 05 17:15:30 CST 2017
			=======================================
			6
			任务已经获取返回值 : 6 时间为 : Tue Sep 05 17:15:30 CST 2017
			=======================================
			7
			任务已经获取返回值 : 7 时间为 : Tue Sep 05 17:15:30 CST 2017
			=======================================
			8
			任务已经获取返回值 : 8 时间为 : Tue Sep 05 17:15:30 CST 2017
			=======================================
			9
			任务已经获取返回值 : 9 时间为 : Tue Sep 05 17:15:30 CST 2017
			=======================================
			10
			任务已经获取返回值 : 10 时间为 : Tue Sep 05 17:15:30 CST 2017
	 * @param futureList
	 */
	
	private static void futureListTest(List<Future<Integer>> futureList) {
		/**
		 * (方法1)   future是提交时返回的，遍历queue则按照任务提交顺序，获取结果
		 */
	   for(Future<Integer> future : futureList ){
			try {
				System.out.println("=======================================");
				System.out.println(future.get());
				System.out.println("任务已经获取返回值 : "+ future.get() + " 时间为 : "+ new Date());
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
			TimeUnit.SECONDS.sleep(5);  // 如果 索引是 第五个 则 休眠5秒
		}
		System.out.println("线程 "+ Thread.currentThread().getName() +" ==== " +  "任务 已经执行 " + "执行的值 为 ： " + i + "   时间为: " + new Date());
		return this.i;
	}
	
	
	
	
}