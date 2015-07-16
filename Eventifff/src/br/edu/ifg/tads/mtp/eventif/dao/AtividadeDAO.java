package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.AtividadeModel;

public class AtividadeDAO {
	
	public boolean adicionaAtividade(AtividadeModel atividade) {
		boolean retorno = true;
		String sql = "insert into atividade(idEvento,nomeAtividade,descricaoAtividade,palestrante,horaInicio,horaEncerramento,data,tipoAtividade,cargaHoraria,numeroVagas,vagasD) values(?,?,?,?,?,?,?,?,?,?,?)";
		//Tá dando erro nessa porra
		Connection con = null;
		try {
			// prepared statement para inserção
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			// seta os valores
			
			stmt.setInt(1, atividade.getIdEvento());
			stmt.setString(2, atividade.getNomeAtividade());
			stmt.setString(3, atividade.getDescricaoAtividade());
			stmt.setString(4,atividade.getPalestrante());
			stmt.setString(5,atividade.getHoraInicio());
			stmt.setString(6, atividade.getHoraEncerramento());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");////////////
			stmt.setDate(7, new Date(format.parse(atividade.getData()).getTime()));/////////
			stmt.setString(8, atividade.getTipoAtividade());
			stmt.setString(9, atividade.getCargaHoraria());
			stmt.setInt(10, atividade.getNumeroVagas());
			stmt.setInt(11, atividade.getNumeroVagas());
			
			// executa
			stmt.execute();
		} catch (Exception e) {
			retorno = false;
			throw new RuntimeException(
					"falha ao tentar executar. "+e.getMessage());
		} finally {
			try {
				con.close();
			} catch (Exception e) {

				retorno = false;
				throw new RuntimeException(
						"não foi possível fechar a conexão com o BD");
			}
		}
		return retorno;
	}
	
