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
				JOptionPane.showMessageDialog(null, "Aluno já é um Monitor!");
			} else {

				String verifica2 = "select horaInicio, horaEncerramento from atividade inner join alunoAtividade on atividade.idAtividade=alunoAtividade.idAtividade where(alunoAtividade.idAluno = ?)"
						+ "intersect "
						+ "select horaInicio, horaEncerramento from atividade where(idAtividade=?);";
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

							String sql2 = "select idMonitor from monitor where(idAluno=?)";

							// adicionaMonitorAtividade(idMonitor, idAtividade);
							// irá ter que inserir o monitor depois pegar o
							// ultimo id e levar para monitor atividade
							try {
								int idMonitorReal;
								con = new ConnectionFactory().getConnection();
								PreparedStatement stmt3 = con
										.prepareStatement(sql2);

								stmt3.setInt(1, idMonitor);

								ResultSet result3 = stmt3.executeQuery();
								if (result3.next()) {
									idMonitorReal = result3.getInt("idMonitor");
									adicionaMonitorAtividade(idMonitorReal,
											idAtividade);
								} else {
									JOptionPane
											.showMessageDialog(null,
													"Não existe Participante com tais Requisitos! ");
								}

							} catch (SQLException e) {
								JOptionPane
										.showMessageDialog(null,
												"Não existe Participante com tais Requisitos! ");
							}

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

	public int verificaLogin(String cpf, String senha) {
		String sql = "select idMonitor from monitor m inner join aluno a on m.idAluno=a.idAluno inner join pessoa p on a.idPessoa = p.idPessoa where(p.cpf=? and m.senha=? and p.ativo=true);";
		Connection con = null;
		int idMonitor = -1;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, cpf);
			stmt.setString(2, senha);
			ResultSet result = stmt.executeQuery();
			if (result.next()) {
				idMonitor = result.getInt("idMonitor");
				
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
		return idMonitor;
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

	public void sairAtividade(int idMonitor){
		String sql = "delete from monitorAtividade where idMonitor=?";
		String sql2 = "delete from monitor where(idMonitor = ?)";
		
		Connection con=null;
		try {
			con = new ConnectionFactory().getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			PreparedStatement stmt2 = con.prepareStatement(sql2);
			
			stmt.setInt(1, idMonitor);
			stmt.execute();
			
			stmt2.setInt(1, idMonitor);
			stmt2.execute();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível excluir conta! " + e.getMessage());
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
