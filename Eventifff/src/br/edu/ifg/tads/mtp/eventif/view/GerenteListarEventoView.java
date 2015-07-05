package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.control.GVizualizarEventoControl;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;

public class GerenteListarEventoView {
	private JPanel painel;
	private JTextField jtfPesquisar;
	private JTable table;
	private JButton jbtnPesquisar;
	private JButton alterarEvento;
	private JButton excluirEvento;
	private JButton addAtividade;
	private JButton listarAtividade;

	public JPanel getGerenteListarEventoView() {
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

		addAtividade = new JButton("AddAtividade");
		excluirEvento = new JButton("Excluir");
		alterarEvento = new JButton("Alterar");
		listarAtividade = new JButton("Atividades");

		addAtividade.setBounds(5, 550, 170, 25);
		excluirEvento.setBounds(190, 550, 170, 25);
		alterarEvento.setBounds(375, 550, 170, 25);
		listarAtividade.setBounds(560, 550, 170, 25);

		painel.add(addAtividade);
		painel.add(excluirEvento);
		painel.add(alterarEvento);
		painel.add(listarAtividade);
		painel.add(jScrollPane);
		painel.add(jtfPesquisar);
		painel.add(jbtnPesquisar);
		painel.setBackground(new Color(240, 240, 240));

		return painel;
	}

	public JButton getAlterarEvento() {
		return alterarEvento;
	}

	public void setAlterarEvento(JButton alterarEvento) {
		this.alterarEvento = alterarEvento;
	}

	public JButton getExcluirEvento() {
		return excluirEvento;
	}

	public void setExcluirEvento(JButton excluirEvento) {
		this.excluirEvento = excluirEvento;
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

	public JButton getListarAtividade() {
		return listarAtividade;
	}

	public void setListarAtividade(JButton listarAtividade) {
		this.listarAtividade = listarAtividade;
	}
	
	
}