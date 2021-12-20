package country;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CountryService {

    public static String getCountiries() throws Exception {
        URL url = new URL("https://countriesnow.space/api/v0.1/countries");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Data countiries = objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        ArrayList<Country> countries = countiries.getData();

        String res = "";
        for (int i = 0; i < countries.size(); i++) {
            res += (i + 1) + ". " + countries.get(i).getCountry() + "\n";

        }
        res += "\n ⬆️ Mamlakat  raqamini kiriting ⬆️  ";
        return res;

    }

    static public String getCity(int index) throws Exception {
        URL url = new URL("https://countriesnow.space/api/v0.1/countries");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Data countiries = objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        ArrayList<Country> countries = countiries.getData();
        String  res = "\n  ⬇️Shaharni  raqamini kiriting ⬇️ \n\n";
        for (int i = 0; i < countries.get(index).cities.size(); i++) {
            res += (i + 1) + ". " + countries.get(index).cities.get(i) + "\n";
        }

        res += "\n ⬆️ Shaharni  raqamini kiriting ⬆️";
        return res;
    }

    static public String getCityName(int countryIn, int cityId) throws Exception {
        URL url = new URL("https://countriesnow.space/api/v0.1/countries");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        Data countiries = objectMapper.readValue(inputStream, new TypeReference<>() {
        });
        ArrayList<Country> countries = countiries.getData();

        return countries.get(countryIn - 1).cities.get(cityId - 1);

    }


}


