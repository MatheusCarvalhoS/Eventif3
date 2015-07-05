package br.edu.ifg.tads.mtp.eventif.model;

public class GerenteModel extends PessoaModel{
	private int idGerente;
	private String senha;

	public int getIdGerente() {
		return idGerente;
	}

	public void setIdGerente(int idGerente) {
		this.idGerente = idGerente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
