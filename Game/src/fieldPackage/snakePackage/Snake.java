package fieldPackage.snakePackage;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Vector;

import fieldPackage.Cnst;

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
	private int flagWay = Cnst.RIGHT;

	/** Поле длина змейки */
	private int lenght = Cnst.startLenght;

	public Snake() {

		snake = new ArrayList<SnakeElement>();
		head = new SnakeElement(Cnst.startX, Cnst.startY);
		tail = new SnakeElement(Cnst.startX, Cnst.startY);
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
	
	/** Функция установки длины */
	public void setLenght(int lenght) {
		
		this.lenght = lenght;
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
	 * Функция получения контейнера змейки {@link Snake#snake}
	 * 
	 * @return возвращает контейнер змейки
	 */
	public ArrayList<SnakeElement> getArrayList() {

		return snake;
	}

	/**
	 * Функция установления нового направления движения змейки {@link Snake#flagWay}
	 * 
	 * @param flagUser - направление выбранное игроком
	 */
	public boolean way(int flagUser) {

		if (flagUser == Cnst.UP && flagWay != Cnst.DOWN)
		{
			flagWay = flagUser;
			return true;
		}
		if (flagUser == Cnst.DOWN && flagWay != Cnst.UP)
		{
			flagWay = flagUser;
			return true;
		}
		if (flagUser == Cnst.LEFT && flagWay != Cnst.RIGHT)
		{
			flagWay = flagUser;
			return true;
		}
		if (flagUser == Cnst.RIGHT && flagWay != Cnst.LEFT)
		{
			flagWay = flagUser;
			return true;
		}		
		return false;
	}

	/** Функция проверки выхода за пределы поля */
	public void circleWorld() {

		if (head.getY() > Cnst.MAXSIZE - 1) {
			head.setY(0);
		}
		if (head.getY() < 0) {
			head.setY(Cnst.MAXSIZE - 1);
		}
		if (head.getX() < 0) {
			head.setX(Cnst.MAXSIZE - 1);
		}
		if (head.getX() > Cnst.MAXSIZE - 1) {
			head.setX(0);
		}
	}

	/**
	 * Функция проверки на пути движения фрукта
	 * 
	 * @param x - координата x и y фрукта
	 * @return результат проверки
	 */
	public boolean eat(int x) {

		if (head.getX() == x && head.getY() == x) {
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
				if (flag == Cnst.RIGHT) {
					snakeElement.incX();
				}
				if (flag == Cnst.LEFT)
					snakeElement.decX();
				if (flag == Cnst.DOWN)
					snakeElement.incY();
				if (flag == Cnst.UP) {
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
	 * @param flag - текущее направление движения змейки
	 * @return возвращает элемент с координатами головы змейки
	 */
	public SnakeElement getHeadNextStep(int flag) {

		SnakeElement headCheck = new SnakeElement(head.getX(), head.getY());
		switch (flag) {
		case Cnst.RIGHT:
			headCheck.incX();
			break;
		case Cnst.LEFT:
			headCheck.decX();
			break;
		case Cnst.DOWN:
			headCheck.incY();
			break;
		case Cnst.UP:
			headCheck.decY();
			break;
		}

		if (headCheck.getY() > Cnst.MAXSIZE - 1) {
			headCheck.setY(0);
		}
		if (headCheck.getY() < 0) {
			headCheck.setY(Cnst.MAXSIZE - 1);
		}
		if (headCheck.getX() < 0) {
			headCheck.setX(Cnst.MAXSIZE - 1);
		}
		if (headCheck.getX() > Cnst.MAXSIZE - 1) {
			headCheck.setX(0);
		}
		return headCheck;
	}
}