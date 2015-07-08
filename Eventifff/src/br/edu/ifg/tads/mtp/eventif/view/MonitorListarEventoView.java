package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class MonitorListarEventoView {
	private JPanel painel;
	private JTextField jtfPesquisar;
	private JTable table;
	private JButton jbtnPesquisar;
	private JButton btListarAtividades;
	private JButton lerQRcode;

	public JPanel getMonitorListarEventoView() {
		painel = new JPanel();
		painel.setBounds(0, 0, 979, 624);
		painel.setLayout(null);

		jtfPesquisar = new JTextField();
		jtfPesquisar.setForeground(new Color(0, 0, 128));
		jtfPesquisar.setFont(new Font("HanziPen TC", Font.BOLD, 12));
		jtfPesquisar.setBounds(20, 20, 500, 28);
		jtfPesquisar.setColumns(10);

		jbtnPesquisar = new JButton("Pesquisar");
		jbtnPesquisar.setForeground(new Color(0, 0, 128));
		jbtnPesquisar.setFont(new Font("HanziPen TC", Font.BOLD, 12));

		btListarAtividades = new JButton("Atividades");
		btListarAtividades.setBounds(560, 550, 170, 25);

		jbtnPesquisar.setBounds(530, 20, 117, 26);
		
		lerQRcode = new JButton("Ler QRcode Evento");
		lerQRcode.setBounds(40, 550, 170, 25);

		table = new JTable();
		table.setFont(new Font("HanziPen TC", Font.BOLD, 15));
		table.setBounds(5, 60, 970, 500);
		table.setBackground(UIManager.getColor("Button.background"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(5, 60, 970, 450);

		painel.add(jScrollPane);
		painel.add(lerQRcode);
		painel.add(btListarAtividades);
		painel.add(jtfPesquisar);
		painel.add(jbtnPesquisar);
		painel.setBackground(new Color(240, 240, 240));

		return painel;
	}

	public JButton getLerQRcode() {
		return lerQRcode;
	}

	public void setLerQRcode(JButton lerQRcode) {
		this.lerQRcode = lerQRcode;
	}

	public JButton getBtListarAtividades() {
		return btListarAtividades;
	}

	public void setBtListarAtividades(JButton btListarAtividades) {
		this.btListarAtividades = btListarAtividades;
	}

	public JTextField getJtfPesquisar() {
		return jtfPesquisar;
	}

	public void setJtfPesquisar(JTextField jtfPesquisar) {
		this.jtfPesquisar = jtfPesquisar;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getJbtnPesquisar() {
		return jbtnPesquisar;
	}

	public void setJbtnPesquisar(JButton jbtnPesquisar) {
		this.jbtnPesquisar = jbtnPesquisar;
	}

}
