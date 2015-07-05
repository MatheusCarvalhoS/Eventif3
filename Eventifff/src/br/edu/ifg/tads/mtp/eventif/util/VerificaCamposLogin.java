package br.edu.ifg.tads.mtp.eventif.util;

import br.edu.ifg.tads.mtp.eventif.view.LoginView;

public class VerificaCamposLogin {
	private LoginView login;
	public boolean getVerificaCamposCriarLogin(LoginView login){
		boolean verify = true;
		this.login = login;
		
		if(login.getTxCpf().getText().isEmpty()){
			verify = false;
		}else if(login.getTfSenha().getText().isEmpty()){
			verify = false;
		}
		return verify;
	}
}
