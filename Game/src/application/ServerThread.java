package application;

import java.util.concurrent.Exchanger;

import fieldPackage.Field;
import javafx.scene.layout.BorderPane;

public class ServerThread extends Thread {

	private Field field;
	private Options options;
	private ClientThread clientThread;
	private Exchanger<String> ex;
	private String str;

	ServerThread(BorderPane root, Exchanger<String> c) {
		options = new Options();
		field = new Field(root, options);
		ex = c;
	}

	void setThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	public void run() {
		do {
			try {
				if (Thread.currentThread().isInterrupted()) {
					Thread.currentThread().interrupted();
					str = ex.exchange(str);
					if (str != "write" && str != "read") {
						System.out.println(str + " server");
						field.stopGame();
						options.setOptions(str);
						field.startGame();
					}
					if (str == "write") {
						field.stopGame();
						FileSystem fileSystem = new FileSystem();
						fileSystem.write(field);
						field.startGame();
					}
					if (str == "read") {
						field.stopGame();
						field.deleteSnake();
						FileSystem fileSystem = new FileSystem();
						fileSystem.read(field);
						field.startGame();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (true);
	}
}