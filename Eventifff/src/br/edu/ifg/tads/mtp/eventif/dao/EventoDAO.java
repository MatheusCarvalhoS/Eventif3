package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.*;

public class EventoDAO {

	public boolean adicionaEvento(EventoModel evento) {
		boolean retorno = true;
		String sql = "insert into evento (nomeEvento,descricaoEvento,dataInicio,dataEncerramento, "
				+ "organizador, telefoneContato, localEvento, idEndereco) values (?,?,?,?,?,?,?,?)";
		Connection con = null;
		try {
			// prepared statement para inserção
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setDate(3, new Date(evento.getDataInicio().getTimeInMillis()));
			stmt.setDate(4, new Date(evento.getDataFim().getTimeInMillis()));
			stmt.setString(5, evento.getOrganizador());
			stmt.setString(6, evento.getTelefone());
			stmt.setString(7, evento.getLocal());
			stmt.setInt(8, evento.getIdEndereco());
			// executa
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
	
	
	public Vector<Vector<String>> buscaEventos(){
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from evento order by idEvento");
			
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				EventoModel evento = new EventoModel();
				evento.setIdEvento(result.getLong("idEvento"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setDescricao(result.getString("descricaoEvento"));
				Calendar dataInicio = Calendar.getInstance();
				dataInicio.setTime(result.getDate("dataInicio"));
				
				evento.setDataInicio(dataInicio);
				evento.setEmail(result.getString("email"));
				
				Calendar dataFim = Calendar.getInstance();
				dataFim.setTime(result.getDate("dataEncerramento"));
				
				evento.setDataFim(dataFim);
				
				evento.setOrganizador(result.getString("organizador"));
				evento.setTelefone(result.getString("telefoneContato"));
				evento.setLocal(result.getString("localEvento"));
				evento.setIdEndereco(result.getInt("idEndereco"));
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+evento.getIdEvento());
				colunas.add(evento.getNome());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				colunas.add(sdf.format(evento.getDataInicio().getTime()));
				
				colunas.add(sdf.format(evento.getDataFim().getTime()));
				colunas.add(evento.getEmail());
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add(""+evento.getIdEndereco());
				
				listaEventos.add(colunas);
			}
			result.close();
			stmt.close();
			return listaEventos;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabela de eventos! ");
			throw new RuntimeException(e);
		}
	}
	
	
	public EventoModel buscaEventos(int id){
		try {
			EventoModel evento = new EventoModel();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from evento where(idEvento = ?);");
			
			stmt.setInt(1,id);
			ResultSet result = stmt.executeQuery();
			if(result.next()){
				
				evento.setIdEvento(result.getLong("idEvento"));
				evento.setNome(result.getString("nomeEvento"));
				
				Calendar dataInicio = Calendar.getInstance();
				dataInicio.setTime(result.getDate("dataInicio"));
				
				evento.setDataInicio(dataInicio);
				
				Calendar dataFim = Calendar.getInstance();
				dataFim.setTime(result.getDate("dataEncerramento"));
				
				evento.setDataFim(dataFim);
				
			}else{
				JOptionPane.showMessageDialog(null, "Erro ao encontrar o Evento selecionado! ");
			}
			result.close();
			stmt.close();
			return evento;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na conexão com o banco de dados! ");
			throw new RuntimeException(e);
		}
	}
	
	public Vector<Vector<String>> pesquisarEvento(String nome){
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select * from evento where(nomeEvento like lower('"+nome+"%')) order by idEvento"); // o problema estava aqui, pois o like não retornava nada pelo fade de haver um espaço
			
			ResultSet result = stmt.executeQuery();
			while(result.next()){
				EventoModel evento = new EventoModel();
				
				evento.setIdEvento(result.getLong("idEvento"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setDescricao(result.getString("descricaoEvento"));
				Calendar dataInicio = Calendar.getInstance();
				dataInicio.setTime(result.getDate("dataInicio"));
				
				evento.setDataInicio(dataInicio);
				evento.setEmail(result.getString("email"));
				
				Calendar dataFim = Calendar.getInstance();
				dataFim.setTime(result.getDate("dataEncerramento"));
				
				evento.setDataFim(dataFim);
				
				evento.setOrganizador(result.getString("organizador"));
				evento.setTelefone(result.getString("telefoneContato"));
				evento.setLocal(result.getString("localEvento"));
				evento.setIdEndereco(result.getInt("idEndereco"));
				
				Vector<String> colunas = new Vector<String>();
				colunas.add(""+evento.getIdEvento());
				colunas.add(evento.getNome());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
				colunas.add(sdf.format(evento.getDataInicio().getTime()));
				
				colunas.add(sdf.format(evento.getDataFim().getTime()));
				colunas.add(evento.getEmail());
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add(""+evento.getIdEndereco());
				
				listaEventos.add(colunas);
			}
			result.close();
			stmt.close();
			return listaEventos;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar tabelas de Evento! " +e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
}
