package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.AtividadeModel;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;

public class AtividadeDAO {
	
	public boolean adicionaAtividade(AtividadeModel atividade) {
		boolean retorno = true;
		String sql = "insert into atividade(idEvento,nomeAtividade,descricaoAtividade,palestrante,horaInicio,horaEncerramento,data,tipoAtividade,cargaHoraria,numeroVagas) values(?,?,?,?,?,?,?,?,?,?)";
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
			stmt.setDate(7, new Date(atividade.getData().getTimeInMillis()));
			stmt.setString(8, atividade.getTipoAtividade());
			stmt.setString(9, atividade.getCargaHoraria());
			stmt.setInt(10, atividade.getNumeroVagas());
			
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
	
	public Vector<Vector<String>> buscaAtividades(int id){
		try {
			Vector<Vector<String>> listaAtividades = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from atividade where(idEvento = ?) order by idAtividade");
			
			stmt.setInt(1, id);
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
				
				
				Calendar data = Calendar.getInstance(); // o erro está aqui, não consegue listar = null
				data.setTime(result.getDate("data"));
				atividade.setData(data);
				
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
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				colunas.add(sdf.format(atividade.getData().getTime()));
				
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
							"select * from atividade where(nomeAtividade like lower('"+nome+"%') and idEvento = ?) order by idAtividade");
			
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
				
				
				Calendar data = Calendar.getInstance(); // o erro está aqui, não consegue listar = null
				data.setTime(result.getDate("data"));
				atividade.setData(data);
				
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
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				colunas.add(sdf.format(atividade.getData().getTime()));
				
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
	
}
