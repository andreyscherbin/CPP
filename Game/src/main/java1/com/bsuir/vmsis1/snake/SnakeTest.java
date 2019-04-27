package com.bsuir.vmsis1.snake;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bsuir.vmsis1.Cnst;

/** Тестирование класса Snake */
public class SnakeTest {

	private Snake snake;

	@Before
	public void init() {
		snake = new Snake();
	}

	@After
	public void tearDown() {
		snake = null;
	}

	/** Тест для функции движения змейки */
	@Test
	public void testmoveSnake() {

		snake.moveSnake(Cnst.RIGHT);
		assertEquals(Cnst.startX + 1, snake.getXHead());
		snake.moveSnake(Cnst.LEFT);
		assertEquals(Cnst.startX, snake.getXHead());
		snake.moveSnake(Cnst.DOWN);
		assertEquals(Cnst.startY + 1, snake.getYHead());
		snake.moveSnake(Cnst.UP);
		assertEquals(Cnst.startY, snake.getYHead());
	}

	/** Тест для функции по проверке выхода за поле змейки */
	@Test
	public void testCircleWorld() {

		ArrayList<SnakeElement> list = snake.getArrayList();
		SnakeElement head = list.get(0);
		head.setX(Cnst.MAXSIZE);
		snake.circleWorld();
		assertEquals(Cnst.startX, snake.getXHead());
		head.setY(Cnst.MAXSIZE);
		snake.circleWorld();
		assertEquals(Cnst.startY, snake.getYHead());
	}

	@Test
	/** Тест для функции проверки на пути движения змейки фрукта */
	public void testEat() {

		int lenght = snake.getLenght();
		ArrayList<SnakeElement> list = snake.getArrayList();
		SnakeElement head = list.get(0);
		head.setX(15);
		head.setY(15);
		assertTrue(snake.eat(15) == true);
		assertTrue(lenght + 1 == snake.getLenght());
	}
}
