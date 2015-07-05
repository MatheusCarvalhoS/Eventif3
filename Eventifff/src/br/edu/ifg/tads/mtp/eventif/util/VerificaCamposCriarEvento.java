package br.edu.ifg.tads.mtp.eventif.util;

import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;

public class VerificaCamposCriarEvento {
	public boolean getVerificaCamposCriarEvento(GerenteCriarEventoView eventoView){
		boolean verify=true;
		if(eventoView.getTxNome().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxDataInicio().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxDataEncerramento().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxOrganizador().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxTelefone().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxEmail().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxLocal().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxCep().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxNumero().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxUf().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxBairro().getText().isEmpty()){
			verify=false;
		}else if(eventoView.getTxCidade().getText().isEmpty()){
			verify=false;
		}
		return verify;
	}
}
