package view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.util.Scanner;

import Interface.IPoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Column;
import model.DataSet;
import model.Distance;
import model.Iris;
import model.Pokemon;
import model.Titanic;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartGen extends Application implements Observer{

	DataSet dt;
	NumberAxis xAxis;
    NumberAxis yAxis;        
    ScatterChart<Number,Number> sc;
    List<Column> cols;
    ChoiceBox<String> cbx;
    ChoiceBox<String> cby;
    
    @Override 
    public void start(Stage stage) {
    	dt = new DataSet();
    	dt.attach(this);
        stage.setTitle("Classification");
        xAxis = new NumberAxis(0, 1, 0.1);
        yAxis = new NumberAxis(0, 1, 0.1);        
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Attribut X");                
        yAxis.setLabel("Attribut Y");
        sc.setTitle("Aucun type chargé");
       
        final Button changeAxis = new Button("Change Axis");
    	final Button remove = new Button("Remove Last");
    	final Button btn = new Button("Load File");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        
        cbx = new ChoiceBox<String>();
        cby = new ChoiceBox<String>();
          
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (!sc.getData().isEmpty())
            		sc.getData().remove((int)(sc.getData().size()-1));
            	}});
        
        
        btn.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {                	
                	FileChooser fileChooser = new FileChooser();
                	fileChooser.setTitle("Open Resource File");
                	File file = fileChooser.showOpenDialog(stage);
                	String path = file.getAbsolutePath();
                	
                	final Stage dialog = new Stage();
                    dialog.initModality(Modality.APPLICATION_MODAL);
                    dialog.initOwner(stage);
                	final ToggleGroup group = new ToggleGroup();
                	RadioButton rb1 = new RadioButton("Iris");
                	rb1.setToggleGroup(group);
                	rb1.setSelected(true);
                	RadioButton rb2 = new RadioButton("Pokemon");
                	rb2.setToggleGroup(group);                	 
                	RadioButton rb3 = new RadioButton("Titanic");
                	rb3.setToggleGroup(group);
                	
                	 Button load = new Button();
                     load.setText("Load");
                     load.setOnAction(e -> {
                        if (rb1.isSelected()) {
                        	dt.loadFromFiles(path,Iris.class);
                        } else if (rb2.isSelected()) {
                        	dt.loadFromFiles(path,Pokemon.class);
                        } else if (rb3.isSelected()) {
                        	dt.loadFromFiles(path,Titanic.class);
                        }
                        dialog.close();
                     });
                    
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().addAll(rb1,rb2,rb3,load);
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
             });
        
        changeAxis.setOnAction(e -> getChoice());
        
        hbox.setSpacing(10);
        hbox.getChildren().addAll(changeAxis,cby,remove,btn);
        
        vbox.getChildren().addAll(sc,cbx,hbox);
        hbox.setPadding(new Insets(10, 10, 10, 50));
        
        Scene scene  = new Scene(vbox, 500, 400);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    public void getChoice() {
    	Column colx = null;
    	Column coly = null;
    	for (Column c : dt.getData()) {
    		if (c.isNormalizable()) {
    			if (cbx.getValue().equals(c.getName())) {
    				colx = c;
    			} else if (cby.getValue().equals(c.getName())) {
    				coly = c;
    			}
    		}
    	}
    	xAxis.setLabel(colx.getName());
    	yAxis.setLabel(coly.getName());
    	addData(colx, coly);
    }
    	
    
    public void changeType() {
    	cbx.getItems().clear();
		cby.getItems().clear();
		sc.setTitle(dt.getLines().get(0).getClass().getName());
		for (Column c : dt.getData()) {
    		if (c.isNormalizable()) {
    			cbx.getItems().add(c.getName());
    			cby.getItems().add(c.getName());
    		}
    	}
    }
    
    public void addData(Column col1,Column col2) {
    	sc.getData().clear();
    	for (String g : dt.getLines().get(0).getAllGroup()) {
			ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
			series.setName(g);
			for (IPoint i : dt.getLines()) {
				if (Objects.equals(g, i.getGroup())) {
					String valeur1 = ""+col1.getNormalizedValue(i);
					String valeur2 = ""+col2.getNormalizedValue(i);
		        	series.getData().add(new ScatterChart.Data<Number, Number>(Double.valueOf(valeur1),Double.valueOf(valeur2)));
				}
	        }
	        sc.getData().add(series);
		}
    }
    
    
	@Override
	public void update(Subject subj) {
		changeType();
		addData(dt.getData().get(1),dt.getData().get(2));
	}

	@Override
	public void update(Subject subj, Object data) {
		// TODO document why this method is empty
	}
}
