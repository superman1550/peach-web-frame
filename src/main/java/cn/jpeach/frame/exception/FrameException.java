package cn.jpeach.frame.exception;

public class FrameException extends RuntimeException {
	private static final long serialVersionUID = 3466579236301133981L;

	public FrameException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrameException(String message) {
		super(message);
	}
}
