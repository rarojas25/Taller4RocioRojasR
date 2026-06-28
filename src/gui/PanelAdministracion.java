package gui;

import java.util.List;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import modelo.Carta;
import modelo.CartaEnergy;
import modelo.CartaItem;
import modelo.CartaPokemon;
import modelo.CartaSupporter;
import sistema.SistemaCartas;

public class PanelAdministracion extends JPanel{
	
	private SistemaCartas sistema;
	private PanelColeccion panelColeccion;
	private JList<String> listaCartas;
	private JTextField txtNombre;
	private JSpinner spinRareza;
	private JComboBox<String> comboTipo;
	
	private JSpinner spinDanio;
	private JSpinner spinEnergias;
	private JSpinner spinBonificacion;
	private JSpinner spinEfectos;
	private JTextField txtElemento;
	
	private JPanel panelAtributos;
	private DefaultListModel<String> modeloLista;
	
	public PanelAdministracion(SistemaCartas sistema, PanelColeccion panelColeccion) {
		this.sistema = sistema;
		this.panelColeccion = panelColeccion;
		
		setLayout(new BorderLayout(10, 10));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setBackground(new Color(244, 244, 252));
		
		inicializarSpinners();
		inicializarComponentes();
		actualizarLista();
	}
	
	private void inicializarSpinners() {
		spinDanio = new JSpinner(new SpinnerNumberModel(10, 0, 9999, 10));
		spinEnergias = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
		spinBonificacion = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
		txtElemento = new JTextField("Fire");
	}
	
	private void inicializarComponentes() {
		JPanel panelIzquierdo = crearPanelLista();
		JPanel panelDerecho = crearPanelFormulario();
		
		JPanel contenedor = new JPanel(new GridLayout(1, 2, 10, 0));
		contenedor.setBackground(new Color(244, 244, 252));
		contenedor.add(panelIzquierdo);
		contenedor.add(panelDerecho);
		
		add(contenedor, BorderLayout.CENTER);
	}
	
