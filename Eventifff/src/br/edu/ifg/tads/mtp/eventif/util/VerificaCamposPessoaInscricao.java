package br.edu.ifg.tads.mtp.eventif.util;

import java.util.InputMismatchException;

import br.edu.ifg.tads.mtp.eventif.view.PessoaInscricaoSistemaView;

public class VerificaCamposPessoaInscricao {
	public boolean getVerificaCamposPessoaInscricao(
			PessoaInscricaoSistemaView inscircaoView) {
		boolean verify = true;
		if (inscircaoView.getTxNome().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxCpf().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxRg().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxCep().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxComplemento().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxNumero().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxUf().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxBairro().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxCidade().getText().isEmpty()) {
			verify = false;
		} else if (inscircaoView.getTxSenha().getText().isEmpty()) {
			verify = false;
		}
		return verify;
	}
}
