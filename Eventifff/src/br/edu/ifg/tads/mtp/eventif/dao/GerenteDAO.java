package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;

public class GerenteDAO {	
	public int verificaLogin(String cpf, String senha){
		String sql = "select idPessoa from gerente g inner join pessoa p on g.idPessoa = p.idPessoa where(p.cpf=? and g.senha=?);";
		Connection con = null;
		int idGerente=0;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			stmt.executeQuery();
			ResultSet result = stmt.executeQuery();
			idGerente = result.getInt("idPessoa");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Verifique CPF e Senha! " + e.getMessage());
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
}
