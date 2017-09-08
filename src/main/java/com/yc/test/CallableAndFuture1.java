package com.yc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


/**
 * callable �� FutureTask �����߳��е� ���� demo (����  ��;ȡ�� )
 * Executor.submit(new FutureTask(new Callable<?>() ) );  (����һ���ʹ���̳߳� �ȽϺ��� ) (==> ע������  : ���з���ֵ �� FutureTask ʱ���� submit() ������ʱ�����ǻ���ʹ��execute() 
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
			executor.submit(reTask[i]);   //submit�з���ֵ����executeû��!! (������Ҫ��futureTask ��ϵ�һ���� submit()����  )
		}
		
		try {
			TimeUnit.SECONDS.sleep(3);   // �� Thread.sleep(3000) �ȼ�~
			//Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		for(int i = 0 ; i < 5 ; i++){
			reTask[i].cancel(true);
		}
		
		//��ѯ�Ƿ��Ѿ���ȡ��  (�Ͼ� ��ͣ��3���� Ӧ�û���һ���ֵ� ���� �� ȡ�� һ���ŵ������Ѿ� )
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
		  if (this.isCancelled()) {//����ȡ��  
	            System.out.println(this.name+" has bean cancel");  
	        }else{//������������  
	            System.out.println(this.name+" has bean finished");  
	        }  
	}
	
}


