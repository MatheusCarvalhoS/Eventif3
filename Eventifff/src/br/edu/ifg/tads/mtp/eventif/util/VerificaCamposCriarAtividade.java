package br.edu.ifg.tads.mtp.eventif.util;

import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarAtividadeView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteCriarEventoView;

public class VerificaCamposCriarAtividade {
	public boolean getVerificaCamposCriarAtividade(GerenteCriarAtividadeView atividadeView){
		boolean verify=true;
		if(atividadeView.getNome().getText().isEmpty()){
			verify=false;
		}else if(atividadeView.getTxCargaHoraria().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxData().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxDescricaoAtividade().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxHoraFim().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxHoraInicio().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxNumeroVagas().getText().isEmpty()){
			verify = false;
		}else if(atividadeView.getTxTipoAtividade().getText().isEmpty()){
			verify = false;
		}
		return verify;
	}
}
