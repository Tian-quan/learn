package cn.stq.learn.concurrency.synchronizer;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * <b>循环栅栏</b>
 * 通过它可以实现让一组线程等待至某个状态之后再全部同时执行。叫做回环是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用。<br/>
 * 例子:10个人赛跑,当都到达终点裁判宣布比赛结束后,运动员才能去休息.
 *
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/3
 */
public class CycleBarrierTest {

    @Test
    public void test() throws InterruptedException {
        int runners = 10;
        ExecutorService executors = Executors.newFixedThreadPool(runners);
        //裁判
        Runnable judger = new Runnable() {
            @Override
            public void run() {
                System.out.println("裁判宣布比赛结束.");

            }
        };

        CyclicBarrier barrier = new CyclicBarrier(runners, judger);

        System.out.println("begin>>>>>>>>>");
        for (int i = 0; i < runners; i++) {
            executors.submit(new Runner(barrier));
        }

        TimeUnit.SECONDS.sleep(10L);
    }


    /**
     * 运动员
     */
    private static class Runner implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Runner(CyclicBarrier barrier) {
            this.cyclicBarrier = barrier;
        }

        @Override
        public void run()  {
            try {
                int runningTime = new Random().nextInt(4) + 1;
                TimeUnit.SECONDS.sleep(runningTime);
                System.out.println(Thread.currentThread().getId() + "arrived,cost time:" + runningTime );
                System.out.println(Thread.currentThread().getId() + " waiting juder announce end."+(cyclicBarrier.getNumberWaiting()+1)+" runners is waiting.....");
                //wait others come to barrier.阻塞这这里,当统计被调用10次后,就执行裁判线程.裁判线程执行后该线程继续往后执行.
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getId() + "--go to rest.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
