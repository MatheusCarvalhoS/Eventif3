package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.MonitorListarEventoView;

public class MonitorListarEventoControl {
	private MonitorListarEventoView listarEvento;
	private JPanel painel;
	private Action alterar;
	private Action excluir;
	private Action addAtividade;
	private AppView appView;
	private int idMonitor;
	
	public JPanel getMonitorListarEventoControl(AppView app, int idMonitor) {
		this.appView = app;
		this.idMonitor=idMonitor;
		listarEvento = new MonitorListarEventoView();
		painel = listarEvento.getMonitorListarEventoView();
		
		preencheTabela();
		adicionaEventos();
		return painel;
	}

	public void preencheTabela() {
		Vector<Vector<String>> listaEventos = new EventoDAO().buscaEventosMonitor(idMonitor);
		preencheTabela(listaEventos);
	}

	public void preencheTabela(Vector<Vector<String>> listaEvento) {

		listarEvento.getTable().clearSelection();
		listarEvento.getTable().removeAll();

		Vector<String> colunas = new Vector<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("dataInicio");
		colunas.add("dataFim");
		colunas.add("email");
		colunas.add("organizador");
		colunas.add("telefoneContato");
		colunas.add("localEvento");
		colunas.add("idEndereco");
		

		DefaultTableModel model = new DefaultTableModel(listaEvento, colunas);
		listarEvento.getTable().setModel(model);
		listarEvento.getTable().getColumn("id").setMaxWidth(25);
	}

	public void adicionaEventos() {
		
		listarEvento.getJbtnPesquisar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("pesquisei evento");
				listarEvento.getTable().removeAll();
				listarEvento.getTable().setModel(new PesquisarEventoOuAtividadeControl().getPesquisarEventoControl(listarEvento.getJtfPesquisar().getText()));

				listarEvento.getTable().getColumn("id").setMaxWidth(25);
	
				listarEvento.getTable().repaint();
			}
		});
		
		listarEvento.getBtListarAtividades().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				int id = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(new MonitorListarAtividadeControl().getMonitorListarAtividadeControl(appView, id));
				appView.getPainelDireita().repaint();
			}
		});
		
		listarEvento.getLerQRcode().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {   
				int id = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				try {
					new MonitorLerQRcodeEventoControl().getMonitorLerQRcodeControl(appView.getDesk(), id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public MonitorListarEventoView getListarEvento() {
		return listarEvento;
	}

	public void setListarEvento(MonitorListarEventoView listarEvento) {
		this.listarEvento = listarEvento;
	}

	public Action getAlterar() {
		return alterar;
	}

	public void setAlterar(Action alterar) {
		this.alterar = alterar;
	}

	public Action getExcluir() {
		return excluir;
	}

	public void setExcluir(Action excluir) {
		this.excluir = excluir;
	}

	public Action getAddAtividade() {
		return addAtividade;
	}

	public void setAddAtividade(Action addAtividade) {
		this.addAtividade = addAtividade;
	}

}

