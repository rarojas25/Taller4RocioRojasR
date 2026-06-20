package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Carta;
import patron.factory.CartaFactory;
import patron.factory.CartaFactoryImpl;
import patron.strategy.OrdenStrategy;
import patron.visitor.PoderVisitor;

public class SistemaCartas {
	private static SistemaCartas instancia;
	private List<Carta> coleccion;
	private static final String RUTA_ARCHIVO = "Sobras.txt";
	private CartaFactory factory;
	private PoderVisitor poderVisitor;
	private OrdenStrategy estrategiaOrden;
	
	private SistemaCartas(){
		this.coleccion = new ArrayList<>();
		this.factory = new CartaFactoryImpl();
		this.poderVisitor = new PoderVisitor();
		cargarDesdeArchivo();
	}

	public static SistemaCartas getInstancia() {
		if(instancia == null) {
			instancia = new SistemaCartas();
		}
		return instancia;
	}
	
	public void cargarDesdeArchivo() {
		coleccion.clear();
		File archivo = new File(RUTA_ARCHIVO);
		if(!archivo.exists()) {
			return;
		}
		
		try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
			String linea;
			while((linea = br.readLine()) != null) {
					linea = linea.trim();
					if(linea.isEmpty())continue;
			try {
				String[] partes = linea.split(";");
				Carta carta = factory.crearCarta(partes);
				coleccion.add(carta);
				
			}catch(Exception e) {
				System.out.println("Error al parsear linea: " + linea + " --> " + e.getMessage());
			}
		}
			
	}catch (IOException e) {
		System.out.println("Error al leer el archcivo: " + e.getMessage());
		}
	}
	
	public void guardarEnArchivo() {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))){
			for(Carta carta : coleccion) {
				bw.write(carta.toFileLine());
				bw.newLine();
			}
		}catch (IOException e) {
			System.err.println("Error al guardar el archivo: " + e.getMessage());
		}
	}
	
	public void agregarCarta(Carta carta) {
		coleccion.add(carta);
		guardarEnArchivo();
	}
	
	public void eliminarCarta(int indice) {
		coleccion.remove(indice);
		guardarEnArchivo();
	}
	
	public void modificarCarta(int indice, Carta nuevaCarta) {
		coleccion.set(indice, nuevaCarta);
		guardarEnArchivo();
	}
	
	public List<Carta> getColeccionOrdenada(){
		List<Carta> copia = new ArrayList<>(coleccion);
		if(estrategiaOrden != null) {
			estrategiaOrden.ordenar(copia);
		}
		return copia;
	}
	
	public List<Carta> getColeccion(){
		return new ArrayList<>(coleccion);
	}
	
	public void setEstrategiaOrden(OrdenStrategy estrategia) {
		this.estrategiaOrden = estrategia;
	}
	
	public double calcularPoder(Carta carta) {
		return carta.accept(poderVisitor);
	}
	
	public PoderVisitor getPoderVisitor() {
		return poderVisitor;
	}
}