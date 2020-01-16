package pl.sda.intermediate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.intermediate.customers.CarOption;
import pl.sda.intermediate.customers.Customer;
import pl.sda.intermediate.customers.CustomerStatistics;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CustomerStatisticsTest {
    @Test
    void shouldGiveProperSalaryCustomersMap() {
        Map<BigDecimal, List<Customer>> mapSortedBySalary = CustomerStatistics.createMapSortedBySalary();

        List<Customer> customers = mapSortedBySalary.get(BigDecimal.valueOf(3333.0));

        Assertions.assertEquals(2, customers.size());
    }

    @Test
    void shouldCreateMapOfCustomerPreferences() {
        //given
        List<CarOption> carOptions = CustomerStatistics.giveCarOptionList();
        Map<Integer, List<CarOption>> expectedMap = new HashMap<>();
        expectedMap.put(1,carOptions);
        expectedMap.put(2,carOptions);
        expectedMap.put(3,carOptions);
        expectedMap.put(4,carOptions);
        expectedMap.put(5,carOptions);
        expectedMap.put(6,carOptions);
        expectedMap.put(7,carOptions);
        expectedMap.put(8,carOptions);
        //when
        Map<Integer, List<CarOption>> resultMap = CustomerStatistics.givePreferencesMap();

        //then
        Assertions.assertEquals(expectedMap,resultMap);
    }
}