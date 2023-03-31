package is.hi.eidurK.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;

import is.hi.eidurK.vidmot.LibraryApplication;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LibraryApplication.class.getResource("bouncing-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        BouncingController b = fxmlLoader.getController();
        b.orvatakkar(fxmlLoader.getController(), scene);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }


    }
