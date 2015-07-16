package br.edu.ifg.tads.mtp.eventif.main;

import java.sql.SQLException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.control.*;

public class Principal {
	public static void main(String[] args) throws JRException, SQLException {	
		JDesktopPane desk =  new JDesktopPane();
		
		AppView app = new AppView(desk);
		ConnectionFactory con = new ConnectionFactory();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLayout(null);
		
		app.getPainelDireita().add(new LoginControl().getLoginControl(app));
		app.getPainelEsquerda().add(new MenuPrincipalControl().getMenuPrincipalControl(app));
		
		app.setVisible(true);
		con.getConnection();
	}
}
