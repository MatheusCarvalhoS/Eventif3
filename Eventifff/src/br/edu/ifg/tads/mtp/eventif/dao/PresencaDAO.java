package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;

public class PresencaDAO {
	public int getIdPessoaCPF(String cpf){
		int idPessoa=0;
		String sql = "select idPessoa as id from pessoa where (cpf=?)";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				idPessoa=result.getInt("id");
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação! "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return idPessoa;
	}
	
	public String verificaChekinChekoutEvento(int idAluno, int idEvento){
		String sql1 = "select idAluno as id from alunoEvento where (idAluno=? and idEvento=?)";
		Connection con = null;
		String resultado="nada";
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setInt(1, idAluno);
			stmt1.setInt(2, idEvento);
			
			ResultSet result = stmt1.executeQuery();
			
			if(result.next()){
				idAluno = result.getInt("id");
				System.out.println("entrei idAluno = "+idAluno);
				
				String sql2 = "select pE.horaCheckin as entrada, pE.horaCheckout as saida from presencaEvento as pE "
						+ "where(pE.idPresencaEvento = (select max(idPresencaEvento) from presencaEvento "
						+ "where (idAluno=? and idEvento=?)) and pE.idAluno=? and pE.idEvento=?)";
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, idAluno);
				stmt2.setInt(2, idEvento);
				stmt2.setInt(3, idAluno);
				stmt2.setInt(4, idEvento);
				
				
				ResultSet result2 = stmt2.executeQuery();
				
				if(result2.next()){
					
					if(result2.getTimestamp("saida") == null){
						resultado = "checkout";
					}else if(result2.getTimestamp("entrada") == null){
						resultado = "checkin";
					}else{
						System.out.println("entrei não sei");
						resultado = "checkin";
					}
				}else{
					resultado = "checkin";
				}
			}
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível realizar esta operação! "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return resultado;
	}
	
	public void chekinEvento(int idAluno, int idEvento){
		String sql = "insert into presencaEvento(horaCheckin, idAluno, idEvento) values(current_Timestamp, ?, ?)";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql);
			stmt1.setInt(1, idAluno);
			stmt1.setInt(2, idEvento);
			stmt1.execute();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível fazer checkin "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
	}
	
	public void checkoutEvento(int idAluno, int idEvento){
		String sql = "update presencaEvento set horaCheckout = current_Timestamp "
				+"where(idPresencaEvento = (select max(idPresencaEvento) from presencaEvento "
				+"where (idAluno=? and idEvento=?)) and idAluno=? and idEvento=?)";
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql);
			stmt1.setInt(1, idAluno);
			stmt1.setInt(2, idEvento);
			stmt1.setInt(3, idAluno);
			stmt1.setInt(4, idEvento);
			stmt1.execute();
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Não foi possível fazer checkin "+e.getMessage());
		} finally{
			try{
				con.close();
			}catch(SQLException e){
				JOptionPane.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
	}
}
