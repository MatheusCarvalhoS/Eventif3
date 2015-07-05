package br.edu.ifg.tads.mtp.eventif.model;

public class AlunoModel extends PessoaModel{
	private int idAluno;
	private String senha;
	
	public int getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}

