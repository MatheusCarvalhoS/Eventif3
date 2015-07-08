package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipalAlunoView {
	private JPanel painelEsquerda;
	private JButton btListarEventos;
	private JButton minhaConta;
	
	public JPanel getMenuPrincipalAlunoView(){
		painelEsquerda = new JPanel();
		painelEsquerda.setBounds(0, 0, 220, 624);
		painelEsquerda.setLayout(null);
		painelEsquerda.setBackground(new Color(66, 105, 51));
		
		btListarEventos = new JButton("Listar Eventos");
		minhaConta = new JButton("Minha Conta");
		
		// dimencionando os botões
		btListarEventos.setBounds(35, 150, 150, 25);
		minhaConta.setBounds(35, 185, 150, 25);
		
		// adicionando no painel
		painelEsquerda.add(btListarEventos);
		painelEsquerda.add(minhaConta);
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

	public JButton getMinhaConta() {
		return minhaConta;
	}

	public void setMinhaConta(JButton minhaConta) {
		this.minhaConta = minhaConta;
	}
}