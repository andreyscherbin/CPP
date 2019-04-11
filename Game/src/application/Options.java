package application;

/** Класс описания опций игры */
public class Options {

	/** Поле автоматического режима */
	private boolean automodeFlag = false;

	/** Поле скорости движения */
	private int time = 25;

	/** Поле наличия паузы в игре */
	private boolean pause = false;

	public Options() {
	}

	/** Функция проверки наличия паузы в игре */
	public boolean isPause() {
		
		if (!pause)
			return false;
		else
			return true;
	}

	/** Функция установки паузы */
	public void setPause() {
		pause = true;
	}

	/** Функция сброса паузы */
	public void resetPause() {
		pause = false;
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
