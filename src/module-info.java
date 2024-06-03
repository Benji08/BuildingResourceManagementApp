module App {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc6;

    opens loginapp;
    opens register;
    opens admin;
    opens user;
    opens Main;
}