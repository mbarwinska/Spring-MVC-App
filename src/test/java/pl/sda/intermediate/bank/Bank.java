package pl.sda.intermediate.bank;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;


public class Bank {

    private static AtomicInteger balance = new AtomicInteger(1000);
    private static AtomicInteger counter = new AtomicInteger(0);

    public static /*synchronized*/ void deposit(int amount) {
        balance.addAndGet(amount);
        counter.incrementAndGet();
        System.out.println(Thread.currentThread().getName() + " Stan konta po wpłacie: " + balance);
    }

    public static /*synchronized*/ void withdraw(int amount) {
        balance.addAndGet(-amount);
        System.out.println(Thread.currentThread().getName() + " Stan konta po wypłacie: " + balance);
    }

    public static int getBalance() {
        return balance.intValue();
    }

    public static int getCounter() {
        return counter.intValue();
    }
}
