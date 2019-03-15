package application;

/** Класс описания опций игры */
public class Options {

	/** Поле автоматического режима */
	private boolean automodeFlag = false;

	/** Поле скорости движения */
	private int time = 25;
	
	public Options() {
	}

	/**
	 * Функция установки поля {@link Options#automodeFlag}
	 * 
	 * @param automodeFlag - установка режима
	 */
	private void setAutomodeFlag(boolean automodeFlag) {
		
		this.automodeFlag = automodeFlag;
	}

	/**
	 * Функция установки поля {@link Options#time}
	 * 
	 * @param time
	 */
	private void setTime(int time) {
		
		this.time = time;
	}

	/**
	 * Функция получения поля {@link Options#automodeFlag}
	 * 
	 * @return возвращает режим работы
	 */
	public boolean getAutomodeFlag() {
		
		return automodeFlag;
	}

	/**
	 * Функция получения поля {@link Options#time}
	 * 
	 * @return возвращает скорость движения
	 */
	public int getTime() {
		
		return time;
	}

	/**
	 * Функция установки выбранных опций
	 * 
	 * @param nameOperation - название опции
	 */
	public void setOptions(String nameOperation) {

		switch (nameOperation) {
		case "automodeOn":
			setAutomodeFlag(true);
			break;
		case "automodeOff":
			setAutomodeFlag(false);
			break;
		case "easy":
			setTime(100);
			break;
		case "medium":
			setTime(50);
			break;
		case "hard":
			setTime(20);
			break;
		default:
			break;
		}
	}
}
