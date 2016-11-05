package cn.stq.learn.concurrency.lock;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * <b>ReentrantLock</b>,{@link ReentrantLock#tryLock()}会尝试获取锁,该方法立即返回,不会阻塞.{@link ReentrantLock#lock()}会阻塞直到获取锁.测试方法{@link #testReentrantLock()}
 * <b>ReadWriteLock</b>,{@link #testReentrantReadWriteLockRead()},{@link #testReentrantReadWriteLockWrite()} 和{@link #testSyncRead()},{@link #testSyncWrite()}展示了读写锁和synchronized关键字来同步的差异.<br/>
 * {@link ReadWriteLock}可在初始化构造时指定是否使用公平特性,即等待最久的线程优先获得锁.<br>
 * 如果有一个线程已经占用了<b>读锁</b>，则此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁。<br/>
 * 如果有一个线程已经占用了<b>写锁</b>，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。<p/>
 *
 * @author tianquan.shi<tianquan.shi@msxf.com>
 * @update
 * @create 2016/11/5
 */
public class LockTest {

    //-----------------读写锁和synchronized同步测试.------------------//

    //使用读写锁,有一个线程已经占用了写锁，则此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁。
    @Test
    public void testReentrantReadWriteLockWrite() throws InterruptedException {
        Reader reader = new Reader(Reader.LOCK, Reader.WRITE);
        for (int i = 0; i < 3; i++) {
            new Thread(reader).start();
        }
        TimeUnit.SECONDS.sleep(2L);
    }

    //使用synchronize同步的方法,会顺序获取锁进入临界区.
    @Test
    public void testSyncWrite() throws InterruptedException {
        Reader reader = new Reader(Reader.SYNC, Reader.WRITE);
        for (int i = 0; i < 3; i++) {
            new Thread(reader).start();
        }
        TimeUnit.SECONDS.sleep(2L);
    }

    //使用读写锁,读操作会竞争进入临界区,这样就大大提升了读操作的效率。
    @Test
    public void testReentrantReadWriteLockRead() throws InterruptedException {
        Reader reader = new Reader(Reader.LOCK, Reader.READ);
        for (int i = 0; i < 3; i++) {
            new Thread(reader).start();
        }
        TimeUnit.SECONDS.sleep(2L);
    }

    //使用synchronize同步的方法,会顺序获取锁进入临界区.
    @Test
    public void testSyncRead() throws InterruptedException {
        Reader reader = new Reader(Reader.SYNC, Reader.READ);
        for (int i = 0; i < 3; i++) {
            new Thread(reader).start();
        }
        TimeUnit.SECONDS.sleep(2L);
    }

    private static class Reader implements Runnable {
        public static final String LOCK = "LOCK";
        public static final String SYNC = "SYNC";
        public static final String READ = "READ";
        public static final String WRITE = "WRITE";
        //SYNC | LOCK
        private final String lockType;
        private final String operation;
        private ReadWriteLock rwLock;

        public Reader(String lockType, String operation) {
            this.lockType = lockType;
            this.operation = operation;
            rwLock = new ReentrantReadWriteLock();
        }

        @Override
        public void run() {
            if (StringUtils.equals(LOCK, this.lockType)) {
                switch (operation) {
                    case READ:
                        this.lockRead();
                        break;
                    case WRITE:
                        this.lockWrite();
                        break;
                }
            } else if (StringUtils.equals(SYNC, this.lockType)) {
                switch (operation) {
                    case READ:
                        this.syncRead();
                        break;
                    case WRITE:
                        this.syncWrite();
                        break;
                }
            }

        }

        //用可冲入读写锁同步读
        private void lockWrite() {
            rwLock.writeLock().lock();
            try {
                long start = System.currentTimeMillis();

                while (System.currentTimeMillis() - start <= 1) {
                    System.out.println(Thread.currentThread().getName() + "正在进行读操作");
                }
                System.out.println(Thread.currentThread().getName() + "读操作完毕");
            } finally {
                rwLock.writeLock().unlock();
            }
        }

        //用可冲入读写锁同步读
        private void lockRead() {
            rwLock.readLock().lock();
            try {
                long start = System.currentTimeMillis();

                while (System.currentTimeMillis() - start <= 1) {
                    System.out.println(Thread.currentThread().getName() + "正在进行读操作");
                }
                System.out.println(Thread.currentThread().getName() + "读操作完毕");
            } finally {
                rwLock.readLock().unlock();
            }
        }

        //用synchronize同步写
        private synchronized void syncWrite() {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(Thread.currentThread().getName() + "正在进行写操作");
            }
            System.out.println(Thread.currentThread().getName() + "写操作完毕");
        }

        //用synchronize同步读
        private synchronized void syncRead() {
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(Thread.currentThread().getName() + "正在进行读操作");
            }
            System.out.println(Thread.currentThread().getName() + "读操作完毕");
        }

    }

    //---------------------------//
    @Test
    public void testReentrantLock() throws InterruptedException {
        Worker worker = new Worker();
        for (int i = 0; i < 3; i++) {
            new Thread(worker).start();
        }
        TimeUnit.SECONDS.sleep(5L);
    }

    private static class Worker implements Runnable {
        private ReentrantLock rlock;

        public Worker() {
            this.rlock = new ReentrantLock();
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " try LOCK.");
            //// block until condition hold
            rlock.lock();
            System.out.println(Thread.currentThread().getName() + " get LOCK.");
            try {
                System.out.println(Thread.currentThread().getName() + " working.");
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " unlock.");
                rlock.unlock();
            }
        }
    }
}
