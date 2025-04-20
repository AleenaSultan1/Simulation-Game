package org.team12;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DungeonGameApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Grab some property values from System
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");

        // Create a Label from a String showing these properties
        Label label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java "
                + javaVersion + ".");

        // Set up our Scene Graph. Use a StackPane layout manager to manage the scene
        StackPane pane = new StackPane();
        pane.getChildren().add(label);
        Scene scene = new Scene(pane, 640, 480);

        // Set the scene to render for the stage and show it!
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
