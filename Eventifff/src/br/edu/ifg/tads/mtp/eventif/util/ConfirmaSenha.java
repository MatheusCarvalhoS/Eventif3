package br.edu.ifg.tads.mtp.eventif.util;

public class ConfirmaSenha {
	public boolean confirmaSenha(String senha, String confirmaSenha){
		if(senha.equals(confirmaSenha)){
			return true;
		}else{
			return false;
		}
	}
}