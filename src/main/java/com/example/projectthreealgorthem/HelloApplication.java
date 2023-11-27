package com.example.projectthreealgorthem;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class HelloApplication extends Application {
    Dijkstra dijkstra = new Dijkstra();
    File file = null;
    int w = 1200;
    int h = 800;

    public void InsertTheCountry(Pane map , ChoiceBox<String> src_combo , ChoiceBox<String> dest_combo , TextArea path_field) {


        for (Node node : dijkstra.getNodes().values()) {
            Button button = new Button(node.name);
            button.setOnMouseClicked(e -> {
                if (src_combo.getValue() == null) {
                    src_combo.setValue(node.name);

                } else if (dest_combo.getValue() == null) {
                    dest_combo.setValue(node.name);

                } else {
                    path_field.setText("Source and Destination are full");
                    Alert alert = new Alert(Alert.AlertType.NONE, "Source and Destination are Full ", ButtonType.OK);
                    if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                    }
                }

            });
            button.setStyle(
                    "-fx-background-radius: 10em; " +
                            "-fx-min-width: 7px; " +
                            "-fx-min-height: 7px; " +
                            "-fx-max-width: 7px; " +
                            "-fx-max-height: 7px;" +
                            "-fx-background-color: red"
            );
            Label label = new Label(node.name);
            label.setTextFill(Color.LIGHTBLUE);

            //label.setStyle("-fx-font-size: 15;-fx-text-fill: white ; -fx-stroke: black; -fx-stroke-width: 50;");
            label.setLayoutX(node.x);
            label.setLayoutY(node.y - 15);
            button.setLayoutX(node.x);
            button.setLayoutY(node.y);

            map.getChildren().addAll( label,button);

        }
    }

    private void updateButtonColor(String selectedOption, Button button, String color) {
        if (selectedOption != null && button.getText().equals(selectedOption)) {
            button.setStyle("-fx-background-color: " + color);
        } else {
            button.setStyle("-fx-background-color: red");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {


        Pane st8 = new Pane();
        Image mh8 = new Image("Screenshot_2.png");
        ImageView mah8 = new ImageView(mh8);
        mah8.setFitWidth(1200);
        mah8.setFitHeight(800);


        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setPrefHeight(25);
        choiceBox.setPrefWidth(150);
        choiceBox.setLayoutX(1236);
        choiceBox.setLayoutY(27);
        choiceBox.setAccessibleText("Source");


        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox2.setPrefHeight(25);
        choiceBox2.setPrefWidth(150);
        choiceBox2.setLayoutX(1236);
        choiceBox2.setLayoutY(92);
        choiceBox2.setAccessibleText("Destination");




        TextArea textArea = new TextArea();
        textArea.setPrefWidth(150);
        textArea.setPrefHeight(157);
        textArea.setLayoutX(1236);
        textArea.setLayoutY(140);
        textArea.setEditable(false);


        Group group = new Group();



        Button button = new Button("Run");
        button.setPrefWidth(50);
        button.setPrefHeight(25);
        button.setLayoutX(1260);
        button.setLayoutY(358);
        button.setDisable(true);
        button.setOnAction(event -> {
            if (choiceBox.getValue() == null || choiceBox2.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.NONE, "You must choose a country", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            group.getChildren().clear();

            try {

                textArea.setText("");
                dijkstra.Dijkstra(choiceBox.getValue(), choiceBox2.getValue());
                textArea.setText(dijkstra.getShortPath()+"\n"+dijkstra.getShortPathWeight()+" Meters");

                for (int i = 0; i < dijkstra.shortestPathNodes.length - 1; i++) {
                    Node temp = dijkstra.getNodes().get(dijkstra.shortestPathNodes[i]);
                    Node parent = dijkstra.getNodes().get(dijkstra.shortestPathNodes[i + 1]);

                    double x = temp.x;
                    double y = temp.y;
                    double x1 = parent.x;
                    double y1 = parent.y;

                    Line line = new Line(x, y, x1, y1);
                    Polyline polyline = new Polyline(x, y, x1, y1);

                    if (i == 0 || i == dijkstra.shortestPathNodes.length - 2) {
                        line.setStroke(Color.RED);
                        line.setStrokeWidth(3);
                        polyline.setStroke(Color.RED);
                        polyline.setStrokeWidth(3);
                    } else {
                        line.setStroke(Color.RED);
                        line.setStrokeWidth(3);
                        polyline.setStroke(Color.RED);
                        polyline.setStrokeWidth(3);
                    }

                    group.getChildren().add(polyline);
                }


                dijkstra.setShortPath("");
                dijkstra.shortestPathNodes = null;
                dijkstra.setShortPathWeight(0);
            } catch (NullPointerException e) {
                Alert alert = new Alert(Alert.AlertType.NONE, "Cannot go from " + choiceBox.getValue() + " to " + choiceBox2.getValue(), ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                dijkstra.setShortPath("");
                dijkstra.shortestPathNodes = null;
                dijkstra.setShortPathWeight(0);
            }
        });
        Button Load = new Button("Read");
        Load.setPrefWidth(50);
        Load.setPrefHeight(25);
        Load.setLayoutX(1334);
        Load.setLayoutY(358);
        Load.setOnAction(e->{
            choiceBox.setValue(null);
            choiceBox2.setValue(null);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
             file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                try {
                    dijkstra.read(file);
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "You must Chose a File ", ButtonType.OK);
                if (alert.showAndWait().orElse(ButtonType.NO) == ButtonType.YES) {
                }
                return;
            }

            for (Node node : dijkstra.getNodes().values()) {
                choiceBox.getItems().add(node.name);
            }


            for (Node node : dijkstra.getNodes().values()) {
                choiceBox2.getItems().add(node.name);
            }



            ObservableList<String> items = choiceBox.getItems();
            Collections.sort(items);
            choiceBox.setItems(items);

            ObservableList<String> items2 = choiceBox2.getItems();
            Collections.sort(items2);
            choiceBox2.setItems(items2);

            ObservableList<String> items1 = choiceBox.getItems();
            items.sort(Comparator.naturalOrder());
            choiceBox.setItems(items1);

            ObservableList<String> items3 = choiceBox2.getItems();
            items2.sort(Comparator.naturalOrder());
            choiceBox2.setItems(items3);

            InsertTheCountry(st8,choiceBox,choiceBox2,textArea);

            Load.setDisable(true);

            button.setDisable(false);


        });
        st8.getChildren().addAll(mah8, group, choiceBox, choiceBox2, button, Load,textArea);
        Scene scene = new Scene(st8, 1400, 900);

        stage.setTitle("Dijkstra’s Algorithm!");
        stage.setScene(scene);
       // stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}