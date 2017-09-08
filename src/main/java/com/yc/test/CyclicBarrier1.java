package com.yc.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch 的升级版 （CyclicBarrier）
 * @author yuanchen
 *
 */
public class CyclicBarrier1 {

	
    public static void main(String[] args) throws InterruptedException {

        HashMap<String,String> result=new HashMap<String,String>();
        final CountDownLatch begin=new CountDownLatch(1) ;  
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        
        // 结束的倒数锁  使用CyclicBarrier,在所有线程结束后启动统计线程
        final CyclicBarrier end=new CyclicBarrier(10, new Statistics(result));  

        // 十名选手         
        for (int index = 0; index < 10; index++) {
            new Thread(new Player1(begin,end,result,threadLocal),"player"+index).start();
        }  
        System.out.println("Game Start");  
        begin.countDown();  

    }
}

class Player1 implements Runnable{
	
    private  final CountDownLatch begin ;    //countDownLatch
    private final CyclicBarrier end;         //CyclicBarrier
    HashMap<String,String> result;
    private final ThreadLocal<String> tl;

    public Player1(CountDownLatch begin,CyclicBarrier end,HashMap<String,String> result,ThreadLocal<String> tl){
        this.begin=begin;
        this.end=end;
        this.result=result;
        this.tl = tl;
    }
    
    public void run() {
        try {  
            // 等待
            begin.await();  //countDownLatch 的拦截 直到 countDown()  的总数 达到定值
            Long num = (long) (Math.random() * 10000);
            Thread.sleep(num);  
            result.put(Thread.currentThread().getName(), new Date().toString());
            System.out.println(Thread.currentThread().getName() + " arrived" + "休眠了 "+ num + "秒");  
            tl.set(Thread.currentThread().getName()+  " ::  " + num +"秒" );
            end.await(); // CyclicBarrier 的await拦截等待  直到 await()的 总数达到定值
            
            
            //注意 :: cyclicBarrier收集完毕 await()总数之后  会首先运行runnable 中的 内容 ，当全部走完 才会 继续走下面的逻辑!!!
            System.out.println("=  这里的 内容只会等到 CyclicBarrier 的 runnable 接口全部走完之后才会 开始!!!" + Thread.currentThread().getName()
            		  +tl.get()  );
        } catch (InterruptedException e) { 
            e.printStackTrace();          
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } 
    }
}
class Statistics implements Runnable{

    private HashMap<String,String> result;
    public Statistics(HashMap<String,String> result){
        this.result=result;
    }
    
    public void run() {
    	
        System.out.println("Game Over\nbegin statistics:");
        
        Iterator it=result.keySet().iterator();
        while(it.hasNext()){
        	try {
				Thread.sleep(2000);
				String key=(String)it.next();
	            String value=result.get(key);
	            System.out.println(key+":"+value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

}
