module com.example.filesorter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.filesorter to javafx.fxml;
    exports com.example.filesorter;
    exports com.example.filesorter.UI;
    opens com.example.filesorter.UI to javafx.fxml;
}