package Interfaces;
import java.awt.Color;
/*
Code created by Josh Braza 
*/
public interface UNOConstants_Original {
	public static Color BLACK = new Color(0,0,0);
	
	//Types
	public static int NUMBERS = 1;
	public static int ACTION = 2;
	public static int WILD = 3;
	
	//ActionCard Characters
	Character charREVERSE = (char) 8634;							//Decimal
	Character charSKIP    = (char) Integer.parseInt("2718",16); 	//Unicode
	
	//ActionCard Functions
	String REVERSE = charREVERSE.toString();
	String SKIP	= charSKIP.toString();
	String DRAW2PLUS = "2+";
	
	//Wild card functions
	String W_COLORPICKER = "W";
	String W_DRAW4PLUS = "4+";	
}
