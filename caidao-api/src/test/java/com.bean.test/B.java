package com.bean.test;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by bean on 2016/9/12.
 */
public class B {

    private static a s = new a();

    public static void main(String[] args) {

        Thread a = new Thread("a") {
            public void run() {
                s.call(0);
            }
        };
        Thread b = new Thread("b") {
            public void run() {
                s.call(3000);
            }
        };
        a.start();
        b.start();
    }


}

class a {

    Lock lock = new ReentrantLock();

    public void call(long time) {

        try {
            lock.lock();
//            Thread.sleep(time);
            System.out.println(Thread.currentThread().getName() + "----" + time + "----date:" + new Date());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}

