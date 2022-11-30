package view;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import Interface.IPoint;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import utils.Observer;
import utils.Subject;
 
 
public class ScatterChartGen extends Application implements Observer{

	DataSet dt;
	NumberAxis xAxis;
    NumberAxis yAxis;        
    ScatterChart<Number,Number> sc;
    ChoiceBox<String> cbx;
    ChoiceBox<String> cby;
    Stage stage;
    @Override 
    public void start(Stage stage) {
    	this.stage = stage;
    	dt = new DataSet();
    	dt.attach(this);
        stage.setTitle("Classification");
        xAxis = new NumberAxis(-0.1, 1.1, 0.1);
        yAxis = new NumberAxis(-0.1, 1.1, 0.1);
        sc = new ScatterChart<>(xAxis,yAxis);
        xAxis.setLabel("Attribut X");                
        yAxis.setLabel("Attribut Y");
        sc.setTitle("Aucun type chargé");
        cbx = new ChoiceBox<>();
        cby = new ChoiceBox<>();
       
        final Button changeAxis = new Button("Change Axis");
    	final Button btn = new Button("Load File");
    	final Button add = new Button("Add Point");
		final Button classifier = new Button("Classify");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();

                
        btn.setOnAction(e -> loadFile());
        changeAxis.setOnAction(e -> getChoice());
        add.setOnAction(e -> addPoint());
		classifier.setOnAction(e -> classify());
        
        hbox.setSpacing(10);
        hbox.getChildren().addAll(changeAxis,cbx,btn,add,classifier);
        
        vbox.getChildren().addAll(sc,cby,hbox);
        hbox.setPadding(new Insets(10, 10, 10, 50));
        
        Scene scene  = new Scene(vbox, 700, 500);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void loadFile() {
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
 
    public static void main(String[] args) {
        launch(args);
    }
    
    public void addPoint() {
    	final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox vboxL = new VBox(20);
        VBox vboxTF = new VBox(20);
        List<String> fields = new ArrayList<>();
        int cpt = 0;
    	for (Field f : dt.getLines().get(0).getClass().getFields()) {
    		if (cpt < dt.getLines().get(0).getClass().getFields().length-2) {
	    		Label l = new Label(f.getName());
	    		TextField tf = new TextField();
	    		vboxL.getChildren().add(l);
	    		vboxTF.getChildren().add(tf);
	    		cpt++;
    		}
    	}
    	Button confirm = new Button("Confirm");
    	final ToggleGroup group = new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Iris");
    	rb1.setToggleGroup(group);
    	rb1.setSelected(true);
    	RadioButton rb2 = new RadioButton("Pokemon");
    	rb2.setToggleGroup(group);                	 
    	RadioButton rb3 = new RadioButton("Titanic");
    	rb3.setToggleGroup(group);    	
         confirm.setOnAction(e -> {
        	 for (Node n : vboxTF.getChildren()) {
        		 TextField t = (TextField) n;
        		 fields.add(t.getText());
        	 }
            if (rb1.isSelected()) {
            	dt.addIris(fields);
            } else if (rb2.isSelected()) {
            	dt.addPokemon(fields);
            } else if (rb3.isSelected()) {
            	dt.addTitanic(fields);
            }
            dialog.close();
         });
		HBox hb = new HBox(rb1,rb2,rb3);
		vboxL.getChildren().addAll(hb,confirm);
    	HBox hbox = new HBox(vboxL,vboxTF);
		ScrollPane sp = new ScrollPane(hbox);
        Scene dialogScene = new Scene(sp, 350, 250);
        dialog.setScene(dialogScene);
        dialog.show();
    }

	public void classify() {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(stage);
		VBox vboxCheck = new VBox(20);
		Slider slid = new Slider(1,5,1);
		slid.setBlockIncrement(1);
		slid.setMajorTickUnit(1);
		slid.setMinorTickCount(0);
		slid.setShowTickLabels(true);
		slid.setSnapToTicks(true);
		slid.setShowTickMarks(true);
		slid.valueProperty().addListener((obs, oldval, newVal) ->
				slid.setValue(Math.round(newVal.doubleValue())));
		int cpt = 0;
		for (Field f : dt.getLines().get(0).getClass().getFields()) {
			if (cpt < dt.getLines().get(0).getClass().getFields().length-2) {
				CheckBox check = new CheckBox(f.getName());
				vboxCheck.getChildren().add(check);
				cpt++;
			}
		}
		Button confirm = new Button("Confirm");
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Euclidian");
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton("Manhattan");
		rb2.setToggleGroup(group);
		
		confirm.setOnAction(e -> dt.classify(getSelectedCol(vboxCheck),(int)slid.getValue(),rb1.isSelected()));
		HBox hb = new HBox(rb1,rb2);
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(hb,slid,confirm);
		VBox vbox = new VBox(vboxCheck,vbox2);
		ScrollPane sp = new ScrollPane(vbox);
		Scene dialogScene = new Scene(sp, 350, 300);
		dialog.setScene(dialogScene);
		dialog.show();
	}
	
	public List<Column> getSelectedCol(VBox vbox) {
		List<Column> col = new ArrayList<>();
		for (int idx = 0; idx< vbox.getChildren().size();idx++) {
			CheckBox t = (CheckBox) vbox.getChildren().get(idx);
			if (t.isSelected()) {
				col.add(dt.getData().get(idx));
			}
		}
		return col;
	}
    
    public void getChoice() {
    	Column colx = dt.getData().get(1);
    	Column coly = dt.getData().get(2);
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
		cbx.setValue(cbx.getItems().get(1));
		cby.setValue(cby.getItems().get(2));
    }
    
    public void addData(Column col1,Column col2) {
    	sc.getData().clear();
		xAxis.setLabel(col1.getName());
		yAxis.setLabel(col2.getName());
		XYChart.Series<Number, Number> unclassified = new ScatterChart.Series<>();
    	for (String g : dt.getLines().get(0).getAllGroup()) {
			XYChart.Series<Number, Number> series = new ScatterChart.Series<>();
			for (IPoint i : dt.getLines()) {
				String valeur1 = ""+col1.getNormalizedValue(i);
				String valeur2 = ""+col2.getNormalizedValue(i);
				XYChart.Data<Number, Number> scPoint = new ScatterChart.Data<>(Double.valueOf(valeur1),Double.valueOf(valeur2));
				scPoint.setNode(new HoveredThresholdNodea(i.toString(), i,stage));
				if (Objects.equals(g, i.getGroup())) {
		        	series.getData().add(scPoint);
				} else if (!i.getClassified()) {
					unclassified.getData().add(scPoint);
				}
			}
			series.setName(g+"("+series.getData().size()+")");
	        sc.getData().add(series);
		}
		unclassified.setName("unclassified("+unclassified.getData().size()/dt.getLines().get(0).getAllGroup().size()+")");
		sc.getData().add(unclassified);
    }
        
	@Override
	public void update(Subject subj) {
		changeType();
		addData(dt.getData().get(1),dt.getData().get(2));
	}

	@Override
	public void update(Subject subj, Object data) {
		// unused
	}
}
