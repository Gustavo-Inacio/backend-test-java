package myExceptions;

public class DatabaseAccessException extends Exception{
	private static final long serialVersionUID = 1L;

	public DatabaseAccessException(String msg) {
		super(msg);
	}
}