	private JPanel crearPanelLista() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(90, 90, 200))
				, "Coleccion actual", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12)
				, new Color(50, 50, 160)));
		
				panel.setBackground(new Color(244, 244, 252));
				modeloLista = new DefaultListModel<String>();
				listaCartas = new JList<String>(modeloLista);
				listaCartas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				listaCartas.setFont(new Font("Monospaced", Font.PLAIN, 11));
				listaCartas.setBackground(Color.WHITE);
				
				panel.add(new JScrollPane(listaCartas), BorderLayout.CENTER);
				
				JPanel panelBotones = new JPanel(new GridLayout(1, 2, 8, 0));
				panelBotones.setBackground(new Color(244, 244, 252));
				panelBotones.setBorder(new EmptyBorder(6, 0, 0, 0));
				
				JButton btnEliminar = new JButton("ELiminar");
				btnEliminar.setBackground(new Color(210, 50, 50));
				btnEliminar.setForeground(Color.WHITE);
				btnEliminar.setFocusPainted(false);
				btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 12));
				btnEliminar.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						eliminarCartaSeleccionada();
					}
				});
				
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setBackground(new Color(50, 120, 200));
				btnModificar.setForeground(Color.WHITE);
				btnModificar.setFocusPainted(false);
				btnModificar.setFont(new Font("SansSerif", Font.BOLD, 12));
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						modificarCartaSeleccionada();
					}
				});
				
				panelBotones.add(btnEliminar);
				panelBotones.add(btnModificar);
				panel.add(panelBotones, BorderLayout.SOUTH);
				
				return panel;
	}
	
	private JPanel crearPanelFormulario() {
		JPanel panel = new JPanel( new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(40, 160, 40))
				, "Agregar carta", TitledBorder.LEFT, TitledBorder.TOP, new Font("SansSerif", Font.BOLD, 12) 
				, new Color(20, 120, 20)));
			panel.setBackground(new Color(244, 244, 252));
			
			JPanel camposComunes = new JPanel(new GridLayout(3, 2, 5, 8));
			camposComunes.setBackground(new Color(244, 244, 252));
			camposComunes.setBorder(new EmptyBorder(8, 8, 4, 8));
			
			camposComunes.add(new JLabel("Nombre"));
			txtNombre = new JTextField();
			camposComunes.add(txtNombre);
			
			camposComunes.add(new JLabel("Rareza (1-5); "));
			spinRareza= new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
			camposComunes.add(spinRareza);
			
			camposComunes.add(new JLabel("Tipo: "));
			comboTipo = new JComboBox<String>(new String[] {"Pokemon", "Item", "Supporter", "Energy"});
			comboTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				actualizarPanelAtributos();
				
			}
		});
		
		camposComunes.add(comboTipo);
		panel.add(camposComunes, BorderLayout.NORTH);
		
		panelAtributos = new JPanel(new BorderLayout());
		panelAtributos.setBackground(new Color(244, 244, 252));
		panelAtributos.setBorder(new EmptyBorder(0, 8, 4, 8));
		panelAtributos.add(crearPanelPokemon(), BorderLayout.CENTER);
		panel.add(panelAtributos, BorderLayout.CENTER);
		
		JButton btnAgregar = new JButton("Agregar carta");
		btnAgregar.setBackground(new Color(30, 150, 30));
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFocusPainted(false);
		btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarCarta();
			}
		});
		
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelBtn.setBackground(new Color(244, 244, 252));
		panelBtn.add(btnAgregar);
		panel.add(panelBtn, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel crearPanelPokemon() {
		JPanel p = new JPanel(new GridLayout(2, 2, 5, 8));
		p.setBackground(new Color(244, 244, 252));
		p.setBorder(BorderFactory.createTitledBorder("Atributos Pokemon"));
		p.add(new JLabel("Daño: "));
		p.add(spinDanio);
		p.add(new JLabel("Cant. Energias: "));
		p.add(spinEnergias);
		return p;
	}
	
	private JPanel crearPanelItem() {
		JPanel p = new JPanel(new GridLayout(1, 2, 5, 8));
		p.setBackground(new Color(244, 244, 252));
		p.setBorder(BorderFactory.createTitledBorder("Atributos Item"));
		p.add(new JLabel("Bonificacion: "));
		p.add(spinBonificacion);
		return p;
	}
	
	private JPanel crearPanelSupporter() {
		JPanel p = new JPanel(new GridLayout(1, 2, 5, 8));
		p.setBackground(new Color(244, 244, 252));
		p.setBorder(BorderFactory.createTitledBorder("Atributos Supporter"));
		p.add(new JLabel("Efectos por turno: "));
		p.add(spinEfectos);
		return p;
	}
	
	private JPanel crearPanelEnergy() {
		JPanel p = new JPanel(new GridLayout(1, 2, 5, 8));
		p.setBackground(new Color(244, 244, 252));
		p.setBorder(BorderFactory.createTitledBorder("Atributos Energy"));
		p.add(new JLabel("Elemento: "));
		p.add(txtElemento);
		return p;
	}
	
	private void actualizarPanelAtributos() {
		String tipo= (String) comboTipo.getSelectedItem();
		
		panelAtributos.removeAll();
		
		if(tipo.equals("Pokemon")) {
			panelAtributos.add(crearPanelPokemon(), BorderLayout.CENTER);
		
		}else if(tipo.equals("Item")) {
			panelAtributos.add(crearPanelItem(), BorderLayout.CENTER);
		}else if(tipo.equals("Supporter")) {
			panelAtributos.add(crearPanelSupporter(), BorderLayout.CENTER);
		}else if(tipo.equals("Enery")){
			panelAtributos.add(crearPanelEnergy(), BorderLayout.CENTER);
		}
		
		panelAtributos.revalidate();
		panelAtributos.repaint();
	}
	
	private void agregarCarta() {
		String nombre = txtNombre.getText().trim();
		
		if(nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre de la carta no puede estar vacio.",
					"Error de validacion", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int rareza= (int) spinRareza.getValue();
		String tipo = (String) comboTipo.getSelectedItem();
		Carta nueva = null;
		
		try {
			if(tipo.equals("Pokemon")) {
				int danio = (int) spinDanio.getValue();
				int energias = (int) spinEnergias.getValue();
				nueva = new CartaPokemon(nombre, rareza, danio, energias);
			
			}else if(tipo.equals("Item")){
				int bonif = (int) spinBonificacion.getValue();
				nueva = new CartaItem(nombre, rareza, bonif);
			
			}else if(tipo.equals("Supporter")) {
				int efectos = (int) spinEfectos.getValue();
				nueva = new CartaSupporter(nombre, rareza, efectos);
			
			}else if(tipo.equals("Energy")) {
				String elemento = txtElemento.getText().trim();
				if(elemento.isEmpty()) {
					JOptionPane.showMessageDialog(this, "El elemento no puede estar vacio."
							, "Error de validacion", JOptionPane.ERROR_MESSAGE);
					return;
				}
				nueva = new CartaEnergy(nombre, rareza, elemento);
			}
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(this, "Error al crear la carta: " + ex.getMessage()
			, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (nueva != null) {
			sistema.agregarCarta(nueva);
			actualizarLista();
			panelColeccion.actualizarColeccion();
			limpiarFormulario();
			JOptionPane.showMessageDialog(this, "Carta \"" + nombre + "\" agregada correctamente."
					,"Exito", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	
		private void eliminarCartaSeleccionada() {
			int idx = listaCartas.getSelectedIndex();
			
			if(idx < 0) {
				JOptionPane.showMessageDialog(this, "Debes seleccionar una carta para eliminar."
						, "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			List<Carta> coleccion = sistema.getColeccion();
			String nombreCarta = coleccion.get(idx).getNombre();
			
			int respuesta = JOptionPane.showConfirmDialog(this, "Eliminar la carta \"" + nombreCarta + "\"?"
					, "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
			
			if(respuesta == JOptionPane.YES_OPTION) {
				sistema.eliminarCarta(idx);
				panelColeccion.actualizarColeccion();
			}
		}
		
		private void modificarCartaSeleccionada() {
			int idx = listaCartas.getSelectedIndex();
			
			if(idx < 0) {
				JOptionPane.showMessageDialog(this, "Debes seleccionar una carta para modificar."
						, "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			List<Carta> coleccion = sistema.getColeccion();
			Carta original = coleccion.get(idx);
			Carta modificada = mostrarDialogoModificacion(original);
			
			if(modificada != null) {
				sistema.modificarCarta(idx, modificada);
				actualizarLista();
				panelColeccion.actualizarColeccion();
				JOptionPane.showMessageDialog(this, "Carta modificada correctamente."
						, "Exito" , JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
		
		private Carta mostrarDialogoModificacion(Carta original) {
			Frame parent = (Frame) javax.swing.SwingUtilities.getWindowAncestor(this);
			JDialog dialog = new JDialog(parent, "Modificar: " + original.getNombre(), true);
			dialog.setSize(320,210);
			dialog.setLocationRelativeTo(this);
			dialog.setLayout(new BorderLayout(8, 8));
			
			JLabel lblInfo = new JLabel("<html><b> " + original.getNombre() + "</b> | Rareza: " + original.getRareza() + " | Tipo: " + original.getTipo() + "</html>");
			lblInfo.setBorder(new EmptyBorder(10, 10, 0, 10));
			dialog.add(lblInfo, BorderLayout.NORTH);
			
			JPanel campos = new JPanel(new GridLayout(2, 2, 8, 8));
			campos.setBorder(new EmptyBorder(8, 10, 8, 10));
			
			final Carta[] resultado = new Carta[1];
			resultado[0] = null;
			
			if(original instanceof CartaPokemon) {
				CartaPokemon p = (CartaPokemon) original;
				
				JSpinner sDanio = new JSpinner(new SpinnerNumberModel(p.getDanio(), 0, 999, 10));
				JSpinner sEnergias = new JSpinner(new SpinnerNumberModel(p.getCantEnergias(), 1, 99, 1));
				
				campos.add(new JLabel("Daño: "));
				campos.add(sDanio);
				campos.add(new JLabel("Cant. Energias"));
				campos.add(sEnergias);
				dialog.add(campos, BorderLayout.CENTER);
				
				JButton btn = new JButton("Guardar cambios");
				btn.setBackground(new Color(50, 120, 200));
				btn.setForeground(Color.WHITE);
				btn.setFocusPainted(false);
				btn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						resultado[0] = new CartaPokemon(p.getNombre(), p.getRareza(),
								(int) sDanio.getValue(), (int) sEnergias.getValue());
						
						dialog.dispose();			
					}
				});
				
				JPanel pb = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pb.add(btn);
				dialog.add(pb, BorderLayout.SOUTH);
				
			}else if (original instanceof CartaItem) {
				CartaItem it = (CartaItem) original;
				
				JSpinner sBonif = new JSpinner(new SpinnerNumberModel(it.getBonificacion(), 0, 999, 5));
				
				campos.add(new JLabel("Bonificacion: "));
				campos.add(sBonif);
				dialog.add(campos, BorderLayout.CENTER);
				
				JButton btn = new JButton("Guardar cambios");
				btn.setBackground(new Color(50, 120, 200));
				btn.setForeground(Color.WHITE);
				btn.setFocusPainted(false);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						resultado[0] = new CartaItem(it.getNombre(), it.getRareza()
								,(int) sBonif.getValue());
						
						dialog.dispose();
					}
				});
				JPanel pb = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pb.add(btn);
				dialog.add(pb, BorderLayout.SOUTH);
				
				}else if(original instanceof CartaSupporter) {
					CartaSupporter s = (CartaSupporter) original;
					
					JSpinner sEf = new JSpinner( new SpinnerNumberModel(s.getEfectosPorTurno(), 1, 99, 1));
					
					campos.add(new JLabel("Efectos por turno: "));
					campos.add(sEf);
					dialog.add(campos, BorderLayout.CENTER);
					
					JButton btn = new JButton("Guardar cambios");
					btn.setBackground(new Color(50, 120, 200));
					btn.setForeground(Color.WHITE);
					btn.setFocusPainted(false);
					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							resultado[0] = new CartaSupporter(s.getNombre(), s.getRareza()
									,(int) sEf.getValue());
							
							dialog.dispose();
									
						}
					});
					
					JPanel pb = new JPanel(new FlowLayout(FlowLayout.CENTER));
					pb.add(btn);
					dialog.add(pb, BorderLayout.SOUTH);
					
				}else if(original instanceof CartaEnergy) {
					CartaEnergy en = (CartaEnergy) original;
					
					JTextField tElem = new JTextField(en.getElemento());
					
					campos.add(new JLabel("Elemento: "));
					campos.add(tElem);
					dialog.add(campos, BorderLayout.CENTER);
					
					JButton btn = new JButton("Guardar cambios");
					btn.setBackground(new Color(50, 120, 200));
					btn.setForeground(Color.WHITE);
					btn.setFocusPainted(false);
					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String elem = tElem.getText().trim();
							if(elem.isEmpty()) {
								JOptionPane.showMessageDialog(dialog, "El elemento no puede estar vacio."
								, "Error", JOptionPane.ERROR_MESSAGE);
								return;
							}
							resultado[0] = new CartaEnergy(en.getNombre(), en.getRareza(), elem);
							dialog.dispose();
						}
					});
					
				JPanel pb = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pb.add(btn);
				dialog.add(pb, BorderLayout.SOUTH);
					
				}
			dialog.setVisible(true);
			return resultado[0];
			}
		
		public void actualizarLista() {
			modeloLista.clear();
			List<Carta> coleccion = sistema.getColeccion();
			
			for(int i = 0; i < coleccion.size(); i++) {
				Carta c = coleccion.get(i);
				modeloLista.addElement((i + 1) + ". [" + c.getTipo() + "] " + c.getNombre() + "(Rareza: " + c.getRareza() + ")");
			}			
		}
		
		private void limpiarFormulario() {
			txtNombre.setText("");
			spinRareza.setValue(1);
			comboTipo.setSelectedIndex(0);
			spinDanio.setValue(10);
			spinEnergias.setValue(1);
			spinBonificacion.setValue(10);
			spinEfectos.setValue(1);
			txtElemento.setText("Fire");
		}
}
