package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EnderecoDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.MenuPrincipalAlunoView;

public class MenuPrincipalAlunoControl {
	private JPanel menu;
	private MenuPrincipalAlunoView menuAluno;
	private AppView appView;
	private int idAluno;
	private EnderecoModel endereco;
	private AlunoModel aluno;
	public JPanel getMenuPrincipalAlunoControl(AppView app, int idAluno){
		this.appView = app;
		this.idAluno=idAluno;
		menuAluno = new MenuPrincipalAlunoView();
		menu = menuAluno.getMenuPrincipalAlunoView();
		adicionaEventos();
		return menu;
	}
	
	public void adicionaEventos(){

		menuAluno.getBtListarEventos().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(new AlunoListarEventoControl().getAlunoListarEventoControl(appView, idAluno));
				appView.repaint();
			}
		});
		
		menuAluno.getMinhaConta().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				aluno = new AlunoDAO().getAlunoMinhaConta(idAluno);
				//endereco = new EnderecoDAO().getEnderecoMinhaConta(aluno.getIdEndereco());
				
			}
		});
		
	}
}
