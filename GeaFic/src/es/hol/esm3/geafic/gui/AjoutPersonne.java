package es.hol.esm3.geafic.gui;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.naming.ldap.Rdn;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import es.hol.esm3.geafic.bo.Personne;
import es.hol.esm3.geafic.utils.Sexe;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class AjoutPersonne extends JDialog {

	protected  JPanel contentPane;
	protected  JTextField tfdNom;
	protected  JTextField tfdPrenom;
	protected  final ButtonGroup SexeGrp = new ButtonGroup();
	
	protected JLabel lblNom;
	protected JLabel lblPrnom;
	protected JLabel lblDateDeNaissance;
	protected JLabel lblDateDeDeces;
	protected JLabel lblSexe;
	protected JLabel lblParents;
	protected JButton btnCreer;
	protected JButton btnAnnuler;
	
	protected JRadioButton rdbtnH = null;
	protected JRadioButton rdbtnF = null;
	protected JRadioButton rdbtnNc = null;
	
	protected JDateChooser dateDeces = new JDateChooser();
	protected JDateChooser dateNaiss = new JDateChooser();

	protected Personne unePers = null;

	/**
	 * Create the frame.
	 */
	public AjoutPersonne(Dialog owner, boolean modal) {
		super(owner, modal);
		
		setTitle("Ajout d'une personne");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNom = new JLabel("Nom :");
		lblNom.setBounds(53, 37, 100, 14);
		contentPane.add(lblNom);
		
		lblPrnom = new JLabel("Pr\u00E9nom :");
		lblPrnom.setBounds(53, 62, 100, 14);
		contentPane.add(lblPrnom);
		
		lblDateDeNaissance = new JLabel("Date de naissance :");
		lblDateDeNaissance.setBounds(53, 112, 112, 14);
		contentPane.add(lblDateDeNaissance);
		
		lblDateDeDeces = new JLabel("Date de d\u00E9c\u00E8s");
		lblDateDeDeces.setBounds(53, 137, 100, 14);
		contentPane.add(lblDateDeDeces);
		
		lblSexe = new JLabel("Sexe :");
		lblSexe.setBounds(53, 87, 100, 14);
		contentPane.add(lblSexe);
		
		lblParents = new JLabel("Parents :");
		lblParents.setBounds(53, 162, 100, 14);
		contentPane.add(lblParents);
		
		dateNaiss = new JDateChooser();
		dateNaiss.setBounds(175, 106, 100, 20);
		dateNaiss.setDateFormatString("dd/MM/yyyy");
		dateNaiss.setMaxSelectableDate(new Date());
		contentPane.add(dateNaiss);
		
		dateDeces = new JDateChooser();
		dateDeces.setBounds(175, 131, 100, 20);
		dateDeces.setDateFormatString("dd/MM/yyyy");
		dateDeces.setMaxSelectableDate(new Date());
		contentPane.add(dateDeces);
		
		tfdNom = new JTextField();
		tfdNom.setBounds(173, 34, 100, 20);
		contentPane.add(tfdNom);
		tfdNom.setColumns(10);
		
		tfdPrenom = new JTextField();
		tfdPrenom.setColumns(10);
		tfdPrenom.setBounds(173, 59, 100, 20);
		contentPane.add(tfdPrenom);
		
		btnCreer = new JButton("Cr\u00E9er");
		btnCreer.setBounds(194, 200, 89, 23);
		btnCreer.addActionListener(new listenerValider());
		contentPane.add(btnCreer);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(95, 200, 89, 23);
		btnAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
		});
		contentPane.add(btnAnnuler);
		
		rdbtnH = new JRadioButton("H");
		SexeGrp.add(rdbtnH);
		rdbtnH.setBounds(175, 83, 37, 23);
		contentPane.add(rdbtnH);
		
		rdbtnF = new JRadioButton("F");
		SexeGrp.add(rdbtnF);
		rdbtnF.setBounds(214, 83, 37, 23);
		contentPane.add(rdbtnF);
		
		rdbtnNc = new JRadioButton("NC");
		rdbtnNc.setSelected(true);
		SexeGrp.add(rdbtnNc);
		rdbtnNc.setBounds(253, 83, 58, 23);
		contentPane.add(rdbtnNc);
		
		
	}
	
	public AjoutPersonne(Dialog owner, boolean modal, Personne unePers) {
		this(owner, modal);
		
		this.btnCreer.setText("Modifier");
		this.tfdNom.setText(unePers.getNom());
		this.tfdPrenom.setText(unePers.getPrenom());
		switch (unePers.getSexe()){
		case MALE:
			rdbtnH.setSelected(true);
			break;
		case FEMALE:
			rdbtnF.setSelected(true);
			break;
		default:
			rdbtnNc.setSelected(true);
		}
		
		
		if (unePers.getDateNaissance() != null){
			this.dateNaiss.setDate(unePers.getDateNaissance().getTime());
		}
		
		if (unePers.getDateDeces() != null){
			this.dateDeces.setDate(unePers.getDateDeces().getTime());
		}
		
		
	}
	
	public Personne getRetour(){
		return this.unePers;
	}
	
	private class listenerValider implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String nomTmp = tfdNom.getText();
			String prenomTmp = tfdPrenom.getText();
			Sexe sexeTmp = Sexe.NC;
			if(rdbtnH.isSelected()){
				sexeTmp = Sexe.MALE;
			}
			if(rdbtnF.isSelected()){
				sexeTmp = Sexe.FEMALE;
			}
			
			
			
			GregorianCalendar dateNaissTmp = null;
			GregorianCalendar dateDecesTmp = null;
			
			
			dateNaissTmp = new GregorianCalendar();
			dateNaissTmp.setTime(dateNaiss.getDate());
			
			if(dateDeces.getDate() != null){
				dateDecesTmp = new GregorianCalendar();
				dateDecesTmp.setTime(dateDeces.getDate());	
			}
			
			
			unePers = new Personne(nomTmp, prenomTmp, sexeTmp, dateNaissTmp, null);
			try {
				unePers.setDateDeces(dateDecesTmp);
				dispose();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
			
			
		}
		
	}
}
