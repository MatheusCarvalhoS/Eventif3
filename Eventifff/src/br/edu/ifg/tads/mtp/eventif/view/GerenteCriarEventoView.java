package br.edu.ifg.tads.mtp.eventif.view;

import java.awt.Color;
import java.awt.TextField;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import br.edu.ifg.tads.mtp.eventif.util.FormatadorMascara;

public class GerenteCriarEventoView {
	private JPanel painel;
	private JLabel nome;
	private JLabel descricao;
	private JLabel dataInicio;
	private JLabel dataEncerramento;
	private JLabel organizador;
	private JLabel telefone;
	private JLabel email;
	private JLabel local;
	private JLabel cep;
	private JLabel numero;
	private JLabel bairro;
	private JLabel cidade;
	private JLabel uf;

	private JTextField txNome;
	private JTextArea txDescricao;
	private JFormattedTextField txDataInicio;
	private JFormattedTextField txDataEncerramento;
	private JTextField txOrganizador;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txLocal;
	private JTextField txCep;
	private JTextField txNumero;
	private JTextField txBairro;
	private JTextField txCidade;
	private JTextField txUf;
	private JButton btCriar;

	public JPanel getGerenteCriarEventoView() {
		painel = new JPanel();
		painel.setBounds(0, 0, 979, 624);
		painel.setLayout(null);
		// Criação dos elemento
		nome = new JLabel("Nome:");
		descricao = new JLabel("Descricao:");
		dataInicio = new JLabel("Data de Início:");
		dataEncerramento = new JLabel("Encerramento:");
		organizador = new JLabel("Organizador:");
		telefone = new JLabel("Tel:");
		email = new JLabel("Email:");
		local = new JLabel("Local:");
		cep = new JLabel("Cep:");
		numero = new JLabel("Numero:");
		bairro = new JLabel("Bairro:");
		cidade = new JLabel("Cidade:");
		uf = new JLabel("UF:");

		txNome = new JTextField();
		txDescricao = new JTextArea();
		txDataInicio = new JFormattedTextField(new FormatadorMascara().mascara("##/##/####"));
		txDataEncerramento = new JFormattedTextField(new FormatadorMascara().mascara("##/##/####"));
		txOrganizador = new JTextField();
		txTelefone = new JTextField();
		txEmail = new JTextField();
		txLocal = new JTextField();
		txCep = new JTextField();
		txNumero = new JTextField();
		txBairro = new JTextField();
		txCidade = new JTextField();
		txUf = new JTextField();
		btCriar = new JButton("Criar");

		// Setando posições e tamanhos

		// Labels
		nome.setBounds(20, 5, 100, 25);
		descricao.setBounds(340, 5, 100, 25);
		dataInicio.setBounds(20, 60, 120, 25);
		dataEncerramento.setBounds(175, 60, 120, 25);
		organizador.setBounds(20, 110, 100, 25);
		telefone.setBounds(20, 160, 100, 25);
		email.setBounds(150, 160, 100, 25);
		local.setBounds(20, 210, 100, 25);
		cep.setBounds(330, 210, 100, 25);
		numero.setBounds(20, 300, 100, 25);
		uf.setBounds(110, 300, 80, 25);
		bairro.setBounds(20, 345, 100, 25);
		cidade.setBounds(20, 390, 100, 25);

		// TextFields
		txNome.setBounds(20, 25, 300, 25);
		txDescricao.setBounds(340, 25, 400, 130);
		txDataInicio.setBounds(20, 80, 145, 25);
		txDataEncerramento.setBounds(175, 80, 145, 25);
		txOrganizador.setBounds(20, 131, 300, 25);
		txTelefone.setBounds(20, 180, 120, 25);
		txEmail.setBounds(150, 180, 300, 25);
		txLocal.setBounds(20, 230, 300, 25);
		txCep.setBounds(330, 230, 120, 25);
		txNumero.setBounds(20, 320, 80, 25);
		txUf.setBounds(110, 320, 80, 25);
		txUf.setColumns(2);
		txBairro.setBounds(20, 365, 310, 25);
		txCidade.setBounds(20, 410, 310, 25);
		btCriar.setBounds(570, 540, 150, 25);

		painel.add(nome);
		painel.add(descricao);
		painel.add(dataInicio);
		painel.add(dataEncerramento);
		painel.add(organizador);
		painel.add(telefone);
		painel.add(email);
		painel.add(local);
		painel.add(cep);
		painel.add(numero);
		painel.add(bairro);
		painel.add(cidade);
		painel.add(uf);

		painel.add(txNome);
		painel.add(txDescricao);
		painel.add(txDataInicio);
		painel.add(txDataEncerramento);
		painel.add(txOrganizador);
		painel.add(txTelefone);
		painel.add(txEmail);
		painel.add(txLocal);
		painel.add(txCep);
		painel.add(txTelefone);
		painel.add(txNumero);
		painel.add(txBairro);
		painel.add(txCidade);
		painel.add(txUf);
		painel.add(btCriar);

		painel.setBackground(new Color(240, 240, 240));
		return painel;
	}

	public JTextField getTxNome() {
		return txNome;
	}

	public void setTxNome(JTextField txNome) {
		this.txNome = txNome;
	}

	public JTextArea getTxDescricao() {
		return txDescricao;
	}

	public void setTxDescricao(JTextArea txDescricao) {
		this.txDescricao = txDescricao;
	}

	public JTextField getTxOrganizador() {
		return txOrganizador;
	}

	public void setTxOrganizador(JTextField txOrganizador) {
		this.txOrganizador = txOrganizador;
	}

	public JTextField getTxTelefone() {
		return txTelefone;
	}

	public void setTxTelefone(JTextField txTelefone) {
		this.txTelefone = txTelefone;
	}

	public JTextField getTxEmail() {
		return txEmail;
	}

	public void setTxEmail(JTextField txEmail) {
		this.txEmail = txEmail;
	}

	public JTextField getTxLocal() {
		return txLocal;
	}

	public void setTxLocal(JTextField txLocal) {
		this.txLocal = txLocal;
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

	public JTextField getTxBairro() {
		return txBairro;
	}

	public void setTxBairro(JTextField txBairro) {
		this.txBairro = txBairro;
	}

	public JTextField getTxCidade() {
		return txCidade;
	}

	public void setTxCidade(JTextField txCidade) {
		this.txCidade = txCidade;
	}

	public JTextField getTxUf() {
		return txUf;
	}

	public void setTxUf(JTextField txUf) {
		this.txUf = txUf;
	}

	public JButton getBtCriar() {
		return btCriar;
	}

	public void setBtCriar(JButton btCriar) {
		this.btCriar = btCriar;
	}

	public JFormattedTextField getTxDataInicio() {
		return txDataInicio;
	}

	public void setTxDataInicio(JFormattedTextField txDataInicio) {
		this.txDataInicio = txDataInicio;
	}

	public JFormattedTextField getTxDataEncerramento() {
		return txDataEncerramento;
	}

	public void setTxDataEncerramento(JFormattedTextField txDataEncerramento) {
		this.txDataEncerramento = txDataEncerramento;
	}

	
	
}
