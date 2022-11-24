package view;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
    ChoiceBox cbx;
    ChoiceBox cby;
    
    @Override 
    public void start(Stage stage) {
    	dt = new DataSet();
    	dt.attach(this);
        stage.setTitle("Scatter Chart Sample");
        xAxis = new NumberAxis(0, 1, 0.1);
        yAxis = new NumberAxis(0, 1, 0.1);        
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Petal Length");                
        yAxis.setLabel("Petal Width");
        sc.setTitle("Iris Petals");
       
        final Button addI = new Button("Add Iris");
        final Button addP = new Button("Add Pokemon");
        final Button addT = new Button("Add Titanic");
        final Button changeAxis = new Button("Change Axis");
    	final Button remove = new Button("Remove Last");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        
        cbx = new ChoiceBox<String>();
        cby = new ChoiceBox<String>();
          
        addI.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dt.loadFromFiles(name,Iris.class);
            }});
        
        addP.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dt.loadFromFiles(name,Pokemon.class);
            }});
        
        addT.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dt.loadFromFiles(name,Titanic.class);
            }});
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (!sc.getData().isEmpty())
            		sc.getData().remove((int)(sc.getData().size()-1));
            	}});
        
        changeAxis.setOnAction(e -> getChoice());
        
        hbox.setSpacing(10);
        hbox.getChildren().addAll(changeAxis,cby,addI,addP,addT,remove);
        
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
