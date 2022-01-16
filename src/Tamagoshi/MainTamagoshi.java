package Tamagoshi;


import java.io.IOException;

public class MainTamagoshi {
/**
 * Methode de lancement du jeu
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		
		Saisi.saisie();  // permet la saisie et enregistre ds arraylist
		Saisi.afficheTout();// affiche tout les tama de l'arraylist
		Saisi.choixTama();//choix du tama et lancement du jeu
		
}
}


