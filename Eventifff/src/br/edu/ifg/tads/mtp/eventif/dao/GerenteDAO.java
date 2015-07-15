package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;

public class GerenteDAO {
	public int verificaLogin(String cpf, String senha) {
		String sql = "select g.idPessoa as id from gerente g inner join pessoa p on g.idPessoa = p.idPessoa where(p.cpf=? and g.senha=?);";
		Connection con = null;
		int idGerente = -1;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				idGerente = result.getInt("id");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Impossível fechar conexão! " + e.getMessage());
			}
		}
		return idGerente;
	}

	public ResultSet gerarCertificadosEvento(int idAluno, int idEvento) {
		String sql = "select sum(Extract (minute from (horacheckout - horaCheckin))) as totalHoras from presencaEvento where (idAluno = ? and idEvento = ?)";
		Connection con = null;
		ResultSet result = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idAluno);
			System.out.println("entrei");
			result = stmt.executeQuery();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}

	public ResultSet gerarCertificadosIdAluno(int idAtividade) {
		String sql = "select distinct idAluno from presencaAtividade where (idAtividade = ?);";
		Connection con = null;
		ResultSet result = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idAtividade);
			
			result = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}
	
	public ResultSet gerarCertificadosAtividade(int idAluno, int idAtividade) {
		String sql = "select sum(Extract (minute from (pa.horacheckout - pa.horaCheckin))) as totalHoras, a.cargaHoraria from presencaAtividade as pa " +
				"inner join atividade as a on pa.idAtividade = a.idAtividade where (pa.idAluno = ? and pa.idAtividade = ?) group by a.cargaHoraria;";
		Connection con = null;
		ResultSet result = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idAluno);
			stmt.setInt(2, idAtividade);
			result = stmt.executeQuery();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}
	
	public ResultSet informacoesCertificado(int idAluno, int idAtividade) {
		
		String sql = "select p.cpf, p.nomePessoa, ativ.cargaHoraria, ativ.data, ativ.nomeAtividade from pessoa as p " +
				"inner join aluno as a on p.idPessoa = a.idPessoa " +
				"inner join alunoAtividade as alativ on alativ.idAluno = a.idAluno " +
				"inner join atividade as ativ on alativ.idAtividade = ativ.idAtividade " +
				"where(a.idAluno = ? and ativ.idAtividade= ?);";
		
		Connection con = null;
		ResultSet result = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idAluno);
			stmt.setInt(2, idAtividade);
			
			result = stmt.executeQuery();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return result;
	}
	
	
}
