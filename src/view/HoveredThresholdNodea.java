package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

class HoveredThresholdNodea extends StackPane {

    public HoveredThresholdNodea(String string, Object object,Stage stage) {
        setPrefSize(15, 15);

        final Label label = createDataThresholdLabel(string, object);
        Popup popup = new Popup();
        popup.getContent().add(label);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().setAll(label);
                popup.show(stage);
                toFront();
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
            }
        });
    }
    
    
    private Label createDataThresholdLabel(String string, Object object) {
        final Label label = new Label(object + "");
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        if (string.equals("engine1")) {
            label.setTextFill(Color.RED);
            label.setStyle("-fx-border-color: RED;");
        } else if (string.equals("engine2")) {
            label.setTextFill(Color.ORANGE);
            label.setStyle("-fx-border-color: ORANGE;");
        } else {
            label.setTextFill(Color.GREEN);
            label.setStyle("-fx-border-color: GREEN;");
        }

        label.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        return label;
    }
}
