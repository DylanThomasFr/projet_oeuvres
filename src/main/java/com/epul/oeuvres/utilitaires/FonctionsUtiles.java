package com.epul.oeuvres.utilitaires;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FonctionsUtiles {

	// /
	// / Conversion d'une date en chaîne
	// /
	public static String DateToString(Date dt, String modeleEntree)  {
	
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(modeleEntree);
		Calendar myC = GregorianCalendar.getInstance();
		myC.setTime(dt);
		String retour = "";
	    retour += String.valueOf(myC.get(Calendar.YEAR))+ "-";
		retour +=String.valueOf(myC.get(Calendar.MONTH)+1) + "-";
		retour +=String.valueOf( myC.get(Calendar.DAY_OF_MONTH)) + " ";
		retour +=String.valueOf(myC.get(Calendar.HOUR_OF_DAY))+ ":";
		retour +=String.valueOf( myC.get(Calendar.MINUTE)) + ":";
		retour += String.valueOf(myC.get(Calendar.SECOND));
		return retour;
	}

	public static String conversionDateenChaine(Date unedate, String modele)
	// le modèlet est une combinaison de MM dd yyyy avec / ou –
	// exemple dd/MM/yyyy
			throws Exception {
		String datesortie = "";
		// on définit un format de sortie
		SimpleDateFormat defFormat = new SimpleDateFormat(modele);
		datesortie = defFormat.format(unedate);
		return datesortie;
	}
	
	
	public static Date conversionChaineenDate(String unedate, String unformat)
			throws Exception {
		Date datesortie;
		// on définit un format de sortie
		SimpleDateFormat defFormat = new SimpleDateFormat(unformat);
		datesortie = defFormat.parse(unedate);
		return datesortie;
	}
	
	// cryptage MD5
	public static String md5(String input) throws NoSuchAlgorithmException {
		String result = input;
		if (input != null) {
			MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
			md.update(input.getBytes());
			BigInteger hash = new BigInteger(1, md.digest());
			result = hash.toString(16);
			while (result.length() < 32) { //40 for SHA-1
				result = "0" + result;
			}
		}
		return result;
	}

}