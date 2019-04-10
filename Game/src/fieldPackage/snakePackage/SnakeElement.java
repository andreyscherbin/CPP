package fieldPackage.snakePackage;

/** Класс описывающий один элемент змейки, со свойствами <b>x</b> и <b>y</b> */
public class SnakeElement {

	/** Поле координата x */
	private int x;

	/** Поле координата y */
	private int y;

	/**
	 * Конструктор создания нового элемента змейки
	 * 
	 * @param x - координата x
	 * @param y - координата y
	 * @see SnakeElement#SnakeElement(SnakeElement)
	 */
	public SnakeElement(int x, int y) {

		this.x = x;
		this.y = y;
	}

	/**
	 * Конструктор создания нового элемента змейки используя элемент
	 * 
	 * @param next - элемент змейки
	 * @see SnakeElement#SnakeElement(int, int)
	 */
	public SnakeElement(SnakeElement next) {

		this.x = next.getX();
		this.y = next.getY();
	}

	/**
	 * Функция получения значения поля {@link SnakeElement#x}
	 * 
	 * @return возвращает координату x
	 */
	public int getX() {

		return x;
	}

	/**
	 * Функция получения значения поля {@link SnakeElement#y}
	 * 
	 * @return возвращает координату y
	 */
	public int getY() {

		return y;
	}

	/**
	 * Функция установления новой координаты {@link SnakeElement#x}
	 * 
	 * @param x - координата x
	 */
	public void setX(int x) {

		this.x = x;
	}

	/**
	 * Функция установления новой координаты {@link SnakeElement#y}
	 * 
	 * @param y - координата y
	 */
	public void setY(int y) {

		this.y = y;
	}

	/** Функция инкремента координаты {@link SnakeElement#x} */
	public void incX() {

		this.x++;
	}

	/** Функция инкремента координаты {@link SnakeElement#y} */
	public void incY() {

		this.y++;
	}

	/** Функция декремента координаты {@link SnakeElement#x} */
	public void decX() {

		this.x--;
	}

	/** Функция декремента координаты {@link SnakeElement#y} */
	public void decY() {

		this.y--;
	}

}
