package es.hol.esm3.geafic.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.w3c.dom.events.MouseEvent;

import es.hol.esm3.geafic.bll.PersonneMgt;
import es.hol.esm3.geafic.bo.Personne;
import es.hol.esm3.geafic.utils.Sexe;

public class GestionFiches extends JDialog {

	private JPanel contentPane;
	protected DefaultListModel model = new DefaultListModel();
	protected JList list;
	protected Personne persEnCours;
	protected PersonneMgt mgtPersonne = new PersonneMgt();
	protected ArrayList<Personne> datas = new ArrayList<Personne>();
	protected JComboBox cbxTri;
	
	protected JButton btnModifier;
	protected JButton btnSupprimerarchiver;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionFiches frame = new GestionFiches();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestionFiches() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(629, 41, 89, 23);
		btnAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nouvellePersonne();
			}
		});
		contentPane.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setBounds(629, 72, 89, 23);
		btnModifier.setEnabled(false);
		btnModifier.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modifPersonne();
				
			}
		});
		contentPane.add(btnModifier);
		
		btnSupprimerarchiver = new JButton("Supprimer");
		btnSupprimerarchiver.setBounds(629, 106, 89, 23);
		btnSupprimerarchiver.addActionListener(new listenerSupprimer());
		btnSupprimerarchiver.setEnabled(false);
		contentPane.add(btnSupprimerarchiver);
		
		JButton btnFusionner = new JButton("Fusionner");
		btnFusionner.setBounds(629, 140, 89, 23);
		btnFusionner.setEnabled(false);
		contentPane.add(btnFusionner);
		
		cbxTri = new JComboBox();
		cbxTri.setModel(new DefaultComboBoxModel(new String[] {"Nom", "Prenom", "Age"}));
		cbxTri.setBounds(629, 10, 89, 20);
		cbxTri.addItemListener(new listenerComboTri());
		contentPane.add(cbxTri);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBounds(629, 419, 89, 23);
		btnQuitter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		contentPane.add(btnQuitter);
		
		JPanel panel = new JPanel();
		panel.setBounds(33, 21, 438, 392);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		list = new JList(model);
		list.addListSelectionListener(new listenerListSelect());
		list.addMouseListener(new dblClickListener());
//		Personne moi = new Personne("rousseau", "romuald", Sexe.MALE, new GregorianCalendar(1977,8,30), null);
//		Personne papa = new Personne("rousseau", "alain", Sexe.MALE, new GregorianCalendar(1956,0,12), null);
//		Personne maman = new Personne("touya", "marie-hélène", Sexe.FEMALE, new GregorianCalendar(1958,6,29), null);
//		model.addElement(papa);
//		model.addElement(maman);
//		model.addElement(moi);
		panel.add(new JScrollPane(list));
		
		JButton btnSauver = new JButton("Sauver");
		btnSauver.setBounds(629, 211, 89, 23);
		btnSauver.addActionListener(new listenerSauvegarder());
		contentPane.add(btnSauver);
		
		JButton btnCharger = new JButton("Charger");
		btnCharger.setBounds(629, 245, 89, 23);
		btnCharger.addActionListener(new listenerCharger());
		contentPane.add(btnCharger);
		
		
	}
	
	private void nouvellePersonne(){
		AjoutPersonne ajPers = new AjoutPersonne(this,true);
		ajPers.setVisible(true);
		persEnCours = ajPers.getRetour();
		model.addElement(persEnCours);
	}
	
	public void setPersonne(Personne value){
		this.persEnCours = value;
	}
	
	private void modifPersonne(){
		if(list.getSelectedIndices().length == 1){
			int index = list.getSelectedIndex();
			persEnCours =  (Personne)list.getSelectedValue();
			model.remove(index);
			AjoutPersonne ajPers = new AjoutPersonne(this,true,persEnCours);
			ajPers.setVisible(true);
			
			if ((ajPers.getRetour()) != null){
				model.insertElementAt(ajPers.getRetour(), index);			
			} else {
				model.insertElementAt(persEnCours, index);
				persEnCours = null;
			}
			
		}
		
	}
	
	private class listenerSauvegarder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String fichier ="";
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Sauvegarde Geafic", "sav");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(getParent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					fichier = chooser.getSelectedFile().getCanonicalPath();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			FileOutputStream fos;
			ObjectOutputStream oos;
			
			File fic = new File(fichier);
			try {
				
				fos = new FileOutputStream(fic);
				oos = new ObjectOutputStream(fos);
				int taille = model.getSize();
				
				for (int i = 0 ; i < taille; i++){
					oos.writeObject(model.getElementAt(i));
				}
				
				oos.flush();
				oos.close();
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException ioe) {
				// TODO Auto-generated catch block
				ioe.printStackTrace();
			}
			
		}
		
	}
	
	private class listenerCharger implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String fichier ="";
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Sauvegarde Geafic", "sav");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(getParent());
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					fichier = chooser.getSelectedFile().getCanonicalPath();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			File fic = new File(fichier);
			
			FileInputStream fis;
			ObjectInputStream ois;
			
			try {
				fis = new FileInputStream(fic);
				ois = new ObjectInputStream(fis);
				
				while (fis.available() >0 ){
					Personne unePers = (Personne) ois.readObject();
					model.addElement(unePers);
				}
				
				ois.close();
				
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"e1",JOptionPane.ERROR_MESSAGE);
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(null, ioe.getMessage(),"ioe",JOptionPane.ERROR_MESSAGE);
				ioe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				JOptionPane.showMessageDialog(null, cnfe.getMessage(),"cnfe",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class listenerComboTri implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED);
	
		}
	}
	
	private class listenerListSelect implements ListSelectionListener{


		@Override
		public void valueChanged(ListSelectionEvent e) {
			if(list.getSelectedIndex() > -1){
				ligneSelect(true);
			}else{
				ligneSelect(false);
			}
		}
		
	}

	private class listenerSupprimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			model.remove(list.getSelectedIndex());
			
		}
		
	}
	
	private void triListe(String critere){
		ArrayList<Personne> tabPers = new ArrayList<Personne>();
		int taille = model.getSize();
		
		for (int i = 0;  i < taille; i++){
			tabPers.add((Personne)model.getElementAt(0));
		}
	}
	
	private void ligneSelect(Boolean lgSelect){
			btnModifier.setEnabled(lgSelect);
			btnSupprimerarchiver.setEnabled(lgSelect);
		
	}
	
	private class dblClickListener implements MouseListener{

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (e.getClickCount() == 2){
				modifPersonne();
			}
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	}
	

