package ThreadStudy;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadStudy {

    @Test
    public void test(){
        Thread thread = new Thread(()->{
            int num = 0;
            while (num<=Integer.MAX_VALUE/2 && !java.lang.Thread.currentThread().isInterrupted()){
                if(num % 1000 == 0)
                    System.out.println(num);
                num++;
            }

        });

        try {
            thread.start();
            thread.sleep(1000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){

        Thread thread = new Thread(()->{
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("线程正在执行!");
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getId());

                    Thread.currentThread().interrupt();//重新设置中断
                    System.out.println("第一次调用thread.interrupted()2："+Thread.currentThread().interrupted());
                    System.out.println("第一次调用thread.interrupted()2："+Thread.currentThread().interrupted());
//                    e.printStackTrace();
                }
            }
            System.out.println("第一次调用thread.interrupted()："+Thread.currentThread().interrupted());
            System.out.println("线程给中断了,可恶!");
        });

        try {
            thread.start();
            thread.sleep(1000);
            thread.interrupt();
            System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
            System.out.println("第一次调用thread.interrupted()："+thread.interrupted());
            System.out.println("get id ："+thread.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        Thread thread=new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("i="+(i+1));
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println("第一次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("第二次调用thread.isInterrupted()："+thread.isInterrupted());
        System.out.println("thread是否存活："+thread.isAlive());
    }

    //AtomicInteger:int类型的原子操作类,在此例子中由于num(线程外声明的变量)需要被多线程修改值,未了保证多线程中获取的num值一致,所以需要int类型的原子操作类
    AtomicInteger num = new AtomicInteger(10);
    public void increment() throws Exception{
        System.out.println("线程1获取的num值:"+num.get());
        int sum = 10/num.get();
        System.out.println("sum:"+sum);
        System.out.println("方法中:"+num.get());
        num.addAndGet(-1);
    }
    @Test
//子线程获取异常,自己处理
    public void test4(){
        Thread thread = new Thread(()->{
            while(num.get()>-10&&!Thread.currentThread().isInterrupted()){
               try {
                   increment();
               }catch (Exception e){
                   e.printStackTrace();
                   System.out.println("线程1出现异常,停止");
                   Thread.currentThread().interrupt();
               }
           }
            System.out.println("线程1正常完成!");
        });

        Thread thread2 = new Thread(()->{
            while(num.get()>-10&&!Thread.currentThread().isInterrupted()){
                try {
                    increment();
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("线程2出现异常,停止");
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("线程2正常完成!");
        });
        try {
            thread.start();
            thread.join();
            System.out.println("线程2开始执行");
            thread2.start();
            thread2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
//子线程抛出异常,父线程捕获异常处理
    public void test5(){
        Thread thread = new Thread(()->{
            try {
            while(num.get()>-10)
                increment();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("线程1出现异常,停止");
                Thread.currentThread().interrupt();
            }
            System.out.println("线程1正常完成!");
        });
        try {
            thread.start();
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
