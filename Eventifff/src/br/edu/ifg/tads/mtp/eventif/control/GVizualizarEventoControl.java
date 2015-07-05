package br.edu.ifg.tads.mtp.eventif.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ifg.tads.mtp.eventif.dao.EventoDAO;
import br.edu.ifg.tads.mtp.eventif.model.EventoModel;
import br.edu.ifg.tads.mtp.eventif.util.*;
import br.edu.ifg.tads.mtp.eventif.view.*;

public class GVizualizarEventoControl{
	
	private GerenteListarEventoView eventoView;
	private JDesktopPane desktopPane;
	private EventoDAO eventoDAO = new EventoDAO();
	private Action alterarAction;
	private Action excluirAction;
	
	public GVizualizarEventoControl(GerenteListarEventoView eventoView, JDesktopPane desktopPane){
		this.eventoView = eventoView;
		this.desktopPane = desktopPane;
	}
}
