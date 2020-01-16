package pl.sda.intermediate.bank;

import java.util.Random;

import static java.lang.Thread.sleep;

public class ClientActionRunnable implements Runnable {
    @Override
    public void run() {
        int amount = new Random().nextInt(100);
        Bank.withdraw(amount);
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bank.deposit(amount);
    }
}
