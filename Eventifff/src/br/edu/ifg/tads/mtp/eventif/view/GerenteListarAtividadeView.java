package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class GerenteListarAtividadeView {
	private JPanel painel;
	private JTextField jtfPesquisar;
	private JTable table;
	private JButton jbtnPesquisar;
	private JButton alterarAtividade;
	private JButton excluirAtividade;
	private JButton addAtividade;
	private JButton selectMonitor;
	private JButton gerarCertificados;

	public JPanel getGerenteListarAtividadeView() {
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

		jbtnPesquisar.setBounds(530, 20, 117, 26);

		table = new JTable();
		table.setFont(new Font("HanziPen TC", Font.BOLD, 15));
		table.setBounds(5, 60, 970, 500);
		table.setBackground(UIManager.getColor("Button.background"));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane jScrollPane = new JScrollPane(table);
		jScrollPane.setBounds(5, 60, 970, 450);

		excluirAtividade = new JButton("Excluir");
		alterarAtividade = new JButton("Alterar");
		selectMonitor = new JButton("Selecionar Monitor");
		gerarCertificados = new JButton("Gerar Certificados");
		
		excluirAtividade.setBounds(5, 550, 170, 25);
		alterarAtividade.setBounds(190, 550, 170, 25);
		selectMonitor.setBounds(375, 550, 170, 25);
		gerarCertificados.setBounds(560, 550, 170, 25);
		painel.add(jScrollPane);
		painel.add(jtfPesquisar);
		painel.add(gerarCertificados);
		painel.add(jbtnPesquisar);
		painel.add(excluirAtividade);
		painel.add(alterarAtividade);
		painel.add(selectMonitor);
		painel.setBackground(new Color(240, 240, 240));

		return painel;
	}

	public JButton getSelectMonitor() {
		return selectMonitor;
	}

	public void setSelectMonitor(JButton selectMonitor) {
		this.selectMonitor = selectMonitor;
	}

	public JButton getAlterarAtividade() {
		return alterarAtividade;
	}

	public void setAlterarAtividade(JButton alterarAtividade) {
		this.alterarAtividade = alterarAtividade;
	}

	public JButton getExcluirAtividade() {
		return excluirAtividade;
	}

	public void setExcluirAtividade(JButton excluirAtividade) {
		this.excluirAtividade = excluirAtividade;
	}

	public JButton getAddAtividade() {
		return addAtividade;
	}

	public void setAddAtividade(JButton addAtividade) {
		this.addAtividade = addAtividade;
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
	public JButton getGerarCertificados() {
		return gerarCertificados;
	}

	public void setGerarCertificados(JButton gerarCertificados) {
		this.gerarCertificados = gerarCertificados;
	}
	
}