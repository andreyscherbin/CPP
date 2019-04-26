package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Exchanger;

import fieldPackage.Field;
import fieldPackage.snakePackage.SnakeElement;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

public class ServerThread extends Thread {

	private Field field;
	private Options options;
	private ClientThread clientThread;
	private Exchanger<String> ex;
	private String str;
	private FileSystem snakeNotation;
	private BorderPane root;
	private ArrayList<FileSystem> notationList;

	ServerThread(BorderPane root, Exchanger<String> c) {
		this.root = root;
		options = new Options();
		snakeNotation = new FileSystem();
		notationList = new ArrayList<FileSystem>();
		field = new Field(root, options, snakeNotation, notationList);
		ex = c;
	}

	void setThread(ClientThread clientThread) {
		this.clientThread = clientThread;
	}

	private void javaQuickSort(int a, int b) {

		if (a >= b)
			return;
		FileSystem pivot = notationList.get((a + b) / 2);
		int left = a;
		int right = b;
		while (left < right) {
			while (notationList.get(left).compareTo(pivot) < 0)
				left++;

			while (notationList.get(right).compareTo(pivot) > 0)
				right--;

			if (right > left)
				
			{
				Collections.swap(notationList, left, right);
			}
		}
		javaQuickSort(a, right - 1);
		javaQuickSort(right + 1, b);
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
						FileSystem snakeNotationc = new FileSystem(snakeNotation);
						if (snakeNotation.N == 0) {
							notationList.add(snakeNotationc);
							snakeNotation.N = 1;
							snakeNotationc.write(field, false, notationList);
						}
						field.startGame();
					}
					if (str == "read") {
						field.stopGame();
						field.deleteSnake();
						snakeNotation.read(field);
						field.startGame();
					}
					if (str == "new") {
						field.stopGame();
						field.deleteSnake();
						snakeNotation = new FileSystem();
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								field = new Field(root, options, snakeNotation, notationList);
							}
						});
					}
					if (str == "javaSort") {
						
						long start = System.nanoTime();
				        long milliStart = System.currentTimeMillis();
				        
						javaQuickSort(0, notationList.size() - 1);
						
						
						long finish = System.nanoTime();
				        long milliStop = System.currentTimeMillis();
				        long nano = finish - start;
				        long milli = milliStop - milliStart;
				        System.out.println(nano + "  " + milli);
				        
				        snakeNotation.write(field, true, notationList);
					}
					
					if (str == "scalaSort") {
						
						long start = System.nanoTime();
				        long milliStart = System.currentTimeMillis();
						
						ScalaQuickSort.scalaQuickSort(notationList);
						
						long finish = System.nanoTime();
				        long milliStop = System.currentTimeMillis();
				        long nano = finish - start;
				        long milli = milliStop - milliStart;
				        System.out.println(nano + "  " + milli);
						
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
