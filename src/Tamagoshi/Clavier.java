package Tamagoshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Clavier {
	/**
	 * Methode pour lire un string au clavier de la console
	 * @return String la ligne lue
	 */
	public static String lireString()
	// lecture d'une chaine
	{
		String ligne_lue = null;
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			ligne_lue = entree.readLine();
		} catch (IOException err) {
			System.exit(0);
		}
		return ligne_lue;
	}
	/** Methode pour lire string oui ou non UNIQUEMENT
	 * 
	 * @return lignelue lower case si oui/non sinon null
	 */
	public static String lireOuiNon()
	// lecture d'une chaine
	{
		String ligne_lue_brut = null;
		String ligne_lue = null;
		try {
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			ligne_lue_brut = entree.readLine();
			ligne_lue = ligne_lue_brut.toLowerCase();//transforme en minuscule
			if (!ligne_lue.equals("oui")&&!ligne_lue.equals("non")) {
				ligne_lue = null;
			}
		} catch (IOException err) {
			return null;
		}
		return ligne_lue;
	}

	public static float lireFloat()
	// lecture d'un float
	{
		float x = 0;
		// valeur a lire
		try {
			String ligne_lue = lireString();
			x = Float.parseFloat(ligne_lue);
		} catch (NumberFormatException err) {
			System.out.println("*** Erreur de donnee ***");
			System.exit(0);
		}
		return x;
	}

	public static double lireDouble()
	// lecture d'un double
	{
		double x = 0;
		// valeur a lire
		try {
			String ligne_lue = lireString();
			x = Double.parseDouble(ligne_lue);
		} catch (NumberFormatException err) {
			System.out.println("*** Erreur de donnee ***");
			System.exit(0);
		}
		return x;
	}

	public static int lireInt()
	// lecture d'un int
	{
		int n = 0;
		// valeur a lire
		try {
			String ligne_lue = lireString();
			n = Integer.parseInt(ligne_lue);
		} catch (NumberFormatException err) {
			System.out.println("*** Erreur de donnee ***");
			System.exit(0);
		}
		return n;
	}
	
	/**
	 * Methode pour lire un int compris entre int debut et fin compris
	 * sinon return debut-1
	 * @param debut
	 * @param fin
	 * @return
	 */
	public static int lireInt(int debut, int fin)
	// lecture d'un int
	{
		int n = debut-1;
		// valeur a lire
		
		try {
			String ligne_lue = lireString();
			n = Integer.parseInt(ligne_lue);
			if (n<debut||n>fin) {
				return (debut-1);
			}
		} catch (NumberFormatException err) {
			System.out.println("*** Erreur de donnee ***");
//			System.exit(0);
			return (debut-1);
		}
		return n;
	}
}
