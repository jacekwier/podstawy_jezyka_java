import org.apache.log4j.Logger;

public class Main {
	private static Logger logger = Logger.getLogger(Main.class.getName());
	
	public static void main(String[] args) 
	{
		StringBuffer string1=new StringBuffer();
		
		string1.append("Napis");	//append
		logger.info(string1.toString());	//toString
		string1.appendCodePoint(36);	//appendCodePoint (znak ascii)
		logger.info(string1);
		logger.info(string1.capacity());	//pojemnosc
		logger.info(string1.charAt(2));	//zwraca znak o indeksie 2
		logger.info(string1.codePointAt(2)); //zwraca kod znaku o podanym indeksie
		
		logger.info("");
		logger.info(string1.codePointBefore(2)); //zwraca kod znaku ktory jest przed podanym indeksem
		logger.info(string1.codePointCount(1, 4)); //Unicode points
		string1.delete(3, 5); //usuwa znaki z podanego przedzialu 
		logger.info(string1);
		string1.deleteCharAt(3); //usuwa znak o podanym indeksie
		logger.info(string1);
		string1.ensureCapacity(17); //rozszerzenie pojemnosci,jesli male to 2*poj+2
		logger.info(string1.capacity());
		
		logger.info("");
		char[] dst=new char[2];
		string1.getChars(1, 3,dst, 0); //kopiuje przedzial do tablicy dst zaczynajac od podanego indeksu
		string1.append("ap");
		logger.info(string1.indexOf("ap"));	//indeks pierwszego znaku pierwszego wystapienia wzorca w tekscie
		string1.insert(2, "xxxap");	//wstawia w podany index podany string reszte przesuwajac
		logger.info(string1);
		logger.info(string1.lastIndexOf("ap"));	//zwraca indeks ostatniego wzorca w tekscie
		logger.info(string1.length());
		logger.info(string1.offsetByCodePoints(1, 3));
		
		logger.info("");
		string1.replace(0, 2, "a4tech");
		logger.info(string1);
		string1.reverse();
		logger.info(string1);
		string1.setCharAt(0, 'J');
		logger.info(string1);
		string1.setLength(12);	//ustawia nowa dlugosc, obcina koncowke
		logger.info(string1);
		
		logger.info("");
		logger.info(string1.subSequence(0, 3));
		logger.info(string1.substring(3));
		logger.info(string1.capacity());
		string1.trimToSize();
		logger.info(string1.capacity());
	}

}
