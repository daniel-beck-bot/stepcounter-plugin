package tk.stepcounter;

import java.io.*;

/** �X�e�b�v�J�E���^�̃C���^�[�t�F�[�X */
public interface StepCounter {

	/** �J�E���g���܂� */
	public CountResult count(File file, String encoding) throws IOException;

	/** �t�@�C���^�C�v���擾���܂� */
	public String getFileType();

}