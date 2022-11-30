package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

class HoveredThresholdNodea extends StackPane {

    public HoveredThresholdNodea(String string,Label label) {
        setPrefSize(15, 15);
        String color = getStyle();
        Tooltip tool = new Tooltip(string);
		Tooltip.install(this, tool);
		tool.setShowDelay(Duration.millis(10));

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	setStyle("-fx-background-color: black");
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            	setStyle(color);
            }
        });
    }
}
