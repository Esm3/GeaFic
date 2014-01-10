package es.hol.esm3.geafic.bo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

import es.hol.esm3.geafic.utils.Localite;
import es.hol.esm3.geafic.utils.Sexe;

public class Personne implements Serializable {

	protected UUID identifiant = null;
	protected String nom = null;
	public int getAge() {
		return age;
	}

	public UUID getIdentifiant() {
		return identifiant;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public GregorianCalendar getDateNaissance() {
		return dateNaissance;
	}

	public GregorianCalendar getDateDeces() {
		return dateDeces;
	}

	public Localite getLieuxNaissance() {
		return lieuxNaissance;
	}

	public Union getParents() {
		return parents;
	}

	protected String prenom = null;
	protected Sexe sexe = null;
	protected GregorianCalendar dateNaissance = null;
	protected boolean archiver = false;
	protected GregorianCalendar dateDeces = null;
	protected Localite lieuxNaissance = null;
	protected Union parents = null;
	protected int age = 0;

	public Personne(String nom, String prenom, Sexe sexe,
			GregorianCalendar dateNaissance, Localite lieuxNaissance) {
		this.identifiant = UUID.randomUUID();
		setNom(nom);
		setPrenom(prenom);
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.lieuxNaissance = lieuxNaissance;
		
		this.age = calculAge(this.dateNaissance, null);
	}

	public Personne(String nom, String prenom, Sexe sexe,
			GregorianCalendar dateNaissance, Localite lieuxNaissance,
			GregorianCalendar dateDeces) throws Exception {
		this(nom, prenom, sexe, dateNaissance, lieuxNaissance);
		this.setDateDeces(dateDeces);
	}

	// --------------------------------- Getters / Setters
	// ---------------------------------
	public void setDateDeces(GregorianCalendar dateDeces) throws Exception {

		if (dateDeces != null && this.dateNaissance != null) {

			if (dateDeces.after(this.dateNaissance) || dateDeces.equals(dateNaissance)) {
				
				this.dateDeces = dateDeces;
				this.age = calculAge(this.dateNaissance, this.dateDeces);
						
			} else {
				throw new IllegalArgumentException("Ne peut pas �tre mort avant d'�tre n�");
			}
		} 
			
		
	}

	public void setParents(Union parents) {
		this.parents = parents;
	}
	
	public void setPrenom(String value){
		this.prenom = firstToUpper(value);
	}
	
	public void setNom(String value){
		if (value != null){
			this.nom = value.toUpperCase();
		} 
		
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		sb.append(this.nom).append(" ").append(this.prenom).append(" (");
		switch (this.sexe) {
		case MALE:
			sb.append("H");
			break;
		case FEMALE:
			sb.append("F");
			break;
		default:
			sb.append("NC");
		}
		
		sb.append(")");

		if (dateDeces != null) {
			sb.append(" Décédée le ").append(
					sdf.format(this.dateDeces.getTime())).append(" à l'age de ").append(this.age).append(" ans");
		} else {
			sb.append(" Encore en vie (").append(this.age).append(" Ans )");
		}
		
		return sb.toString();
	}
	
	/*
	 * Calcul un nombre d'années entre deux dates
	 * dateDebut : date de dÉbut de l'intervalle
	 * dateFin : date de fin de l'intervalle, si cette date n'est pas précisée la date du jour sera prise
	 */
	private int calculAge(GregorianCalendar dateDebut, GregorianCalendar dateFin){
		if (dateDebut != null) {
			if (dateFin == null){
				dateFin = new GregorianCalendar();
			}
			
			
			if (dateFin.get(Calendar.MONTH) > dateDebut.get(Calendar.MONTH)) {
				return dateFin.get(Calendar.YEAR) - dateDebut.get(Calendar.YEAR);
			} else {
				if (dateFin.get(Calendar.MONTH) == dateDebut.get(Calendar.MONTH)
						&& dateFin.get(Calendar.DAY_OF_MONTH) >= dateDebut.get(Calendar.DAY_OF_MONTH)) {
					return dateFin.get(Calendar.YEAR) - dateDebut.get(Calendar.YEAR);
				} else {
					return dateFin.get(Calendar.YEAR) - dateNaissance.get(Calendar.YEAR) - 1;
				}
			}
		} else {
			return -1;
		}
	}

	private String firstToUpper(String chaine){
		StringBuilder sb = new StringBuilder(chaine.length());
		int tailleChaine = chaine.length();
		
		for (int i = 0 ; i < tailleChaine; i++){
			if(i == 0){
				sb.append(Character.toUpperCase(chaine.charAt(i)));
			}else{
				sb.append(Character.toLowerCase(chaine.charAt(i)));
			}
			
		}
		
		
		return sb.toString();
	}
	
}
