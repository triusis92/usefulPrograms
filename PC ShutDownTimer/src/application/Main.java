package application;
	
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	TextField tf;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			VBox h = new VBox();
			tf = new TextField();
			tf.setPromptText("Enter time e.g. 13:04:00");
			tf.setFocusTraversable(false);
			Button b = new Button();
			b.setMinWidth(100);
			b.setText("Start timer");
			b.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					
					try {
						scheduleTimer(tf.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			
			h.getChildren().addAll(tf,b);
			root.setCenter(h);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void shutDown(){
		try {
			Runtime.getRuntime().exec("shutdown -s -t 0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void scheduleTimer(String time) throws ParseException{
		LocalDate today = LocalDate.now().plusDays(1L);
		System.out.println(today.toString()+" "+time);
		
		Timer t=new Timer();
		t.schedule(new TimerTask() {
		    public void run() {
		    	shutDown();
		    }
		}, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(today.toString()+" "+time));
	}
}
