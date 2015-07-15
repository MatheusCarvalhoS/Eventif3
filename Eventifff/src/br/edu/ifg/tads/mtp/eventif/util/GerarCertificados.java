package br.edu.ifg.tads.mtp.eventif.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.ifg.tads.mtp.eventif.model.CertificadoModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class GerarCertificados {
	public static void certificadoEvento(String nome, String evento, String cpf, String horas) throws JRException, SQLException {

		System.out.println("Gerando relatório...");
		// lista com os nossos clientes
		List<CertificadoModel> lista = new ArrayList<CertificadoModel>();

		String linha1 = "Certificamos que "+nome+" com CPF  '"+cpf+"', Participou do Evento";
		String linha2 = evento+" No Instituto Federal de Ciência e Tecnologia de Goiás, Câmpus Formosa.";
		String linha3 = "Carga horária: "+horas+" horas.";
		
		CertificadoModel c = new CertificadoModel();
		c.setLinha1(linha1);
		c.setLinha2(linha2);
		c.setLinha3(linha3);
		
		lista.add(c);
		// compilacao do JRXML
		JasperReport report = JasperCompileManager
				.compileReport("Certificados/Modelos/Certificado.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print,
				"Certificados/"+nome+"Evento.pdf");

		System.out.println("Relatório gerado.");
	}
	
	public static void certificadoAtividade(String nome, String atividade, String cpf, String horas) throws JRException, SQLException {

		System.out.println("Gerando relatório...");
		// lista com os nossos clientes
		List<CertificadoModel> lista = new ArrayList<CertificadoModel>();

		String linha1 = "Certificamos que "+nome+" com CPF  '"+cpf+"', Participou da Atividade ";
		String linha2 = atividade+" no Instituto Federal de Ciência e Tecnologia de Goiás, Câmpus Formosa.";
		String linha3 = "Carga horária: "+horas+" horas.";
		
		CertificadoModel c = new CertificadoModel();
		c.setLinha1(linha1);
		c.setLinha2(linha2);
		c.setLinha3(linha3);
		
		lista.add(c);
		// compilacao do JRXML
		JasperReport report = JasperCompileManager
				.compileReport("Certificados/Modelos/Certificado.jrxml");

		JasperPrint print = JasperFillManager.fillReport(report, null,
				new JRBeanCollectionDataSource(lista));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print,
				"Certificados/"+nome+"Atividade.pdf");

		System.out.println("Relatório gerado.");
	}
}