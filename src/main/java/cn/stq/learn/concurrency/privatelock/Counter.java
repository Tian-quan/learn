package cn.stq.learn.concurrency.privatelock;

/**
 *私有锁对象,而不是对象的内部锁,有很多好处.<br/>
 *私有的锁对象可以封装锁,这样客户代码就无法得到它.然而可以通过公共访问的锁允许客户代码涉足它的同步策略.<br/>
 * Created by tianquan.shi on 2016/11/1.
 */
public class Counter {
    private int n;
    //private lock
    private Object privateLock = new Object();

    public int getCount(){
        synchronized (privateLock){
            return n;
        }
    }

    public void increment(){
        synchronized (privateLock){
            ++this.n;
        }
    }
}
