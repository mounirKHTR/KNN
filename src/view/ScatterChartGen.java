package view;

import java.io.File;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



import Interface.IPoint;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
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
    List<Column> cols;
    ChoiceBox<String> cbx;
    ChoiceBox<String> cby;
    Stage stage;
    @Override 
    public void start(Stage stage) {
    	this.stage = stage;
    	dt = new DataSet();
    	dt.attach(this);
        stage.setTitle("Classification");
        xAxis = new NumberAxis(0, 1, 0.1);
        yAxis = new NumberAxis(0, 1, 0.1);
        sc = new ScatterChart<>(xAxis,yAxis);
        xAxis.setLabel("Attribut X");                
        yAxis.setLabel("Attribut Y");
        sc.setTitle("Aucun type chargé");
        cbx = new ChoiceBox<>();
        cby = new ChoiceBox<>();
       
        final Button changeAxis = new Button("Change Axis");
    	final Button remove = new Button("Remove Last");
    	final Button btn = new Button("Load File");
    	final Button add = new Button("Add Point");
		final Button classifier = new Button("Classify");
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
                         
        remove.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	if (!sc.getData().isEmpty())
            		sc.getData().remove((sc.getData().size()-1));
            	}});
                
        btn.setOnAction(e -> loadFile());
        changeAxis.setOnAction(e -> getChoice());
        add.setOnAction(e -> addPoint());
		classifier.setOnAction(e -> classify());
        
        hbox.setSpacing(10);
        hbox.getChildren().addAll(changeAxis,cby,btn,add,classifier,remove);
        
        vbox.getChildren().addAll(sc,cbx,hbox);
        hbox.setPadding(new Insets(10, 10, 10, 50));
        
        Scene scene  = new Scene(vbox, 500, 400);
        
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
        List<String> fields = new ArrayList<String>();
        int cpt = 0;
    	for (Field f : dt.getLines().get(0).getClass().getFields()) {
    		if (cpt < dt.getLines().get(0).getClass().getFields().length-1) {
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
            	Iris i = new Iris();
            	dt.addIris(fields);
            } else if (rb2.isSelected()) {
            	Pokemon p = new Pokemon();
            	dt.addPokemon(fields);
            } else if (rb3.isSelected()) {
            	Titanic t = new Titanic();
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
		List<Column> col = new ArrayList<Column>();
		Slider slid = new Slider(1,5,1);
		slid.setShowTickLabels(true);
		slid.setShowTickMarks(true);
		int cpt = 0;
		for (Field f : dt.getLines().get(0).getClass().getFields()) {
			if (cpt < dt.getLines().get(0).getClass().getFields().length-1) {
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
		confirm.setOnAction(e -> {
			int k = (int)slid.getValue();
			for (int idx = 0; idx< vboxCheck.getChildren().size();idx++) {
				CheckBox t = (CheckBox) vboxCheck.getChildren().get(idx);
				if (t.isSelected()) {
					col.add(dt.getData().get(idx));
				}
			}
			MethodeKnn knn = new MethodeKnn();
			if (rb1.isSelected()) {
				System.out.println(knn.getNearestNeigbhour(knn.sortEuclidian(dt.getLines().get(dt.getNbLines()-1),dt.getLines(),col),k));
			} else if (rb2.isSelected()) {
				System.out.println(knn.getNearestNeigbhour(knn.sortManhattan(dt.getLines().get(dt.getNbLines()-1),dt.getLines(),col),k));
			}
			dialog.close();
		});
		HBox hb = new HBox(rb1,rb2);
		VBox vbox2 = new VBox();
		vbox2.getChildren().addAll(hb,slid,confirm);
		VBox vbox = new VBox(vboxCheck,vbox2);
		ScrollPane sp = new ScrollPane(vbox);
		Scene dialogScene = new Scene(sp, 350, 300);
		dialog.setScene(dialogScene);
		dialog.show();
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
		ScatterChart.Series<Number, Number> unclassified = new ScatterChart.Series<>();
		unclassified.setName("unclassified");
    	for (String g : dt.getLines().get(0).getAllGroup()) {
			ScatterChart.Series<Number, Number> series = new ScatterChart.Series<>();
			series.setName(g);
			for (IPoint i : dt.getLines()) {
				String valeur1 = ""+col1.getNormalizedValue(i);
				String valeur2 = ""+col2.getNormalizedValue(i);
				if (Objects.equals(g, i.getGroup())) {
		        	series.getData().add(new ScatterChart.Data<Number, Number>(Double.valueOf(valeur1),Double.valueOf(valeur2)));
				} else if (i.getGroup()==null && !unclassified.getData().contains(i)) {
					unclassified.getData().add(new ScatterChart.Data<Number, Number>(Double.valueOf(valeur1),Double.valueOf(valeur2)));
				}
			}
	        sc.getData().add(series);
		}
		sc.getData().add(unclassified);
    }
        
	@Override
	public void update(Subject subj) {
		changeType();
		addData(dt.getData().get(1),dt.getData().get(2));
	}

	@Override
	public void update(Subject subj, Object data) {

	}
}
