package pl.sda.intermediate;

import org.junit.jupiter.api.Test;

public class FibonacciExample {

    @Test
    void recursive() {
        System.out.println(fibonacci(6));
    }
    private int fibonacci (int n){
        if (n == 1){
            return 0;
        }
        if (n == 2){
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
