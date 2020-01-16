package pl.sda.intermediate.calculator;

import java.util.List;
import java.util.stream.Collectors;

public class NegativeNumbersException extends RuntimeException {
    public NegativeNumbersException(List<Integer> negativeNumbers) {
        super(messageCreator(negativeNumbers));
    }

    private static String messageCreator(List<Integer> negativeNumbers) {
        String message = "Negative numbers forbidden: ";
        message = message + negativeNumbers.stream()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        return message;
    }
}
