package br.edu.ifg.tads.mtp.eventif.control;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;

public class PesquisarEventoOuAtividadeControl {
	private String nome;
	
	public DefaultTableModel getPesquisarEventoControl(String nome) {
		this.nome = nome;
		
		Vector<Vector<String>> listaEvento = new EventoDAO().pesquisarEvento(nome);
		

		Vector<String> colunas = new Vector<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("descrição");
		colunas.add("horaInicio");
		colunas.add("horaFim");
		colunas.add("Palestrante");
		colunas.add("Tipo");
		colunas.add("Carga Horaria");
		colunas.add("Numero de Vagas");

		DefaultTableModel model = new DefaultTableModel(listaEvento, colunas);
	
		return model;
	}
	
	public DefaultTableModel getPesquisarAtividadeControl(String nome, int idEvento) {
		this.nome = nome;
		
		Vector<Vector<String>> listaAtividade = new AtividadeDAO().pesquisaAtividade(nome, idEvento);
		
		Vector<String> colunas = new Vector<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("descrição");
		colunas.add("horaInicio");
		colunas.add("horaFim");
		colunas.add("Palestrante");
		colunas.add("Tipo");
		colunas.add("Carga Horaria");
		colunas.add("Numero de Vagas");
		colunas.add("Data");

		DefaultTableModel model = new DefaultTableModel(listaAtividade, colunas);
		System.out.println("retornei");
		return model;
	}
	
}
