package com.bsuir.vmsis1.snake;

import java.util.Random;
import com.bsuir.vmsis1.Cnst;

/** Класс описания фрукта */
public class Fruit {

	/** Поле координата фрукта */
	private int x = 0;

	/** Флаг наличия на поле фрукта */
	private boolean flagFruit = false;

	public Fruit() {
	}

	/**
	 * Функция получения поля {@link Fruit#x}
	 * 
	 * @return возвращает координату
	 */
	public int getX() {

		return x;
	}

	/**
	 * Функция получения поля {@link Fruit#flagFruit}
	 * 
	 * @return возвращает флаг наличия на поле фрукта
	 */
	public boolean getFlag() {

		return flagFruit;
	}

	/** Функция установления поля {@link Fruit#x} */
	public void setX() {

		Random generator = new Random();
		x = generator.nextInt(Cnst.MAXSIZE);
	}

	/** Функция установления поля {@link Fruit#flagFruit} */
	public void setFlag() {

		flagFruit = true;
	}

	/** Функция сброса поля {@link Fruit#flagFruit} */
	public void resetFlag() {

		flagFruit = false;
	}
}