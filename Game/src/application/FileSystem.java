package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import fieldPackage.Field;
import fieldPackage.snakePackage.SnakeElement;

/** Класс в котором описана нотация игры */
public class FileSystem {

	private FileWriter writer = null;
	private FileReader reader = null;
	
	/** За голову возьмем '*' */
	private char head = '*';
	
	/** За хвост возьмем '#' */
	private char tail = '#';
	
	/** За обычный элемент возьмем '>' */	
	private char element = '>';
	
	private char x = 'x';
	private char y = 'y';

	public FileSystem() {
	}

	public void write(Field field) throws Exception {
		int i = 1;
		try {
			writer = new FileWriter("C:\\Users\\VALERA\\eclipse-workspace\\Game\\src\\savedgame.txt");
			ArrayList<SnakeElement> list = field.getSnake().getArrayList();
			Collections.reverse(list);
			int lenght = field.getSnake().getLenght();
			int flagWay = field.getSnake().wayFlag();
			for (SnakeElement snakeElement : list) {
				if (i == 1) {
					String s = Integer.toString(lenght);
					writer.write(s);
					s = Integer.toString(flagWay);
					writer.write(s);
					if (lenght == 1) {
						writer.write(head);
					} else {
						writer.write(tail);
					}
					writer.write(x);
					s = Integer.toString(snakeElement.getX());
					writer.write(s);
					writer.write(y);
					s = Integer.toString(snakeElement.getY());
					writer.write(s);
					i++;
					continue;
				}
				if (i != lenght && i < lenght) {
					writer.write(element);
					writer.write(x);
					String s = Integer.toString(snakeElement.getX());
					writer.write(s);
					writer.write(y);
					s = Integer.toString(snakeElement.getY());
					writer.write(s);
					i++;
				}
				if (i == lenght) {
					writer.write(head);
					writer.write(x);
					String s = Integer.toString(snakeElement.getX());
					writer.write(s);
					writer.write(y);
					s = Integer.toString(snakeElement.getY());
					writer.write(s);
					i++;

				}
			}
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("File writing complete.");
	}

	public void read(Field field) throws Exception {
		try {
			System.out.println("read");
			reader = new FileReader("C:\\Users\\VALERA\\eclipse-workspace\\Game\\src\\savedgame.txt");
			ArrayList<SnakeElement> list = field.getSnake().getArrayList();
			list.clear();
			int lenght = Integer.parseInt(String.valueOf(reader.read()));
			int flagWay = Integer.parseInt(String.valueOf(reader.read()));
			System.out.println(lenght);
			System.out.println(flagWay);
			for (int i = 0; i < lenght; i++) {

				reader.read();
				reader.read();
				int x = Integer.parseInt(String.valueOf(reader.read()));
				reader.read();
				int y = Integer.parseInt(String.valueOf(reader.read()));
				SnakeElement snakeElement = new SnakeElement(x, y);
				list.add(snakeElement);
			}
			Collections.reverse(list);
			field.getSnake().way(flagWay);
			field.getSnake().setLenght(lenght);
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("File reading complete.");
	}
}
