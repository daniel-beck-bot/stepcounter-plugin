package tk.stepcounter.diffcount.object;

/**
 * �t�@�C���A�f�B���N�g���̕ύX�󋵂������񋓌^�ł��B
 *
 * @author Naoki Takezoe
 */
public enum DiffStatus {

	/**
	 * �ύX�Ȃ��������X�e�[�^�X�ł��B
	 */
	NONE {
		@Override
		public String toString() {
			return "�ύX�Ȃ�";
		}
	},

	/**
	 * �ǉ��������X�e�[�^�X�ł��B
	 */
	ADDED {
		@Override
		public String toString() {
			return "�V�K";
		}
	},

	/**
	 * �ύX�������X�e�[�^�X�ł��B
	 */
	MODIFIED {
		@Override
		public String toString() {
			return "�ύX";
		}
	},

	/**
	 * �폜�������X�e�[�^�X�ł��B
	 */
	REMOVED {
		@Override
		public String toString() {
			return "�폜";
		}
	},

	/**
	 * �T�|�[�g�ΏۊO�������X�e�[�^�X�ł��B
	 */
	UNSUPPORTED {
		@Override
		public String toString() {
			return "�T�|�[�g�ΏۊO";
		}
	}
}
