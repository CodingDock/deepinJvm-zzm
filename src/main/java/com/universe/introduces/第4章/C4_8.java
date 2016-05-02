package com.universe.introduces.第4章;

import java.util.ArrayList;
import java.util.List;

public class C4_8 {
    /**
    * 鍐呭瓨鍗犱綅绗﹀璞★紝涓�涓狾OMObject澶х害鍗�64K
    */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 绋嶄綔寤舵椂锛屼护鐩戣鏇茬嚎鐨勫彉鍖栨洿鍔犳槑鏄�
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }
}
