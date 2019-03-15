package fieldPackage.snakePackage;

import java.util.Random;

/** ����� �������� ������ */
public class Fruit {

	/** ���� ���������� ������ */
	private int x = 0;

	/** ���� ������� �� ���� ������ */
	private boolean flagFruit = false;

	public Fruit() {
	}

	/**
	 * ������� ��������� ���� {@link Fruit#x}
	 * 
	 * @return ���������� ����������
	 */
	public int getX() {

		return x;
	}

	/**
	 * ������� ��������� ���� {@link Fruit#flagFruit}
	 * 
	 * @return ���������� ���� ������� �� ���� ������
	 */
	public boolean getFlag() {

		return flagFruit;
	}

	/** ������� ������������ ���� {@link Fruit#x} */
	public void setX() {

		Random generator = new Random();
		x = generator.nextInt(49);
	}

	/** ������� ������������ ���� {@link Fruit#flagFruit} */
	public void setFlag() {

		flagFruit = true;
	}

	/** ������� ������ ���� {@link Fruit#flagFruit} */
	public void resetFlag() {

		flagFruit = false;
	}
}
