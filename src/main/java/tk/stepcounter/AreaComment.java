package tk.stepcounter;

/**
 * �����s�R�����g
 */
public class AreaComment {
	
	private String start;
	private String end;
	
	/**
	 * �����Ȃ��̃R���X�g���N�^
	 */
	public AreaComment(){ }
	
	/**
	 * �J�n������ƏI����������w�肵��AreaComment�𐶐����܂��B
	 *
	 * @param start �J�n������
	 * @param end   �I��������
	 */
	public AreaComment(String start,String end){
		setStartString(start);
		setEndString(end);
	}
	
	/**
	 * �R�����g�̊J�n�������ݒ肵�܂�
	 *
	 * @param start �J�n������
	 */
	public void setStartString(String start){
		this.start = start;
	}

	/**
	 * �R�����g�̏I���������ݒ肵�܂�
	 *
	 * @param end �I��������
	 */
	public void setEndString(String end){
		this.end = end;
	}
	
	/**
	 * �R�����g�̊J�n��������擾���܂�
	 *
	 * @return �J�n������
	 */
	public String getStartString(){
		return this.start;
	}

	/**
	 * �R�����g�̏I����������擾���܂�
	 *
	 * @return �I��������
	 */
	public String getEndString(){
		return this.end;
	}
}