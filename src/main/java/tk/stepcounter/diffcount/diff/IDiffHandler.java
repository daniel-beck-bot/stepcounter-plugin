package tk.stepcounter.diffcount.diff;

/**
 * Diff�N���X����ʒm���󂯎�邽�߂̃n���h���̃C���^�[�t�F�[�X�ł��B
 *
 * @author Naoki Takezoe
 */
public interface IDiffHandler {

	/** �s����v�����ꍇ�ɌĂяo����܂��B */
	public void match(String text);

	/** �s���폜����Ă����ꍇ�ɌĂяo����܂��B */
	public void delete(String text);

	/** �s���ǉ�����Ă����ꍇ�ɌĂяo����܂��B */
	public void add(String text);

}
