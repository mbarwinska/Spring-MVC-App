package pl.sda.intermediate.customers;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerPreferences {

    static Customer[] people = new Customer[]{
            new Customer("Anna", "Nowak   ",33,"1200"),
            new Customer("Beata", "Kowalska", 22, "1200"),
            new Customer("Marek", " Nowak", 25, "1250 "),
            new Customer("Adam", "Twardowski", 33, "4100 "),
            new Customer("Monika  ", "Kos", 25, "2500"),
            new Customer("Adam ", "Rudy", 45, "3333"),
            new Customer("Marek", "Rudy", 15, 2210),
            new Customer("Adam", "Madej", 15, 3333)

    };
    static CarOption[] items = new CarOption[]{
            new CarOption("Klima",1500),
            new CarOption("Radyjko",1200),
            new CarOption("Wycieraczki",100),
            new CarOption("Dywaniki",150)
    };

    public static List<String> createCustomerPreferenceList(){

        List<String> preferences = new ArrayList<>();
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki:4");
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki:2");
        preferences.add("Klima,Radyjko,Wycieraczki:3,Dywaniki");
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki");
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki");
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki");
        preferences.add("Klima,Radyjko,Wycieraczki:2,Dywaniki");
        preferences.add("Klima,Radyjko,Wycieraczki,Dywaniki");
        return preferences;
    }

    public static List<Customer> populateCustomersPreferenceList(){
        List<String> customerPreferenceList = createCustomerPreferenceList();
        List<Customer> customers = Arrays.asList(people);
        for (int i = 0; i < customers.size(); i++){
            customers.get(i).setPreferences(customerPreferenceList.get(i));
        }
        return customers;
    }

    public List<Integer> giveLackOfMoneyToBuyEverything(){
        List<Customer> customers = populateCustomersPreferenceList();
        List<String[]> collect = customers.stream().map(x -> x.getPreferences()).map(y -> y.split(",")).collect(Collectors.toList());
        return null;
    }

    public static void main(String[] args) {
        System.out.println(populateCustomersPreferenceList());
    }
}
