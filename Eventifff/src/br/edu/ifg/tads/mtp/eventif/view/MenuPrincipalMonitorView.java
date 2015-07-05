package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipalMonitorView {
	private JPanel painelEsquerda;
	private JButton btListarEventos;
	
	
	public JPanel getMenuPrincipalMonitorView(){
		painelEsquerda = new JPanel();
		painelEsquerda.setBounds(0, 0, 220, 624);
		painelEsquerda.setLayout(null);
		painelEsquerda.setBackground(new Color(66, 105, 51));
		
		btListarEventos = new JButton("Listar Eventos");
		
		// dimencionando os botões
		btListarEventos.setBounds(35, 150, 150, 25);
		
		// adicionando no painel
		painelEsquerda.add(btListarEventos);
		return painelEsquerda;
	}
	
	





	public JPanel getPainelEsquerda() {
		return painelEsquerda;
	}

	public void setPainelEsquerda(JPanel painelEsquerda) {
		this.painelEsquerda = painelEsquerda;
	}

	public JButton getBtListarEventos() {
		return btListarEventos;
	}

	public void setBtListarEventos(JButton btListarEventos) {
		this.btListarEventos = btListarEventos;
	}
	
}