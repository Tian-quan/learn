package cn.stq.learn.jdk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tianquan.shi on 2016/11/2.
 */
public class ArrayListTest {

    @Test
    public void test002remove() {
        ArrayList<Integer> list = new ArrayList<>();
        //DO 添加11个数据,使容量扩到15.
        for (int i = 1; i <= 11; i++) {
            list.add(i);
        }

        Iterator<Integer> iter = list.listIterator(list.size() - 2);
        System.out.println(iter.next());
        iter.remove();
        System.out.println(list.get(9));
    }

    /**
     * ArrayList的初始长度0
     * 第一次add数据扩容到DEFAULT_CAPACITY=10.
     * 当超过容量,newCapacity = oldCapacity + (oldCapacity>>1)
     */
    @Test
    public void test001Add() {
        List<String> list = new ArrayList<>();
        //DO 第1次添加,容量扩到10.
        list.add("1");
        //DO 添加9个字符串
        for (int i = 0; i < 9; i++) {
            list.add("1");
        }
        //DO 第11次添加字符串,触发第二次扩容.
        list.add("2");
        System.out.println(111);
    }


}
