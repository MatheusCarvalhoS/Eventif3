package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;
import br.edu.ifg.tads.mtp.eventif.util.ButtonColumn;
import br.edu.ifg.tads.mtp.eventif.util.Dispatcher;
import br.edu.ifg.tads.mtp.eventif.util.Listener;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposCriarEvento;
import br.edu.ifg.tads.mtp.eventif.view.AlunoListarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarEventoView;

public class AlunoListarAtividadeControl {
	private AlunoListarAtividadeView listarAtividade;
	private AlunoModel aluno;
	private JPanel painel;
	private Action alterar;
	private Action excluir;
	private Action addAtividade;
	private AppView appView;
	private int idAtividade;
	private int idEvento;

	public JPanel getAlunoListarAtividadeControl(AppView app, int idEvento, int idAluno) {
		this.appView = app;
		aluno = new AlunoModel();
		aluno.setIdAluno(idAluno);
		this.idEvento = idEvento;

		listarAtividade = new AlunoListarAtividadeView();
		painel = listarAtividade.getAlunoListarAtividadeView();
		adicionaEventos();
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

	public void adicionaEventos() {
		
		listarAtividade.getInscrever().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				idAtividade = Integer.parseInt(listarAtividade.getTable()
						.getValueAt(listarAtividade.getTable().getSelectedRow(), 0).toString());
				
				if(new AlunoDAO().inscricaoAtividade(aluno.getIdAluno(), idAtividade)){
					JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso na Atividade!");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(
							new AlunoListarAtividadeControl()
									.getAlunoListarAtividadeControl(appView, idEvento, aluno.getIdAluno()));
					appView.getPainelDireita().repaint();
				}else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado na Atividade!");
				}
			}
		});
		
		listarAtividade.getJbtnPesquisar().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("pesquisei");
				listarAtividade.getTable().removeAll();
				listarAtividade.getTable().setModel(new PesquisarEventoOuAtividadeControl().getPesquisarAtividadeControl(listarAtividade.getJtfPesquisar().getText(), idAtividade));
				
				listarAtividade.getTable().getColumn("id").setMaxWidth(25);
				listarAtividade.getTable().repaint();
			}
		});
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
