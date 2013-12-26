package it.vviser.common;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CommonMethod {
	
	/**
	 * Crea una data GregorianCalendar da una stringa in formato "YYYY-MM-DD"
	 * @param data stringa che contiene la data in formato "YYYY-MM-DD"
	 * @return un GregorianCalendar contenente la data inserita in input
	 */
	public static GregorianCalendar stringToDate(String data){
		GregorianCalendar toReturn;
		String[] tokens = new String[3];
		int[] g=new int[3];
			
		tokens = data.split("-");
			
		g[0]=Integer.parseInt(tokens[0]); 
		g[1]=Integer.parseInt(tokens[1])-1; 
		g[2]=Integer.parseInt(tokens[2]);
			
		toReturn = new GregorianCalendar(g[0],g[1],g[2]);
		return toReturn;
	}
	
	/**
	 * Converte una data in formato stringa
	 * @param data Data da convertire
	 * @return Data in formato stringa
	 */
	public static String dateToString(GregorianCalendar data){
		return data.get(Calendar.YEAR)+"-"+(data.get(Calendar.MONTH)+1)+"-"+data.get(Calendar.DAY_OF_MONTH);
	}
}
