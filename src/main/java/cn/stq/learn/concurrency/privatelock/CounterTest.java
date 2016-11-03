package cn.stq.learn.concurrency.privatelock;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by tianquan.shi on 2016/11/1.
 */
public class CounterTest {

    @Test
    public void testConcutrrency() throws InterruptedException {
        Counter c = new Counter();
        Runnable getter = () -> {
            System.out.println(Thread.currentThread().getName() + "--" + c.getCount());
            c.increment();
        };

        for (int i = 0; i < 100; i++) {
            new Thread(getter).start();
        }

        Thread.sleep(2000);
        assertEquals(100,c.getCount());
    }
}