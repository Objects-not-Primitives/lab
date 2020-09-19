package org.objectsNotPrimitives.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.objectsNotPrimitives.lab.App;
import org.objectsNotPrimitives.lab.FXSurface;
import org.objectsNotPrimitives.lab.Main;
import org.objectsNotPrimitives.lab.Surface;

public class MainController {
    @FXML
    private TableView<FXSurface> mainTable;
    @FXML
    private TableColumn<FXSurface, Integer> idColumn;
    @FXML
    private TableColumn<FXSurface, Double> radColumn;
    @FXML
    private TableColumn<FXSurface, Double> dColumn;
    @FXML
    private TableColumn<FXSurface, Double> nColumn;

    @FXML
    private Button mainButton;


    private Main main;

    public MainController() {
    }

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        radColumn.setCellValueFactory(cellData -> cellData.getValue().radiusProperty().asObject());
        dColumn.setCellValueFactory(cellData -> cellData.getValue().dProperty().asObject());
        nColumn.setCellValueFactory(cellData -> cellData.getValue().nProperty().asObject());
    }

    @FXML
    private void buttonClicked() {
        App app = new App();
        ObservableList<Surface> list = FXCollections.observableArrayList(app.readFile());

        //mainTable.getSelectionModel().selectedItemProperty().addListener();


        /*Alert alert = new Alert(Alert.AlertType.INFORMATION, "123");
        alert.showAndWait();*/
        mainButton.setText("lol");
    }

    public void setMain(Main main){
        this.main = main;

        mainTable.setItems(main.getData());
    }


}
