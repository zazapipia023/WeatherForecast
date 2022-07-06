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
        cityTextField.clear();
        if(!city.equals("")) {
            String output = WeatherForecastUtil.getUrlContent("https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=7f3807d97d32ab879f38caec10cb787a&units=metric");
            if(!output.isEmpty()) {

                String[] arr = WeatherForecastUtil.giveForecast(output);

                cityLabel.setText(city.toUpperCase());
                tempLabel.setText(arr[0]);
                tempFeelsLabel.setText(arr[1]);
                maxTempLabel.setText(arr[2]);
                minTempLabel.setText(arr[3]);
                pressureLabel.setText(arr[4]);

                cityTextField.clear();
            } else {
                cityTextField.setPromptText("CITY NOT FOUND!");
            }
        }
    }

    void checkWeather() {
        String city = cityTextField.getText().trim();
        cityTextField.clear();
        if(!city.equals("")) {
            String output = WeatherForecastUtil.getUrlContent("https://api.openweathermap.org/data/2.5/weather?q="
                    + city + "&appid=7f3807d97d32ab879f38caec10cb787a&units=metric");
            if(!output.isEmpty()) {

                String[] arr = WeatherForecastUtil.giveForecast(output);

                cityLabel.setText(city.toUpperCase());
                tempLabel.setText(arr[0]);
                tempFeelsLabel.setText(arr[1]);
                maxTempLabel.setText(arr[2]);
                minTempLabel.setText(arr[3]);
                pressureLabel.setText(arr[4]);

                cityTextField.clear();
            } else {
                cityTextField.setPromptText("CITY NOT FOUND!");
            }
        }
        checkWeatherButton.requestFocus();
    }

    void onKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().equals(KeyCode.ENTER)) checkWeather();
    }
}