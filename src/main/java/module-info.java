module org.tudogostoso {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.mail;
    requires java.desktop;
    requires static lombok;
    requires org.json;

    opens org.tudogostoso.fxcontroller to javafx.fxml;
    exports org.tudogostoso.main;
}