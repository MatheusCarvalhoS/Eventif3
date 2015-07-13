package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.EnderecoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.PessoaDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;
import br.edu.ifg.tads.mtp.eventif.util.ConfirmaSenha;
import br.edu.ifg.tads.mtp.eventif.util.CriarQRCode;
import br.edu.ifg.tads.mtp.eventif.util.MD5;
import br.edu.ifg.tads.mtp.eventif.util.ValidacaoCPF;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposPessoaInscricao;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.PessoaInscricaoSistemaView;

public class PessoaInscricaoSistemaControl {
	private PessoaInscricaoSistemaView inscreverPessoa;
	private JPanel painel;
	private AppView appView;
	private JButton excluirConta;
	private AlunoModel aluno;
	private EnderecoModel endereco;
	
	public JPanel getPessoaInscricaoSistemaControl(AppView app) {
		this.appView = app;
		inscreverPessoa = new PessoaInscricaoSistemaView();
		painel = inscreverPessoa.getPessoaInscricaoSistemaView();
		adicionaEventosInserir();
		return painel;
	}

	public JPanel getPessoaAlteracaoSistemaControl(AppView app,
			AlunoModel aluno, EnderecoModel endereco) {
		this.aluno = aluno;
		this.endereco = endereco;
		this.appView = app;
		inscreverPessoa = new PessoaInscricaoSistemaView();
		painel = inscreverPessoa.getPessoaInscricaoSistemaView();
		inscreverPessoa.getTxNome().setText(aluno.getNomePessoa());
		inscreverPessoa.getTxCpf().setText(aluno.getCpf());
		inscreverPessoa.getTxRg().setText(aluno.getRg());
		inscreverPessoa.getTxNumero().setText(endereco.getNumero());
		inscreverPessoa.getTxBairro().setText(endereco.getBairro());
		inscreverPessoa.getTxCep().setText(endereco.getCep());
		inscreverPessoa.getTxCidade().setText(endereco.getCidade());
		inscreverPessoa.getTxUf().setText(endereco.getUf());
		// colocar EMail e Telefone, pois tem na Regra de negócio porém não tem
		// no sistema
		// retirar TxComplemento de todas as classes que a contenham.
		painel.remove(inscreverPessoa.getTxSenha());
		painel.remove(inscreverPessoa.getTxConfirmaSenha());
		painel.remove(inscreverPessoa.getSenha());
		painel.remove(inscreverPessoa.getConfirmaSenha());
		inscreverPessoa.getBtInscrever().setText("Alterar");
		excluirConta = new JButton("Excluir conta");
		excluirConta.setBounds(5, 540, 150, 25);
		painel.add(excluirConta);

		adicionaEventosAlterar();
		return painel;
	}

