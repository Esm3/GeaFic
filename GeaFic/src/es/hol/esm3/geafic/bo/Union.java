package es.hol.esm3.geafic.bo;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Union {
	
	protected Personne conjoint1 = null;
	protected Personne conjoint2 =  null;
	protected GregorianCalendar dateMariage = null;
	protected GregorianCalendar dateDivorce = null;
	
	public Union( Personne conjoint1, Personne conjoint2){
		this.conjoint1 = conjoint1;
		this.conjoint2 = conjoint2;
	}
	
	public Union(Personne conjoint1, Personne conjoint2, GregorianCalendar dateMariage){
		this(conjoint1, conjoint2);
		this.dateMariage = dateMariage;
	}
	
	public Union(Personne conjoint1, Personne conjoint2, GregorianCalendar dateMariage, GregorianCalendar dateDivorce){
		this(conjoint1, conjoint2, dateMariage);
		this.dateDivorce = dateDivorce;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Père : \n----------\n").append(conjoint1.toString()).append("\n\n");
		sb.append("Mère : \n----------\n").append(conjoint2.toString()).append("\n\n");
		if ( dateMariage != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			sb.append("Mariés le : ").append(sdf.format(dateMariage.getTime()));
		}
		
		
		
		return sb.toString();
	}

}
