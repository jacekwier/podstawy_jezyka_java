package Exceptions;

public class ClientExists extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8495971050537564291L;
	
	public String getMessage(){
		return "Client with this PESEL already exists.";
	}
}
