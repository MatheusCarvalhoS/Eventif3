package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipalView {
	private JPanel painelEsquerda;
	private JButton btInscricao;
	private JButton btLogin;
	
	public JPanel getMenuPrincipal(){
		painelEsquerda = new JPanel();
		painelEsquerda.setBounds(0, 0, 220, 624);
		painelEsquerda.setLayout(null);
		painelEsquerda.setBackground(new Color(66, 105, 51));
		
		btInscricao = new JButton("INSCRIÇÃO");
		btLogin = new JButton("LOGIN");
		
		// dimencionando os botões
		btInscricao.setBounds(35, 150, 150, 25);
		btLogin.setBounds(35, 190, 150, 25);
		
		// adicionando no painel
		painelEsquerda.add(btInscricao);
		painelEsquerda.add(btLogin);
		
		return painelEsquerda;
	}

	public JButton getBtInscricao() {
		return btInscricao;
	}

	public void setBtInscricao(JButton btInscricao) {
		this.btInscricao = btInscricao;
	}

	public JButton getBtLogin() {
		return btLogin;
	}

	public void setBtLogin(JButton btLogin) {
		this.btLogin = btLogin;
	}
	
	
}
