package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.MenuPrincipalMonitorView;

public class MenuPrincipalMonitorControl {
	private JPanel menu;
	private JButton lerQRcode;
	private MenuPrincipalMonitorView menuMonitor;
	private AppView appView;
	private int idMonitor;
	public JPanel getMenuPrincipalMonitorControl(AppView app, int idMonitor){
		this.appView = app;
		this.idMonitor=idMonitor;
		menuMonitor = new MenuPrincipalMonitorView();
		menu = menuMonitor.getMenuPrincipalMonitorView();
		adicionaEventos();
		return menu;
	}
	
	public void adicionaEventos(){

		menuMonitor.getBtListarEventos().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				appView.getPainelDireita().removeAll();
				appView.getPainelDireita().add(new MonitorListarEventoControl().getMonitorListarEventoControl(appView, idMonitor));
				appView.repaint();
			}
		});
		
	}
	
	
}
