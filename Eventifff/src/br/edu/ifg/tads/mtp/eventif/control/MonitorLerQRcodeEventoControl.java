package br.edu.ifg.tads.mtp.eventif.control;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.util.LerQRcode;
import br.edu.ifg.tads.mtp.eventif.view.AppView;


public class MonitorLerQRcodeEventoControl {
	
	public void getMonitorLerQRcodeControl(JDesktopPane appView, int id) throws Exception {
		JInternalFrame inter = new LerQRcode().getLerQRcode("evento", id);
		inter.setBounds(0, 60, 640, 70);
		appView.add(inter);
		appView.moveToFront(inter);
		
	}
}