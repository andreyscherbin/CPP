package com.bsuir.vmsis;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.bsuir.vmsis1.Field;

/** Класс в котором описана нотация игры */
public class FileSystem implements Comparable<FileSystem> {

	private FileWriter writer;
	private FileReader reader;

	/** За голову возьмем '*' */
	private char head = '*';

	/** За хвост возьмем '#' */
	private char tail = '#';
	
	private char x = 'x';
	private char y = 'y';

	public int[] masX;
	public int[] masY;
	public int[] masWay;
	public int[] lenght;
	public int index = 0;
	public int N = 0;	

	public FileSystem() {

		masX = new int[50];
		masY = new int[50];
		masWay = new int[50];
		lenght = new int[50];
	}

	public FileSystem(FileSystem copyObject) {

		masX = new int[50];
		masY = new int[50];
		masWay = new int[50];
		lenght = new int[50];
		index = copyObject.index;
		N = copyObject.N;
		System.arraycopy(copyObject.masX, 0, masX, 0, 50);
		System.arraycopy(copyObject.masY, 0, masY, 0, 50);
		System.arraycopy(copyObject.masWay, 0, masWay, 0, 50);
		System.arraycopy(copyObject.lenght, 0, lenght, 0, 50);
	}

	public void write(Field field, boolean rewrite, ArrayList<FileSystem> notationList) throws Exception {
		if (!rewrite) {

			writer = new FileWriter("../Game/src/main/resources/savedgame.txt", true);
			BufferedWriter bufferWriter = new BufferedWriter(writer);

			try {

				for (int i = 0; i < index; i++) {
					String s = Integer.toString(lenght[i]);
					writer.write(s);
					s = Integer.toString(masWay[i]);
					writer.write(s);
					writer.write(head);
					writer.write(x);
					s = Integer.toString(masX[i]);
					writer.write(s);
					writer.write(y);
					s = Integer.toString(masY[i]);
					writer.write(s);
					writer.write(' ');

				}
				writer.write('\n');
				bufferWriter.close();

			} catch (Exception e) {
				System.out.println("error rewrite file system");
			}

		}
		if (rewrite) {

			writer = new FileWriter("../Game/src/main/resources/savedgame.txt");
			for (FileSystem snakeNotation : notationList) {

				for (int i = 0; i < snakeNotation.index; i++) {
					String s = Integer.toString(snakeNotation.lenght[i]);
					writer.write(s);
					s = Integer.toString(snakeNotation.masWay[i]);
					writer.write(s);
					writer.write(snakeNotation.head);
					writer.write(snakeNotation.x);
					s = Integer.toString(snakeNotation.masX[i]);
					writer.write(s);
					writer.write(snakeNotation.y);
					s = Integer.toString(snakeNotation.masY[i]);
					writer.write(s);
					writer.write(' ');
				}
				writer.write('\n');

			}
			writer.close();
		}

	}

	public void read(Field field) throws IOException {

		List<String> lines = Files.readAllLines(Paths.get("../Game/src/main/resources/savedgame.txt"),
				StandardCharsets.UTF_8);
		for (String line : lines) {
			System.out.println(line);
		}
	}

	public int compareTo(FileSystem o) {

		Integer lenght1 = this.lenght[this.index - 1];
		Integer lenght2 = o.lenght[o.index - 1];

		if (lenght2 < lenght1) {
			return -1;
		} else if (lenght2 > lenght1) {
			return 1;
		} else {
			return 0;
		}
	}
}
