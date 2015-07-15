package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private int idEvento;

	public JPanel getGerenteCriarEventoControl() {
		criarEvento = new GerenteCriarEventoView();
		painel = criarEvento.getGerenteCriarEventoView();
		adicionaEventosCriar();
		return painel;
	}

	public JPanel getGerenteAlterarEventoControl(int id) {
		this.idEvento = id;
		EventoModel evento = new EventoDAO().buscaEvento(id);
		EnderecoModel endereco = new EnderecoDAO().getAlterarEndereco(evento.getIdEndereco());
		
		criarEvento = new GerenteCriarEventoView();
		painel = criarEvento.getGerenteCriarEventoView();
		
		criarEvento.getBtCriar().setText("Alterar");
		
		criarEvento.getTxNome().setText(evento.getNome());
		criarEvento.getTxDescricao().setText(evento.getDescricao());
		criarEvento.getTxEmail().setText(evento.getEmail());
		
		String string = evento.getDataInicio();///////// formatando manualmente a data;
		string=string+" ";
		String ano = string.substring(0, 4);
		String mes = string.substring(5, 7);
		String dia = string.substring(8, 10);
		string = (dia+"/"+mes+"/"+ano);
		criarEvento.getTxDataInicio().setValue(string);
		
		string = evento.getDataFim();///////// formatando manualmente a data;
		string=string+" ";
		ano = string.substring(0, 4);
		mes = string.substring(5, 7);
		dia = string.substring(8, 10);
		string = (dia+"/"+mes+"/"+ano);
	
		criarEvento.getTxDataEncerramento().setValue(string);
		criarEvento.getTxOrganizador().setText(evento.getOrganizador());
		criarEvento.getTxTelefone().setText(evento.getTelefone());
		criarEvento.getTxLocal().setText(evento.getLocal());
		
		criarEvento.getTxCep().setText(endereco.getCep());
		criarEvento.getTxNumero().setText(endereco.getNumero());
		criarEvento.getTxUf().setText(endereco.getUf());
		criarEvento.getTxBairro().setText(endereco.getBairro());
		criarEvento.getTxCidade().setText(endereco.getCidade());
		
		adicionaEventosAlterar();
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

					evento.setDataInicio(criarEvento.getTxDataInicio().getText());
					evento.setDataFim(criarEvento
							.getTxDataEncerramento().getText());
					
					evento.setOrganizador(criarEvento.getTxOrganizador().getText());
					evento.setTelefone(criarEvento.getTxTelefone().getText());
					evento.setLocal(criarEvento.getTxLocal().getText());
					evento.setDescricao(criarEvento.getTxDescricao().getText());
					evento.setEmail(criarEvento.getTxEmail().getText());
					
					
					if(new EnderecoDAO().adiconaEndereco(endereco)){
						evento.setIdEndereco(new EnderecoDAO().retornaMaxIdEndereco());
						try {
							if(new EventoDAO().adicionaEvento(evento)){
								JOptionPane.showMessageDialog(null, "Evento Criado com sucesso! ");				
							}
						} catch (HeadlessException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique o preenchimento dos campos");
				}
			}
		});
	}
	
	public void adicionaEventosAlterar() {
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
					
					evento.setIdEvento(idEvento);

					evento.setDataInicio(criarEvento.getTxDataInicio()
							.getText());

					evento.setDataFim(criarEvento.getTxDataEncerramento().getText());
					
					evento.setOrganizador(criarEvento.getTxOrganizador().getText());
					evento.setTelefone(criarEvento.getTxTelefone().getText());
					evento.setLocal(criarEvento.getTxLocal().getText());
					evento.setDescricao(criarEvento.getTxDescricao().getText());
					evento.setEmail(criarEvento.getTxEmail().getText());
					
					if(new EnderecoDAO().alterarEndereco(endereco)){
						evento.setIdEndereco(evento.getIdEndereco());
						try {
							if(new EventoDAO().alterarEvento(evento)){
								JOptionPane.showMessageDialog(null, "Evento Alterado com sucesso! ");				
							}
						} catch (HeadlessException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Verifique o preenchimento dos campos");
				}
			}
		});
	}
	
	
}
