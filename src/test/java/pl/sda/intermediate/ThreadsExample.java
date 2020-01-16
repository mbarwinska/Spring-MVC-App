package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

public class ThreadsExample {

    @Test
    public void runnableBasics(){

        OurRunnable ourRunnable = new OurRunnable();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Klasa anonimowa");
            }
        };
        Runnable lambdaRunnable = () -> System.out.println(Thread.currentThread().getName() + " Lambda Runnable");
        ourRunnable.run();
        Thread t1 = new Thread(ourRunnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(lambdaRunnable);
        t1.start();
        t2.start();
        t3.start();
    }
}
