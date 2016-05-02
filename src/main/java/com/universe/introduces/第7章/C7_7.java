package com.universe.introduces.第7章;


public class C7_7 {
    static class DeadLoopClass {
        static {
            // 濡傛灉涓嶅姞涓婅繖涓猧f璇彞锛岀紪璇戝櫒灏嗘彁绀衡�淚nitializer does not complete normally鈥濆苟鎷掔粷缂栬瘧
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {}
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}

