package com.yc.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * CountDownLatch �������� ��CyclicBarrier��
 * @author yuanchen
 *
 */
public class CyclicBarrier1 {

	
    public static void main(String[] args) throws InterruptedException {

        HashMap<String,String> result=new HashMap<String,String>();
        final CountDownLatch begin=new CountDownLatch(1) ;  
        ThreadLocal<String> threadLocal = new ThreadLocal<String>();
        
        // �����ĵ�����  ʹ��CyclicBarrier,�������߳̽���������ͳ���߳�
        final CyclicBarrier end=new CyclicBarrier(10, new Statistics(result));  

        // ʮ��ѡ��         
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
            // �ȴ�
            begin.await();  //countDownLatch ������ ֱ�� countDown()  ������ �ﵽ��ֵ
            Long num = (long) (Math.random() * 10000);
            Thread.sleep(num);  
            result.put(Thread.currentThread().getName(), new Date().toString());
            System.out.println(Thread.currentThread().getName() + " arrived" + "������ "+ num + "��");  
            tl.set(Thread.currentThread().getName()+  " ::  " + num +"��" );
            end.await(); // CyclicBarrier ��await���صȴ�  ֱ�� await()�� �����ﵽ��ֵ
            
            
            //ע�� :: cyclicBarrier�ռ���� await()����֮��  ����������runnable �е� ���� ����ȫ������ �Ż� ������������߼�!!!
            System.out.println("=  ����� ����ֻ��ȵ� CyclicBarrier �� runnable �ӿ�ȫ������֮��Ż� ��ʼ!!!" + Thread.currentThread().getName()
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
