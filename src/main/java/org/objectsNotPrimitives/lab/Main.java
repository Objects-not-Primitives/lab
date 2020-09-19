package org.objectsNotPrimitives.lab;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.objectsNotPrimitives.lab.controller.MainController;

import java.net.URL;
import java.util.List;

public class Main extends Application {

    private ObservableList<FXSurface> personData = FXCollections.observableArrayList();

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainScene.fxml");

        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));

        MainController controller = loader.getController();
        controller.setMain(this);

        primaryStage.show();
    }

    public ObservableList<FXSurface> getData() {
        App app = new App();
        List<Surface> surfaceList = app.readFile();
        surfaceList.forEach(surface -> personData.add( surface.surfaceToFX(surface.getId(),surface.getRadius(),surface.getD(),surface.getN())));
        return personData;
    }


}