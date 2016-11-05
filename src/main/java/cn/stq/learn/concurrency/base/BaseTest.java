package cn.stq.learn.concurrency.base;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


/**
 * JDK wait notify notifyAll sleep yield.<br/>
 * <b>sleep</b>,睡眠,放弃CPU使用,让其他线程去竞争CPU时间片.但是会持有锁. 使用{@link #testWaitAndNotify()}测试.<br/>
 * <b>yield</b>,让步,放弃CPU使用,自己和其他进程一起参与竞争CPU时间片,会持有锁.使用{@link #testWaitAndNotify()}测试.<br/>
 * <b>wait</b>,释放锁并等待唤醒.如果没有设置等待时间或者,没有线程调用notify或notifyAll,那么该线程会一直在等待队列中.使用{@link #testWaitAndNotify()}测试.
 * <b>notify</b>,唤醒,唤醒某个等待该锁的线程.即使调用notify方法,也会等该线程退出临界区才释放锁并唤醒某个等待该锁的线程,使用{@link #testWaitAndNotify()}测试.<br/>
 * <b>notifyAll</b>,唤醒所有,唤醒所有等待该锁的线程,但是只要一个锁能竞争到锁,进入临界区.其余与<b>notify</b>类似.<br/>
 * <b>join</b>,加入,假如在main线程中，调用thread.join方法，则main方法会等待thread线程执行完毕或者等待一定的时间。
 * 如果调用的是无参join方法，则等待thread执行完毕，如果调用的是指定了时间参数的join方法，则等待指定的时间.使用{@link #testJoin()}测试.<br/>
 * <b>interrupt</b>,中断,调用interrupt方法可以使得处于阻塞状态的线程抛出一个InterruptedException，也就说，它可以用来中断一个正处于阻塞状态的线程;非阻塞的线程是无法被中断的.
 * 另外，通过interrupt方法和isInterrupted()方法来停止正在运行的线程.通过{@link #testInterrupt()}测试.
 *
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/4
 */
public class BaseTest {

    @Test
    public void tt(){

    }
    @Test
    public void testInterrupt() throws InterruptedException {
        Thread t = new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(50L);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("catch exception->"+e.getMessage());
                }
        });
        t.start();
        TimeUnit.SECONDS.sleep(1L);
        t.interrupt();
    }

    @Test
    public void testJoin() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + ">>>>>>>");
        Thread t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ">>>>>>>");
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "<<<<<<<");
        });
        //如果线程被生成了，但还未被起动，调用它的 join() 方法是没有作用的
        t.start();//
        System.out.println("线程" + Thread.currentThread().getName()+Thread.currentThread().isDaemon() + "等待");
        //DO join, join是一个synchronized方法,main线程需拿到t的锁才能进入,如果锁被其他线程持有,那么等待的时间要加上等待其他线程释放锁的时间.
        //join的实现原理是依靠synchronized和wait,以t线程是否存活为判断条件. main线程调用t.wait进入等待队列.t线程死亡时,调用notify唤醒等待.
//        t.join();//主线程等待t执行完毕后,主线程继续执行.
        t.join(15000);//主线程最多等待15秒,主线程继续执行.如果t在2秒后执行完毕,那么主线程立即继续执行.
        System.out.println("线程" + Thread.currentThread().getName() + "继续执行");
        System.out.println(Thread.currentThread().getName() + "<<<<<<<");

    }

    @Test
    public void testWaitAndNotify() throws InterruptedException {
        Object innerLock = new Object();
        MyThread[] threads = new MyThread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new MyThread(i, innerLock, (num) -> {
                //DO test yield.
                /*Thread.currentThread().yield();
                System.out.println(num + " yield..");*/
                //DO test sleep.
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            threads[i].start();
        }
        TimeUnit.SECONDS.sleep(10L);
        //DO test wait,测试线程5个,偶数(wait)3个,奇数(notify)2个.始终有一个wait的线程不会被唤醒,此处唤醒最后一个阻塞在wait的线程.
//        synchronized (innerLock) {
//            innerLock.notifyAll();
//        }
        for (MyThread t : threads) {
            System.out.println(t.getName() + " is alive--->" + t.isAlive());
        }
    }

    private static class MyThread extends Thread {
        private int num;
        private Object innerLock;
        private Consumer<Integer> work;

        public MyThread(int num, Object innerLock, Consumer<Integer> work) {
            this.num = num;
            this.innerLock = innerLock;
            this.work = work;
            setName(num + "");
        }


        @Override
        public void run() {
            synchronized (innerLock) {
                if (num % 2 == 0) {
                    try {
                        System.out.println(num + " --num is odd, release LOCK and wait....");
                        //释放锁,进入等到被唤醒状态,如果没有持有该锁的进程唤醒,则会一直睡眠.
                        innerLock.wait();
                        System.out.println(num + " --num is odd,exit.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(num + " --num is not odd");
                    System.out.println(num + " do some work...");
                    work.accept(num);
                    System.out.println(num + " notify");
                    innerLock.notifyAll();
                    //DO 模拟一个耗时操作,证明在临界区内使用notify,也会等该线程执行完毕后再释放锁,起唤醒其他某个等待该锁的线程.
                    for (int i = 0; i < 1000000000; i++) {
                    }
                    System.out.println(num + " --num is not odd,exit.");
                }
            }

        }
    }
}