	public Vector<Vector<String>> buscaAtividadesAluno(int idEvento, int idAluno){
		try {
			Vector<Vector<String>> listaAtividades = new Vector<Vector<String>>();
			PreparedStatement stmt1 = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from atividade where(idEvento = ?) order by idAtividade");
			
			stmt1.setInt(1, idEvento);
			ResultSet result1 = stmt1.executeQuery();
			
			while(result1.next()){
				String situacao = "Não inscrito";
				AtividadeModel atividade = new AtividadeModel();
				atividade.setIdAtividade(result1.getLong("idAtividade"));
				atividade.setNomeAtividade(result1.getString("nomeAtividade"));
				atividade.setDescricaoAtividade(result1.getString("descricaoAtividade"));
				atividade.setHoraInicio(result1.getString("horaInicio"));
				atividade.setHoraEncerramento(result1.getString("horaEncerramento"));
				atividade.setPalestrante(result1.getString("palestrante"));
				atividade.setTipoAtividade(result1.getString("tipoAtividade"));				
				atividade.setCargaHoraria(result1.getString("cargaHoraria"));
				atividade.setNumeroVagas(result1.getInt("numeroVagas"));
				
				atividade.setData(result1.getString("data"));
				String data = atividade.getData();///////// formatando manualmente a data;
				data=data+" ";
				String ano = data.substring(0, 4);
				String mes = data.substring(5, 7);
				String dia = data.substring(8, 10);
				data = (dia+"/"+mes+"/"+ano);
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+atividade.getIdAtividade());
				colunas.add(atividade.getNomeAtividade());
				colunas.add(atividade.getDescricaoAtividade());
				colunas.add(atividade.getHoraInicio());
				colunas.add(atividade.getHoraEncerramento());
				colunas.add(atividade.getPalestrante());
				colunas.add(atividade.getTipoAtividade());
				colunas.add(atividade.getCargaHoraria());
				colunas.add(""+atividade.getNumeroVagas());
				colunas.add(data);
				
				PreparedStatement stmt2 = new ConnectionFactory()
				.getConnection()
				.prepareStatement(
						"select idAluno from alunoAtividade where idAluno=? and idAtividade=?");
				stmt2.setInt(1, idAluno);
				stmt2.setInt(2, (int) atividade.getIdAtividade());
				
				ResultSet result2 = stmt2.executeQuery();
				if(result2.next()){
					situacao = "Inscrito";
				}
				
				colunas.add(situacao);
				listaAtividades.add(colunas);
			}
			result1.close();
			stmt1.close();
			return listaAtividades;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabela de eventos! ");
			throw new RuntimeException(e);
		}
	}
	
	public Vector<Vector<String>> buscaAtividades(int idEvento){
		try {
			Vector<Vector<String>> listaAtividades = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from atividade where(idEvento = ?) order by idAtividade");
			
			stmt.setInt(1, idEvento);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				AtividadeModel atividade = new AtividadeModel();
				atividade.setIdAtividade(result.getLong("idAtividade"));
				atividade.setNomeAtividade(result.getString("nomeAtividade"));
				atividade.setDescricaoAtividade(result.getString("descricaoAtividade"));
				atividade.setHoraInicio(result.getString("horaInicio"));
				atividade.setHoraEncerramento(result.getString("horaEncerramento"));
				atividade.setPalestrante(result.getString("palestrante"));
				atividade.setTipoAtividade(result.getString("tipoAtividade"));				
				atividade.setCargaHoraria(result.getString("cargaHoraria"));
				atividade.setNumeroVagas(result.getInt("numeroVagas"));
				
				atividade.setData(result.getString("data"));
				String data = atividade.getData();///////// formatando manualmente a data;
				data=data+" ";
				String ano = data.substring(0, 4);
				String mes = data.substring(5, 7);
				String dia = data.substring(8, 10);
				data = (dia+"/"+mes+"/"+ano);
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+atividade.getIdAtividade());
				colunas.add(atividade.getNomeAtividade());
				colunas.add(atividade.getDescricaoAtividade());
				colunas.add(atividade.getHoraInicio());
				colunas.add(atividade.getHoraEncerramento());
				colunas.add(atividade.getPalestrante());
				colunas.add(atividade.getTipoAtividade());
				colunas.add(atividade.getCargaHoraria());
				colunas.add(""+atividade.getNumeroVagas());
				colunas.add(data);
				
				listaAtividades.add(colunas);
			}
			result.close();
			stmt.close();
			return listaAtividades;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabelas de Atividade! " +e.getMessage());
			throw new RuntimeException(e);
		}
	}
	public Vector<Vector<String>> pesquisaAtividade(String nome, int idEvento){
		try {
			Vector<Vector<String>> listaAtividades = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from atividade where(nomeAtividade like '%"+nome+"%' and idEvento = ?) order by idAtividade");
			
			stmt.setInt(1, idEvento);
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				AtividadeModel atividade = new AtividadeModel();
				atividade.setIdAtividade(result.getLong("idAtividade"));
				atividade.setNomeAtividade(result.getString("nomeAtividade"));
				atividade.setDescricaoAtividade(result.getString("descricaoAtividade"));
				atividade.setHoraInicio(result.getString("horaInicio"));
				atividade.setHoraEncerramento(result.getString("horaEncerramento"));
				atividade.setPalestrante(result.getString("palestrante"));
				atividade.setTipoAtividade(result.getString("tipoAtividade"));				
				atividade.setCargaHoraria(result.getString("cargaHoraria"));
				atividade.setNumeroVagas(result.getInt("numeroVagas"));
				atividade.setData(result.getString("data"));
				
				String data = atividade.getData();///////// formatando manualmente a data;
				data=data+" ";
				String ano = data.substring(0, 4);
				String mes = data.substring(5, 7);
				String dia = data.substring(8, 10);
				data = (dia+"/"+mes+"/"+ano);
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+atividade.getIdAtividade());
				colunas.add(atividade.getNomeAtividade());
				colunas.add(atividade.getDescricaoAtividade());
				colunas.add(atividade.getHoraInicio());
				colunas.add(atividade.getHoraEncerramento());
				colunas.add(atividade.getPalestrante());
				colunas.add(atividade.getTipoAtividade());
				colunas.add(atividade.getCargaHoraria());
				colunas.add(""+atividade.getNumeroVagas());
				colunas.add(data);
				
				listaAtividades.add(colunas);
			}
			result.close();
			stmt.close();
			return listaAtividades;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabelas de Atividade! " +e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public AtividadeModel buscaAtividade(int idAtividade){
		try {
			AtividadeModel atividade = new AtividadeModel();
			
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from atividade where(idAtividade = ?)");
			
			stmt.setInt(1, idAtividade);
			ResultSet result = stmt.executeQuery();
			
			if(result.next()){
				
				atividade.setIdAtividade(result.getLong("idAtividade"));
				atividade.setNomeAtividade(result.getString("nomeAtividade"));
				atividade.setDescricaoAtividade(result.getString("descricaoAtividade"));
				atividade.setHoraInicio(result.getString("horaInicio"));
				atividade.setHoraEncerramento(result.getString("horaEncerramento"));
				atividade.setPalestrante(result.getString("palestrante"));
				atividade.setTipoAtividade(result.getString("tipoAtividade"));				
				atividade.setCargaHoraria(result.getString("cargaHoraria"));
				atividade.setNumeroVagas(result.getInt("numeroVagas"));
				atividade.setData(result.getString("data"));
			}
			result.close();
			stmt.close();
			return atividade;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabelas de Atividade! " +e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public boolean alterarAtividade(AtividadeModel atividade) throws ParseException {
		boolean retorno = false;
		String sql = "update atividade set nomeAtividade = ?,descricaoAtividade = ?,palestrante = ?,horaInicio = ?,"
				+ "horaEncerramento = ?,data = ?,tipoAtividade = ?,cargaHoraria = ?, numeroVagas = ?,vagasD = ? where(idAtividade = ?)";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, atividade.getNomeAtividade());
			stmt.setString(2, atividade.getDescricaoAtividade());
			stmt.setString(3,atividade.getPalestrante());
			stmt.setString(4,atividade.getHoraInicio());
			stmt.setString(5, atividade.getHoraEncerramento());
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			stmt.setDate(6, new Date(format.parse(atividade.getData()).getTime()));
			stmt.setString(7, atividade.getTipoAtividade());
			stmt.setString(8, atividade.getCargaHoraria());
			stmt.setInt(9, atividade.getNumeroVagas());
			stmt.setInt(10, atividade.getNumeroVagas());
			stmt.setLong(11, atividade.getIdAtividade());

			stmt.execute();
			retorno = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "falha ao tentar executar. "+e.getMessage());
			
		} finally {
			try {
				con.close();
			} catch (SQLException e) {

				retorno = false;
				throw new RuntimeException(
						"não foi possível fechar a conexão com o BD");
			}
		}
		return retorno;
	}
	
	public boolean excluirAtividade(int idEvento, int idAtividade){
		boolean retorno = false;
		String sql="delete from atividade where(idEvento = ? and idAtividade = ?);"; 
		
		Connection con = null;
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idEvento);
			stmt.setInt(2, idAtividade);
			
			stmt.execute();
			
			retorno=true;
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO ao tentar Deletar Atividade! "+e.getMessage());
		}
		
		return retorno;
	}
	
}
