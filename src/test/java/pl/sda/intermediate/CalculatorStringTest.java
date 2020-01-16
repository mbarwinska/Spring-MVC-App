package pl.sda.intermediate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate.calculator.CalculatorString;
import pl.sda.intermediate.calculator.NegativeNumbersException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorStringTest {
    private CalculatorString calculatorString;

    @BeforeEach
    public void setUp(){
         calculatorString = new CalculatorString();
    }

    @Test
    public void shouldReturn0WhenEmptyStringWasEntered(){
        //given
        int expected = 0;

        //when
        int actual = calculatorString.add("");

        //then
        assertEquals(expected,actual);
    }
    @Test
    public void shouldReturn0WhenTextIsNull(){
        //given
        int expected = 0;

        //when
        int actual = calculatorString.add(null);

        //then
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSumWhenTextIsOneNumber(){
        //given
        int expected = 5;

        //when
        int actual = calculatorString.add("5");

        //then
        assertEquals(expected,actual);
    }
    @Test
    public void shouldReturn0WhenTextIsBlank(){
        //given
        int expected = 0;

        //when
        int actual = calculatorString.add(" ");

        //then
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSumWhenTextIsTwoNumbers(){
        //given
        int a = 2;
        int b = 3;
        int expected = a + b;

        //when
        int actual = calculatorString.add("2,3");

        //then
        assertEquals(expected,actual);
    }

    @Test
    public void shouldReturnSumWhenThereAreManyNumbers(){

        //when
        int actual = calculatorString.add("1,2,3");

        //then
        assertEquals(6,actual);
    }
    @Test
    public void shouldReturnSumWhenThereIsCommaOrNewLine(){

        //when
        int actual = calculatorString.add("1 \n2,3");

        //then
        assertEquals(6, actual);
    }
    @Test
    public void shouldReturnSumWhenThereIsCustomDelimiter(){

        //when
        int actual = calculatorString.add("//;\n1;2");

        //then
        assertEquals(3,actual);
    }
    @Test
    public void shouldThrowExceptionWhenNumberIsNegative(){

        //then
        NegativeNumbersException exception = assertThrows(
                NegativeNumbersException.class,
                () -> calculatorString.add("-3,2,-1"));
        assertEquals("Negative numbers forbidden: -3, -1", exception.getMessage());

    }
    @Test
    public void shouldReturnSumIgnoringNumbersBiggerThanThousand(){

        //when
        int actual = calculatorString.add("2,3,1001");

        //then
        assertEquals(5, actual);
    }
    @Test
    public void shouldReturnSumWhenTextHasCustomDelimiter(){

        //when
        int actual = calculatorString.add("//xxx\n2xxx3xxx5xxx4");
        assertEquals(14, actual);
    }


}