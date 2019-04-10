package fieldPackage.snakePackage;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.Vector;

/**
 * ����� ������ �� ���������� <b>snake</b>, <b>head</b>, <b>tail</b>,
 * <b>flagWay</b>, <b>lenght</b>
 */
public class Snake {

	/** ���� ��������� � ���������� ������ */
	private ArrayList<SnakeElement> snake;

	/** ���� ������ ������ */
	private SnakeElement head;

	/** ���� ����� ������ */
	private SnakeElement tail;

	/** ���� ����������� ������ */
	private int flagWay = 4;

	/** ���� ����� ������ */
	private int lenght = 1;

	public Snake() {

		snake = new ArrayList<SnakeElement>();
		head = new SnakeElement(0, 0);
		tail = new SnakeElement(0, 0);
		snake.add(head);
	}
 
	/**
	 * ������� ��������� ���������� x ������ ������ {@link Snake#head}
	 * 
	 * @return ���������� ���������� x ������ ������
	 */
	public int getXHead() {

		return head.getX();
	}

	/**
	 * ������� ��������� ���������� y ������ ������ {@link Snake#head}
	 * 
	 * @return ���������� ���������� y ������ ������
	 */
	public int getYHead() {

		return head.getY();
	}

	/**
	 * ������� ��������� ���������� x ������ ������ {@link Snake#tail}
	 * 
	 * @return ���������� ���������� x ������ ������
	 */
	public int getXTail() {

		return tail.getX();
	}

	/**
	 * ������� ��������� ���������� y ������ ������ {@link Snake#tail}
	 * 
	 * @return ���������� ���������� y ������ ������
	 */
	public int getYTail() {

		return tail.getY();
	}

	/**
	 * ������� ��������� ����� ������ {@link Snake#lenght}
	 * 
	 * @return ���������� ����� ������
	 */
	public int getLenght() {

		return lenght;
	}

	/**
	 * ������� ��������� ����������� �������� ������ {@link Snake#flagWay}
	 * 
	 * @return ���������� ����������� �������� ������
	 */
	public int wayFlag() {

		return flagWay;
	}

	/**
	 * ������� �������� � ���������� �������� � ��������� {@link Snake#snake}
	 * 
	 * @param x - ���������� x ������ ��������
	 * @param y - ���������� y ������ ��������
	 */
	private void addSnakeElement(int x, int y) {

		SnakeElement element = new SnakeElement(x, y);
		snake.add(element);
	}

	/**
	 * ������� ��������� ��������� ���������� {@link Snake#snake}
	 * 
	 * @return ���������� �������� ����������
	 */
	public Iterator<SnakeElement> getIterator() {

		return snake.iterator();
	}

	/**
	 * ������� ������������ ������ ����������� �������� ������ {@link Snake#flagWay}
	 * 	
	 * @param flag - ����� ����������� �������� (�������� ���������� WARNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!)
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

	/** ������� �������� ������ �� ������� ���� */
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
	 * ������� �������� �� ���� �������� ������
	 * 
	 * @param fruit - ���������� x � y ������
	 * @return ��������� ��������
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
	 * ������� �������� ������ �� 1 ���
	 * 
	 * @param flag - ������� ����������� �������� ������
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
	 * ������� ��������� ��������� ������ ��� �������� ����
	 * 
	 * @param flag - ������� �����������
	 * @return ���������� ������� � ������������ ������ ������
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
