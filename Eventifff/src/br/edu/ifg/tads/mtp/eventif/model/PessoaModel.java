package br.edu.ifg.tads.mtp.eventif.model;

public abstract class PessoaModel {

	private int idPessoa;
	private String nomePessoa;
	private String cpf;
	private String rg;
	private int idEndereco;
	private boolean ativo;

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public int getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(int idEncereco) {
		this.idEndereco = idEncereco;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
}
