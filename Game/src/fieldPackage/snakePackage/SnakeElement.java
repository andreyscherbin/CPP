package fieldPackage.snakePackage;

/** ����� ����������� ���� ������� ������, �� ���������� <b>x</b> � <b>y</b> */
public class SnakeElement {

	/** ���� ���������� x */
	private int x;

	/** ���� ���������� y */
	private int y;

	/**
	 * ����������� �������� ������ �������� ������
	 * 
	 * @param x - ���������� x
	 * @param y - ���������� y
	 * @see SnakeElement#SnakeElement(SnakeElement)
	 */
	public SnakeElement(int x, int y) {

		this.x = x;
		this.y = y;
	}
 
	/**
	 * ����������� �������� ������ �������� ������ ��������� �������
	 * 
	 * @param next - ������� ������
	 * @see SnakeElement#SnakeElement(int, int)
	 */
	public SnakeElement(SnakeElement next) {

		this.x = next.getX();
		this.y = next.getY();
	}

	/**
	 * ������� ��������� �������� ���� {@link SnakeElement#x}
	 * 
	 * @return ���������� ���������� x
	 */
	public int getX() {

		return x;
	}

	/**
	 * ������� ��������� �������� ���� {@link SnakeElement#y}
	 * 
	 * @return ���������� ���������� y
	 */
	public int getY() {

		return y;
	}

	/**
	 * ������� ������������ ����� ���������� {@link SnakeElement#x}
	 * 
	 * @param x - ���������� x
	 */
	public void setX(int x) {

		this.x = x;
	}

	/**
	 * ������� ������������ ����� ���������� {@link SnakeElement#y}
	 * 
	 * @param y - ���������� y
	 */
	public void setY(int y) {

		this.y = y;
	}

	/** ������� ���������� ���������� {@link SnakeElement#x} */
	public void incX() {

		this.x++;
	}

	/** ������� ���������� ���������� {@link SnakeElement#y} */
	public void incY() {

		this.y++;
	}

	/** ������� ���������� ���������� {@link SnakeElement#x} */
	public void decX() {

		this.x--;
	}

	/** ������� ���������� ���������� {@link SnakeElement#y} */
	public void decY() {

		this.y--;
	}

}
