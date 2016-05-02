package com.universe.introduces.第13章;

import java.util.Vector;

public class C13_3 {
	
    
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println((vector.get(i)));
                        }
                    }
                }
            });

            removeThread.start();
            printThread.start();

            //涓嶈鍚屾椂浜х敓杩囧鐨勭嚎绋嬶紝鍚﹀垯浼氬鑷存搷浣滅郴缁熷亣姝�
            while (Thread.activeCount() > 20);
        }
    }
    
    
	 
}