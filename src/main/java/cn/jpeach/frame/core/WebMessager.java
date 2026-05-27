package cn.jpeach.frame.core;

public final class WebMessager extends FrameObject {
	private static final int ALERT = 1;
	private static final int CONFIRM = 2;
	private String formId;

	WebMessager(String formId) {
		this.formId = formId;
	}

	public void alert(String msg) {
		this.show(ALERT, "提示", msg, "info", -1, -1);
	}

	public void alert(String msg, int flag) {
		this.show(ALERT, "提示", msg, "info", flag, -1);
	}

	public void alert(String title, String msg, String icon, int flag) {
		this.show(ALERT, title, msg, icon, flag, -1);
	}

	public void confirm(String msg, int confirmFlag) {
		this.show(CONFIRM, "询问", msg, "", confirmFlag, -1);
	}

	public void confirm(String msg, int confirmFlag, int cancelFlag) {
		this.show(CONFIRM, "询问", msg, "", confirmFlag, cancelFlag);
	}

	public void confirm(String title, String msg, int confirmFlag, int cancelFlag) {
		this.show(CONFIRM, title, msg, "", confirmFlag, cancelFlag);
	}

	private void show(int type, String title, String msg, String icon, int confirmFlag, int cancelFlag) {
		this.addChange(this.formId, null, Constant.ACTION_MESSAGE, new Object[] { type, title, msg, icon, confirmFlag, cancelFlag });
	}
}
