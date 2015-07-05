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
	
	public JPanel getMenuPrincipalMonitorControl(AppView app){
		this.appView = app;
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
				appView.getPainelDireita().add(new MonitorListarEventoControl().getMonitorListarEventoControl(appView));
				appView.repaint();
			}
		});
		
		menuMonitor.getLerQRcode().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {             //somente abre a tela de leitura
				try {
					new MonitorLerQRcodeControl().getMonitorLerQRcodeControl(appView.getDesk());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
}
