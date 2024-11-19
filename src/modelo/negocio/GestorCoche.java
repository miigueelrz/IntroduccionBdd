package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCoche;
import modelo.persistencia.DaoCocheSql;

public class GestorCoche {

	private DaoCoche daoCoche = new DaoCocheSql();
	
	/**
	 * Metodo que da de alta un coche en la base de datos
	 * @param c coche que quiere dar de alta
	 * @return 0 en caso de que se haya dado de alta correctamente y 
	 * 1 en caso de que de algun error de conexion
	 */
	public int alta(Coche c) {
		
		boolean alta = daoCoche.alta(c);
		
		if(alta) {
			return 0;
		}
		else return 1;
	} 
	/**
	 * Metodo que da de baja un coche de la base de datos
	 * @param c coche que quiere dar de baja
	 * @return true en caso de que se haya dado de baja correctamente y
	 *  false en caso de que haya errores
	 */
	public boolean baja(int id) {
		boolean baja = daoCoche.baja(id);
		return baja;
	}
	
	public boolean modificar(Coche c) {
		boolean modificar = daoCoche.modificar(c);
		return modificar;
	}
	
	public Coche obtener(int id) {
		Coche c = daoCoche.obtener(id);
		return c;
	}
	
	public List<Coche> obtener (String marca){
		List<Coche> listaCoches = daoCoche.obtener(marca);
		return listaCoches;
	}
	
	public List<Coche> listar(){
		List<Coche> listaCoches = daoCoche.listar();
		return listaCoches;
	}
}
