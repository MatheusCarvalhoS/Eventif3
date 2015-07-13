package br.edu.ifg.tads.mtp.eventif.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://192.168.43.137:5432/eventif","postgres","1234");
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ERRO ao conectar ao banco de dados!!!");
		}
		return con;
	}
}