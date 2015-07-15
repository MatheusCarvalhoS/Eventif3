package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;
import br.edu.ifg.tads.mtp.eventif.model.*;

public class EventoDAO {
	private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");////////////

	public boolean adicionaEvento(EventoModel evento) throws ParseException {
		boolean retorno = true;
		String sql = "insert into evento (nomeEvento,descricaoEvento,dataInicio,dataEncerramento, "
				+ "organizador, telefoneContato, localEvento, idEndereco, email) values (?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		try {
			// prepared statement para inserção
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
	
			stmt.setDate(3, new Date(format.parse(evento.getDataInicio()).getTime()));/////////
			stmt.setDate(4, new Date(format.parse(evento.getDataFim()).getTime()));
			stmt.setString(5, evento.getOrganizador());
			stmt.setString(6, evento.getTelefone());
			stmt.setString(7, evento.getLocal());
			stmt.setInt(8, evento.getIdEndereco());
			stmt.setString(9, evento.getEmail());
			// executa
			stmt.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível inserir. "
					+ e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane
						.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return retorno;
	}

	public Vector<Vector<String>> buscaEventosAluno(int idAluno) {
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt1 = new ConnectionFactory().getConnection()
					.prepareStatement("select * from evento order by idEvento");

			ResultSet result1 = stmt1.executeQuery();

			while (result1.next()) {
				String situacao = "Não inscrito";
				EventoModel evento = new EventoModel();
				evento.setIdEvento(result1.getLong("idEvento"));
				evento.setNome(result1.getString("nomeEvento"));
				evento.setDescricao(result1.getString("descricaoEvento"));
				evento.setDataInicio(result1.getString("dataInicio"));
				evento.setEmail(result1.getString("email"));
				evento.setDataFim(result1.getString("dataEncerramento"));
				
				String dataInicio = evento.getDataInicio();///////// formatando manualmente a data;
				dataInicio=dataInicio+" ";
				String ano = dataInicio.substring(0, 4);
				String mes = dataInicio.substring(5, 7);
				String dia = dataInicio.substring(8, 10);
				dataInicio = (dia+"/"+mes+"/"+ano);
				
				String dataFim = evento.getDataFim();///////// formatando manualmente a data;
				dataFim=dataFim+" ";
				ano = dataFim.substring(0, 4);
				mes = dataFim.substring(5, 7);
				dia = dataFim.substring(8, 10);
				dataFim = (dia+"/"+mes+"/"+ano);
				
				System.out.println("dtIn: "+dataInicio+" dtFim: "+dataFim);

				evento.setOrganizador(result1.getString("organizador"));
				evento.setTelefone(result1.getString("telefoneContato"));
				evento.setLocal(result1.getString("localEvento"));
				evento.setIdEndereco(result1.getInt("idEndereco"));

				Vector<String> colunas = new Vector<String>();
				colunas.add("" + evento.getIdEvento());
				colunas.add(evento.getNome());
				colunas.add(dataInicio);
				colunas.add(dataFim);
				colunas.add(evento.getEmail());
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add("" + evento.getIdEndereco());

				PreparedStatement stmt2 = new ConnectionFactory()
						.getConnection()
						.prepareStatement(
								"select idAluno from alunoEvento where idAluno=? and idEvento=?");
				stmt2.setInt(1, idAluno);
				stmt2.setInt(2, (int) evento.getIdEvento());

				ResultSet result2 = stmt2.executeQuery();
				if (result2.next()) {
					situacao = "Inscrito";
				}
				colunas.add(situacao);

				listaEventos.add(colunas);
			}
			result1.close();
			stmt1.close();
			return listaEventos;

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar tabela de eventos! e: "+e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Vector<Vector<String>> buscaEventosMonitor(int idMonitor) {
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt1 = new ConnectionFactory()
					.getConnection()
					.prepareStatement(
							"select distinct * from evento inner join atividade on evento.idEvento = atividade.idEvento inner join monitorAtividade on monitorAtividade.idAtividade=atividade.idAtividade inner join monitor on monitorAtividade.idMonitor=monitor.idMonitor where(monitor.idMonitor=?)");

			stmt1.setInt(1, idMonitor);
			ResultSet result1 = stmt1.executeQuery();

			while (result1.next()) {
				String situacao = "Não inscrito";
				EventoModel evento = new EventoModel();
				evento.setIdEvento(result1.getLong("idEvento"));
				evento.setNome(result1.getString("nomeEvento"));
				evento.setDescricao(result1.getString("descricaoEvento"));
				evento.setDataInicio(result1.getString("dataInicio"));
				evento.setDataFim(result1.getString("dataEncerramento"));
				evento.setEmail(result1.getString("email"));
				
				String dataInicio = evento.getDataInicio();///////// formatando manualmente a data;
				dataInicio=dataInicio+" ";
				String ano = dataInicio.substring(0, 4);
				String mes = dataInicio.substring(5, 7);
				String dia = dataInicio.substring(8, 10);
				dataInicio = (dia+"/"+mes+"/"+ano);
				
				String dataFim = evento.getDataFim();///////// formatando manualmente a data;
				dataFim=dataFim+" ";
				ano = dataFim.substring(0, 4);
				mes = dataFim.substring(5, 7);
				dia = dataFim.substring(8, 10);
				dataFim = (dia+"/"+mes+"/"+ano);
				
				evento.setOrganizador(result1.getString("organizador"));
				evento.setTelefone(result1.getString("telefoneContato"));
				evento.setLocal(result1.getString("localEvento"));
				evento.setIdEndereco(result1.getInt("idEndereco"));

				Vector<String> colunas = new Vector<String>();
				colunas.add("" + evento.getIdEvento());
				colunas.add(evento.getNome());
				colunas.add(dataInicio);
				colunas.add(dataFim);
				colunas.add(evento.getEmail());	
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add("" + evento.getIdEndereco());

				PreparedStatement stmt2 = new ConnectionFactory()
						.getConnection()
						.prepareStatement(
								"select idAluno from alunoEvento where idAluno=? and idEvento=?");
				stmt2.setInt(1, idMonitor);
				stmt2.setInt(2, (int) evento.getIdEvento());

				ResultSet result2 = stmt2.executeQuery();
				if (result2.next()) {
					situacao = "Inscrito";
				}
				colunas.add(situacao);

				listaEventos.add(colunas);
			}
			result1.close();
			stmt1.close();
			return listaEventos;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar tabela de eventos! ");
			throw new RuntimeException(e);
		}
	}

	public Vector<Vector<String>> buscaEventos() {
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt1 = new ConnectionFactory().getConnection()
					.prepareStatement("select * from evento order by idEvento");

			ResultSet result1 = stmt1.executeQuery();

			while (result1.next()) {
				String situacao = "Não inscrito";
				EventoModel evento = new EventoModel();
				evento.setIdEvento(result1.getLong("idEvento"));
				evento.setNome(result1.getString("nomeEvento"));
				evento.setDescricao(result1.getString("descricaoEvento"));
				evento.setDataInicio(result1.getString("dataInicio"));
				evento.setEmail(result1.getString("email"));
				evento.setDataFim(result1.getString("dataEncerramento"));
				
				String dataInicio = evento.getDataInicio();///////// formatando manualmente a data;
				dataInicio=dataInicio+" ";
				String ano = dataInicio.substring(0, 4);
				String mes = dataInicio.substring(5, 7);
				String dia = dataInicio.substring(8, 10);
				dataInicio = (dia+"/"+mes+"/"+ano);
				
				String dataFim = evento.getDataFim();///////// formatando manualmente a data;
				dataFim=dataFim+" ";
				ano = dataFim.substring(0, 4);
				mes = dataFim.substring(5, 7);
				dia = dataFim.substring(8, 10);
				dataFim = (dia+"/"+mes+"/"+ano);

				evento.setOrganizador(result1.getString("organizador"));
				evento.setTelefone(result1.getString("telefoneContato"));
				evento.setLocal(result1.getString("localEvento"));
				evento.setIdEndereco(result1.getInt("idEndereco"));

				Vector<String> colunas = new Vector<String>();
				colunas.add("" + evento.getIdEvento());
				colunas.add(evento.getNome());
				colunas.add(dataInicio);
				colunas.add(dataFim);
				colunas.add(evento.getEmail());
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add("" + evento.getIdEndereco());
				listaEventos.add(colunas);
			}
			result1.close();
			stmt1.close();
			return listaEventos;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar tabela de eventos! ");
			throw new RuntimeException(e);
		}
	}

	public EventoModel buscaEvento(int id) {
		try {
			EventoModel evento = new EventoModel();
			PreparedStatement stmt = new ConnectionFactory().getConnection()
					.prepareStatement(
							"select * from evento where(idEvento = ?);");

			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				evento.setIdEvento(result.getLong("idEvento"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setDataInicio(result.getString("dataInicio"));
				evento.setEmail(result.getString("email"));
				evento.setDataFim(result.getString("dataEncerramento"));
				
				evento.setIdEndereco(result.getInt("idEndereco"));
				evento.setOrganizador(result.getString("organizador"));
				evento.setTelefone(result.getString("telefoneContato"));
				evento.setEmail(result.getString("email"));
				evento.setLocal(result.getString("localEvento"));

			} else {
				JOptionPane.showMessageDialog(null,
						"Erro ao encontrar o Evento selecionado! ");
			}
			result.close();
			stmt.close();
			return evento;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro na conexão com o banco de dados! " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Vector<Vector<String>> pesquisarEvento(String nome) {
		try {
			Vector<Vector<String>> listaEventos = new Vector<Vector<String>>();
			PreparedStatement stmt = new ConnectionFactory().getConnection()
					.prepareStatement(
							"select * from evento where(nomeEvento like lower('"
									+ nome + "%')) order by idEvento");

			ResultSet result = stmt.executeQuery();
			while (result.next()) {
				EventoModel evento = new EventoModel();
				evento.setIdEvento(result.getLong("idEvento"));
				evento.setNome(result.getString("nomeEvento"));
				evento.setDescricao(result.getString("descricaoEvento"));
				evento.setEmail(result.getString("email"));
				evento.setDataInicio(result.getString("dataInicio"));
				evento.setDataFim(result.getString("dataEncerramento"));
				
				String dataInicio = evento.getDataInicio();///////// formatando manualmente a data;
				dataInicio=dataInicio+" ";
				String ano = dataInicio.substring(0, 4);
				String mes = dataInicio.substring(5, 7);
				String dia = dataInicio.substring(8, 10);
				dataInicio = (dia+"/"+mes+"/"+ano);
				
				String dataFim = evento.getDataFim();///////// formatando manualmente a data;
				dataFim=dataFim+" ";
				ano = dataFim.substring(0, 4);
				mes = dataFim.substring(5, 7);
				dia = dataFim.substring(8, 10);
				dataFim = (dia+"/"+mes+"/"+ano);

				evento.setOrganizador(result.getString("organizador"));
				evento.setTelefone(result.getString("telefoneContato"));
				evento.setLocal(result.getString("localEvento"));
				evento.setIdEndereco(result.getInt("idEndereco"));

				Vector<String> colunas = new Vector<String>();
				colunas.add("" + evento.getIdEvento());
				colunas.add(evento.getNome());
				colunas.add(dataInicio);
				colunas.add(dataFim);
				colunas.add(evento.getEmail());
				colunas.add(evento.getOrganizador());
				colunas.add(evento.getTelefone());
				colunas.add(evento.getLocal());
				colunas.add("" + evento.getIdEndereco());

				listaEventos.add(colunas);
			}
			result.close();
			stmt.close();
			return listaEventos;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar tabelas de Evento! " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public boolean alterarEvento(EventoModel evento) throws ParseException {
		boolean retorno = false;
		String sql = "update evento set nomeEvento= ? ,descricaoEvento= ? ,dataInicio= ? ,dataEncerramento= ? , "
				+ "organizador= ? , telefoneContato= ? , localEvento= ? , idEndereco= ?, email= ?   where (idEvento = ?)";
		Connection con = null;
		try {
			// prepared statement para inserção
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, evento.getNome());
			stmt.setString(2, evento.getDescricao());
			stmt.setDate(3, new Date(format.parse(evento.getDataInicio()).getTime()));/////////
			stmt.setDate(4, new Date(format.parse(evento.getDataFim()).getTime()));
			stmt.setString(5, evento.getOrganizador());
			stmt.setString(6, evento.getTelefone());
			stmt.setString(7, evento.getLocal());
			stmt.setInt(8, evento.getIdEndereco());
			stmt.setString(9, evento.getEmail());
			stmt.setLong(10, evento.getIdEvento());
			// executa
			stmt.execute();
			retorno = true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Não foi possível Alterar. "
					+ e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane
						.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
		return retorno;
	}

	public boolean excluirEvento(int idEvento){
		boolean retorno = false;
		Connection con = null;
		String sql = "delete from evento where(idEvento = ?);";
		
		try{
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			
			stmt.setInt(1, idEvento);
			
			stmt.execute();
			retorno = true;
		}catch(SQLException e){
			retorno = false;
		}
		return retorno;
	}
}
