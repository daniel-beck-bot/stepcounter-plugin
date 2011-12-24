package tk.stepcounter.diffcount;

import java.io.File;
import java.util.ResourceBundle;

import tk.stepcounter.StepCounter;
import tk.stepcounter.StepCounterFactory;

/**
 * {@link Cutter}�̃t�@�N�g���ł��B
 * �t�@�C���̊g���q����K�؂�{@link Cutter}�̎�����ԋp���܂��B
 *
 * @author Naoki Takezoe
 */
public class CutterFactory {

	private static final String NOT_SUPPORTED = ResourceBundle.getBundle(
			"tk.eclipse.plugin.stepcounter.StepCounterPluginResources").getString("StepCountView.notSupported");

	/**
	 * �t�@�C���̊g���q����K�؂�{@link Cutter}�̎�����ԋp���܂��B
	 * �Ή�����{@link Cutter}�̎��������݂��Ȃ��ꍇ��null��Ԃ��܂��B
	 *
	 * @param file �t�@�C��
	 * @return �t�@�C���ɑΉ�����{@link Cutter}�̎���
	 */
	public static Cutter getCutter(File file) {
		StepCounter counter = StepCounterFactory.getCounter(file.getName());
		if(counter != null && counter instanceof Cutter){
			return (Cutter) counter;
		}
		return null;
	}

	/**
	 * �t�@�C���̎�ʂ�ԋp���܂��B
	 * �T�|�[�g����Ă��Ȃ��t�@�C���`���̏ꍇ�� "Unknown" ��ԋp���܂��B
	 *
	 * @param file �t�@�C��
	 * @return �t�@�C�����
	 */
	public static String getFileType(File file){
		Cutter cutter = getCutter(file);
		if(cutter == null){
			return NOT_SUPPORTED;
		}
		return cutter.getFileType();
	}

}
