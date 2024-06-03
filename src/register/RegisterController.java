package register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import loginapp.LoginApp;

import java.net.URL;
import java.util.ResourceBundle;

/** Main class responsible for registration */
public class RegisterController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    RegisterModel registerModel = new RegisterModel();

    @FXML
    private TextField loginField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField passwordCheckField;
    @FXML
    private Button registerButton;
    @FXML
    private Label errorLabel;

    /**
     *  Method triggered by the Register button
     *  Adds a new user to the database
     */
    public void registerAction(ActionEvent event){
        try{
            registerModel.setLogin(this.loginField.getText());
            registerModel.setName(this.nameField.getText());
            registerModel.setSurname(this.surnameField.getText());
            registerModel.setPassword(this.passwordField.getText());
            registerModel.setPasswordCheck(this.passwordCheckField.getText());

            if(registerModel.getLogin().isEmpty() || registerModel.getName().isEmpty()
                    || registerModel.getSurname().isEmpty() || registerModel.getPassword().isEmpty()
                    || registerModel.getPasswordCheck().isEmpty() )
            {
                errorLabel.setText("Please fill all fields!");
                return;
            }

            if(!registerModel.validateLogin()){
                errorLabel.setText("Login already taken!");
                return;
            }
            if(!registerModel.validatePasswords()){
                errorLabel.setText("Passwords must be identical");
                return;
            }
            if(!registerModel.validatePasswordStrength()){
                errorLabel.setText("Password must have at least 6 letters");
                return;
            }
            registerModel.registerUser();
            backToLogin();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * After registration, it takes the user back to the login window
     */
    public void backToLogin(){
        try {
            Stage stage = (Stage) this.registerButton.getScene().getWindow();
            stage.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
