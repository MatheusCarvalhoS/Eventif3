package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.MenuPrincipalView;

public class MenuPrincipalControl {
	private MenuPrincipalView menu;
	private JPanel painel;
	private AppView appView;
	
	public JPanel getMenuPrincipalControl(AppView app){
		this.appView=app;
		menu = new MenuPrincipalView();
		painel = menu.getMenuPrincipal();
	
		// botao de tela de Increver
		menu.getBtInscricao().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(new PessoaInscricaoSistemaControl().getPessoaInscricaoSistemaControl(appView));
				appView.getPainelDireita().repaint();
			}
		});
		
		// botar de tela de Login
		menu.getBtLogin().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(new LoginControl().getLoginControl(appView));
				appView.getPainelDireita().repaint();
			}
		});
		return painel;
	}

}
