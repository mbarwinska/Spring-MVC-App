package pl.sda.intermediate.calculator;

import pl.sda.intermediate.calculator.NegativeNumbersException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CalculatorString {

    public int add(String text) {
        if (text == null || text.isEmpty() || text.startsWith(" ")) {
            return 0;
        }
        String[] splitNumbers = {};
        if (text.startsWith("//")) {
            Pattern pattern = Pattern.compile("(//)(.+)(\\n)(.+)");
            Matcher matcher = pattern.matcher(text);
           if(matcher.matches()){
               String delimiter = matcher.group(2);
               String numbersInString = matcher.group(4);
               splitNumbers = numbersInString.split(delimiter);
           }

        } else {
            splitNumbers = text.split(",| \\n");
        }
        List<Integer> negativeNumbers = Arrays.stream(splitNumbers).map(Integer::parseInt).filter(x -> x < 0).collect(Collectors.toList());
        if (!negativeNumbers.isEmpty()) {

            throw new NegativeNumbersException(negativeNumbers);
        }

        return Arrays.stream(splitNumbers).map(Integer::parseInt).mapToInt(y -> y).filter(x -> x < 1000).sum();
    }
}
