package com.universe.introduces.第3章;

public class C3_6 {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM鍙傛暟锛�-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; // 鐩存帴鍒嗛厤鍦ㄨ�佸勾浠ｄ腑
    }
}


