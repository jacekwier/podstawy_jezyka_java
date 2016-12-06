package Exceptions;

public class NoEnoughMoney extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7724648078213512020L;
	
	public String getMessage(){
		return "You do not have enough money";
	}

}
