package br.edu.ifg.tads.mtp.eventif.control;

import br.edu.ifg.tads.mtp.eventif.dao.MonitorDAO;

public class GerenteFazerMonitorControl {
	
	public void getGerenteFazerMonitorControl(int idAluno, int idAtividade){ 
		new MonitorDAO().adicionaMonitor(idAluno, idAtividade);
	}
}
