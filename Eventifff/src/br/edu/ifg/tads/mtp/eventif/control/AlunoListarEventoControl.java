package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.view.AlunoListarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.AppView;

public class AlunoListarEventoControl {
	private AlunoListarEventoView listarEvento;
	private AlunoModel aluno;
	private JPanel painel;
	private JButton alterar;
	private JButton excluir;
	private JButton addAtividade;
	private AppView appView;

	public JPanel getAlunoListarEventoControl(AppView app, int idAluno) {
		aluno = new AlunoModel();
		aluno.setIdAluno(idAluno);
		this.appView = app;

		listarEvento = new AlunoListarEventoView();
		painel = listarEvento.getAlunoListarEventoView();
		
		preencheTabela();
		adicionaEventos();
		//adicionaListenner();
		return painel;
	}

	public void preencheTabela() {
		Vector<Vector<String>> listaEventos = new EventoDAO().buscaEventosAluno(aluno.getIdAluno());
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
		colunas.add("Situação");

		DefaultTableModel model = new DefaultTableModel(listaEvento, colunas);
		listarEvento.getTable().setModel(model);
		listarEvento.getTable().getColumn("id").setMaxWidth(25);
	}

	public void adicionaEventos() {
		listarEvento.getInscrever().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idEvento = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				String situacao = listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 9).toString();
				
				if(!situacao.equals("Inscrito") & new AlunoDAO().inscricaoEvento(aluno.getIdAluno(), idEvento)){
					JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso no Evento!");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(
							new AlunoListarEventoControl().getAlunoListarEventoControl(appView, aluno.getIdAluno()));
					appView.getPainelDireita().repaint();
				}else if(situacao.equals("Inscrito")){
					JOptionPane.showMessageDialog(null, "Aluno não pode ser recadastrado no Evento!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado no Evento!");
				}
			}
		});

		listarEvento.getSairEvento().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idEvento = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				String situacao = listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 9).toString();
				
				if(situacao.equals("Inscrito") & new AlunoDAO().sairDoEvento(aluno.getIdAluno(), idEvento)){
					JOptionPane.showMessageDialog(null, "O aluno foi desvinculado do Evento!");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(
							new AlunoListarEventoControl().getAlunoListarEventoControl(appView, aluno.getIdAluno()));
					appView.getPainelDireita().repaint();
				}else if(!situacao.equals("Inscrito")){
					JOptionPane.showMessageDialog(null, "O aluno não está cadastrado no Evento!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação!");
				}
			}
		});
		
		listarEvento.getListarAtividade().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idEvento = Integer.parseInt(listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 0).toString());
				
				String situacao = listarEvento.getTable()
						.getValueAt(listarEvento.getTable().getSelectedRow(), 9).toString();
				
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(
						new AlunoListarAtividadeControl()
								.getAlunoListarAtividadeControl(appView,idEvento,aluno.getIdAluno(), situacao));
				appView.getPainelDireita().repaint();
			}
		});
		
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
	}

	public AlunoListarEventoView getListarEvento() {
		return listarEvento;
	}

	public void setListarEvento(AlunoListarEventoView listarEvento) {
		this.listarEvento = listarEvento;
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
}
