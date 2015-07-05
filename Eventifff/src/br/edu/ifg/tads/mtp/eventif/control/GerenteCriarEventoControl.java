package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.dao.EnderecoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposCriarEvento;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;

public class GerenteCriarEventoControl {
	private GerenteCriarEventoView criarEvento;
	private JPanel painel;

	public JPanel getGerenteCriarEventoControl() {
		criarEvento = new GerenteCriarEventoView();
		painel = criarEvento.getGerenteCriarEventoView();
		adicionaEventosCriar();
		return painel;
	}

	public JPanel getGerenteAlterarEventoControl(int id) {
		EventoModel evento = new EventoDAO().buscaEventos(id);
		
		criarEvento = new GerenteCriarEventoView();
		painel = criarEvento.getGerenteCriarEventoView();
		//adicionaEventosAlterar();
		criarEvento.getBtCriar().setText("Alterar");
		criarEvento.getTxNome().setText(evento.getNome());
		criarEvento.getTxDescricao().setText(evento.getDescricao());
		return painel;
	}
	

	
	public void adicionaEventosCriar() {
		criarEvento.getBtCriar().addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (new VerificaCamposCriarEvento()
						.getVerificaCamposCriarEvento(criarEvento)) {
					EnderecoModel endereco = new EnderecoModel();
					endereco.setCep(criarEvento.getTxCep().getText());
					endereco.setUf(criarEvento.getTxUf().getText());
					endereco.setCidade(criarEvento.getTxCidade().getText());
					endereco.setBairro(criarEvento.getTxBairro().getText());
					endereco.setNumero(criarEvento.getTxNumero().getText());

					EventoModel evento = new EventoModel();
					evento.setNome(criarEvento.getTxNome().getText());

					Calendar dataInicio = Calendar.getInstance();
					dataInicio.setTime(new Date(criarEvento.getTxDataInicio()
							.getText()));

					evento.setDataInicio(dataInicio);
					
					Calendar dataFim = Calendar.getInstance();
					dataFim.setTime(new Date(criarEvento
							.getTxDataEncerramento().getText()));

					evento.setDataFim(dataFim);
					
					evento.setOrganizador(criarEvento.getTxOrganizador().getText());
					evento.setTelefone(criarEvento.getTxTelefone().getText());
					evento.setLocal(criarEvento.getTxLocal().getText());
					evento.setDescricao(criarEvento.getTxDescricao().getText());

					
					if(new EnderecoDAO().adiconaEndereco(endereco)){
						evento.setIdEndereco(new EnderecoDAO().retornaMaxIdEndereco());
						if(new EventoDAO().adicionaEvento(evento)){
							JOptionPane.showMessageDialog(null, "Evento Criado com sucesso! ");				
						}
					}
					
					JOptionPane.showMessageDialog(null, "Evento criado");
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique o preenchimento dos campos");
				}
			}
		});
	}
}
