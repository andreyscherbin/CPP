package application;

import java.util.concurrent.Exchanger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class ClientThread extends Thread {

	private BorderPane root;
	private ServerThread serverThread;

	void setThread(ServerThread serverThread) {
		this.serverThread = serverThread;
	}

	ClientThread(BorderPane root, Exchanger<String> e) {

		Exchanger<String> exgr = e;
		this.root = root;

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
				String str = "write";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		open.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "read";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		MenuItem automodeon = new MenuItem("on");
		MenuItem automodeoff = new MenuItem("off");
		automode.getItems().addAll(automodeon, automodeoff);
		automodeon.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "automodeOn";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		automodeoff.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "automodeOff";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		MenuItem easy = new MenuItem("Легкий");

		easy.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "easy";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		MenuItem medium = new MenuItem("Средний");
		medium.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "medium";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		MenuItem hard = new MenuItem("Тяжелый");
		hard.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				String str = "hard";
				try {
					serverThread.interrupt();
					str = exgr.exchange(str);
					System.out.println(str + " client");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		level.getItems().addAll(easy, medium, hard);
		mb.getMenus().add(level);
		mb.getMenus().add(automode);
		mb.getMenus().add(saveandopen);

	}

	/** Основной алгоритм реализации жизни змейки на поле игры */
	public void run() {
		do {
			System.out.println("ClientSnake");
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (true);
	}
}