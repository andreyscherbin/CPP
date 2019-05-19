package com.bsuir.vmsis;

import javafx.application.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import java.util.concurrent.Exchanger;

@SpringBootApplication
public class Main extends Application {

	private ConfigurableApplicationContext springContext;

	public static void main(String[] args) {

		System.out.println("Hello World!");
		Application.launch(args);
	}

	@Override
	public void init() {

		springContext = SpringApplication.run(getClass());
		//springContext.getAutowireCapableBeanFactory().autowireBean(this);
	}

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

		System.out.println(root);
		Exchanger<String> exchanger = new Exchanger<String>();
		ServerThread serverThread = new ServerThread(root, exchanger);
		serverThread.start();
		ClientThread clientThread = new ClientThread(root, exchanger);
		clientThread.start();
		serverThread.setThread(clientThread);
		clientThread.setThread(serverThread);
		
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		springContext.close();
	}

}
