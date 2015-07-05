package br.edu.ifg.tads.mtp.eventif.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.JOptionPane;

import br.edu.ifg.tads.mtp.eventif.bd.ConnectionFactory;

public class MonitorDAO {

	public void adicionaMonitor(int idMonitor, int idAtividade) {
		
		String verifica = "select idMonitor from monitor where(idAluno=?);";

		Connection con = null;

		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt1 = con.prepareStatement(verifica);

			stmt1.setInt(1, idMonitor);

			ResultSet result = stmt1.executeQuery();

			if (result.next()) {
				JOptionPane
				.showMessageDialog(null,
						"Aluno já é um Monitor!");
			}else{
				
				String verifica2 = "select horaInicio, horaEncerramento from atividade inner join alunoAtividade on atividade.idAtividade=alunoAtividade.idAtividade where(alunoAtividade.idAluno = ?)" +
								"intersect " +
								"select horaInicio, horaEncerramento from atividade where(idAtividade=?);";
				try {
					con = new ConnectionFactory().getConnection();
					PreparedStatement stmt2 = con.prepareStatement(verifica2);
					stmt2.setInt(1, idMonitor);
					stmt2.setInt(2, idAtividade);

					ResultSet result2 = stmt2.executeQuery();
					if (result2.next()) {
						JOptionPane
						.showMessageDialog(null,
								"Aluno Já cadastrado em uma Atividade que Sobrepoe!");
					} else {
						String sql = "insert into monitor(idAluno, senha) values(?,(select senha from aluno where(? = idAluno)));";

						try {
							con = new ConnectionFactory().getConnection();
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setInt(1, idMonitor);
							stmt.setInt(2, idMonitor);

							stmt.execute();

							adicionaMonitorAtividade(idMonitor, idAtividade);
						} catch (SQLException e) {
							JOptionPane.showMessageDialog(
									null,
									"Não foi possível inserir Monitor. "
											+ e.getMessage());
						} finally {
							try {
								con.close();
							} catch (SQLException e) {
								JOptionPane.showMessageDialog(null,
										"Impossível fechar conexão");
							}
						}
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(
							null,
							"Não foi possível inserir Monitor. "
									+ e.getMessage());
				}
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível inserir Monitor. " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane
						.showMessageDialog(null, "Impossível fechar conexão");
			}
		}

	}

	public void adicionaMonitorAtividade(int idMonitor, int idAtividade) {

		String sql = "insert into monitorAtividade(idMonitor, idAtividade) values(?,?);";

		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, idMonitor);
			stmt.setInt(2, idAtividade);

			stmt.execute();
			JOptionPane.showMessageDialog(null,
					"Monitor Cadastrado com Sucesso! ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Monitor Não Cadastrado! ");
			JOptionPane.showMessageDialog(
					null,
					"Não foi possível inserir MonitorAtividade. "
							+ e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane
						.showMessageDialog(null, "Impossível fechar conexão");
			}
		}
	}

	public void entradaAluno(int idAluno, int idAtividade) {

		String sql = "insert into PresencaAtividade(horaCheckin, horaCheckout, idAtividade, idAluno) values(?,?,?,?);";

		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setTimestamp(1, (Timestamp) Calendar.getInstance().getTime());
			stmt.setTimestamp(2, null);
			stmt.setInt(3, idAtividade);
			stmt.setInt(4, idAluno);

			stmt.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível Fazer Checkin ! " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Impossível fechar conexão! " + e.getMessage());
			}
		}
	}

	public void saidaAluno(int idAluno, int idAtividade) {
		String sql = "insert into PresencaAtividade(horaCheckin, horaCheckout, idAtividade, idAluno) values(?,?,?,?);";
		Connection con = null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setTimestamp(1, null);
			stmt.setTimestamp(2, (Timestamp) Calendar.getInstance().getTime());
			stmt.setInt(3, idAtividade);
			stmt.setInt(4, idAluno);

			stmt.execute();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível Fazer Checkin ! " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Impossível fechar conexão! " + e.getMessage());
			}
		}
	}

}
