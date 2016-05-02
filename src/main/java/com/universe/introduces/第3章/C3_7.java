package com.universe.introduces.第3章;

public class C3_7 {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM鍙傛暟锛�-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4]; // 浠�涔堟椂鍊欒繘鍏ヨ�佸勾浠ｅ喅瀹氫簬XX:MaxTenuringThreshold璁剧疆
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }
}


