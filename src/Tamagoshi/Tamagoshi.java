package Tamagoshi;

public class Tamagoshi {
	private String nom;
	private int poids;
	private int energie;
	private int poidsMax = 15;
	private int energieMin = 1;
	private int poidsMin = 1;
	private int energieMax = 15;
	private boolean pleure = false;
	private int perteEnergiePleure = 6;
	private int prisePoidsMange = 2;
	private int perteEnergieActivite = 1;
	private int perteEnergieActiviteJouer = 4;
	private int poidsPerte=2;

	public int getPerteEnergieActiviteJouer() {
		return perteEnergieActiviteJouer;
	}

	public int getPoidsPerte() {
		return poidsPerte;
	}

	public int getPerteEnergieActivite() {
		return perteEnergieActivite;
	}

	public int getPrisePoidsMange() {
		return prisePoidsMange;
	}

	public int getPerteEnergiePleure() {
		return perteEnergiePleure;
	}

	public int getEnergieMax() {
		return energieMax;
	}

	public int getPoidsMin() {
		return poidsMin;
	}

	public int getPoidsMax() {
		return poidsMax;
	}

	public int getEnergieMin() {
		return energieMin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public boolean isPleure() {
		return pleure;
	}

	public void setPleure(boolean pleure) {
		this.pleure = pleure;
	}

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public Tamagoshi() {

	}

	/**
	 * Methode pour faire manger le tamagoshi si bool pleure =true => ne mange pas
	 * et perte d'energie =perteEnergiePleure si bool pleure =false => mange =
	 * getPrisePoidsMange et energie-getPerteEnergieActivite puis appel de la
	 * méthode affiche() dans tout les cas
	 */
	public void manger() {
		if (!this.isPleure()) {
			this.setPoids((this.getPoids() + getPrisePoidsMange()));
			this.setEnergie((this.getEnergie() - getPerteEnergieActivite()));
			System.out.print("tu as fait manger, ");
			System.out.println(this.getNom());
			affiche();

		} else {
			this.setEnergie((this.getEnergie() - this.getPerteEnergiePleure()));
			System.out.print(this.getNom() + " pleure et ne veut pas manger ");
			affiche();

		}

	}

	/**
	 * methode pour faire jouer 
	 * energie -= getPerteEnergieActiviteJouer() 
	 * poids-= getpPoidsPerte
	 * puis appel
	 * methode affiche()
	 */
	public void jouer() {
		this.setEnergie((this.getEnergie() - this.getPerteEnergieActiviteJouer()));
		this.setPoids((this.getPoids()-this.getPoidsPerte()));
		System.out.print("tu as joué avec ");
		System.out.println(this.getNom());
		affiche();
		// vivant();
	}

	/**
	 * Methode dormir si bool pleure =true => ne dors pas pas et perte d'energie
	 * =perteEnergiePleure si bool pleure =false => enrrgie = 2 puis appel de la
	 * méthode affiche() dans tout les cas
	 */
	public void dormir() {
		if (this.isPleure() == false) {
			this.setEnergie((this.getEnergie() + 2));
			System.out.print("tu as fait dormir, ");
			affiche();
		} else {
			this.setEnergie(this.getEnergie() - this.getPerteEnergiePleure());
			System.out.print(this.getNom() + " pleure et ne veut pas dormir ");
			affiche();
		}
	}

	/**
	 * methode affiche pour afficher les infos du tamagoshi
	 */
	public void affiche() {

		System.out.println("le tamagoshi se nommant " + this.getNom() + " qui a pour poids "
				+ this.getPoids() + "kg et a pour energie " + this.getEnergie());
	}

	/**
	 * Méthode vivant() teste si le tama est vivant et doit pleurer
	 * 
	 * @param numTama => index+1 dans la arraylist contenant les tama puis appel de
	 *                la méthode choixTama() dans Saisi.java
	 */
	public void vivant(int numTama) {
		int ceTama = (numTama - 1);
		int poids = this.getPoids();
		int energie = this.getEnergie();
		int poidsMax = this.getPoidsMax();
		int energieMin = this.getEnergieMin();
		int energieMax = this.getEnergieMax();
		if (poids > poidsMax) {
			System.out.println("tu as tué " + this.getNom() + "!!! Tu l'as trop fait manger, imbécile!!!!");
			Saisi.arraylist.remove(ceTama);
		} else if (energie < energieMin) {
			System.out.println("tu as tué " + this.getNom() + "!!! Tu l'as trop fatigué, imbécile!!!!");
			Saisi.arraylist.remove(ceTama);
		} else if (energie >= energieMax) {
			System.out.println(this.getNom() + " pleure! Il a trop d'energie a dépenser!!!");
			this.setPleure(true);
		} else if (energie < energieMax && this.isPleure() == true) {
			System.out.println("Dieu merci !!! " + this.getNom() + " ne pleure plus!");
			this.setPleure(false);
		}
		Saisi.choixTama();
	}

}
