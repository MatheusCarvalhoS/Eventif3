package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.view.AlunoListarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.AppView;

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
	private String situacaoEvento;

	public JPanel getAlunoListarAtividadeControl(AppView app, int idEvento, int idAluno, String situacaoEvento) {
		this.appView = app;
		aluno = new AlunoModel();
		aluno.setIdAluno(idAluno);
		this.idEvento = idEvento;
		this.situacaoEvento=situacaoEvento;

		listarAtividade = new AlunoListarAtividadeView();
		painel = listarAtividade.getAlunoListarAtividadeView();
		adicionaEventos();
		preencheTabela();
		
		return painel;
	}


	public void preencheTabela() {
		Vector<Vector<String>> listaAtividade = new AtividadeDAO().buscaAtividadesAluno(idEvento, aluno.getIdAluno());
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
		colunas.add("Situação");

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
				
				String situacao = listarAtividade.getTable()
						.getValueAt(listarAtividade.getTable().getSelectedRow(), 10).toString();
				
				if(situacaoEvento.equals("Inscrito") && !situacao.equals("Inscrito") && new AlunoDAO().inscricaoAtividade(aluno.getIdAluno(), idAtividade)){
					JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso na Atividade!");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(
							new AlunoListarAtividadeControl().getAlunoListarAtividadeControl(appView, idEvento, aluno.getIdAluno(), situacaoEvento));
					appView.getPainelDireita().repaint();
				}else if(!situacaoEvento.equals("Inscrito")){
					JOptionPane.showMessageDialog(null, "Aluno não está inscrito no Evento!");
				}else if(situacao.equals("Inscrito")){
					JOptionPane.showMessageDialog(null, "Aluno não pode ser recadastrado na Atividade!");
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
		
		listarAtividade.getSairAtividade().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int idAtividade = Integer.parseInt(listarAtividade.getTable()
						.getValueAt(listarAtividade.getTable().getSelectedRow(), 0).toString());
				
				String situacao = listarAtividade.getTable()
						.getValueAt(listarAtividade.getTable().getSelectedRow(), 10).toString();
				
				if(situacao.equals("Inscrito") && new AlunoDAO().sairDeAtividade(aluno.getIdAluno(), idAtividade)){
					JOptionPane.showMessageDialog(null, "O aluno foi desvinculado da atividade!");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(
							new AlunoListarAtividadeControl().getAlunoListarAtividadeControl(appView, idEvento, aluno.getIdAluno(), situacaoEvento));
					appView.getPainelDireita().repaint();
				}else if(!situacao.equals("Inscrito")){
					JOptionPane.showMessageDialog(null, "O aluno não está cadastrado no Evento!");
				}
				else{
					JOptionPane.showMessageDialog(null, "Não foi possível realizar a operação!");
				}
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
