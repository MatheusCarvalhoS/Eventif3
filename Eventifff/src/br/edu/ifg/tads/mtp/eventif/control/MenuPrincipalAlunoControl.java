package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.MenuPrincipalAlunoView;

public class MenuPrincipalAlunoControl {
	private JPanel menu;
	private MenuPrincipalAlunoView menuAluno;
	private AppView appView;
	private int idAluno;
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
		
	}
	
	
}
