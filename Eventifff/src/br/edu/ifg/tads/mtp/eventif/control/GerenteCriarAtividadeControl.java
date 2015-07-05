package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.model.AtividadeModel;
import br.edu.ifg.tads.mtp.eventif.util.Dispatcher;
import br.edu.ifg.tads.mtp.eventif.util.Listener;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposCriarAtividade;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarAtividadeView;

public class GerenteCriarAtividadeControl {
	private GerenteCriarAtividadeView criarAtividade;
	private JPanel painel;
	private int idEvento;
	

	public JPanel getGerenteCriarAtividadeControl(int idEvento) {
		this.idEvento = idEvento;
		criarAtividade = new GerenteCriarAtividadeView();
		painel = criarAtividade.getGerenteCriarAtividadeView();
		adicionaEventos();
		return painel;
	}

	public void adicionaEventos() {
		criarAtividade.getBtCriarAtividade().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (new VerificaCamposCriarAtividade()
						.getVerificaCamposCriarAtividade(criarAtividade)) {

					AtividadeModel atividade = new AtividadeModel();
					
					atividade.setIdEvento(idEvento);
					atividade.setNomeAtividade(criarAtividade.getTxNome().getText());
					atividade.setCargaHoraria(criarAtividade.getTxCargaHoraria().getText());
					atividade.setDescricaoAtividade(criarAtividade.getTxDescricaoAtividade().getText());
					atividade.setHoraEncerramento(criarAtividade.getTxHoraFim().getText());
					atividade.setHoraInicio(criarAtividade.getTxHoraInicio().getText());
					
					Calendar data = Calendar.getInstance();
					data.setTime(new Date(criarAtividade.getTxData()
							.getText()));

					atividade.setData(data);
					
					atividade.setNumeroVagas(Integer.parseInt(criarAtividade.getTxNumeroVagas().getText()));
					atividade.setTipoAtividade(criarAtividade.getTxTipoAtividade().getText());
					
					if(new AtividadeDAO().adicionaAtividade(atividade)){
						JOptionPane.showMessageDialog(null, "Atividade criada");
					}else{
						JOptionPane.showMessageDialog(null, "Atividade não Foi criada");
					}
	
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique o preenchimento dos campos");
				}
			}
		});
	}
}
