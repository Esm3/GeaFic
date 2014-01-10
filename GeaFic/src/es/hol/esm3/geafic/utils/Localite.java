package es.hol.esm3.geafic.utils;

public class Localite {

	protected int cp;
	protected String ville;
	protected String pays;
	
	public Localite(int cp, String ville){
		this(cp, ville, "France");
	}
	
	public Localite(int cp, String ville, String pays){
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(ville).append(" (").append(cp).append(" - ").append(pays).append(" )");
		return sb.toString();
	}
	
}
