package pl.sda.intermediate;

import java.math.BigDecimal;

public class Customer {
    private static Integer counter = 1;
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal salary;

    {
        this.id = counter++;
    }

    public Customer(String firstName, String lastName, Integer age, String salary) {
        this(firstName, lastName, age, Double.parseDouble(salary));
    }

    public Customer(String firstName, String lastName, Integer age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = BigDecimal.valueOf(salary);
    }
}
