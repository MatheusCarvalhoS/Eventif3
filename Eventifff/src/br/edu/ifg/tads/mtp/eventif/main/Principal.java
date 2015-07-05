package br.edu.ifg.tads.mtp.eventif.main;

import java.awt.Component;
import java.awt.Label;
import java.sql.Connection;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.LoginView;
import br.edu.ifg.tads.mtp.eventif.view.MenuPrincipalView;
import br.edu.ifg.tads.mtp.eventif.view.MonitorListarEventoView;
import br.edu.ifg.tads.mtp.eventif.view.PessoaInscricaoSistemaView;
import br.edu.ifg.tads.mtp.eventif.control.*;

public class Principal {
	public static void main(String[] args) {
		JDesktopPane desk =  new JDesktopPane();
		
		AppView app = new AppView(desk);
		ConnectionFactory con = new ConnectionFactory();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLayout(null);
		
		//app.getPainelDireita().add(new GerenteListarAtividadeView().getGerenteListarAtividadeView());
		app.getPainelDireita().add(new LoginControl().getLoginControl(app));
		//app.getPainelDireita().add(new MonitorListarEventoControl().getMonitorListarEventoControl(app));
		app.getPainelEsquerda().add(new MenuPrincipalControl().getMenuPrincipalControl(app));
		//app.getPainelEsquerda().add(new MenuPrincipalGerenteControl().getMenuPrincipalGerente(app));
		//app.getPainelEsquerda().add(new MenuPrincipalMonitorControl().getMenuPrincipalMonitorControl(app));
		
		app.setVisible(true);
		con.getConnection();
	}
}
