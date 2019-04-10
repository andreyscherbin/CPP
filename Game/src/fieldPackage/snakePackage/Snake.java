package fieldPackage.snakePackage;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Vector;

/**
 * Класс змейки со свойствами <b>snake</b>, <b>head</b>, <b>tail</b>,
 * <b>flagWay</b>, <b>lenght</b>
 */
public class Snake {

	/** Поле контейнер с элементами змейки */
	private ArrayList<SnakeElement> snake;

	/** Поле голова змейки */
	private SnakeElement head;

	/** Поле хвост змейки */
	private SnakeElement tail;

	/** Поле направление змейки */
	private int flagWay = 4;

	/** Поле длина змейки */
	private int lenght = 1;

	public Snake() {

		snake = new ArrayList<SnakeElement>();
		head = new SnakeElement(0, 0);
		tail = new SnakeElement(0, 0);
		snake.add(head);
	}

	/**
	 * Функция получения координаты x головы змейки {@link Snake#head}
	 * 
	 * @return возвращает координату x головы змейки
	 */
	public int getXHead() {

		return head.getX();
	}

	/**
	 * Функция получения координаты y головы змейки {@link Snake#head}
	 * 
	 * @return возвращает координату y головы змейки
	 */
	public int getYHead() {

		return head.getY();
	}

	/**
	 * Функция получения координаты x хвоста змейки {@link Snake#tail}
	 * 
	 * @return возвращает координату x хвоста змейки
	 */
	public int getXTail() {

		return tail.getX();
	}

	/**
	 * Функция получения координаты y хвоста змейки {@link Snake#tail}
	 * 
	 * @return возвращает координату y хвоста змейки
	 */
	public int getYTail() {

		return tail.getY();
	}

	/**
	 * Функция получения длины змейки {@link Snake#lenght}
	 * 
	 * @return возвращает длину змейки
	 */
	public int getLenght() {

		return lenght;
	}

	/**
	 * Функция получения направления движения змейки {@link Snake#flagWay}
	 * 
	 * @return возвращает направление движения змейки
	 */
	public int wayFlag() {

		return flagWay;
	}

	/**
	 * Функция создания и добавления элемента в контейнер {@link Snake#snake}
	 * 
	 * @param x - координата x нового элемента
	 * @param y - координата y нового элемента
	 */
	private void addSnakeElement(int x, int y) {

		SnakeElement element = new SnakeElement(x, y);
		snake.add(element);
	}

	/**
	 * Функция получения итератора контейнера {@link Snake#snake}
	 * 
	 * @return возвращает итератор контейнера
	 */
	public Iterator<SnakeElement> getIterator() {

		return snake.iterator();
	}

	/**
	 * Функция установления нового направления движения змейки {@link Snake#flagWay}
	 * 	
	 * @param flag - новое направление движения (добавить информацию WARNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
	 */
	public void way(int flag) {

		if (flag == 1 && flagWay != 2)
			flagWay = flag;
		if (flag == 2 && flagWay != 1)
			flagWay = flag;
		if (flag == 3 && flagWay != 4)
			flagWay = flag;
		if (flag == 4 && flagWay != 3)
			flagWay = flag;
	}

	/** Функция проверки выхода за пределы поля */
	public void circleWorld() {

		if (head.getY() > 49) {
			head.setY(0);
		}
		if (head.getY() < 0) {
			head.setY(49);
		}
		if (head.getX() < 0) {
			head.setX(49);
		}
		if (head.getX() > 49) {
			head.setX(0);
		}
	}

	/**
	 * Функция проверки на пути движения фрукта
	 * 
	 * @param fruit - координата x и y фрукта
	 * @return результат проверки
	 */
	public boolean eat(int fruit) {

		if (head.getX() == fruit && head.getY() == fruit) {
			addSnakeElement(tail.getX(), tail.getY());
			lenght++;
			return true;
		} else
			return false;
	}

	/**
	 * Функция движения змейки на 1 шаг
	 * 
	 * @param flag - текущее направление движения змейки
	 */
	public void moveSnake(int flag) {

		int x = 0;
		int y = 0;
		int i = 0;
		for (SnakeElement snakeElement : snake) {
			if (i == 0) {
				x = head.getX();
				y = head.getY();
				if (flag == 4) {
					snakeElement.incX();
				}
				if (flag == 3)
					snakeElement.decX();
				if (flag == 2)
					snakeElement.incY();
				if (flag == 1) {
					snakeElement.decY();
				}
			} else {
				SnakeElement element = snakeElement;

				int indexX = x, indexY = y;
				x = element.getX();
				y = element.getY();
				element.setX(indexX);
				element.setY(indexY);
				if (i == lenght - 1) {
					tail.setX(x);
					tail.setY(y);
				}
			}

			i++;

		}
	}

	/**
	 * Функция получения координат головы при следущем шаге
	 * 
	 * @param flag - текущее направление
	 * @return возвращает элемент с координатами головы змейки
	 */
	public SnakeElement checkHead(int flag) {

		SnakeElement headCheck = new SnakeElement(head.getX(), head.getY());
		switch (flag) {
		case 4:
			headCheck.incX();
			break;
		case 3:
			headCheck.decX();
			break;
		case 2:
			headCheck.incY();
			break;
		case 1:
			headCheck.decY();
			break;
		}

		if (headCheck.getY() > 49) {
			headCheck.setY(0);
		}
		if (headCheck.getY() < 0) {
			headCheck.setY(49);
		}
		if (headCheck.getX() < 0) {
			headCheck.setX(49);
		}
		if (headCheck.getX() > 49) {
			headCheck.setX(0);
		}
		return headCheck;
	}
}
