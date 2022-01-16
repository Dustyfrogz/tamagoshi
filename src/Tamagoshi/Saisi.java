package Tamagoshi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Saisi {
	public static String test;
	public static ArrayList<Tamagoshi> arraylist = new ArrayList<Tamagoshi>();
	public static int energie;
	public static int numTama; // tamagoshi en cours de jeu
	public static int poids;

	/**
	 * Methode de saisi au clavier des infos du tamagoshi puis ajout l'objet dans
	 * arraylist tama
	 * 
	 * @throws IOException
	 */
	public static void saisie() throws IOException {
		Scanner sc = new Scanner(System.in);

		// saisi du nom avec vérification pas de type sexuel
		do {
			String nom;
			Tamagoshi tama = new Tamagoshi();
			boolean testNom = true;

			do {
				System.out.println("saisissez le nom de votre nouveau tamagoshi");
				nom = sc.nextLine();
				testNom = aTester(nom);// appel de aTester pour verif du nom

			} while (testNom == true);
			tama.setNom(nom);

			// saisir un poids avec vérification de la plage de poids
			do {
				System.out.println("saisissez le poids en kilo (nb entier de " + tama.getPoidsMin() + " à "
						+ tama.getPoidsMax() + ")");
				poids = Clavier.lireInt(tama.getPoidsMin(), tama.getPoidsMax()); // methode spécial pour lire int entre
																					// 2 valeurs
				if (poids == (tama.getPoidsMin() - 1)) {
					System.out.println("Le poids doit être un nb entier de " + tama.getPoidsMin() + " à "
							+ tama.getPoidsMax() + ")");
				} else {
					tama.setPoids(poids);
				}
			} while (poids == (tama.getPoidsMin() - 1));

			// saisir l'energie comprise entre 2 valeurs avec if et des getter sans methode
			// spécial
			do {
				System.out.println("saisissez l'energie(nb entier de " + tama.getEnergieMin() + " à "
						+ tama.getEnergieMax() + ")");
				energie = Clavier.lireInt();
				if (energie > tama.getEnergieMax()) {
					System.out.println("le max pour l'energie c'est " + tama.getEnergieMax());
				} else if (energie < 1) {
					System.out.println("le minimum pour l'energie c'est " + tama.getEnergieMin());
				}
			} while (energie > tama.getEnergieMax() || energie < tama.getEnergieMin());
			tama.setEnergie(energie);

			arraylist.add(tama);

			// pour ajouter d'autre objet tama la arraylist
			do {
				System.out.println("voulez vous jouer avec un autre Tamagochi en même temps? oui/non");

				test = Clavier.lireOuiNon();
			} while (test == null);

		} while (test.equals("oui"));

	}

	/**
	 * Methode affiche tout les tamas de la arraylist
	 */
	public static void afficheTout() {
		if (arraylist.size() > 0) {
			if (arraylist.size() > 1) {
				System.out.println("Vous avez ces tamagoshis");
				for (int i = 0; i < arraylist.size(); i++) {

					arraylist.get(i).affiche();
				}
			} else {
				System.out.println("Vous avez ce tamagoshi");
				arraylist.get(0).affiche();
			}

		} else {
			System.out.println("il n'y a pas de tamagoshi vivant");
			System.exit(0);
		}
	}

	/**
	 * methode de choix de tama dans la arraylist si aucun tama =exit
	 */
	public static void choixTama() {
		if (arraylist.size() > 0) {
			if (arraylist.size() > 1) {
				do {
					System.out.println("Avec quel tamagoshi tu veux jouer?");
					for (int i = 0; i < arraylist.size(); i++) {
						System.out.println(i + 1 + " : " + arraylist.get(i).getNom());
					}
					numTama = Clavier.lireInt(1, (arraylist.size()));
				} while (numTama == 0);

				System.out.println("Ok, on joue avec " + arraylist.get(numTama - 1).getNom());
			} else {
				System.out.println("On joue avec " + arraylist.get(0).getNom());
				numTama = 1;
			}
			propoJouer(numTama);
		} else {
			System.out.println("il n'y a plus de tamagoshi vivant");
			System.exit(0);
		}
	}

	/**
	 * proposition de jeu (jouer,manger,dormir)ou appel methode stopJeu ou changement de tamagoshi
	 * si il y en a plusieurs
	 */

	public static void propoJouer(int numTama) {

		int input = 0;
		do {
			System.out.println("tu veux ? ");
			System.out.println("1: faire jouer " + arraylist.get(numTama - 1).getNom());
			System.out.println("2: faire manger " + arraylist.get(numTama - 1).getNom());
			System.out.println("3: faire dormir " + arraylist.get(numTama - 1).getNom());
			if (arraylist.size() > 1) {
				System.out.println("4: changer de tamagoshi");
				System.out.println("5: arreter de jouer");
				input = Clavier.lireInt(1, 5);
			} else {
				System.out.println("4: arreter de jouer");
				input = Clavier.lireInt(1, 4);
			}

		} while (input == 0);

		switch (input) {
		case 1:
			jouer(numTama);
			break;

		case 2:
			manger(numTama);
			break;

		case 3:
			dormir(numTama);
			break;
		case 4:
			if (arraylist.size() > 1) {
				choixTama();
			} else {
				stopJeu();
			}
		case 5:
			stopJeu();
		}
	}

	/**
	 * Methode pour faire manger le tama puis appel methode vivant()
	 * 
	 * @param numTama =>index du tama dans la arraylist+1
	 */
	public static void manger(int numTama) {
		arraylist.get(numTama - 1).manger();
		arraylist.get(numTama - 1).vivant(numTama);
	}

	/**
	 * Methode pour faire jouer le tama puis appel methode vivant()
	 * 
	 * @param numTama =>index du tama dans la arraylist+1
	 */
	public static void jouer(int numTama) {
		arraylist.get(numTama - 1).jouer();
		arraylist.get(numTama - 1).vivant(numTama);
	}

	/**
	 * Methode pour faire dormir le tama puis appel methode vivant()
	 * @param numTama =>index du tama dans la arraylist+1
	 */
	public static void dormir(int numTama) {
		arraylist.get(numTama - 1).dormir();
		arraylist.get(numTama - 1).vivant(numTama);
	}
/**
 * methode pour stopper le jeu suite demande utilisateur
 */
	public static void stopJeu() {
		System.out.println("Au revoir, tu crois qu'un tamagoshi peut vivre sans son maitre?");
		String res = "";
		do {
			res = Clavier.lireString();
			if (res.equals("oui")) {
				System.out.println("tu es vraiment sans coeur!!! Ciao et sans regret");
				System.exit(0);
			} else if (res.equals("non")) {
				System.out.println("Ca c'est une bonne réponse, il t'attend ton ptit tamagoshi <3");
				choixTama();
			} else {
				System.out
						.println("tu pourrai faire preuve de plus de franchise \n en répondant par oui ou par non !!!");
			}
		} while (!res.equals("oui") && !res.equals("non"));
	}


	/**
	 * methode de recherche de string contenant de mot à caractère sexuel par API/JSON
	 * true= contient caractere sexuel
	 * @param NomATester
	 * @return
	 * @throws IOException
	 */
	public static boolean aTester(String NomATester) throws IOException {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		String line;
		boolean test = true;
		try {
			URL realUrl = new URL("https://api.sightengine.com/1.0/text/check.json"); //url de l'api
			HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection(); //ouverture de connexion
			conn.setDoOutput(true);
			conn.setInstanceFollowRedirects(false);
			conn.setRequestMethod("POST");
			out = new PrintWriter(conn.getOutputStream()); //objet contenant les infos 
			/*
			 * opt countries=langue à tester
			 * mode= standard pour version gratuite
			 * text= string à envoyer pour test
			 * api user= le login
			 * api secret = la clef de securité
			 */
			out.print("opt_countries=us,gb,fr&mode=standard&text=" + NomATester
					+ "&lang=fr&api_user=XXXXXXXX&api_secret=XXXXXXXXXXXX");
			out.flush();// envoie
			in = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			while ((line = in.readLine()) != null) {
				result += line;
			}

			// a décommenter pour tester le string retouné
			// System.out.println(result);

			// decoupage pour avoir la valeur souhaité (type : sexuel)
			String str = result.replace("{", ",");
			String strun = str.replace("}", ",");
			String str2 = strun.replaceAll(" ", "");
			String[] parts = str2.split(",");

			if (parts[9].equals("\"type\":\"sexual\"")) {
				test = true;
				System.out.println("le nom a un caractère sexuel, il n'est pas accepté");
			} else {
				test = false;
			}
			return test;

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return test;

	}

}