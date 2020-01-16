package pl.sda.intermediate.customers;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Customer {
    private static Integer counter = 1;
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private BigDecimal salary;
    private String preferences;

    {
        this.id = counter++;
    }

    public Customer(String firstName, String lastName, Integer age, String salary) {
        this(firstName, lastName, age, salary == null ? 0: Double.parseDouble(salary));
    }

    public Customer(String firstName, String lastName, Integer age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = BigDecimal.valueOf(salary);
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
