package loginapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import java.util.Objects;



public class LoginApp extends Application {
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("ClassRoomManager");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
