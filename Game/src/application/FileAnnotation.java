package application;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import fieldPackage.Field;
import fieldPackage.snakePackage.Snake;
import fieldPackage.snakePackage.SnakeElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/** Аннотация к методу запись в файл */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface WriteInFile {

	String name() default "hello, i am write annotation";

	String way() default "../Game/src/savedgame.txt";
}

/** Аннотация к методу чтение из файла */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface ReadFromFile {

	String name() default "hello, i am read annotation";

	String way() default "../Game/src/savedgame.txt";
}

/**
 * Класс в котором будем извлекать аннотации и вызывать соответствующие им
 * методы
 */
public class FileAnnotation {
	public FileAnnotation(Field field, String nameOperation) {
		WorkWithFile object = new WorkWithFile();
		for (Method method : object.getClass().getDeclaredMethods()) {
			if (nameOperation == "write") {
				if (method.isAnnotationPresent(WriteInFile.class)) {
					try {

						method.invoke(object, field); // Через рефлекцию вызываем функцию write

					} catch (Exception e) {

					}
				}
			}
			if (nameOperation == "read") {
				if (method.isAnnotationPresent(ReadFromFile.class)) {
					try {
						method.invoke(object, field); // Через рефлекцию вызываем функцию read

					} catch (Exception e) {

					}
				}

			}
		}
	}
}

/** Класс в котором описаны методы чтения и записи в файл */
class WorkWithFile {

	private FileOutputStream output = null;
	private FileInputStream input = null;

	@WriteInFile
	public void write(Field field) throws IOException {
		try {
			output = new FileOutputStream("../Game/src/savedgame.txt");
		} catch (FileNotFoundException e) {
			System.out.println("no file");
		}
		field.stopGame();
		int lenght = field.getSnake().getLenght();
		int wayFlag = field.getSnake().wayFlag();

		output.write(lenght);
		output.write(wayFlag);
		for (SnakeElement s : field.getSnake().getArrayList()) {
			output.write(s.getX());
			output.write(s.getY());
		}
		field.startGame();
	}

	@ReadFromFile
	public void read(Field field) throws IOException {
		try {
			input = new FileInputStream("../Game/src/savedgame.txt");
		} catch (FileNotFoundException e) {
			System.out.println("no file");
		}
		field.stopGame();
		field.deleteSnake();
		field.getSnake().getArrayList().clear();
		int lenght = input.read();
		int wayFlag = input.read();
		field.getSnake().getArrayList().add(new SnakeElement(input.read(), input.read()));
		for (int i = 0; i < lenght; i++) {
			field.getSnake().getArrayList().add(new SnakeElement(input.read(), input.read()));
		}
		field.getSnake().way(wayFlag);
		field.startGame();
	}
}
