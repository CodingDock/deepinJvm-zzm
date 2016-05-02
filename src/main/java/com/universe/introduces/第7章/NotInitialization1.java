package com.universe.introduces.��7��;

/**
 * 被动使用类字段演示二�?
* 通过数组定义来引用类，不会触发此类的初始�?
 **/
public class NotInitialization1 {

    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }

}

