package weather;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import weather.Weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WeatherService {

    public  static Weather getWeather(String cityName)throws  Exception{
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" +
                cityName +
                "&appid=1edaa42700d981bbbdc8967c353226db");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Weather weather = objectMapper.readValue(inputStream, new TypeReference<>(){});
        return weather;
    }

    public static void main(String[] args) throws  Exception{
        System.out.println(getWeather("Toshkent"));
    }
}
