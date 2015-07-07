package br.edu.ifg.tads.mtp.eventif.control;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.dao.PresencaDAO;

public class PresencaControl {
	public void getLerQrCode(String cpf, String tipo, int id){
		int idAluno = new PresencaDAO().getIdPessoaCPF(cpf, id);
		if(idAluno!=0){
			if(tipo.equals("evento")){
				if(new PresencaDAO().verificaChekinChekoutEvento(idAluno, id).equals("chekin")){
					//A função acima deverá verificar se o aluno está inscrito no evento
					new PresencaDAO().chekinEvento(idAluno, id);
				}else if(new PresencaDAO().verificaChekinChekoutEvento(idAluno, id).equals("checkout")){
					new PresencaDAO().checkoutEvento(idAluno, id);
				}else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado no evento");
				}
			}else if(tipo.equals("atividade")){
				/*
				if(new PresencaDAO().verificaChekinChekoutEvento(idAluno, id).equals("chekin")){
					new PresencaDAO().chekinAtividade(idAluno, id);
				}else if(PresencaDAO().verificaChekinChekoutAtividade(idAluno).equals("checkout")){
					new PresencaDAO().chekoutAtividade(idAluno, id);
				}else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado na atividade");
				}
				*/
				JOptionPane.showMessageDialog(null, "Tem que fazer funcionar ainda!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Este aluno não está cadastrada no sistema!");
		}
	}
}
