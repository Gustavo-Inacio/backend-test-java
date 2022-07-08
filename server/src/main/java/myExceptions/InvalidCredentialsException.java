package myExceptions;

public class InvalidCredentialsException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidCredentialsException(String msg) {
		super(msg);
	}
}
