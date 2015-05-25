package com.framework.util;

import java.math.BigDecimal;
import java.util.Random;

public class RandomString {

	private static Random rand = new Random(); 
	 private static String LowerCaseAlpha = "abcdefghijklmnopqrstuvwxyz";
	 private static String UpperCaseAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 private static String Numeric = "0123456789";
	 private static String NonZeroNumeric = "123456789";

	 /**
	  * Generate alphanumeric n characters string
	  * @param length
	  * @return String
	  */
	 public static String generateAlphaNumericString(int length) {
		 return generateString(LowerCaseAlpha + UpperCaseAlpha+Numeric, length);
	 }
	 
	 /**
	  * Generate alphanumeric 12 characters string
	  * @return String
	  */
	 public static String generateAlphaNumericString() {
		 return generateString(LowerCaseAlpha + UpperCaseAlpha + Numeric, 12);
	 }

	 /**
	  * Generate random string of known characters
	  * @param characters
	  * @param length
	  * @return String
	  */
	 public static String generateString(String characters, int length){
	     char[] text = new char[length];
	     for (int i = 0; i < length; i++){
	         text[i] = characters.charAt(rand.nextInt(characters.length()));
	     }
	     return new String(text);
	 }
	
	 /**
	  * Generate alphabetical string
	  * @param length
	  * @return String
	  */
	 public static String generateAlphaString(int length) {
		 return generateString(LowerCaseAlpha, length);
	 }
	
	 /**
	  * Generate random number string
	  * @param length
	  * @return String
	  */
	 public static String generateNumericString(int length) {
		 return generateString(NonZeroNumeric, 1) + generateString(Numeric, length-1);
	 }
	 
	 /**
	  * Generate Random Address String
	  * @return String
	  */
	 public static String generateRandomAddress () {
		 return generateNumericString(4) + " " + generateAlphaString(15) + " st.";
	 }
	 
	 /**
	  * Genarate Random Password
	  * @param length
	  * @return String 
	  */
	 public static String generateRandomPassword (int length) {
		 return generateAlphaString(length/2) + generateNumericString(length-(length/2));
	 }
	
	 /**
	  * Generate float value with n number of decimal digits
	  * @param f
	  * @param decimal
	  * @return float
	  */
	 public static float roundFloat(float f,int decimal){
		 BigDecimal bd = new BigDecimal(Float.toString(f));
		 bd = bd.setScale(decimal, BigDecimal.ROUND_HALF_UP);
		 return bd.floatValue();
	 }
}
