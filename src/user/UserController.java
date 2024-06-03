package user;

import DatabaseConnection.DatabaseConnection;
import admin.AdminController;

import java.net.URL;
import java.util.ResourceBundle;

/** Main class responsible for all admin functionalities */
public class UserController extends AdminController {
    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.dc = new DatabaseConnection();
    }

}
