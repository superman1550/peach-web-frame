package cn.jpeach.frame.dao;

 class DaoTransactionException extends RuntimeException {
	private static final long serialVersionUID = 324066182027139409L;

	public DaoTransactionException(String message) {
		super(message);
	}
}
