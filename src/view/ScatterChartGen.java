import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Interface.IDataSet;
import Interface.IPoint;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Iris;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartGen extends Application implements Observer{
	//IDataSet dt;
	//List<Iris> listIris;
	//List<List<Iris>> data;
	DatasetIris dtI;
	final Button add = new Button("Add Iris");
	final Button remove = new Button("Remove Last");
	final NumberAxis xAxis = new NumberAxis(0, 8, 0.8);
    final NumberAxis yAxis = new NumberAxis(0, 3, 0.3);        
    final ScatterChart<Number,Number> sc = new
        ScatterChart<Number,Number>(xAxis,yAxis);
    DistanceEuclidienne dE = new DistanceEuclidienne();
    List<Column> colI = new ArrayList<Column>();
 
    @Override 
    public void start(Stage stage) {
    	dtI = new DatasetIris(dE, colI);
    	dtI.attach(this);
    	
        dtI = new DatasetIris(dE, colI);
        //dtI.loadFromFile("iris.csv");
    	
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 8, 0.8);
        final NumberAxis yAxis = new NumberAxis(0, 3, 0.3);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Petal Length");                
        yAxis.setLabel("Petal Width");
        sc.setTitle("Iris Petals");
       
        /*XYChart.Series series1 = new XYChart.Series();
        series1.setName("Iris");
        for(Iris i : dtI.getSet()) {
        	series1.getData().add(new XYChart.Data(i.getPetalLength(), i.getPetalWidth()));
        }*/
        
        
        /*XYChart.Series series2 = new XYChart.Series();
        series2.setName("Mutual funds");
        series2.getData().add(new XYChart.Data(5.2, 229.2));
        series2.getData().add(new XYChart.Data(2.4, 37.6));
        series2.getData().add(new XYChart.Data(3.2, 49.8));
        series2.getData().add(new XYChart.Data(1.8, 134));
        series2.getData().add(new XYChart.Data(3.2, 236.2));
        series2.getData().add(new XYChart.Data(7.4, 114.1));
        series2.getData().add(new XYChart.Data(3.5, 323));
        series2.getData().add(new XYChart.Data(9.3, 29.9));
        series2.getData().add(new XYChart.Data(8.1, 287.4));*/
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
          
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	Scanner scan= new Scanner(System.in);
            	System.out.println("Entrez le nom du fichier");
            	String name= scan.next();
            	dtI.loadFromFile(name);
	            //sc.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
	            ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
	            dtI = new DatasetIris(dE, colI);
	            dtI.loadFromFile(name);
	            series.setName("Iris n°"+(sc.getData().size()+1));
	            for (Iris i : dtI.getSet()) {
	            	series.getData().add(new ScatterChart.Data<Number, Number>(i.getPetalLength(), i.getPetalWidth()));
	            }
	            sc.getData().add(series);
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
        
 
        //sc.getData().add(series1);
        Scene scene  = new Scene(vbox, 500, 400);
        
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    public void add() {
    	//if (sc.getData() == null) 
    	/*System.out.println("yes");
        sc.setData(FXCollections.<XYChart.Series<Number, Number>>observableArrayList());
        ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
        DatasetIris dt = new DatasetIris(dE, colI);
        dt.loadFromFile("iris.csv");
        series.setName("Iris n°"+(sc.getData().size()+1));
        for (Iris i : dt.getSet()) series.getData().add(
        new ScatterChart.Data<Number, Number>(i.getPetalLength(), i.getPetalWidth()));
        sc.getData().add(series);*/
    }
    
    public void remove() {
    	/*if (!sc.getData().isEmpty())
    		sc.getData().remove((int)(Math.random()*(sc.getData().size()-1)));*/

    }
    
    /*public List<List<IPoint>> getdtDT(Subject subj) {
		// TODO Auto-generated method stub
		return dtDt;
	}*/
    
	@Override
	public void update(Subject subj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Subject subj, Object data) {
		// TODO Auto-generated method stub
		dtI.setSet((List<Iris>) data);
		ScatterChart.Series<Number, Number> series = new ScatterChart.Series<Number, Number>();
		series.setName("Iris n°"+(sc.getData().size()+1));
		for (Iris i : dtI.getSet()) {
        	series.getData().add(new ScatterChart.Data<Number, Number>(i.getPetalLength(), i.getPetalWidth()));
        }
        sc.getData().add(series);
	}
}
