package pl.sda.intermediate.app.weather;

import com.google.gson.Gson;
import pl.sda.intermediate.app.users.User;
import pl.sda.intermediate.app.users.UserContextHolder;
import pl.sda.intermediate.app.users.UserDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherService {
    private UserDAO userDAO;
    private String apiKey = "ea900b66f547fd7b23625544873a4200";

    public WeatherService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public WeatherWrapper getWeatherForCurrentUser() {
        String userLoggedIn = UserContextHolder.getUserLoggedIn();
        User user = userDAO.findUserByEmail(userLoggedIn).orElse(null);
        String city = user.getCity();
        String country = user.getCountry().toLowerCase();

        String json = downloadText("https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + ","
                + country
                + "&appid="
                + apiKey
                + "&units=metric&lang="
                + country);
        Gson gson = new Gson();
        return gson.fromJson(json, WeatherWrapper.class);

}

    private String downloadText(String address) {
        try {
            URL url = new URL(address);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (IOException e) {
            return "";
        }
    }
}
