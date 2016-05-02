package com.universe.introduces.第3章;

public class C3_5 {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM鍙傛暟锛�-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
      */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 鍑虹幇涓�娆inor GC
    }

}


