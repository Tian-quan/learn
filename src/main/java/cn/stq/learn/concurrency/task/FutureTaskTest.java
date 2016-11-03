package cn.stq.learn.concurrency.task;

import cn.stq.learn.utils.TestUtil;
import org.junit.Test;

import java.util.concurrent.*;


/**
 * FutureTask实现Callable接口,用于获取线程执行结果.
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/3
 */
public class FutureTaskTest {

    /**
     * 使用Callable+FutureTask获取执行结果
     */
    @Test
    public void test002CallableFutureTask(){
        ExecutorService executors= Executors.newCachedThreadPool();
        MyTask task = new MyTask();
        FutureTask<Integer> futureTask = new FutureTask<>(task);

        executors.submit(futureTask);
        executors.shutdown();

        TestUtil.out("main is running.");
        try {
            //阻塞在这里,知道task执行完毕返回.
            TestUtil.out("result-->"+futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TestUtil.out("main is exiting.");
    }
    /**
     * 使用Callable+Future获取执行结果
     */
    @Test
    public void test001CallableFuture(){
        ExecutorService executors= Executors.newCachedThreadPool();
        MyTask task = new MyTask();
        Future<Integer> future = executors.submit(task);
        executors.shutdown();

        TestUtil.out("main is running.");
        try {
            //阻塞在这里,知道task执行完毕返回.
            TestUtil.out("result-->"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        TestUtil.out("main is exiting.");
    }

    private static class MyTask implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            TestUtil.out("MyTask 正在计算.");
            int sum = 0;
            for(int i=0;i<1000000;++i){
                sum += i;
            }
            TimeUnit.SECONDS.sleep(2L);
            return sum;
        }
    }
}
