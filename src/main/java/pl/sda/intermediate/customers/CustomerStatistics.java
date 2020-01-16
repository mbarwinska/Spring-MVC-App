package pl.sda.intermediate.customers;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class CustomerStatistics {

    static Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ", 33, "1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250 "),
            new Customer("Adam", "Twardowski", 33, "4100 "),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333),
            new Customer("Adam", "Nowak ", 15, null)
    };
    static CarOption[] items = new CarOption[]{
            new CarOption("Klima",1500),
            new CarOption("Radyjko",1200),
            new CarOption("Wycieraczki",100),
            new CarOption("Dywaniki",150)
    };

    public static int countPriceOfAllCarOptions () {
        int sum = giveCarOptionList().stream().map(x -> x.getPartPrice()).mapToInt(x -> x).sum();
        return sum;
    }

   public static Map<Integer, List<CarOption>> givePreferencesMap(){
       Map<Integer, List<CarOption>> preferences = new HashMap<>();
       List<CarOption> carOptions = giveCarOptionList();
       preferences.put(1,carOptions);
       preferences.put(2,carOptions);
       preferences.put(3,carOptions);
       preferences.put(4,carOptions);
       preferences.put(5,carOptions);
       preferences.put(6,carOptions);
       preferences.put(7,carOptions);
       preferences.put(8,carOptions);
       return preferences;
   }
    public static List<CarOption> giveCarOptionList() {
        return Arrays.stream(items).collect(Collectors.toList());
    }

    public static List<Customer> convertToList() {
        return Arrays.stream(people).collect(Collectors.toList());
    }

    public static List<String> giveFirstLastNameList() {
        return convertToList().stream().map(x -> x.getFirstName() + x.getLastName()).collect(Collectors.toList());

    }

    public static Map<Integer, Customer> createPeopleMap() {
        return convertToList().stream().collect(Collectors.toMap(Customer::getId, x -> x));
    }

    public static Map<BigDecimal, List<Customer>> createMapSortedBySalary() {
        return convertToList().stream().collect(Collectors.groupingBy(Customer::getSalary));
    }

    public static Map<String, BigDecimal> giveNameAndSumSalaryMap() {
        Map<String, BigDecimal> resultMap = new HashMap<>();
        for (Customer person : people) {
            if (resultMap.containsKey(person.getFirstName())) {
                BigDecimal salarySum = resultMap.get(person.getFirstName());
                BigDecimal newSum = salarySum.add(person.getSalary());
                resultMap.put(person.getFirstName(), newSum);
            } else {
                resultMap.put(person.getFirstName(), person.getSalary());

            }

        }
        return resultMap;
    }

    public static Map<String, Optional<BigDecimal>> giveNameAndSumSalaryMapWithStream() {
        Map<String, Optional<BigDecimal>> collect = convertToList().stream().collect(Collectors.groupingBy(x -> x.getFirstName(),
                Collectors.mapping(e -> e.getSalary(),
                        Collectors.reducing((a, b) -> a.add(b)))));
        return collect;
    }

    public static List<String> giveSetOfUniqueNamesReverseSorted() {
        List<String> uniqueNamesReversed = convertToList()
                .stream()
                .map(x -> x.getFirstName())
                .map(x -> x.trim())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return uniqueNamesReversed;
    }

    public static Map<Boolean,List<Customer>> giveMapOfAdultAndNoAdultCustomers() {

      return convertToList().stream().collect(Collectors.groupingBy(x -> x.getAge() > 18));
    }


    public static void main(String[] args) {
        System.out.println(givePreferencesMap());
    }

}
