package es.hol.esm3.geafic.utils;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import es.hol.esm3.geafic.bo.Personne;

public class ModelList implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(
			JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
			if (isSelected){
				System.out.println(value.toString());
			}
		
		return null;
	}

}
