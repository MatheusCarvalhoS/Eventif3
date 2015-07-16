package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.edu.ifg.tads.mtp.eventif.dao.AlunoDAO;
import br.edu.ifg.tads.mtp.eventif.dao.GerenteDAO;
import br.edu.ifg.tads.mtp.eventif.dao.MonitorDAO;
import br.edu.ifg.tads.mtp.eventif.dao.PessoaDAO;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.GerenteModel;
import br.edu.ifg.tads.mtp.eventif.model.MonitorModel;
import br.edu.ifg.tads.mtp.eventif.util.MD5;
import br.edu.ifg.tads.mtp.eventif.util.ValidacaoCPF;
import br.edu.ifg.tads.mtp.eventif.util.VerificaCamposLogin;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.LoginView;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class LoginControl {
	private LoginView login;
	private JPanel painel;
	private AppView appView;
	private GerenteModel gerente;
	private MonitorModel monitor;
	private AlunoModel aluno;
	private JLabel lbSair;
	private ImageIcon icon;

	public JPanel getLoginControl(AppView app) {
		icon = new ImageIcon(getClass().getResource("/Exit.png"));
		lbSair = new JLabel(icon);
		lbSair.setBounds(1095,5,79,79);
		this.appView = app;
		login = new LoginView();
		painel = login.getPainelLogin();
		adicionaEventos();
		return painel;
	}

	public void adicionaEventos() {
		
		lbSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				appView.getPainelDireita().removeAll();
				appView.getPainelEsquerda().removeAll();
				
				appView.getPainelDireita().add(new LoginControl().getLoginControl(appView));
				appView.getPainelEsquerda().add(new MenuPrincipalControl().getMenuPrincipalControl(appView));
				appView.getPainelSuperior().remove(lbSair);
				
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
				int id=0;
				if (new VerificaCamposLogin().getVerificaCamposCriarLogin(login)) {
					String cpf = login.getTxCpf().getText().replace(".", "")
							.replace("-", "");
					String senha = "";
					
					if (new ValidacaoCPF().validaCpf(cpf)) {
						try {
							senha = new MD5().gerarSenha(login.getTfSenha().getText());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						validacao = false;
						JOptionPane.showMessageDialog(null, "CPF inválido!");
						login.getTxCpf().setText("");
						login.getTfSenha().setText("");
					}
					
					if(validacao && text.equals("Participante") && (idAluno=new AlunoDAO().verificaLogin(cpf, senha))<=-1){
						validacao=false;
						id=idAluno;
					}else if(validacao && text.equals("Monitor (a)") && (idMonitor = new MonitorDAO().verificaLogin(cpf, senha))==-1){
						validacao=false;
					}else if(validacao && text.equals("Gerente") && (idGerente=new GerenteDAO().verificaLogin(cpf, senha))==-1){
						validacao=false;
					}
					
					if(!validacao){
						if(id==-2){
							login.getTxCpf().setText("");
							login.getTfSenha().setText("");
							if(JOptionPane.showConfirmDialog(null,
									"Aluno já cadastrado no sistema, deseja reativá-lo?") == 0){
								new PessoaDAO().reativaPessoa(cpf);
							}
						}else{
							JOptionPane.showMessageDialog(null,"Verifique CPF e/ou Senha ou se o "+text+" está cadastrado");
						}
					}
					else if (text.equals("Gerente")) {
						appView.getPainelSuperior().add(lbSair);
						appView.getPainelSuperior().repaint();
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
						appView.getPainelSuperior().add(lbSair);
						appView.getPainelSuperior().repaint();
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
						appView.getPainelSuperior().add(lbSair);
						appView.getPainelSuperior().repaint();
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

	public JLabel getLbSair() {
		return lbSair;
	}

	public void setLbSair(JLabel lbSair) {
		this.lbSair = lbSair;
	}
}
