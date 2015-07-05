package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EnderecoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.PessoaDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.util.ConfirmaSenha;
import br.edu.ifg.tads.mtp.eventif.util.ValidacaoCPF;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposPessoaInscricao;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.PessoaInscricaoSistemaView;

public class PessoaInscricaoSistemaControl {
	private PessoaInscricaoSistemaView inscreverPessoa;
	private JPanel painel;
	private AppView appView;
	
	public JPanel getPessoaInscricaoSistemaControl(AppView app){
		this.appView = app;
		inscreverPessoa=new PessoaInscricaoSistemaView();
		painel=inscreverPessoa.getPessoaInscricaoSistemaView();
		adicionaEventos();
		return painel;
	}
	
	public void adicionaEventos(){
		inscreverPessoa.getBtInscrever().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(new VerificaCamposPessoaInscricao().getVerificaCamposPessoaInscricao(inscreverPessoa)){
					boolean validacao = true;
					EnderecoModel endereco = new EnderecoModel();
					endereco.setCep(inscreverPessoa.getTxCep().getText());
					endereco.setUf(inscreverPessoa.getTxUf().getText());
					endereco.setCidade(inscreverPessoa.getTxCidade().getText());
					endereco.setBairro(inscreverPessoa.getTxBairro().getText());
					endereco.setNumero(inscreverPessoa.getTxNumero().getText());
					endereco.setComplemento(inscreverPessoa.getTxComplemento().getText());
					
					AlunoModel aluno = new AlunoModel();
					aluno.setNomePessoa(inscreverPessoa.getTxNome().getText());
					String cpf = inscreverPessoa.getTxCpf().getText().replace(".","").replace("-","");
					if(!new ValidacaoCPF().validaCpf(cpf)){
						validacao = false;
						JOptionPane.showMessageDialog(null, "CPF inválido!");
					}else if(!new ConfirmaSenha().confirmaSenha(inscreverPessoa.getTxSenha().getText(), inscreverPessoa.getTxConfirmaSenha().getText())){
						validacao = false;
						JOptionPane.showMessageDialog(null, "Senhas não conferem!");
					}else{
						aluno.setCpf(cpf);
						aluno.setRg(inscreverPessoa.getTxRg().getText());
						aluno.setSenha(inscreverPessoa.getTxSenha().getText());
					}
					
					if(!validacao){
						inscreverPessoa.getTxCpf().setText("");
						inscreverPessoa.getTxSenha().setText("");
						inscreverPessoa.getTxConfirmaSenha().setText("");
					}
					
					if(validacao && new EnderecoDAO().adiconaEndereco(endereco)){
						aluno.setIdEndereco(new EnderecoDAO().retornaMaxIdEndereco());
						if(new PessoaDAO().adiconaPessoa(aluno)){
							aluno.setIdPessoa(new PessoaDAO().retornaMaxIdPessoa());
							if(new AlunoDAO().adiconaAluno(aluno)){
								JOptionPane.showMessageDialog(null, "Aluno inscrito com sucesso, CPF: "+aluno.getCpf());
								appView.getPainelDireita().removeAll();
								//appView.getPainelDireita().add(new AlunoListarEventoControl().getAlunoListarEventoControl(appView));
								appView.getPainelDireita().repaint();
							}
						}
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "Verifique o preenchimento dos campos");
				}
			}
		});
	}
}
