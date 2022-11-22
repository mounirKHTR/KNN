package view;

import java.util.ArrayList;
import java.util.List;

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
import model.DatasetIris;
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
    
    @Override 
    public void start(Stage stage) {
    	Distance dE = new Distance();
        List<Column> colI = new ArrayList<Column>();
    	dt = new DataSet();
    	dt.attach(this);
        stage.setTitle("Scatter Chart Sample");
        xAxis = new NumberAxis(0, 5200, 520);
        yAxis = new NumberAxis(0, 300, 30);        
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
        ChoiceBox cbx = new ChoiceBox<String>();
        ChoiceBox cby = new ChoiceBox<String>();
          
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
        
        hbox.setSpacing(10);
        hbox.getChildren().addAll(addI,addP,addT,cbx,cby,remove);
        
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
		for (Column c : dt.getData()) {
    		if (c.isNormalizable()) {
    			cols.add(c);
    			
    		}
    	}
		ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
		series.setName("Groupe n°"+(sc.getData().size()+1));
		for (IPoint i : dt.getLines()) {
			String valeur1 = ""+i.getValue(dt.getData().get(2));
			String valeur2 = ""+i.getValue(dt.getData().get(3));
        	series.getData().add(new ScatterChart.Data<Number, Number>(Double.valueOf(valeur1),Double.valueOf(valeur2)));
        }
        sc.getData().add(series);
	}

	@Override
	public void update(Subject subj, Object data) {
		
	}
}
