package io.art.fibers;

import static io.art.fibers.service.FiberService.*;

public class Fibers {
    public static void main(String[] args) {
        createFiber(Fibers::coroutine);
        System.out.println("suspend");
        suspend();
    }

    public static void coroutine() {
        suspend();
        System.out.println("resume");
        System.out.println("Done");
    }
}
