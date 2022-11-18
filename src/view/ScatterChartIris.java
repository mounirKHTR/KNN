package view;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Column;
import model.DatasetIris;
import model.Distance;
import model.Iris;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartIris extends Application implements Observer{

	DatasetIris dtI;
	NumberAxis xAxis;
    NumberAxis yAxis;        
    ScatterChart<Number,Number> sc;
    
    @Override 
    public void start(Stage stage) {
    	Distance dE = new Distance();
        List<Column> colI = new ArrayList<Column>();
    	dtI = new DatasetIris(dE, colI);
    	dtI.attach(this);
        stage.setTitle("Scatter Chart Sample");
        xAxis = new NumberAxis(0, 8, 0.8);
        yAxis = new NumberAxis(0, 3, 0.3);        
        sc = new ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Petal Length");                
        yAxis.setLabel("Petal Width");
        sc.setTitle("Iris Petals");
       
        final Button add = new Button("Add Iris");
    	final Button remove = new Button("Remove Last");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
          
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dtI.loadFromFile(name);
            }});
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (!sc.getData().isEmpty())
            		sc.getData().remove((int)(sc.getData().size()-1));
            	}});
   
        hbox.setSpacing(10);
        hbox.getChildren().addAll(add, remove);
        
        vbox.getChildren().addAll(sc, hbox);
        hbox.setPadding(new Insets(10, 10, 10, 50));
        
        Scene scene  = new Scene(vbox, 500, 400);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Subject subj, Object data) {
		ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
		series.setName("Iris n°"+(sc.getData().size()+1));
		for (Iris i : dtI.getSet()) {
        	series.getData().add(new ScatterChart.Data<Number, Number>(i.getPetalLength(), i.getPetalWidth()));
        }
        sc.getData().add(series);
	}
}
