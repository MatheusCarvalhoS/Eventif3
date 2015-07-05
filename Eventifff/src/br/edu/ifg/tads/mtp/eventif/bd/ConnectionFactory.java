package br.edu.ifg.tads.mtp.eventif.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eventif","postgres","1234");
			System.out.println("Conectado!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRO ao conectar com o banco!");
			String erro = "ERRO ao conectar ao banco de dados!!!";
			JOptionPane.showMessageDialog(null, erro);
		}
		return con;
	}
}