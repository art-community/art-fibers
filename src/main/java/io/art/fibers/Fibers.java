package io.art.fibers;

import static io.art.fibers.service.FiberService.*;

public class Fibers {
    public static void main(String[] args) {
        createFiber(Fibers::coroutine);
    }

    public static void coroutine() {
        System.out.println("Done");
    }
}
