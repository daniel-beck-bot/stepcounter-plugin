package tk.stepcounter.diffcount;

import java.util.Comparator;

import tk.stepcounter.diffcount.object.AbstractDiffResult;
import tk.stepcounter.diffcount.object.DiffFolderResult;

public class FileComparator implements Comparator<AbstractDiffResult> {

	public int compare(AbstractDiffResult o1, AbstractDiffResult o2) {
		// �����Ƃ��f�B���N�g���̏ꍇ
		if (o1 instanceof DiffFolderResult && o2 instanceof DiffFolderResult) {
			return o1.getName().compareTo(o2.getName());
		}
		// �Е��̂݃f�B���N�g���ꍇ
		if (o1 instanceof DiffFolderResult) {
			return -1;
		} else if (o2 instanceof DiffFolderResult) {
			return 1;
		}
		// �����Ƃ��t�@�C���̏ꍇ
		return o1.getName().compareTo(o2.getName());
	}

}
