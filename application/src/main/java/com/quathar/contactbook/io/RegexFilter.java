package com.quathar.contactbook.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 *
 * @author Q
 * @since 2022-05-05
 */
public class RegexFilter { // CLASE FINALIZADA
	
	/**
	 * Comprueba los espacios en una cadena de texto.
	 * 
	 * @param str the string to check
	 * @return str with 1 space between words
	 */
	public static String checkSpaces(String str) {
		str = str.trim();
		String[] p = str.split(" ");
		str = "";
		for (String parts : p) {
			if (!parts.equals(""))
				str += parts + " ";
		}
		return str;
	}

	/**
	 * Comprueba que el tel�fono tenga el formato adecuado.
	 * 
	 * @param telephone the telephone to check
	 * @return telephone/ERROR - if the format is/isn't correct
	 */
	public static String checkTelephone(String telephone) {
		String regex = "^\\d{9,20}$";
		Matcher m = Pattern.compile(regex).matcher(telephone);
		
		if (m.matches()) {
			telephone = telephone.trim().replaceAll(" ", "");
			if (telephone.length() > 9)
				telephone = telephone.substring(0, 9);
			String[] p = {telephone.substring(0, 3), telephone.substring(3, 6), telephone.substring(6, 9)};
			return telephone = "+34   " + p[0] + " " + p[1] + " " + p[2];
		} else {
			return "ERROR";
		}
	}
	
	/**
	 * Comprueba que el correo tenga el formato adecuado.
	 * 
	 * @param mail the mail to check
	 * @return mail/ERROR - if the format is/isn't correct
	 */
	public static String checkMail(String mail) {
		String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Matcher m = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(mail);
		
		if (m.matches())
			return mail;
		else
			return "ERROR";
	}
	
}
