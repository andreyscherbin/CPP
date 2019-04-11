package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import fieldPackage.Field;

public class Main extends Application {

	private Field field;
	private Options options;

	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();

		MenuBar mb = new MenuBar();
		Menu newgame = new Menu("Новая игра");
		mb.getMenus().add(newgame);
		root.setTop(mb);

		Menu record = new Menu("Рекорды");
		mb.getMenus().add(record);

		Menu level = new Menu("Сложность");
		Menu automode = new Menu("Automode");
		Menu saveandopen = new Menu("save/open");

		MenuItem save = new MenuItem("save");
		MenuItem open = new MenuItem("open");
		saveandopen.getItems().addAll(save, open);
		save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				FileAnnotation fileannotation = new FileAnnotation(field, "write");
			}
		});
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				FileAnnotation fileannotation = new FileAnnotation(field, "read");
			}
		});

		MenuItem automodeon = new MenuItem("on");
		MenuItem automodeoff = new MenuItem("off");
		automode.getItems().addAll(automodeon, automodeoff);
		automodeon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				field.stopGame();
				options.setOptions("automodeOn");
				root.getChildren().clear();
				root.setTop(mb);
				field = new Field(root, options);
			}
		});
		automodeoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				field.stopGame();
				options.setOptions("automodeOff");
				root.getChildren().clear();
				root.setTop(mb);
				field = new Field(root, options);
			}
		});

		MenuItem easy = new MenuItem("Легкий");

		easy.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				field.stopGame();
				options.setOptions("easy");
				root.getChildren().clear();
				root.setTop(mb);
				field = new Field(root, options);
			}
		});

		MenuItem medium = new MenuItem("Средний");
		medium.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				field.stopGame();
				options.setOptions("medium");
				root.getChildren().clear();
				root.setTop(mb);
				field = new Field(root, options);
			}
		});
		MenuItem hard = new MenuItem("Тяжелый");
		hard.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				field.stopGame();
				options.setOptions("hard");
				root.getChildren().clear();
				root.setTop(mb);
				field = new Field(root, options);
			}
		});
		level.getItems().addAll(easy, medium, hard);
		mb.getMenus().add(level);
		mb.getMenus().add(automode);
		mb.getMenus().add(saveandopen);

		options = new Options();
		field = new Field(root, options);
	}

	public static void main(String[] args) {

		launch(args);
	}
}
