package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.edu.ifg.tads.mtp.eventif.util.FormatadorMascara;

public class PessoaInscricaoSistemaView {
	private JPanel painel;

	private JLabel nome;
	private JLabel cpf;
	private JLabel rg;
	private JLabel cep;
	private JLabel uf;
	private JLabel cidade;
	private JLabel bairro;
	private JLabel numero;
	private JLabel complemento;
	private JLabel senha;
	private JLabel confirmaSenha;

	private JTextField txNome;
	private JFormattedTextField txCpf;
	private JTextField txRg;
	private JTextField txCep;
	private JTextField txUf;
	private JTextField txCidade;
	private JTextField txBairro;
	private JTextField txNumero;
	private JTextField txComplemento;
	private JPasswordField txSenha;
	private JPasswordField txConfirmaSenha;
	
	private JButton btInscrever;

	public JPanel getPessoaInscricaoSistemaView() {
		painel = new JPanel();
		painel.setBounds(0, 0, 979, 624);
		painel.setLayout(null);

		// Criando JLabels
		nome = new JLabel("Nome:");
		cpf = new JLabel("CPF:");
		rg = new JLabel("RG:");
		cep = new JLabel("CEP:");
		uf = new JLabel("UF:");
		cidade = new JLabel("Cidade:");
		bairro = new JLabel("Bairro:");
		numero = new JLabel("Numero:");
		complemento = new JLabel("Complemento:");
		senha = new JLabel("Senha:");
		confirmaSenha = new JLabel("Confirme sua Senha:");
		
		// Criando JTextFields
		txNome = new JTextField();
		txCpf = new JFormattedTextField(new FormatadorMascara().mascara("###.###.###-##"));
		txRg = new JTextField();
		txCep = new JTextField();
		txUf = new JTextField();
		txCidade = new JTextField();
		txBairro = new JTextField();
		txNumero = new JTextField();
		txComplemento = new JTextField();
		txSenha = new JPasswordField();
		txConfirmaSenha = new JPasswordField();
		btInscrever = new JButton("Inscrever");

		// setando posições das Labels
		nome.setBounds(20, 5, 100, 25);
		cpf.setBounds(20, 50, 120, 25);
		rg.setBounds(175, 50, 120, 25);
		cep.setBounds(20, 95, 100, 25);
		uf.setBounds(130, 95, 100, 25);
		cidade.setBounds(20, 140, 100, 25);
		bairro.setBounds(20, 185, 100, 25);
		numero.setBounds(20, 230, 100, 25);
		complemento.setBounds(20, 275, 110, 25);
		senha.setBounds(20, 320, 100, 25);
		confirmaSenha.setBounds(20, 365, 150, 25);
		
		// setando as posições das TextFields
		txNome.setBounds(20, 25, 400, 25);
		txCpf.setBounds(20, 70, 145, 25);
		txRg.setBounds(175, 70, 145, 25);
		txCep.setBounds(20, 115, 100, 25);
		txUf.setBounds(130, 115, 100, 25);
		txCidade.setBounds(20, 160, 300, 25);
		txBairro.setBounds(20, 205, 300, 25);
		txNumero.setBounds(20, 250, 100, 25);
		txComplemento.setBounds(20, 296, 400, 25);
		txSenha.setBounds(20, 340, 200, 25);
		txConfirmaSenha.setBounds(20, 385, 200, 25);
		btInscrever.setBounds(570, 540, 150, 25);
		
		// adicionando ao painel
		painel.add(nome);
		painel.add(cpf);
		painel.add(rg);
		painel.add(cep);
		painel.add(uf);
		painel.add(cidade);
		painel.add(bairro);
		painel.add(numero);
		painel.add(complemento);
		painel.add(senha);
		painel.add(confirmaSenha);
		
		painel.add(txNome);
		painel.add(txCpf);
		painel.add(txRg);
		painel.add(txCep);
		painel.add(txUf);
		painel.add(txCidade);
		painel.add(txBairro);
		painel.add(txNumero);
		painel.add(txComplemento);
		painel.add(txSenha);
		painel.add(txConfirmaSenha);
		
		painel.add(btInscrever);

		painel.setBackground(new Color(240, 240, 240));
		return painel;
	}

	public JTextField getTxNome() {
		return txNome;
	}

	public void setTxNome(JTextField txNome) {
		this.txNome = txNome;
	}

	public JFormattedTextField getTxCpf() {
		return txCpf;
	}

	public void setTxCpf(JFormattedTextField txCpf) {
		this.txCpf = txCpf;
	}

	public JTextField getTxRg() {
		return txRg;
	}

	public void setTxRg(JTextField txRg) {
		this.txRg = txRg;
	}

	public JTextField getTxCidade() {
		return txCidade;
	}

	public void setTxCidade(JTextField txCidade) {
		this.txCidade = txCidade;
	}

	public JTextField getTxBairro() {
		return txBairro;
	}

	public void setTxBairro(JTextField txBairro) {
		this.txBairro = txBairro;
	}

	public JTextField getTxCep() {
		return txCep;
	}

	public void setTxCep(JTextField txCep) {
		this.txCep = txCep;
	}

	public JTextField getTxNumero() {
		return txNumero;
	}

	public void setTxNumero(JTextField txNumero) {
		this.txNumero = txNumero;
	}

	public JTextField getTxComplemento() {
		return txComplemento;
	}

	public void setTxComplemento(JTextField txComplemento) {
		this.txComplemento = txComplemento;
	}

	public JTextField getTxUf() {
		return txUf;
	}

	public void setTxUf(JTextField txUf) {
		this.txUf = txUf;
	}

	public JButton getBtInscrever() {
		return btInscrever;
	}
	
	public JTextField getTxSenha() {
		return txSenha;
	}

	public void setTxSenha(JPasswordField txSenha) {
		this.txSenha = txSenha;
	}

	public void setBtInscrever(JButton btInscrever) {
		this.btInscrever = btInscrever;
	}

	public JPasswordField getTxConfirmaSenha() {
		return txConfirmaSenha;
	}

	public void setTxConfirmaSenha(JPasswordField txConfirmaSenha) {
		this.txConfirmaSenha = txConfirmaSenha;
	}
	
}
