module ru.zaza.weatherforecast {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens ru.zaza.weatherforecast to javafx.fxml;
    exports ru.zaza.weatherforecast;
}