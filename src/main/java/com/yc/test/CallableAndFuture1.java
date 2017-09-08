package com.yc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * callable 和 FutureTask 在子线程中的 返回 demo (包括  中途取消 )
 * Executor.submit(new FutureTask(new Callable<?>() ) );  (我们一般会使用线程池 比较合理 ) (==> 注意这里  : 当有返回值 即 FutureTask 时采用 submit() ，其他时候我们还是使用execute() 
 * @author yuanchen
 *
 */
public class CallableAndFuture1 {
	
	public static void main(String[] args) {
		
		ExecutorService executor =  Executors.newCachedThreadPool();
		
		ResultTask[]  reTask = new ResultTask[5];
		
		for(int i = 0 ; i < 5; i++){
			ExecutableTask etask = new ExecutableTask("task"+i);
			reTask[i] = new ResultTask(etask);
			//executor.execute(reTask[i]);
			executor.submit(reTask[i]);   //submit有返回值，而execute没有!! (所以需要和futureTask 配合的一定是 submit()方法  )
		}
		
		try {
			TimeUnit.SECONDS.sleep(3);   // 和 Thread.sleep(3000) 等价~
			//Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		for(int i = 0 ; i < 5 ; i++){
			reTask[i].cancel(true);
		}
		
		//查询是否已经被取消  (毕竟 暂停了3秒中 应该会有一部分的 数据 会 取消 一部门的数据已经 )
		for(int i = 0 ;i <  5 ; i++){
			if(!reTask[i].isCancelled()){
				try {
					System.out.println(""+ reTask[i].get());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} 
		executor.shutdown();
	}
}


class ExecutableTask implements Callable<String>{

	private String name; 
	
	public ExecutableTask(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String call() throws Exception {
		long duration = (long) (Math.random() * 10);
		System.out.println("name:"+this.name+"---- duration:"+duration);  
        TimeUnit.SECONDS.sleep(duration);  
        return "hello world,I'm "+name;  
	}
}



class ResultTask extends FutureTask<String>{

	private String name;
	private ExecutableTask task;
	
	public ResultTask(Callable<String> callable) {
		super(callable);
		this.task = (ExecutableTask)callable;
		this.name = this.task.getName();  
	}

	@Override
	protected void done() {
		  if (this.isCancelled()) {//任务被取消  
	            System.out.println(this.name+" has bean cancel");  
	        }else{//任务正常结束  
	            System.out.println(this.name+" has bean finished");  
	        }  
	}
	
}


