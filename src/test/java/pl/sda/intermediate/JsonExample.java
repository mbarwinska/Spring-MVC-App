package pl.sda.intermediate;

import com.google.gson.Gson;
import lombok.Setter;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonExample {

    @Test
    public void serializeJson() {
        SomeObject someObject = new SomeObject();
        OtherObject otherObject = new OtherObject();
        otherObject.setId(1);
        otherObject.setText("txt");
        List<OtherObject> list = new ArrayList<>();
        list.add(otherObject);
        Map<Integer, String> map = new HashMap<>();
        map.put(5, "coś");
        someObject.setAge(10);
        someObject.setItTrue(true);
        someObject.setList(list);
        someObject.setMap(map);
        someObject.setName("Jason");
        someObject.setSalary(BigDecimal.valueOf(6000.3));

        Gson gson = new Gson();
        String toJson = gson.toJson(someObject);
        System.out.println(toJson);

        SomeObject object = gson.fromJson(toJson, SomeObject.class);
        System.out.println(object);
    }

    @Setter
    private class SomeObject {
        private String name;
        private int age;
        private BigDecimal salary;
        private List<OtherObject> list;
        private Map<Integer, String> map;
        private boolean isItTrue;
    }

    @Setter
    private class OtherObject {
        private int id;
        private String text;
    }

    @Test
    public void parseJsonFromNbp() throws IOException {
        URL url = new URL(" http://api.nbp.pl/api/exchangerates/tables/A/last?format=json");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        String result = "";
        while((line = br.readLine()) != null){
            result += line;
        }
        System.out.println(result);

        Gson gson = new Gson();

        RatesWrapper[] ratesWrapper = gson.fromJson(result, RatesWrapper[].class);
        System.out.println();
    }

    class RatesWrapper {
        private String table;
        private String no;
        private String effectiveDate;
        private List<Rate> rates;
    }

    private class Rate {
        private String currency;
        private String code;
        private BigDecimal mid;
    }
}

