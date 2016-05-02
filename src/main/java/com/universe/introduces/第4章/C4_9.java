package com.universe.introduces.第4章;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class C4_9 {
    /**
    * 绾跨▼姝诲惊鐜紨绀�
    */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) // 绗�41琛�
                ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 绾跨▼閿佺瓑寰呮紨绀�
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj);
    }
}

