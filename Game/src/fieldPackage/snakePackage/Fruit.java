package fieldPackage.snakePackage;

import java.util.Random;

<<<<<<< HEAD
/** ????? ???????? ?????? */
public class Fruit {

	/** ???? ?????????? ?????? */
	private int x = 0;

	/** ???? ??????? ?? ???? ?????? */
=======
/** ����� �������� ������ */
public class Fruit {

	/** ���� ���������� ������ */
	private int x = 0;

	/** ���� ������� �� ���� ������ */
>>>>>>> parent of 7386c66... change to utf8
	private boolean flagFruit = false;

	public Fruit() {
	}

	/**
<<<<<<< HEAD
	 * ??????? ????????? ???? {@link Fruit#x}
	 * 
	 * @return ?????????? ??????????
=======
	 * ������� ��������� ���� {@link Fruit#x}
	 * 
	 * @return ���������� ����������
>>>>>>> parent of 7386c66... change to utf8
	 */
	public int getX() {

		return x;
	}

	/**
<<<<<<< HEAD
	 * ??????? ????????? ???? {@link Fruit#flagFruit}
	 * 
	 * @return ?????????? ???? ??????? ?? ???? ??????
=======
	 * ������� ��������� ���� {@link Fruit#flagFruit}
	 * 
	 * @return ���������� ���� ������� �� ���� ������
>>>>>>> parent of 7386c66... change to utf8
	 */
	public boolean getFlag() {

		return flagFruit;
	}

<<<<<<< HEAD
	/** ??????? ???????????? ???? {@link Fruit#x} */
=======
	/** ������� ������������ ���� {@link Fruit#x} */
>>>>>>> parent of 7386c66... change to utf8
	public void setX() {

		Random generator = new Random();
		x = generator.nextInt(49);
	}

<<<<<<< HEAD
	/** ??????? ???????????? ???? {@link Fruit#flagFruit} */
=======
	/** ������� ������������ ���� {@link Fruit#flagFruit} */
>>>>>>> parent of 7386c66... change to utf8
	public void setFlag() {

		flagFruit = true;
	}

<<<<<<< HEAD
	/** ??????? ?????? ???? {@link Fruit#flagFruit} */
=======
	/** ������� ������ ���� {@link Fruit#flagFruit} */
>>>>>>> parent of 7386c66... change to utf8
	public void resetFlag() {

		flagFruit = false;
	}
}
