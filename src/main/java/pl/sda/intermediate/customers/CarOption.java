package pl.sda.intermediate.customers;

import lombok.Getter;

@Getter

public class CarOption {
    private static Integer counter = 1;
    private Integer id;
    private String partName;
    private Integer partPrice;

    {
        this.id = counter++;
    }

    public CarOption(String partName, Integer partPrice) {
        this.partName = partName;
        this.partPrice = partPrice;
    }
}
