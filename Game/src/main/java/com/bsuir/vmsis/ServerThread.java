package com.bsuir.vmsis;

import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.vmsis1.Field;
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

	public List<FileSystem> getNotationList() {
		return notationList;
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

	private List<FileSystem> FPQuickSort(List<FileSystem> elements) {

		if (elements.size() == 0)
			return elements;

		FileSystem baseElement = elements.get(0);

		List<FileSystem> lesser = elements.stream().filter(x -> x.compareTo(baseElement) < 0)
				.collect(Collectors.toList());

		List<FileSystem> greater = elements.stream().filter(x -> x.compareTo(baseElement) > 0)
				.collect(Collectors.toList());

		List<FileSystem> sorted = new ArrayList<FileSystem>();
		sorted.addAll(FPQuickSort(lesser));
		sorted.add(baseElement);
		sorted.addAll(FPQuickSort(greater));

		return sorted;
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

						// javaQuickSort(0, notationList.size() - 1);
						notationList = (ArrayList<FileSystem>) FPQuickSort(notationList);

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
