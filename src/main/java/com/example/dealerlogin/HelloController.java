package com.example.dealerlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloController {

    @FXML
    private TextField usernameTF;

    @FXML
    private PasswordField passwordPF;
    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button cancelBtn;

    public void cancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void loginBtnOnAction(ActionEvent event){
        if(usernameTF.getText().isBlank()==false &&  passwordPF.getText().isBlank()== false){
            //loginMessageLabel.setText("You tried to login!");
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password!");
        }
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin ="SELECT * FROM `UserAccounts` WHERE `userName` ='"+usernameTF.getText()+"' AND `password`='"+passwordPF.getText()+"';";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("Welcome!");
                }else{
                    loginMessageLabel.setText("Invalid login! Please try again!");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }