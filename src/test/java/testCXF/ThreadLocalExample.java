package testCXF;

public class ThreadLocalExample {

 

    public static class MyRunnable implements Runnable {

 

        private ThreadLocal threadLocal = new ThreadLocal();

        public void run() {
        	int num = (int) (Math.random() * 100D);
            threadLocal.set(num);
            System.out.println(num);

            try {

            Thread.sleep(2000);

            } catch (InterruptedException e) {

 

            }

            System.out.println(threadLocal.get());

        }

    }

 

    public static void main(String[] args) {

         MyRunnable sharedRunnableInstance = new MyRunnable();

         Thread thread1 = new Thread(sharedRunnableInstance);

         Thread thread2 = new Thread(sharedRunnableInstance);

         thread1.start();

         thread2.start();

    }

 

}

