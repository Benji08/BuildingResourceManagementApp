package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import register.RegisterController;
import user.UserController;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/** Main class responsible for all login functionalities */
public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();

    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox<loginOption> comboBox;
    @FXML
    private Button loginButton;
    @FXML
    private Label loginStatus;
    @FXML
    private Label optionLabel;
    public void initialize(URL url, ResourceBundle rb){
        if(loginModel.isDatabaseConnected()){
            this.dbStatus.setText("Connected");
        } else {
            this.dbStatus.setText("Not Connected");
        }
        this.comboBox.setItems(FXCollections.observableArrayList(loginOption.values()));
    }

    /**
     *  Method triggered by the Login button
     *  if the login details are correct, it moves the user to the appropriate window
     */
    @FXML
    public void loginAction(ActionEvent event){
        try {
            if (this.comboBox.getValue() == null){
                this.optionLabel.setText("Choose one!");
            }
            else {
                String isAdmin;
                if (this.comboBox.getValue().toString().equals("Admin"))
                    isAdmin = "Y";
                else
                    isAdmin = "N";

                if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), isAdmin)) {
                    Stage stage = (Stage) this.loginButton.getScene().getWindow();
                    stage.close();
                    switch (this.comboBox.getValue().toString()) {
                        case "Admin":
                            adminLogin();
                            break;
                        case "User":
                            userLogin();
                            break;
                    }
                } else {
                    this.loginStatus.setText("Invalid Credentials");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     *  Auxiliary method that is responsible for user login
     */
    public void userLogin(){
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(Objects.requireNonNull(getClass().getResource("/user/user.fxml")).openStream());

            UserController userController = loader.getController();
            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setResizable(false);
            userStage.setTitle("User Dashboard");
            userStage.show();
        } catch (IOException e){
            System.out.println("Failed to open user fxml file");
            e.printStackTrace();
        } catch (NullPointerException e){
        e.printStackTrace();
        }
    }

    /**
     *  Auxiliary method that is responsible for admin login
     */
    public void adminLogin(){
        try {
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = adminLoader.load(Objects.requireNonNull(getClass().getResource("/admin/admin.fxml")).openStream());
            AdminController adminController = adminLoader.getController();

            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setResizable(false);
            adminStage.setTitle("Admin Dashboard");
            adminStage.show();
        } catch (IOException e){
            System.out.println("Failed to open admin fxml file");
            e.printStackTrace();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    /**
     *  Method triggered by the Register button
     *  Takes the user to the registration window
     */
    public void registerAction(ActionEvent event){
        try {
            FXMLLoader registerLoader = new FXMLLoader();
            Pane registerRoot = registerLoader.load(Objects.requireNonNull(getClass().getResource("/register/register.fxml")).openStream());
            RegisterController registerController = registerLoader.getController();

            Scene scene = new Scene(registerRoot);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Register Dashboard");
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.initOwner(loginButton.getScene().getWindow());
            primaryStage.show();

            this.optionLabel.setText("");
            this.loginStatus.setText("");
        } catch (IOException e){
            System.out.println("Failed to open register.fxml");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println(".fxml file is null");
            e.printStackTrace();
        }
    }
}
