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
import model.DataSet;
import model.DatasetIris;
import model.Distance;
import model.Iris;
import model.Pokemon;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartGen extends Application implements Observer{

	DatasetIris dtI;
	DataSet dt;
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
       
        final Button addI = new Button("Add Iris");
        final Button addP = new Button("Add Pokemon");
        final Button addT = new Button("Add Titanic");
    	final Button remove = new Button("Remove Last");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
          
        addI.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dtI.loadFromFile(name);
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
            	dt.loadFromFiles(name,Pokemon.class);
            }});
        
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (!sc.getData().isEmpty())
            		sc.getData().remove((int)(sc.getData().size()-1));
            	}});
   
        hbox.setSpacing(10);
        hbox.getChildren().addAll(addI,addP,addT, remove);
        
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
		System.out.println(series.getName());
		for (Iris i : dtI.getSet()) {
        	series.getData().add(new ScatterChart.Data<Number, Number>(i.getPetalLength(), i.getPetalWidth()));
        	System.out.println(i.toString());
        }
		System.out.println(series.getData().size());
        sc.getData().add(series);
	}
}
