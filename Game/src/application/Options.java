package application;

/** ����� �������� ����� ���� */
public class Options {

	/** ���� ��������������� ������ */
	private boolean automodeFlag = false;

	/** ���� �������� �������� */
	private int time = 25;
	
	public Options() {
	}

	/**
	 * ������� ��������� ���� {@link Options#automodeFlag}
	 * 
	 * @param automodeFlag - ��������� ������
	 */
	private void setAutomodeFlag(boolean automodeFlag) {
		
		this.automodeFlag = automodeFlag;
	}
  
	/**
	 * ������� ��������� ���� {@link Options#time}
	 * 
	 * @param time
	 */
	private void setTime(int time) {
		
		this.time = time;
	}

	/**
	 * ������� ��������� ���� {@link Options#automodeFlag}
	 * 
	 * @return ���������� ����� ������
	 */
	public boolean getAutomodeFlag() {
		
		return automodeFlag;
	}

	/**
	 * ������� ��������� ���� {@link Options#time}
	 * 
	 * @return ���������� �������� ��������
	 */
	public int getTime() {
		
		return time;
	}

	/**
	 * ������� ��������� ��������� �����
	 * 
	 * @param nameOperation - �������� �����
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
