module org.tudogostoso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;

    opens org.tudogostoso.fxcontroller to javafx.fxml;
    exports org.tudogostoso.main;
}