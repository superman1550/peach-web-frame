package cn.jpeach.frame.exception;

public class ReflectorException extends FrameException {
	private static final long serialVersionUID = 1522560285183448768L;

	public ReflectorException(String message) {
		super(message);
	}

	public ReflectorException(String message, Throwable cause) {
		super(message, cause);
	}
}
