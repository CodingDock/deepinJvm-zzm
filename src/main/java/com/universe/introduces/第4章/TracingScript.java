package com.universe.introduces.第4章;

/* BTrace Script Template */
import com.sun.btrace.annotations.*;

import net.java.btrace.annotations.Self;

import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class TracingScript {
    @OnMethod(clazz = "org.fenixsoft.monitoring.BTraceTest", method = "add", location = @Location(Kind.RETURN))

    public static void func(@Self BTraceTest instance, int a, int b, @Return int result) {
        println("璋冪敤鍫嗘爤:");
        jstack();
        println(strcat("鏂规硶鍙傛暟A:", str(a)));
        println(strcat("鏂规硶鍙傛暟B:", str(b)));
        println(strcat("鏂规硶缁撴灉:", str(result)));
    }
}

