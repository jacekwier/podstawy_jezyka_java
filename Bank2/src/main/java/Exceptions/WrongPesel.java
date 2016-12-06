package Exceptions;

public class WrongPesel extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -111331183662666147L;

	public String getMessage(){
		return "Wrong pesel";
	}
}
