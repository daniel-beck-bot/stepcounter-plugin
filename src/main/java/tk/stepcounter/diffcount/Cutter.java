package tk.stepcounter.diffcount;

/**
 * �\�[�X�R�[�h����R�����g���s�ȂǁA
 * �X�e�b�v���̃J�E���g���ɕs�v�ȕ�������菜�����߂̃J�b�^�[�̃C���^�[�t�F�[�X�ł��B
 *
 * @author Naoki Takezoe
 */
public interface Cutter {

	/**
	 * �\�[�X�R�[�h����s�v�ȕ�������菜���܂��B
	 *
	 * @param source �\�[�X
	 * @return �s�v�ȕ�������菜����������
	 */
	public DiffSource cut(String source);

	/** �t�@�C���^�C�v���擾���܂� */
	public String getFileType();

}
