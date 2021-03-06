package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;

public class EnderecoDAO {
	private EnderecoModel endereco;

	public boolean adiconaEndereco(EnderecoModel endereco){
		boolean retorno=true;
		String sql = "insert into endereco (numero, bairro, cep, cidade, uf) values(?,?,?,?,?)";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setString(1, endereco.getNumero());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getCep());
			stmt.setString(4, endereco.getCidade());
			stmt.setString(5, endereco.getUf());
			
			stmt.execute();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível inserir. "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return retorno;
	}
	
	public int retornaMaxIdEndereco(){
		int retorno=0;
		String sql = "select max(idEndereco) as ultimo from endereco";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				retorno = rs.getInt("ultimo");
			}else{
				JOptionPane.showMessageDialog(null, "não foi possível pegar o retorno");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Não deu pra retornar"+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Deu merda, não deu pra fechar");
			}
		}
		return retorno;
	}
	
	public boolean alterarEndereco(EnderecoModel endereco){
		this.endereco = endereco;
		String sql = "UPDATE endereco SET numero=?, bairro=?, cep=?, cidade=?, uf=? WHERE(idEndereco = ?)";
		Connection con = null;
		
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, endereco.getNumero());
			stmt.setString(2, endereco.getBairro());
			stmt.setString(3, endereco.getCep());
			stmt.setString(4, endereco.getCidade());
			stmt.setString(5, endereco.getUf());
			stmt.setInt(6, endereco.getIdEndereco());

			stmt.execute();
			return true;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível Alterar. "+e.getMessage());
			return false;
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
	}
	
	public EnderecoModel getAlterarEndereco(int idEndereco){
		EnderecoModel endereco = new EnderecoModel();
		
		String sql = "select * from endereco where idEndereco=?";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idEndereco);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				endereco.setIdEndereco(result.getInt("idEndereco"));
				endereco.setNumero(result.getString("numero"));
				endereco.setBairro(result.getString("bairro"));
				endereco.setCep(result.getString("cep"));
				endereco.setCidade(result.getString("cidade"));
				endereco.setUf(result.getString("uf"));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Não deu pra inserir " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar");
			}
		}
		return endereco;
	}
	
}
