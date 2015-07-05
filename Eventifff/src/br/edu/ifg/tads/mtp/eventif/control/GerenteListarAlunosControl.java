package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposCriarEvento;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAlunosView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarEventoView;

public class GerenteListarAlunosControl {
	private GerenteListarAlunosView listarAluno;
	private JPanel painel;
	private int idAtividade;
	
	public JPanel getGerenteListarAlunosControl() throws SQLException{
		listarAluno = new GerenteListarAlunosView();
		painel=listarAluno.getGerenteListarAlunosView();
		preencheTabela();
		adicionaEventos();
		return painel;
	}
	
	public JPanel getGerenteListarAlunosControl(int idAtividade) throws SQLException{
		this.idAtividade = idAtividade;
		listarAluno = new GerenteListarAlunosView();
		painel=listarAluno.getGerenteFazerMonitorView();
		preencheTabela();
		adicionaEventos();
		return painel;
	}
	
	public void preencheTabela() throws SQLException {
		Vector<Vector<String>> listaAlunos = new AlunoDAO().buscaAlunos();
		preencheTabelaAluno(listaAlunos);
	}
	
	
	public void adicionaEventos(){
		
		listarAluno.getJbtnPesquisar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Pesquisar aqui"); 
			}
		});
		
		listarAluno.getFazerMonitor().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(listarAluno.getTable()
						.getValueAt(listarAluno.getTable().getSelectedRow(), 0).toString());
				
				new GerenteFazerMonitorControl().getGerenteFazerMonitorControl(id, idAtividade);
			}
		});
	}
	
	public void preencheTabelaAluno(Vector<Vector<String>> listaAluno) {

		listarAluno.getTable().clearSelection();
		listarAluno.getTable().removeAll();

		Vector<String> colunas = new Vector<String>();
		colunas.add("idAluno");
		colunas.add("idPessoa");
		colunas.add("Nome");
		colunas.add("RG");
		colunas.add("CPF");
		

		DefaultTableModel model = new DefaultTableModel(listaAluno, colunas);
		listarAluno.getTable().setModel(model);
	}
	
}
