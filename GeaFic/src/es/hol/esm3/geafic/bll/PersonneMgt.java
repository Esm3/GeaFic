package es.hol.esm3.geafic.bll;

import java.util.ArrayList;

import es.hol.esm3.geafic.bo.Personne;



public class PersonneMgt {
	
	protected ArrayList<Personne> listePers = null;
	
	
	public PersonneMgt(){
		this.listePers = new ArrayList<Personne>();
		
	}
	
	public void ajouterPers(Personne unePers){
		
		if (unePers != null){
			listePers.add(unePers);
		}
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		
		for(Personne unePers : this.listePers){
			sb.append(unePers.toString()).append("\n" +
					"--------------------------------------------------\n");
		}
		
		
		return sb.toString();
	}
	
}
