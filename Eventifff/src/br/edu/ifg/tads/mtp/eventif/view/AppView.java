package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AppView extends JFrame {
	private JPanel painelSuperior;
	private JPanel painelEsquerda;
	private JPanel painelDireita;
	private JPanel principal;
	private JLabel logo;
	private ImageIcon icon;
	private JDesktopPane desk;

	public AppView(JDesktopPane desk) {
		super("EventIF - Desk'top'");
		this.desk = desk;
		this.setContentPane(desk);

		principal = new JPanel();
		principal.setLayout(null);
		principal.setSize(1200, 700);
		principal.setBackground(new Color(0, 0, 0));
		icon = new ImageIcon(getClass().getResource("/Logo.png"));

		logo = new JLabel(icon);
		painelSuperior = new JPanel();
		painelEsquerda = new JPanel();
		painelDireita = new JPanel();
		painelSuperior.setLayout(null);
		painelEsquerda.setLayout(null);
		painelDireita.setLayout(null);

		logo.setBounds(5, 0, 220, 75);
		painelSuperior.setBounds(0, 0, 1200, 75);
		painelEsquerda.setBounds(0, 76, 220, 624);
		painelDireita.setBounds(221, 76, 979, 624);
		painelDireita.setBackground(new Color(171, 171, 171));
		painelSuperior.setBackground(new Color(165, 170, 163));
		painelSuperior.setBackground(new Color(66, 105, 51));
		painelEsquerda.setBackground(new Color(66, 105, 51));

		painelSuperior.add(logo);

		principal.add(painelSuperior);
		principal.add(painelEsquerda);
		principal.add(painelDireita);
		
		this.setResizable(true);
		this.setLayout(null);
		this.setSize(1200, 700);
		this.add(principal);
	}

	public JPanel getPainelSuperior() {
		return painelSuperior;
	}

	public void setPainelSuperior(JPanel painelSuperior) {
		this.painelSuperior = painelSuperior;
	}

	public JPanel getPainelEsquerda() {
		return painelEsquerda;
	}

	public void setPainelEsquerda(JPanel painelEsquerda) {
		this.painelEsquerda = painelEsquerda;
	}

	public JPanel getPainelDireita() {
		return painelDireita;
	}

	public void setPainelDireita(JPanel painelDireita) {
		this.painelDireita = painelDireita;
	}

	public JDesktopPane getDesk() {
		return desk;
	}

	public void setDesk(JDesktopPane desk) {
		this.desk = desk;
	}

}
