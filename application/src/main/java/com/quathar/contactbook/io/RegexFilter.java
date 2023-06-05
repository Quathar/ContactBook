package com.quathar.contactbook.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>RegexFilter</h1>
 * 
 * @since 2022-05-05
 * @version 2.0
 * @author Q
 */
public class RegexFilter {

	// <<-CONSTANTS->>
	public static final String TELEPHONE_ERROR_MSG = String.format("INCORRECT FORMAT!%nIntroduce 9 numbers");
	public static final String MAIL_ERROR_MSG = String.format("INCORRECT FORMAT!%nIntroduce: (name)@(mail).(ext)");

	// <<-METHODS->>
	/**
	 * Checks for spaces in a text string.
	 * 
	 * @param str the string to check
	 *
	 * @return str with 1 space between words
	 */
	public static String checkSpaces(String str) {
		String[] words = str.trim().split(" ");

		StringBuilder newStr = new StringBuilder();
		for (String word : words)
			if (!word.isBlank())
				newStr.append(word).append(" ");
		return newStr.toString();
	}

	/**
	 * Check that the phone is in the correct format.
	 * 
	 * @param telephone the telephone to check
	 *
	 * @return the correct formatted telephone
	 */
	public static String checkTelephone(String telephone) {
		String regex = "^\\d{9,20}$";
		Matcher matcher = Pattern.compile(regex).matcher(telephone);
		
		if (matcher.matches()) {
			telephone = telephone.trim().replaceAll(" ", "");
			if (telephone.length() > 9)
				telephone = telephone.substring(0, 9);
			String[] p = {telephone.substring(0, 3), telephone.substring(3, 6), telephone.substring(6, 9)};
			return telephone = "+34   " + p[0] + " " + p[1] + " " + p[2];
		} else throw new RuntimeException();
	}
	
	/**
	 * Check that the mail is properly formatted.
	 * 
	 * @param mail the mail to check
	 *
	 * @return the correct formatted mail
	 */
	public static String checkMail(String mail) throws RuntimeException {
		String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(mail);
		
		if (matcher.matches())
			return mail;
		else throw new RuntimeException();
	}
	
}
