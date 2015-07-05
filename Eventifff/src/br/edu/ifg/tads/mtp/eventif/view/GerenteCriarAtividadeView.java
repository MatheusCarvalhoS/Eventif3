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

public class GerenteCriarAtividadeView {

	private JPanel painel;

	private JLabel nome;
	private JLabel descricaoAtividade;
	private JLabel data;
	private JLabel horaInicio;
	private JLabel horaFim;
	private JLabel tipoAtividade;
	private JLabel cargaHoraria;
	private JLabel numeroVagas;
	private JLabel palestrante;

	private JTextField txNome;
	private JTextArea txDescricaoAtividade;
	private JTextField txData;
	private JTextField txHoraInicio;
	private JTextField txHoraFim;
	private JTextField txTipoAtividade;
	private JTextField txCargaHoraria;
	private JTextField txNumeroVagas;
	private JTextField txPalestrante;
	
	private JButton btCriarAtividade;

	public JPanel getGerenteCriarAtividadeView() {
		painel = new JPanel();
		painel.setBounds(0, 0, 979, 624);
		painel.setLayout(null);

		btCriarAtividade = new JButton("Criar");
		// Criação dos elemento
		nome = new JLabel("Nome:");
		descricaoAtividade = new JLabel("Descrição:");
		data = new JLabel("Data:");
		horaInicio = new JLabel("Hora de Início:");
		horaFim = new JLabel("Hora de Fim:");
		tipoAtividade = new JLabel("Tipo:");
		cargaHoraria = new JLabel("Carga Horária:");
		numeroVagas = new JLabel("Numero de Vagas:");
		palestrante = new JLabel("Palestrante:");

		txNome = new JTextField();
		txDescricaoAtividade = new JTextArea();
		txData = new JFormattedTextField(new FormatadorMascara().mascara("##/##/####"));
		txHoraInicio = new JFormattedTextField(new FormatadorMascara().mascara("##:##"));
		txHoraFim =new JFormattedTextField(new FormatadorMascara().mascara("##:##"));
		txTipoAtividade = new JTextField();
		txCargaHoraria = new JTextField();
		txNumeroVagas = new JTextField();
		txPalestrante = new JTextField();

		nome.setBounds(20, 5, 100, 25);
		descricaoAtividade.setBounds(340, 5, 100, 25);
		data.setBounds(20, 60, 120, 25);
		horaInicio.setBounds(200, 60, 120, 25);
		horaFim.setBounds(20, 110, 100, 25);
		tipoAtividade.setBounds(20, 160, 100, 25);
		cargaHoraria.setBounds(220, 160, 150, 25);
		numeroVagas.setBounds(20, 210, 150, 25);	
		palestrante.setBounds(20, 260, 150, 25);
		
		txNome.setBounds(70, 5, 240, 25);
		txDescricaoAtividade.setBounds(420, 5, 300, 120);
		txData.setBounds(65, 60, 120, 25);
		txHoraInicio.setBounds(310, 60, 100, 25);
		txHoraFim.setBounds(115, 110, 100, 25);
		txTipoAtividade.setBounds(65, 160, 100, 25);
		txCargaHoraria.setBounds(330, 160, 150, 25);
		txNumeroVagas.setBounds(160, 210, 150, 25);	
		txPalestrante.setBounds(120, 260, 150, 25);
		
		btCriarAtividade.setBounds(570, 540, 150, 25);
		
		painel.add(nome);
		painel.add(descricaoAtividade);
		painel.add(data);
		painel.add(horaInicio);
		painel.add(horaFim);
		painel.add(tipoAtividade);
		painel.add(cargaHoraria);
		painel.add(numeroVagas);
		painel.add(palestrante);
		
		painel.add(txNome);
		painel.add(txDescricaoAtividade);
		painel.add(txCargaHoraria);
		painel.add(txHoraFim);
		painel.add(txData);
		painel.add(txHoraInicio);
		painel.add(txNumeroVagas);
		painel.add(txTipoAtividade);
		painel.add(txPalestrante);
		
		painel.add(btCriarAtividade);
		
		painel.setBackground(new Color(240, 240, 240));
		return painel;
	}

	public JLabel getPalestrante() {
		return palestrante;
	}

	public void setPalestrante(JLabel palestrante) {
		this.palestrante = palestrante;
	}

	public JTextField getTxPalestrante() {
		return txPalestrante;
	}

	public void setTxPalestrante(JTextField txPalestrante) {
		this.txPalestrante = txPalestrante;
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JLabel getNome() {
		return nome;
	}

	public void setNome(JLabel nome) {
		this.nome = nome;
	}

	public JLabel getDescricaoAtividade() {
		return descricaoAtividade;
	}

	public void setDescricaoAtividade(JLabel descricaoAtividade) {
		this.descricaoAtividade = descricaoAtividade;
	}

	public JLabel getData() {
		return data;
	}

	public void setData(JLabel data) {
		this.data = data;
	}

	public JLabel getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(JLabel horaInicio) {
		this.horaInicio = horaInicio;
	}

	public JLabel getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(JLabel horaFim) {
		this.horaFim = horaFim;
	}

	public JLabel getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(JLabel tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}

	public JLabel getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(JLabel cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public JLabel getNumeroVagas() {
		return numeroVagas;
	}

	public void setNumeroVagas(JLabel numeroVagas) {
		this.numeroVagas = numeroVagas;
	}

	public JTextField getTxNome() {
		return txNome;
	}

	public void setTxNome(JTextField txNome) {
		this.txNome = txNome;
	}

	public JTextArea getTxDescricaoAtividade() {
		return txDescricaoAtividade;
	}

	public void setTxDescricaoAtividade(JTextArea txDescricaoAtividade) {
		this.txDescricaoAtividade = txDescricaoAtividade;
	}

	public JTextField getTxData() {
		return txData;
	}

	public void setTxData(JTextField txData) {
		this.txData = txData;
	}

	public JTextField getTxHoraInicio() {
		return txHoraInicio;
	}

	public void setTxHoraInicio(JTextField txHoraInicio) {
		this.txHoraInicio = txHoraInicio;
	}

	public JTextField getTxHoraFim() {
		return txHoraFim;
	}

	public void setTxHoraFim(JTextField txHoraFim) {
		this.txHoraFim = txHoraFim;
	}

	public JTextField getTxTipoAtividade() {
		return txTipoAtividade;
	}

	public void setTxTipoAtividade(JTextField txTipoAtividade) {
		this.txTipoAtividade = txTipoAtividade;
	}

	public JTextField getTxCargaHoraria() {
		return txCargaHoraria;
	}

	public void setTxCargaHoraria(JTextField txCargaHoraria) {
		this.txCargaHoraria = txCargaHoraria;
	}

	public JTextField getTxNumeroVagas() {
		return txNumeroVagas;
	}

	public void setTxNumeroVagas(JTextField txNumeroVagas) {
		this.txNumeroVagas = txNumeroVagas;
	}

	public JButton getBtCriarAtividade() {
		return btCriarAtividade;
	}

	public void setBtCriarAtividade(JButton btCriarAtividade) {
		this.btCriarAtividade = btCriarAtividade;
	}
	

}
