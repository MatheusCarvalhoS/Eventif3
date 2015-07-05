package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.MonitorListarAtividadeView;

public class MonitorListarAtividadeControl {
	private MonitorListarAtividadeView listarAtividade;
	private JPanel painel;
	private Action alterar;
	private Action excluir;
	private Action addAtividade;
	private AppView appView;
	private int idEvento;
	
	public JPanel getMonitorListarAtividadeControl(AppView app, int idEvento) {
		this.appView = app;
		this.idEvento = idEvento;

		listarAtividade = new MonitorListarAtividadeView();
		painel = listarAtividade.getMonitorListarAtividadeView();
		
		preencheTabela();
		return painel;
	}
	

	public void preencheTabela() {
		Vector<Vector<String>> listaAtividade = new AtividadeDAO().buscaAtividades(idEvento);
		preencheTabela(listaAtividade);
	}

	public void preencheTabela(Vector<Vector<String>> listaAtividade) {

		listarAtividade.getTable().clearSelection();
		listarAtividade.getTable().removeAll();

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
		listarAtividade.getTable().setModel(model);
		listarAtividade.getTable().getColumn("id").setMaxWidth(25);
	}
}
