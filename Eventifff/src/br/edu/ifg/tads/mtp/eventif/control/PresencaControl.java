package br.edu.ifg.tads.mtp.eventif.control;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.dao.PresencaDAO;

public class PresencaControl {
	
	public void getLerQrCode(String cpf, String tipo, int id){
		int idAluno = new PresencaDAO().getIdPessoaCPF(cpf);
		System.out.println("idAluno: "+ idAluno);
		if(idAluno!=0){
			if(tipo.equals("evento")){
				if(new PresencaDAO().verificaCheckinCheckoutEvento(idAluno, id).equals("checkin")){
					//A função acima deverá verificar se o aluno está inscrito no evento
					new PresencaDAO().checkinEvento(idAluno, id);
				}else if(new PresencaDAO().verificaCheckinCheckoutEvento(idAluno, id).equals("checkout")){
					new PresencaDAO().checkoutEvento(idAluno, id);
				}else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado no evento");
				}
			}else if(tipo.equals("atividade")){
				
				if(new PresencaDAO().verificaChekinChekoutAtividade(idAluno, id).equals("checkin")){
					new PresencaDAO().checkinAtividade(idAluno, id);
				}else if(new PresencaDAO().verificaChekinChekoutAtividade(idAluno, id).equals("checkout")){
					new PresencaDAO().checkoutAtividade(idAluno, id);
				}else{
					JOptionPane.showMessageDialog(null, "Aluno não cadastrado na atividade");
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "Este aluno não está cadastrada no sistema!");
		}
	}
}
