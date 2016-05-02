package com.universe.introduces.第8章;

class GrandFather {
    void thinking() {
        System.out.println("i am grandfather");
    }
}


class Father extends GrandFather {
    void thinking() {
        System.out.println("i am father");
    }
}


class Son extends Father {
    void thinking() {
        // 璇疯鑰呭湪杩欓噷濉叆閫傚綋鐨勪唬鐮侊紙涓嶈兘淇敼鍏朵粬鍦版柟鐨勪唬鐮侊級
        // 瀹炵幇璋冪敤绁栫埗绫荤殑thinking()鏂规硶锛屾墦鍗�"i am grandfather"
    }
}

