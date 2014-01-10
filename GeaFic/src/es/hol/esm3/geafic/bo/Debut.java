package es.hol.esm3.geafic.bo;

import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import es.hol.esm3.geafic.utils.Localite;
import es.hol.esm3.geafic.utils.Sexe;

public class Debut {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Localite lieuxNaissance = new Localite(35000, "RENNES");
		Personne pers = null;
		Personne papa = null;
		Personne maman = null;
		Union mariageParents = null;
		try {
			pers = new Personne("ROUSSEAU", "Romuald", Sexe.MALE, new GregorianCalendar(1977, 8, 30), lieuxNaissance, new GregorianCalendar(2013, 8, 30));
			JOptionPane.showMessageDialog(null, pers.toString(),"Information",JOptionPane.WARNING_MESSAGE);
			
			
			
			papa = new Personne("ROUSSEAU", "Alain", Sexe.MALE, new GregorianCalendar(1956,0,12), null, null);
			maman = new Personne("TOUYA", "Marie-h�l�ne", Sexe.FEMALE, new GregorianCalendar(1958,6,29), null, null);
			mariageParents = new Union(papa, maman, new GregorianCalendar(1977,6,1));
			JOptionPane.showMessageDialog(null, mariageParents.toString(),"Information",JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),"Bloc catch", JOptionPane.ERROR_MESSAGE);
		}
		
		
		

	}

}
