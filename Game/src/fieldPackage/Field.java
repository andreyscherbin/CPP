package fieldPackage;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import application.Options;
import fieldPackage.snakePackage.Fruit;
import fieldPackage.snakePackage.Snake;
import fieldPackage.snakePackage.SnakeElement;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** Класс поле игры */
public class Field {

	/** поле Змейка */
	private Snake snake;

	/** поле Фрукт */
	private Fruit fruit;

	/** поле Поток */
	private SnakeLife snakelife;

	/** поле массив фигур */
	private Rectangle[][] fieldRectangle;

	/** поле массив данных */
	private int[][] fieldInt;
 
	/** поле ссылка на объект опций */
	private Options options;
	
	public Field(BorderPane root, Options options) {
		
		this.options = options;
		fruit = new Fruit();
		drawField(root);
		addSnake();

		if (options.getAutomodeFlag() == false) {
			root.getScene().setOnKeyPressed(e -> writeKeyCode(e.getCode()));			
		}
		if (options.getAutomodeFlag() == true) {
			root.getScene().setOnKeyPressed(null);
		}
		snakelife = new SnakeLife();
		snakelife.start();
	}

	/**
	 * Функция изменения направления движения взависимости от нажатой игроком
	 * кнопкой
	 * 
	 * @param key - нажатая кнопка
	 */
	private void writeKeyCode(KeyCode key) {
		
		if (key == KeyCode.UP) {
			snake.way(1);
		} else if (key == KeyCode.DOWN) {
			snake.way(2);
		} else if (key == KeyCode.LEFT) {
			snake.way(3);
		} else if (key == KeyCode.RIGHT) {
			snake.way(4);
		}
	}

	/**
	 * Функция рисования поля игры
	 * 
	 * @param root - корень в который добавляются компоненты Rectangle
	 */
	private void drawField(BorderPane root) {

		fieldRectangle = new Rectangle[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {

				fieldRectangle[i][j] = new Rectangle(50 + (j * 10), 50 + (i * 10), 10, 10);
				fieldRectangle[i][j].setFill(Color.GREEN);
				root.getChildren().add(fieldRectangle[i][j]);
			}
		}
		fieldInt = new int[50][50];
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				fieldInt[i][j] = 0;
			}
		}
	}

	/** Функция рисования змейки на поле */
	private void drawSnake() {
		
		int lenght = snake.getLenght();
		Iterator<SnakeElement> it = snake.getIterator();
		for (int i = 0; i < lenght; i++) {
			SnakeElement element = it.next();
			int x = element.getX();
			int y = element.getY();
			fieldRectangle[y][x].setFill(Color.RED);
			fieldInt[y][x] = 1;
		}
	}

	/** Функция удаления змейки с поля */
	private void deleteSnake() {
		
		int lenght = snake.getLenght();
		Iterator<SnakeElement> it = snake.getIterator();
		for (int i = 0; i < lenght; i++) {
			SnakeElement element = it.next();
			int x = element.getX();
			int y = element.getY();
			fieldRectangle[y][x].setFill(Color.GREEN);
			fieldInt[y][x] = 0;
		}
	}

	/**
	 * Функция рисования фрукта на поле
	 * 
	 * @param x - координата фрукта
	 */
	private void drawFruit(int x) {
		
		fieldRectangle[x][x].setFill(Color.YELLOW);
		fieldInt[x][x] = 2;
	}

	/** Функция установки начального положения змейки на поле */
	private void addSnake() {
		
		int x = 0, y = 0;
		snake = new Snake();
		x = snake.getXHead();
		y = snake.getYHead();
		fieldInt[y][x] = 1;
		fieldRectangle[y][x].setFill(Color.RED);
	}

	/** Функция окончания игры */
	public void stopGame() {
		
		snakelife.interrupt();
	}

	/**
	 * Внутренний класс-поток в котором реализуется движение змейки на поле игры
	 * 
	 * @author student
	 */
	private class SnakeLife extends Thread {
		
		SnakeLife() {
		}

		/**
		 * Функция поиска на поле фрукта
		 * 
		 * @return возвращает направление движения
		 */
		private int machine() {
			
			int x = 0, y = 0, fruitCordinate = 0;
			fruitCordinate = fruit.getX();
			x = snake.getXHead();
			y = snake.getYHead();
			if (y == fruitCordinate) {
				if (x < fruitCordinate)
					return 4;

				if (x > fruitCordinate)
					return 3;
			}
			if (x == fruitCordinate) {
				if (y > fruitCordinate)
					return 1;
				if (y < fruitCordinate)
					return 2;
			}
			return 0;
		}

		/** Основной алгоритм реализации жизни змейки на поле игры */
		public void run() {
			do {
				if (!Thread.interrupted()) {

					if (!fruit.getFlag()) {
						fruit.setX();
						int x = fruit.getX();
						while (fieldInt[x][x] == 1) {
							fruit.setX();
							x = fruit.getX();
						}
						x = fruit.getX();
						drawFruit(x);
						fruit.setFlag();
					}
					int flag = 0;
					flag = snake.wayFlag();
					deleteSnake();
					if (options.getAutomodeFlag() == true) {
						flag = machine();
						if (flag == 0)
							flag = snake.wayFlag();
						snake.way(flag);
					}
					snake.moveSnake(flag);
					snake.circleWorld();
					if (snake.eat(fruit.getX())) {
						fruit.resetFlag();
					}
					drawSnake();
					SnakeElement headCheck = snake.checkHead(flag);
					int x = headCheck.getX(), y = headCheck.getY();
					if (fieldInt[y][x] == 1) {
						return;
					}
					try {
						Thread.sleep(options.getTime());
					} catch (InterruptedException e) {
						return;
					}
				} else {
					return;
				}
			} while (true);
		}
	}

}
