package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.engine.JRException;

import br.edu.ifg.tads.mtp.eventif.dao.AtividadeDAO;
import br.edu.ifg.tads.mtp.eventif.dao.GerenteDAO;
import br.edu.ifg.tads.mtp.eventif.util.GerarCertificados;
import br.edu.ifg.tads.mtp.eventif.view.AppView;
import br.edu.ifg.tads.mtp.eventif.view.GerenteListarAtividadeView;

public class GerenteListarAtividadeControl {
	private GerenteListarAtividadeView listarAtividade;
	private JPanel painel;
	private JButton alterar;
	private JButton excluir;
	private JButton addAtividade;
	private AppView appView;
	private int idEvento;

	public JPanel getGerenteListarAtividadeControl(AppView app, int idEvento) {
		this.appView = app;
		this.idEvento = idEvento;

		listarAtividade = new GerenteListarAtividadeView();
		painel = listarAtividade.getGerenteListarAtividadeView();
		adicionaEventos();
		preencheTabela();

		return painel;
	}

	public void preencheTabela() {
		Vector<Vector<String>> listaAtividade = new AtividadeDAO()
				.buscaAtividades(idEvento);
		preencheTabela(listaAtividade);
	}

	public void preencheTabela(Vector<Vector<String>> listaAtividade) {

		listarAtividade.getTable().clearSelection();
		listarAtividade.getTable().removeAll();

		Vector<String> colunas = new Vector<String>();
		colunas.add("id");
		colunas.add("nome");
		colunas.add("descrição");
		colunas.add("horaInicio");
		colunas.add("horaFim");
		colunas.add("Palestrante");
		colunas.add("Tipo");
		colunas.add("Carga Horaria");
		colunas.add("Numero de Vagas");
		colunas.add("Data");

		DefaultTableModel model = new DefaultTableModel(listaAtividade, colunas);
		listarAtividade.getTable().setModel(model);
		listarAtividade.getTable().getColumn("id").setMaxWidth(25);
	}

	public void adicionaEventos() {
		listarAtividade.getExcluirAtividade().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Excluir a Atividade?")==0){
							int id = Integer.parseInt(listarAtividade
									.getTable()
									.getValueAt(
											listarAtividade.getTable()
													.getSelectedRow(), 0)
									.toString());
							new GerenteCriarAtividadeControl().excluirAtividade(
									idEvento, id);
							preencheTabela();
					    }
					}
				});

		listarAtividade.getAlterarAtividade().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int id = Integer.parseInt(listarAtividade
								.getTable()
								.getValueAt(
										listarAtividade.getTable()
												.getSelectedRow(), 0)
								.toString());
						appView.getPainelDireita().removeAll();
						appView.getPainelDireita().add(
								new GerenteCriarAtividadeControl()
										.getGerenteAlterarAtividadeControl(id));
						appView.getPainelDireita().repaint();

					}
				});

		listarAtividade.getJbtnPesquisar().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						listarAtividade.getTable().removeAll();
						listarAtividade.getTable().setModel(
								new PesquisarEventoOuAtividadeControl()
										.getPesquisarAtividadeControl(
												listarAtividade
														.getJtfPesquisar()
														.getText(), idEvento));

						listarAtividade.getTable().getColumn("id")
								.setMaxWidth(25);
						listarAtividade.getTable().repaint();
					}
				});

		listarAtividade.getSelectMonitor().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						int idAtividade = Integer.parseInt(listarAtividade
								.getTable()
								.getValueAt(
										listarAtividade.getTable()
												.getSelectedRow(), 0)
								.toString());
						try {
							appView.getPainelDireita().removeAll();
							appView.getPainelDireita()
									.add(new GerenteListarAlunosControl()
											.getGerenteListarAlunosControl(idAtividade));
							appView.getPainelDireita().repaint();
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null,
									"erro! " + e.getID());
							e1.printStackTrace();
						}
					}
				});

		listarAtividade.getGerarCertificados().addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int idAtividade = Integer.parseInt(listarAtividade
								.getTable()
								.getValueAt(
										listarAtividade.getTable()
												.getSelectedRow(), 0)
								.toString());

						ResultSet result1 = null;
						ResultSet result2 = null;

						result1 = new GerenteDAO()
								.gerarCertificadosIdAluno(idAtividade);

						try {

							while (result1.next()) {
								System.out
										.println("gerar Certificados acionado");
								int idAluno = result1.getInt("idAluno");

								result2 = new GerenteDAO()
										.gerarCertificadosAtividade(idAluno,
												idAtividade);

								while (result2.next()) {
									int horasTotal = result2.getInt("totalHoras");
									double cargaHoraria = Double.parseDouble(result2.getString("cargaHoraria"));
									
									if (horasTotal >= (cargaHoraria * 0.75)) {
										
										ResultSet result3 = new GerenteDAO().informacoesCertificado(idAluno, idAtividade);
										if(result3.next()){
											new GerarCertificados();
											GerarCertificados.certificadoAtividade(result3.getString("nomePessoa"), result3.getString("nomeAtividade"),
													result3.getString("cpf"),result3.getString("cargaHoraria"), result3.getString("data"));
										}
									} else {
										// aqui ele irá levar para uma tabela de LOG;
									}
								}

							}

						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(
									null,
									"ERRO ao tentar Gerar Certificados! "
											+ e1.getMessage());
						} catch (JRException e1) {
							
							e1.printStackTrace();
						}
					}
				});

	}

	public GerenteListarAtividadeView getListarAtividade() {
		return listarAtividade;
	}

	public void setListarAtividade(GerenteListarAtividadeView listarAtividade) {
		this.listarAtividade = listarAtividade;
	}

	public JPanel getPainel() {
		return painel;
	}

	public void setPainel(JPanel painel) {
		this.painel = painel;
	}

	public JButton getAlterar() {
		return alterar;
	}

	public void setAlterar(JButton alterar) {
		this.alterar = alterar;
	}

	public JButton getExcluir() {
		return excluir;
	}

	public void setExcluir(JButton excluir) {
		this.excluir = excluir;
	}

	public JButton getAddAtividade() {
		return addAtividade;
	}

	public void setAddAtividade(JButton addAtividade) {
		this.addAtividade = addAtividade;
	}

	public AppView getAppView() {
		return appView;
	}

	public void setAppView(AppView appView) {
		this.appView = appView;
	}

	public int getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
}
