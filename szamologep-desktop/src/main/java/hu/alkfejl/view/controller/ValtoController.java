package hu.alkfejl.view.controller;

import hu.alkfejl.model.Switcher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ValtoController implements Initializable {
    /**
     * FXML import
     * minden gombra, es mezore
     * Kevesebb is eleg lett volna, de itt vannak "kesobbi bovithetoseg miatt"
     */
    @FXML
    Button toExchange;
    @FXML
    Button select1;
    @FXML
    Button select2;
    @FXML
    Button select3;
    @FXML
    Button select4;
    @FXML
    TextField number;
    @FXML
    TextField money;
    @FXML
    TextField temp;
    @FXML
    TextField weight;
    @FXML
    ChoiceBox<String> numberFrom;
    @FXML
    ChoiceBox<String> numberTo;
    @FXML
    ChoiceBox<String> moneyTo;
    @FXML
    ChoiceBox<String> tempTo;
    @FXML
    ChoiceBox<String> weightFrom;
    @FXML
    ChoiceBox<String> weightTo;

    /**
     * ChoiceBox-ok dropdown lista elemei kiszervezve
     */
    private Switcher switcher = new Switcher();
    ObservableList numberList = FXCollections.observableArrayList("hex", "dec", "bin", "oct");
    ObservableList moneyList = FXCollections.observableArrayList("toHuf", "toEur");
    ObservableList tempList = FXCollections.observableArrayList("toF", "toC");
    ObservableList weightList = FXCollections.observableArrayList("mg", "g", "dkg", "kg", "t");

    public ValtoController(){
    }

    /**
     * Choisebox-ok feltoltese init kozben
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberFrom.getItems().addAll(numberList);
        numberTo.getItems().addAll(numberList);
        tempTo.getItems().addAll(tempList);
        moneyTo.getItems().addAll(moneyList);
        weightFrom.getItems().addAll(weightList);
        weightTo.getItems().addAll(weightList);
    }

    /**
     * Scene valtas szamologepre
     * @param event
     */
    @FXML
    private void swapScene(javafx.event.ActionEvent event) {
        try {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/hu/alkfejl/view/calculator.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void calcNumber(){
        number.setText(switcher.baseSwitch(number.getText(), numberFrom.getValue(), numberTo.getValue()));
    }

    @FXML
    private void calcTemp(){
        temp.setText(switcher.degree(temp.getText(), tempTo.getValue()));
    }

    @FXML
    private void calcMoney(){
        money.setText(switcher.exchange(money.getText(), moneyTo.getValue()));
    }

    @FXML
    private void calcWeight(){
        weight.setText(switcher.weight(weight.getText(), weightFrom.getValue(), weightTo.getValue()));
    }
}
