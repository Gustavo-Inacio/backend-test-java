package myExceptions;

public class InvalidLoginException extends Exception{
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String msg) {
		super(msg);
	}
}
