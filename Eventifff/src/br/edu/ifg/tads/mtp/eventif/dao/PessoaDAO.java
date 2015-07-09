package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.PessoaModel;

public class PessoaDAO {
	public boolean adiconaPessoa(PessoaModel aluno){
			boolean retorno=true;
			String sql = "insert into pessoa (nomePessoa, cpf, rg, idEndereco) values(?,?,?,?)";
			Connection con = null;
			try{
				con = new ConnectionFactory().getConnection();
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setString(1, aluno.getNomePessoa());
				stmt.setString(2, aluno.getCpf());
				stmt.setString(3, aluno.getRg());
				stmt.setInt(4, aluno.getIdEndereco());
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
	
	public int retornaMaxIdPessoa(){
		int retorno=0;
		String sql = "select max(idPessoa) as ultimo from pessoa;";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				retorno = rs.getInt("ultimo");
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Não foi possível inserir. "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return retorno;
	}
	
	public boolean verificaExistencia(String cpf){
		String sql = "select idPessoa from pessoa where(cpf=?);";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				return true;
			}
		}catch(Exception e){
		} finally{
			try{
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return false;
	}
	
	public boolean verificaAtivo(String cpf){
		String sql = "select ativo from pessoa where(cpf=?);";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet result = stmt.executeQuery();
			if(result.next() && result.getBoolean("ativo")){
				return true;
			}
		}catch(Exception e){
			
		} finally{
			try{
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return false;
	}
	
	public boolean reativaPessoa(String cpf){
		String sql = "update pessoa set ativo = 'true' where(cpf=?);";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				return true;
			}
		}catch(Exception e){
		} finally{
			try{
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return false;
	}
	
	public boolean alterarPessoa(AlunoModel aluno){
		System.out.println(" contato banco == idAluno = "+ aluno.getIdAluno());
		String sql = "UPDATE pessoa SET nomePessoa=?, cpf=?, rg=? WHERE(idPessoa = ?)";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, aluno.getNomePessoa());
			stmt.setString(2, aluno.getCpf());
			stmt.setString(3, aluno.getRg());
			stmt.setInt(4, aluno.getIdAluno());
			
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
	
	
	
}

