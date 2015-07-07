package br.edu.ifg.tads.mtp.eventif.control;

import br.edu.ifg.tads.mtp.eventif.dao.MonitorDAO;
import br.edu.ifg.tads.mtp.eventif.model.MonitorModel;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAlunosView;

public class GerenteFazerMonitorControl {
	
	public void getGerenteFazerMonitorControl(int idAluno, int idAtividade){ 
		new MonitorDAO().adicionaMonitor(idAluno, idAtividade);
	}
}
