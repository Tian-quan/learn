package cn.stq.learn.concurrency.synchronizer;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <b>信号量</b>
 * 信号量有立即返回的方法(tryAcquire等),也有阻塞方法(acquire).<br/>
 * 例子:3台机器,5个工人.一台机器同一时间只有一个工人可以操作.现在10人要完成生产任务.
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/3
 */
public class SemaphoreTest {
    @Test
    public void test() throws InterruptedException {
        //信号量,五台机器
        Semaphore sem = new Semaphore(3);
        int workerNum = 5;
        ExecutorService executors = Executors.newFixedThreadPool(workerNum);
        for (int i = 0; i < workerNum; i++) {
            executors.submit(new Worker(sem));
        }

        TimeUnit.SECONDS.sleep(3L);
    }

    private static class Worker implements Runnable{
        private Semaphore sem ;

        public Worker(Semaphore sem) {
            this.sem = sem;
        }

        @Override
        public void run() {
            try {
                System.out.printf("worker(%d) 尝试获取机器使用权.%n",Thread.currentThread().getId());
                sem.acquire();//阻塞
                System.out.printf("worker(%d) 获取机器使用权.%n",Thread.currentThread().getId());
                System.out.printf("worker(%d) 开始生产.%n",Thread.currentThread().getId());
                TimeUnit.SECONDS.sleep(1L);
                System.out.printf("worker(%d) 开始生产完毕,释放机器使用权.%n",Thread.currentThread().getId());
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