	public void adicionaEventosInserir() {

		inscreverPessoa.getBtInscrever().addActionListener(
				new ActionListener() {
					@SuppressWarnings("deprecation")
					public void actionPerformed(ActionEvent arg0) {
						if (new VerificaCamposPessoaInscricao()
								.getVerificaCamposPessoaInscricao(inscreverPessoa)) {
							boolean validacao = true;
							EnderecoModel endereco = new EnderecoModel();
							endereco.setCep(inscreverPessoa.getTxCep()
									.getText());
							endereco.setUf(inscreverPessoa.getTxUf().getText());
							endereco.setCidade(inscreverPessoa.getTxCidade()
									.getText());
							endereco.setBairro(inscreverPessoa.getTxBairro()
									.getText());
							endereco.setNumero(inscreverPessoa.getTxNumero()
									.getText());

							AlunoModel aluno = new AlunoModel();
							aluno.setNomePessoa(inscreverPessoa.getTxNome()
									.getText());
							String cpf = inscreverPessoa.getTxCpf().getText()
									.replace(".", "").replace("-", "");
							if (!new ValidacaoCPF().validaCpf(cpf)) {
								validacao = false;
								JOptionPane.showMessageDialog(null,
										"CPF inválido!");
							} else if (!new ConfirmaSenha().confirmaSenha(
									inscreverPessoa.getTxSenha().getText(),
									inscreverPessoa.getTxConfirmaSenha()
											.getText())) {
								validacao = false;
								JOptionPane.showMessageDialog(null,
										"Senhas não conferem!");
							} else {
								if (new PessoaDAO().verificaExistencia(cpf)) {
									if (new PessoaDAO().verificaAtivo(cpf)) {
										JOptionPane.showMessageDialog(null,
												"Já existe participante com CPF: "
														+ cpf);
										validacao = false;
									} else {
										if (JOptionPane
												.showConfirmDialog(null,
														"Aluno já cadastrado no sistema, deseja reativá-lo?") == 0) {
											new PessoaDAO().reativaPessoa(cpf);
										}
										validacao = false;
									}
								} else {
									try {
										new CriarQRCode();
										CriarQRCode.getCriarQRCode(cpf);
										aluno.setCpf(cpf);
										aluno.setRg(inscreverPessoa.getTxRg()
												.getText());
										String senha = null;
										try {
											senha = new MD5()
													.gerarSenha(inscreverPessoa
															.getTxSenha()
															.getText());
										} catch (Exception e) {
											e.printStackTrace();
										}
										aluno.setSenha(senha);
										JOptionPane.showMessageDialog(null,
												"QRcode Gerado com Sucesso! ");
									} catch (NotFoundException
											| WriterException | IOException e) {
										JOptionPane.showMessageDialog(
												null,
												"Erro ao criar o QrCode! "
														+ e.getMessage());
										e.printStackTrace();
									}
								}
							}

							if (!validacao) {
								inscreverPessoa.getTxCpf().setText("");
								inscreverPessoa.getTxSenha().setText("");
								inscreverPessoa.getTxConfirmaSenha()
										.setText("");
							}

							if (validacao
									&& new EnderecoDAO()
											.adiconaEndereco(endereco)) {
								aluno.setIdEndereco(new EnderecoDAO()
										.retornaMaxIdEndereco());
								if (new PessoaDAO().adiconaPessoa(aluno)) {
									aluno.setIdPessoa(new PessoaDAO()
											.retornaMaxIdPessoa());
									if (new AlunoDAO().adiconaAluno(aluno)) {
										JOptionPane.showMessageDialog(null,
												"Aluno inscrito com sucesso, CPF: "
														+ aluno.getCpf());
										appView.getPainelDireita().removeAll();
										appView.getPainelDireita()
												.add(new LoginControl()
														.getLoginControl(appView));
										appView.getPainelDireita().repaint();
									}
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

		inscreverPessoa.getBtInscrever().addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					aluno.setNomePessoa(inscreverPessoa.getTxNome().getText());
					aluno.setCpf(inscreverPessoa.getTxCpf().getText().replace(".", "").replace("-", ""));
					aluno.setRg(inscreverPessoa.getTxRg().getText());
					endereco.setNumero(inscreverPessoa.getTxNumero().getText());
					endereco.setBairro(inscreverPessoa.getTxBairro().getText());
					endereco.setCep(inscreverPessoa.getTxCep().getText());
					endereco.setCidade(inscreverPessoa.getTxCidade().getText());
					endereco.setUf(inscreverPessoa.getTxUf().getText());
					
					if(new PessoaDAO().alterarPessoa(aluno)){
						System.out.println("aluno 1 = "+ aluno.getNomePessoa());
						if(new EnderecoDAO().alterarEndereco(endereco)){
							System.out.println("endereco 1 cidade = "+ endereco.getCidade());
							JOptionPane.showMessageDialog(null, "Dados Alterados Com Sucesso! ");
						}
					}
				}
		});

		excluirConta.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				if(new AlunoDAO().desativarConta(aluno.getIdAluno())){
					JOptionPane.showMessageDialog(null, "Conta excluída com sucesso");
					appView.getPainelDireita().removeAll();
					appView.getPainelDireita().add(new LoginControl().getLoginControl(appView));
					appView.getPainelDireita().repaint();
					
					appView.getPainelEsquerda().removeAll();
					appView.getPainelEsquerda().add(new MenuPrincipalControl().getMenuPrincipalControl(appView));
					appView.getPainelEsquerda().repaint();
					
					appView.getPainelSuperior().removeAll();
					appView.getPainelSuperior().add(appView.getLogo());
					appView.getPainelSuperior().repaint();
				}
			}
		});
	}
}
