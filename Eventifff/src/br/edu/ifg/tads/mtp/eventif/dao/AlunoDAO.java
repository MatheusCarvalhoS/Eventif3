package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.AlunoModel;
import br.edu.ifg.tads.mtp.eventif.model.EnderecoModel;

public class AlunoDAO {
	public EnderecoModel getEnderecoMinhaConta(int idEndereco){
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
	
	public AlunoModel getAlunoMinhaConta(int idAluno){
		AlunoModel aluno = new AlunoModel();
		EnderecoModel endereco = new EnderecoModel();
		aluno.setIdAluno(idAluno);
		String sql1 = "select * from aluno where idAluno=?";
		String sql2 = "select * from pessoa where idPessoa=?";
		String sql3 = "select * from endereco where idEndereco=?";
		Connection con = null;
		
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setInt(1, aluno.getIdAluno());
			ResultSet result1 = stmt1.executeQuery();
			if(result1.next()){
				aluno.setSenha(result1.getString("senha"));
				
				PreparedStatement stmt2 = con.prepareStatement(sql2);
				stmt2.setInt(1, aluno.getIdAluno());
				ResultSet result2 = stmt2.executeQuery();
				if(result2.next()){
					aluno.setIdAluno(result2.getInt("idPessoa"));
					aluno.setNomePessoa(result2.getString("nomePessoa"));
					aluno.setCpf(result2.getString("cpf"));
					aluno.setAtivo(result2.getBoolean("ativo"));
					aluno.setRg(result2.getString("rg"));
					aluno.setIdEndereco(result2.getInt("idEndereco"));
				}
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
		return aluno;
	}
	
	public boolean adiconaAluno(AlunoModel aluno) {
		boolean retorno = true;
		String sql = "insert into aluno (idPessoa, senha) values(?,?)";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, aluno.getIdPessoa());
			stmt.setString(2, aluno.getSenha());

			stmt.execute();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Não deu pra inserir " + e.getMessage());
			retorno = false;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar");
				retorno = false;
			}
		}
		return retorno;
	}
	
	public boolean inscricaoEvento(int idAluno, int idEvento){
		boolean retorno = true;
		String sql = "insert into alunoEvento (idAluno, idEvento) values(?,?)";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setInt(1, idAluno);
			stmt.setInt(2, idEvento);

			stmt.execute();
		} catch (Exception e) {
			retorno = false;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar");
				retorno = false;
			}
		}
		return retorno;
	}
	
	public boolean sairDeAtividade(int idAluno, int idAtividade){
		boolean retorno = true;
		String sql1 = "delete from alunoAtividade where idAluno=? and idAtividade=?";
		String sql2 = "update atividade set vagasD=vagasD+1 where idAtividade=?";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			stmt1.setInt(1, idAluno);
			stmt1.setInt(2, idAtividade);
			stmt1.execute();
			
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			stmt2.setInt(1, idAtividade);
			stmt2.execute();
		} catch (Exception e) {
			retorno = false;
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar");
				retorno = false;
			}
		}
		return retorno;
	} 

	public boolean sairDoEvento(int idAluno, int idEvento){
		boolean retorno = true;
		String sql1 = "delete from alunoEvento where idAluno=? and idEvento=?";
		String sql2 = "delete from alunoAtividade as art using atividade as atv where atv.idAtividade=art.idAtividade and art.idAluno=? and atv.idEvento=?";
		String sql3 = "select idAtividade from alunoAtividade where (idAluno=?)";
		String sql4 = "update atividade set vagasD=vagasD+1 where (idAtividade=?);";
		//Alterar as vagas disponíveis de todas as atividades;
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			PreparedStatement stmt3 = con.prepareStatement(sql3);
			PreparedStatement stmt4 = con.prepareStatement(sql4);
			
			stmt1.setInt(1, idAluno);
			stmt1.setInt(2, idEvento);
			stmt2.setInt(1, idAluno);
			stmt2.setInt(2, idEvento);
			stmt3.setInt(1, idAluno);
			stmt1.execute();
			ResultSet result3 = stmt3.executeQuery();
			while(result3.next()){
				stmt4.setInt(1, result3.getInt("idAtividade"));
				stmt4.execute();
			}
			stmt2.execute();
		} catch (SQLException e) {
			retorno = false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar");
				retorno = false;
			}
		}
		return retorno;
	}
	
	public boolean inscricaoAtividade(int idAluno, int idAtividade){
		boolean retorno = true;
		String sql2 = "insert into alunoAtividade (idAluno, idAtividade) values(?,?)";
		String sql1 = "select vagasD from atividade where (idAtividade = ?);";
		String sql3 = "update atividade set vagasD=vagasD-1 where (idAtividade=?);";
		//Criar um campo na tabela atividades como Estoque
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(sql1);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			PreparedStatement stmt3 = con.prepareStatement(sql3);
			stmt1.setInt(1, idAtividade);
			ResultSet result1 = stmt1.executeQuery();
			if(result1.next()){
				int vagasDisponiveis = result1.getInt("vagasD");
				System.out.println("Vagas Disponiveis: "+vagasDisponiveis);
				if(vagasDisponiveis>0){
					vagasDisponiveis--;
					stmt2.setInt(1, idAluno);
					stmt2.setInt(2, idAtividade);
					stmt2.execute();
					stmt3.setInt(1,idAtividade);
					stmt3.execute();
					retorno = true;
				}else{
					JOptionPane.showMessageDialog(null,
							"Não existem vagas disponíveis");
					retorno = false;
				}
			}else{
				JOptionPane.showMessageDialog(null,
						"Não existe atividade com id: "+idAtividade);
				retorno = false;
			}
		} catch (SQLException e) {
			retorno = false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Deu merda, não deu pra fechar. "+e.getMessage());
				retorno = false;
			}
		}
		return retorno;
	}
	
	
	public Vector<Vector<String>> buscaAlunos() throws SQLException{
		try{
			Vector<Vector<String>> listaAlunos = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from pessoa as p inner join aluno as a on p.idPessoa=a.idPessoa;");
			
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				AlunoModel aluno = new AlunoModel();
				aluno.setIdAluno(result.getInt("idAluno"));
				aluno.setIdPessoa(result.getInt("idPessoa"));
				aluno.setCpf(result.getString("cpf"));
				aluno.setNomePessoa(result.getString("nomePessoa"));
				aluno.setRg(result.getString("rg"));
				
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+aluno.getIdAluno());
				colunas.add(""+aluno.getIdPessoa());
				colunas.add(aluno.getNomePessoa());
				colunas.add(aluno.getRg());
				colunas.add(aluno.getCpf());
				
				listaAlunos.add(colunas);
			}
			result.close();
			stmt.close();
			return listaAlunos;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "Erro ao listar tabelas de Alunos!  erro: " +e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public int verificaLogin(String cpf, String senha){
		String sql = "select idAluno from aluno a inner join pessoa p on a.idPessoa = p.idPessoa where(p.cpf=? and a.senha=?);";
		Connection con = null;
		int idAluno=-1;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				idAluno = result.getInt("idAluno");
			}
		} catch (SQLException e) {
			
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Impossível fechar conexão! " + e.getMessage());
			}
		}
		return idAluno;
	}

}
