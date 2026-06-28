package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import sistema.SistemaCartas;

public class VentanaPrincipal extends JFrame{
	private SistemaCartas sistema;
	private PanelColeccion panelColeccion;
	private PanelAdministracion panelAdmin;
	
	public VentanaPrincipal() {
		sistema = SistemaCartas.getInstancia();
		
		setTitle("Pokemon TCG - Gestion de Coleccion ");
		setSize(900, 650);
		setMinimumSize(new Dimension(750, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		inicializarComponentes();
	}
	
	private void inicializarComponentes() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(new Color(50, 30, 110));
		header.setBorder(new EmptyBorder(10, 16, 10, 16));
		
		JLabel titulo = new JLabel("Pokemon TCG - Gestion de Coleccion");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
		titulo.setForeground(new Color(225, 235, 60));
		header.add(titulo, BorderLayout.WEST);
		
		JLabel subtitulo = new JLabel("Strostian & PPOSandon  ");
		subtitulo.setFont(new Font("SansSerif", Font.ITALIC, 12));
		subtitulo.setForeground(new Color(200, 180, 255));
		header.add(subtitulo, BorderLayout.EAST);
		
		add(header, BorderLayout.NORTH);
		
		JTabbedPane tabs = new JTabbedPane();
		tabs.setFont(new Font("SansSerif", Font.BOLD, 13));
		
		panelColeccion = new PanelColeccion(sistema);
		panelAdmin = new PanelAdministracion(sistema, panelColeccion);
		
		tabs.addTab("  Administracion  ", panelAdmin);
		tabs.addTab(" Ver Coleccion ", panelColeccion);
		
		add(tabs, BorderLayout.CENTER);
		
		JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		footer.setBackground(new Color(50, 30, 110));
		
		JLabel lblFooter = new JLabel("Taller 4 POO - rarojas25");
		lblFooter.setFont(new Font("sansSerif", Font.ITALIC, 11));
		lblFooter.setForeground(new Color(200, 180, 255));
		footer.add(lblFooter);
		
		add(footer, BorderLayout.SOUTH);
	}
}
