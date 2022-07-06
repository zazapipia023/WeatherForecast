package ru.zaza.weatherforecast;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class Controller {

    @FXML
    private Button checkWeatherButton;

    @FXML
    private Label cityLabel;

    @FXML
    private TextField cityTextField;

    @FXML
    private Label maxTempLabel;

    @FXML
    private Label minTempLabel;

    @FXML
    private Label pressureLabel;

    @FXML
    private Label tempFeelsLabel;

    @FXML
    private Label tempLabel;

    @FXML
    void checkWeather(ActionEvent event) {
        String city = cityTextField.getText().trim();
        if(!city.equals("")) {
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=7f3807d97d32ab879f38caec10cb787a&units=metric");
            if(!output.isEmpty()) {

                JSONObject object = new JSONObject(output);

                cityLabel.setText(city.toUpperCase());
                tempLabel.setText(object.getJSONObject("main").getDouble("temp") + "°C");
                tempFeelsLabel.setText(object.getJSONObject("main").getDouble("feels_like") + "°C");
                maxTempLabel.setText(object.getJSONObject("main").getDouble("temp_max") + "°C");
                minTempLabel.setText(object.getJSONObject("main").getDouble("temp_min") + "°C");
                pressureLabel.setText(String.valueOf(object.getJSONObject("main").getDouble("pressure")));

                cityTextField.clear();
                cityTextField.setPromptText("ENTER CITY");
            }
        }
    }

    void checkWeather() {
        String city = cityTextField.getText().trim();
        if(!city.equals("")) {
            String output = getUrlContent("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=7f3807d97d32ab879f38caec10cb787a&units=metric");
            if(!output.isEmpty()) {

                JSONObject object = new JSONObject(output);

                cityLabel.setText(city.toUpperCase());
                tempLabel.setText(object.getJSONObject("main").getDouble("temp") + "°C");
                tempFeelsLabel.setText(object.getJSONObject("main").getDouble("feels_like") + "°C");
                maxTempLabel.setText(object.getJSONObject("main").getDouble("temp_max") + "°C");
                minTempLabel.setText(object.getJSONObject("main").getDouble("temp_min") + "°C");
                pressureLabel.setText(String.valueOf(object.getJSONObject("main").getDouble("pressure")));

                cityTextField.clear();
                cityTextField.setPromptText("ENTER CITY");
            }
        }
        checkWeatherButton.requestFocus();
    }

    public String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) content.append(line + "\n");
            bufferedReader.close();

        } catch (Exception e) {
            cityTextField.clear();
            cityTextField.setPromptText("CITY NOT FOUND!");
        }
        return content.toString();
    }

    void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) checkWeather();
    }
}