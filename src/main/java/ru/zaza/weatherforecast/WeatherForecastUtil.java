package ru.zaza.weatherforecast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WeatherForecastUtil {

    public static String[] giveForecast(String output) {
        JSONObject object = new JSONObject(output);

        return new String[] {
                object.getJSONObject("main").getDouble("temp") + "°C",
                object.getJSONObject("main").getDouble("feels_like") + "°C",
                object.getJSONObject("main").getDouble("temp_max") + "°C",
                object.getJSONObject("main").getDouble("temp_min") + "°C",
                String.valueOf(object.getJSONObject("main").getDouble("pressure"))
        };
    }

    public static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) content.append(line + "\n");
            bufferedReader.close();

        } catch (Exception e) {
            return "";
        }
        return content.toString();
    }

}
