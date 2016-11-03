package cn.stq.learn.concurrency.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <b>单次计数门闩</b>
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/3
 */
// 一个CountDouwnLatch实例是不能重复使用的，也就是说它是一次性的，锁一经被打开就不能再关闭使用了，如果想重复使用，请考虑使用CyclicBarrier。
public class CountDownLatchTest {

    // 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
    public static void main(String[] args) throws InterruptedException {

        // 开始的倒数锁
        final CountDownLatch beginLatch = new CountDownLatch(1);

        // 结束的倒数锁
        final int gamers = 10;
        final CountDownLatch endLatch = new CountDownLatch(gamers);

        // 十名选手
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int index = 0; index < gamers; index++) {
            exec.execute(new Gamer(beginLatch,endLatch,index + 1));
        }

        // begin减一，开始游戏
        beginLatch.countDown();
        System.out.println("Game Start");
        // 等待end变为0，即所有选手到达终点
        endLatch.await();
        exec.shutdown();
        System.out.println("Game Over");
    }

    private static class Gamer implements Runnable{
        private  CountDownLatch beginLatch;
        private CountDownLatch endLatch;
        private int no;

        public Gamer(CountDownLatch beginLatch,CountDownLatch endLatch,int no) {
            this.beginLatch = beginLatch;
            this.endLatch = endLatch;
            this.no = no;
        }
        @Override
        public void run() {
            {
                try {
                    // 如果当前计数为零，则此方法立即返回。
                    // 等待
                    beginLatch.await();
                    TimeUnit.SECONDS.sleep(2L);
                    System.out.printf("No.%d arrived%n", no);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                } finally {
                    // 每个选手到达终点时，end就减一
                    endLatch.countDown();
                }
            }
        }
    }
}
