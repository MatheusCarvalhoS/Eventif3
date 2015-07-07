package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.GerenteDAO;
import br.edu.ifg.tads.mtp.eventif.dao.MonitorDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.GerenteModel;
import br.edu.ifg.tads.mtp.eventif.model.MonitorModel;
import br.edu.ifg.tads.mtp.eventif.util.ConfirmaSenha;
import br.edu.ifg.tads.mtp.eventif.util.MD5;
import br.edu.ifg.tads.mtp.eventif.util.ValidacaoCPF;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposLogin;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.LoginView;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LoginControl {
	private LoginView login;
	private JPanel painel;
	private AppView appView;
	private GerenteModel gerente;
	private MonitorModel monitor;
	private AlunoModel aluno;
	private JButton btSair;

	public JPanel getLoginControl(AppView app) {
		btSair = new JButton("Sair");
		btSair.setBounds(1000,30,100,25);
		
		this.appView = app;
		
		login = new LoginView();
		painel = login.getPainelLogin();
		adicionaEventos();
		return painel;
	}

	public void adicionaEventos() {
	
		btSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appView.getPainelDireita().removeAll();
				appView.getPainelEsquerda().removeAll();
				
				appView.getPainelDireita().add(new LoginControl().getLoginControl(appView));
				appView.getPainelEsquerda().add(new MenuPrincipalControl().getMenuPrincipalControl(appView));
				appView.getPainelSuperior().remove(btSair);
				
				appView.getPainelSuperior().repaint();
				appView.getPainelDireita().repaint();
				appView.getPainelEsquerda().repaint();
			}
		});
		
		
		login.getBtnOk().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean validacao = true;
				String text = (String) login.getCombo().getSelectedItem();
				int idAluno = -1;
				int idMonitor = -1;
				int idGerente = -1;
				if (new VerificaCamposLogin().getVerificaCamposCriarLogin(login)) {
					String cpf = login.getTxCpf().getText().replace(".", "")
							.replace("-", "");
					String senha = "";
					
					if (new ValidacaoCPF().validaCpf(cpf)) {
						try {
							senha = new MD5().gerarSenha(login.getTfSenha().getText());
							System.out.println("Senha: "+senha);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						validacao = false;
						JOptionPane.showMessageDialog(null, "CPF inválido!");
						login.getTxCpf().setText("");
						login.getTfSenha().setText("");
					}
					
					if(validacao && text.equals("Participante") && (idAluno=new AlunoDAO().verificaLogin(cpf, senha))==-1){
						validacao=false;
					}else if(validacao && text.equals("Monitor (a)") && (idMonitor = new MonitorDAO().verificaLogin(cpf, senha))==-1){
						validacao=false;
					}else if(validacao && text.equals("Gerente") && (idGerente=new GerenteDAO().verificaLogin(cpf, senha))==-1){
						validacao=false;
					}
					
					if(!validacao){
						JOptionPane.showMessageDialog(null,
								"Verifique CPF e/ou Senha, Ou verifique se o "+text+" está Cadastrado!");
						
					}else if (text.equals("Gerente")) {
						appView.getPainelSuperior().add(btSair);
						gerente = new GerenteModel();
						gerente.setCpf(cpf);
						gerente.setSenha(senha);
						gerente.setIdGerente(idGerente);
						appView.getPainelEsquerda().removeAll();
						appView.getPainelEsquerda().add(
								new MenuPrincipalGerenteControl()
										.getMenuPrincipalGerente(appView));
						appView.getPainelEsquerda().repaint();
						appView.getPainelDireita().removeAll();
						appView.getPainelDireita()
								.add(new GerenteListarEventoControl()
										.getGerenteListarEventoControl(appView));
						appView.getPainelDireita().repaint();
					} else if (text.equals("Monitor (a)")) {
						appView.getPainelSuperior().add(btSair);
						monitor = new MonitorModel();
						monitor.setCpf(cpf);
						monitor.setSenha(senha);
						monitor.setIdMonitor(idMonitor); // --------- Verificar a existência do monitor no banco com o cpf, validar a senha e receber o id de monitor

						appView.getPainelEsquerda().removeAll();
						appView.getPainelDireita().removeAll();

						appView.getPainelEsquerda()
								.add(new MenuPrincipalMonitorControl()
										.getMenuPrincipalMonitorControl(appView, monitor.getIdMonitor()));
						appView.getPainelDireita()
								.add(new MonitorListarEventoControl()
										.getMonitorListarEventoControl(appView, monitor.getIdMonitor()));

						appView.getPainelDireita().repaint();
						appView.getPainelEsquerda().repaint();
					} else if (text.equals("Participante")) {
						appView.getPainelSuperior().add(btSair);
						aluno = new AlunoModel();
						aluno.setCpf(cpf);
						aluno.setSenha(senha);
						aluno.setIdAluno(idAluno);

						appView.getPainelEsquerda().removeAll();
						appView.getPainelDireita().removeAll();

						appView.getPainelDireita()
								.add(new AlunoListarEventoControl()
										.getAlunoListarEventoControl(appView, aluno.getIdAluno()));
						
						appView.getPainelEsquerda()
						.add(new MenuPrincipalAlunoControl()
								.getMenuPrincipalAlunoControl(appView, aluno.getIdAluno()));

						appView.getPainelDireita().repaint();
						appView.getPainelEsquerda().repaint();
					}
				} else {
					JOptionPane.showMessageDialog(null,"Verifique o preenchimento dos Campos.");
					login.getTxCpf().setText("");
					login.getTfSenha().setText("");
				}
			}
		});
	}
}
