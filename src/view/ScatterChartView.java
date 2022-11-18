package view;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import model.Column;
import model.DatasetIris;
import model.DistanceEuclidienne;
import model.Iris;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartView extends Application implements Observer{
 
    @Override public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 8, 0.8);
        final NumberAxis yAxis = new NumberAxis(0, 3, 0.3);        
        final ScatterChart<Number,Number> sc = new
            ScatterChart<Number,Number>(xAxis,yAxis);
        xAxis.setLabel("Petal Length");                
        yAxis.setLabel("Petal Width");
        sc.setTitle("Iris Petals");
        DistanceEuclidienne dE = new DistanceEuclidienne();
        List<Column> colI = new ArrayList<Column>();
        DatasetIris dtI = new DatasetIris(dE, colI);
        dtI.loadFromFile("iris.csv");
       
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Iris");
        for(Iris i : dtI.getSet()) {
        	series1.getData().add(new XYChart.Data(i.getPetalLength(), i.getPetalWidth()));
        }
        
        
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
 
        sc.getData().add(series1);
        Scene scene  = new Scene(sc, 500, 400);
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
		// TODO Auto-generated method stub
		
	}
}
