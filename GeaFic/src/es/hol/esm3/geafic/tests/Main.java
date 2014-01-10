package es.hol.esm3.geafic.tests;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import es.hol.esm3.geafic.bll.PersonneMgt;
import es.hol.esm3.geafic.bo.Personne;
import es.hol.esm3.geafic.bo.Union;
import es.hol.esm3.geafic.utils.Sexe;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PersonneMgt mgtPers = new PersonneMgt();
		
		Personne pers1 = new Personne("ROUSSEAU", "Romuald", Sexe.MALE, new GregorianCalendar(1977, 8, 30), null);
		Personne pers2 = new Personne("ROUSSEAU", "Alain", Sexe.MALE, new GregorianCalendar(1956, 0, 12), null);
		Personne pers3 = new Personne("TOUYA", "Marie-Hélène", Sexe.FEMALE, new GregorianCalendar(1958, 6, 29), null);
		
			
		Union mariage = new Union(pers2, pers3, new GregorianCalendar(1977,6,1));
		
		pers1.setParents(mariage);
		
		mgtPers.ajouterPers(pers1);
		mgtPers.ajouterPers(pers2);
		mgtPers.ajouterPers(pers3);
		
		JOptionPane.showMessageDialog(null,mgtPers.toString());
		
		
	}

}
