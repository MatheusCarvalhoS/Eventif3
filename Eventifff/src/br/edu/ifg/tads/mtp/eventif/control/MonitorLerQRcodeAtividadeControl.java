package br.edu.ifg.tads.mtp.eventif.control;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import br.edu.ifg.tads.mtp.eventif.util.LerQRcode;

public class MonitorLerQRcodeAtividadeControl {
	
	public void getMonitorLerQRcodeControl(JDesktopPane appView, int id) throws Exception {
		JInternalFrame inter = new LerQRcode().getLerQRcode("atividade", id);
		inter.setBounds(0, 60, 640, 70);
		appView.add(inter);
		appView.moveToFront(inter);
	}
}
