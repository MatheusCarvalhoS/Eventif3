package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EnderecoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.AtividadeModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposCriarAtividade;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;

public class GerenteCriarAtividadeControl {
	private GerenteCriarAtividadeView criarAtividade;
	private JPanel painel;
	private int idEvento;
	private int idAtividade;

	public JPanel getGerenteCriarAtividadeControl(int idEvento) {
		this.idEvento = idEvento;
		criarAtividade = new GerenteCriarAtividadeView();
		painel = criarAtividade.getGerenteCriarAtividadeView();
		adicionaEventos();
		return painel;
	}
	
	public JPanel getGerenteAlterarAtividadeControl(int id) {
		this.idAtividade = id;
		AtividadeModel atividade = new AtividadeDAO().buscaAtividade(id);
		
		criarAtividade = new GerenteCriarAtividadeView();
		painel = criarAtividade.getGerenteCriarAtividadeView();
		
		criarAtividade.getBtCriarAtividade().setText("Alterar");
		
		criarAtividade.getTxNome().setText(atividade.getNomeAtividade());
		criarAtividade.getTxDescricaoAtividade().setText(atividade.getDescricaoAtividade());
		criarAtividade.getTxTipoAtividade().setText(atividade.getTipoAtividade());
		criarAtividade.getTxCargaHoraria().setText(atividade.getCargaHoraria());
		criarAtividade.getTxNumeroVagas().setText(""+atividade.getNumeroVagas());
		criarAtividade.getTxPalestrante().setText(atividade.getPalestrante());
	
		String string = atividade.getData();///////// formatando manualmente a data;
		string=string+" ";
		String ano = string.substring(0, 4);
		String mes = string.substring(5, 7);
		String dia = string.substring(8, 10);
		string = (dia+"/"+mes+"/"+ano);
		criarAtividade.getTxData().setValue(string);
	
		criarAtividade.getTxHoraInicio().setValue(atividade.getHoraInicio());	
		criarAtividade.getTxHoraFim().setValue(atividade.getHoraEncerramento());	
		
		adicionaEventosAlterar();
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
					atividade.setData(criarAtividade.getTxData().getText());
					atividade.setNumeroVagas(Integer.parseInt(criarAtividade.getTxNumeroVagas().getText()));
					atividade.setTipoAtividade(criarAtividade.getTxTipoAtividade().getText());
					atividade.setPalestrante(criarAtividade.getTxPalestrante().getText());
					
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
	
	public void adicionaEventosAlterar() {
		criarAtividade.getBtCriarAtividade().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				if (new VerificaCamposCriarAtividade()
						.getVerificaCamposCriarAtividade(criarAtividade)) {

					AtividadeModel atividade = new AtividadeModel();
					atividade.setIdAtividade(idAtividade);
					atividade.setIdEvento(idEvento);
					atividade.setNomeAtividade(criarAtividade.getTxNome().getText());
					atividade.setCargaHoraria(criarAtividade.getTxCargaHoraria().getText());
					atividade.setDescricaoAtividade(criarAtividade.getTxDescricaoAtividade().getText());
					atividade.setHoraEncerramento(criarAtividade.getTxHoraFim().getText());
					atividade.setHoraInicio(criarAtividade.getTxHoraInicio().getText());
					atividade.setData(criarAtividade.getTxData().getText());
					atividade.setNumeroVagas(Integer.parseInt(criarAtividade.getTxNumeroVagas().getText()));
					atividade.setTipoAtividade(criarAtividade.getTxTipoAtividade().getText());
					atividade.setPalestrante(criarAtividade.getTxPalestrante().getText());
					
					try {
						if(new AtividadeDAO().alterarAtividade(atividade)){
							JOptionPane.showMessageDialog(null, "Atividade Alterada! ");
						}else{
							JOptionPane.showMessageDialog(null, "Atividade não Foi Alterada! ");
						}
					} catch (HeadlessException | ParseException e1) {
						JOptionPane.showMessageDialog(null, "Erro de Operação! "+ e1.getMessage());
						e1.printStackTrace();
					}
	
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique o preenchimento dos campos");
				}
			}
		});
	}
	
}
