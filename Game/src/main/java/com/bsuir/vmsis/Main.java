package com.bsuir.vmsis;

import javafx.application.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import java.util.concurrent.Exchanger;

import com.bsuir.vmsis1.Field;

@SpringBootApplication
public class Main extends Application {
	public void start(Stage primaryStage) {
		
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,700,700);
		primaryStage.setScene(scene);
		primaryStage.show();		
		Exchanger<String> exgr = new Exchanger<String>();			
		ServerThread serverThread = new ServerThread(root,exgr);
		serverThread.start();
		ClientThread clientThread = new ClientThread(root,exgr);
		clientThread.start();		
		serverThread.setThread(clientThread);
		clientThread.setThread(serverThread);			
		
	}

	public static void main(String[] args) {		
		
		launch(args);
		
	}
}
