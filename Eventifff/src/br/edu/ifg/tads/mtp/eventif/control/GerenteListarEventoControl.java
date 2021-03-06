package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarEventoView;

public class GerenteListarEventoControl {
	private GerenteListarEventoView listarEvento;
	private JPanel painel;
	private JButton alterar;
	private JButton excluir;
	private JButton addAtividade;
	private AppView appView;

	public JPanel getGerenteListarEventoControl(AppView app) {
		this.appView = app;

		listarEvento = new GerenteListarEventoView();
		painel = listarEvento.getGerenteListarEventoView();
		
		preencheTabela();
		adicionaEventos();
		return painel;
	}

	public void preencheTabela() {
		Vector<Vector<String>> listaEventos = new EventoDAO().buscaEventos();
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
		listarEvento.getAddAtividade().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(
						new GerenteCriarAtividadeControl()
								.getGerenteCriarAtividadeControl(id));
				appView.getPainelDireita().repaint();
			}
		});

		listarEvento.getAlterarEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(
						new GerenteCriarEventoControl()
								.getGerenteAlterarEventoControl(id));
				appView.getPainelDireita().repaint();
			}
		});
		
		listarEvento.getListarAtividade().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(
						new GerenteListarAtividadeControl()
								.getGerenteListarAtividadeControl(appView,id));
				appView.getPainelDireita().repaint();
			}
		});
		
		listarEvento.getJbtnPesquisar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				listarEvento.getTable().removeAll();
				listarEvento.getTable().setModel(new PesquisarEventoOuAtividadeControl().getPesquisarEventoControl(listarEvento.getJtfPesquisar().getText()));

				listarEvento.getTable().getColumn("id").setMaxWidth(25);
	
				listarEvento.getTable().repaint();
			}
		});
		listarEvento.getExcluirEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Excluir o Evento?")==0){
					int id = Integer.parseInt(listarEvento.getTable()
							.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
					if(new EventoDAO().excluirEvento(id)){
						preencheTabela();
					}else{
						JOptionPane.showMessageDialog(null, "Evento não pode ser deletado! ");
					}
				}
			}
		});
	}

	public GerenteListarEventoView getListarEvento() {
		return listarEvento;
	}

	public void setListarEvento(GerenteListarEventoView listarEvento) {
		this.listarEvento = listarEvento;
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JButton getAlterar() {
		return alterar;
	}

	public void setAlterar(JButton alterar) {
		this.alterar = alterar;
	}

	public JButton getExcluir() {
		return excluir;
	}

	public void setExcluir(JButton excluir) {
		this.excluir = excluir;
	}

	public JButton getAddAtividade() {
		return addAtividade;
	}

	public void setAddAtividade(JButton addAtividade) {
		this.addAtividade = addAtividade;
	}

	public AppView getAppView() {
		return appView;
	}

	public void setAppView(AppView appView) {
		this.appView = appView;
	}
	

}
