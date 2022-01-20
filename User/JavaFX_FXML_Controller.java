package User;

import Bank.Clients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class JavaFX_FXML_Controller {
    Stage stage = new Stage();

    @FXML
    private Text actiontarget;

    @FXML
    private TextField loginfield;

    @FXML
    private PasswordField passwordField;

    static ArrayList<ArrayList<String>> users = new ArrayList<ArrayList<String>>();

    public int Sign_check(String nick, String pas){
        for (int i = 0; i < users.size();i++){
            if (nick.equals(users.get(i).get(0)) && pas.equals(users.get(i).get(1))){
                return 0;
            }
            if (i==users.size()-1 && nick.equals(users.get(i).get(0))==false){
                return 2;
            }
        }
        if (users.size()==0){
            return 2;
        }
        return 1;
    }

    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        String log = loginfield.getText();
        String pas = passwordField.getText();
        int res = Sign_check(log,pas);
        if (res==0) {
            actiontarget.setText("You have been signed in");
        }else if (res==1) {
            actiontarget.setText("Incorrect username or password");
        }else if (res==2){
            actiontarget.setText("You aren't registered");
        }
    }

    @FXML protected void handleRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml_reg.fxml"));

        Scene scene = new Scene(root, 500, 400);

        stage.setTitle("Registration");
        stage.setScene(scene);
        stage.show();
    }

    public boolean Reg_check(String nick, String pas){
        for (int i = 0; i < users.size();i++){
            if (nick.equals(users.get(i).get(0))){
                return false;
            }
        }
        return true;
    }

    @FXML protected void close(ActionEvent event) {
        String log = loginfield.getText();
        String pas = passwordField.getText();
        boolean res = Reg_check(log,pas);
        if (res==true) {
            ArrayList<String> arr = new ArrayList<String>();
            arr.add(log);
            arr.add(pas);
            users.add(arr);
            actiontarget.setText("You have been registered");
        }else{
            actiontarget.setText("This user already exists");
        }
    }
}
