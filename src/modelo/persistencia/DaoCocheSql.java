package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;

public class DaoCocheSql implements DaoCoche{

	private Connection conexion;
	
	@Override
	public boolean alta(Coche c) {
		if(!abrirConexion()) return false;
		
		boolean alta = true;
		String query = "INSERT INTO coche (marca,modelo,tipoMotor,kilometros) VALUES (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setString(3, c.getTipoMotor());
			ps.setInt(4,c.getKilometros());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}
		
	@Override
	public boolean baja(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "delete from coche where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}


	@Override
	public boolean modificar(Coche c,int id) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coche set marca=?, modelo=? ,tipoMotor=?, kilometros=? WHERE ID=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, c.getMarca());
			ps.setString(2, c.getModelo());
			ps.setString(3,c.getTipoMotor());
			ps.setInt(4, c.getKilometros());
			ps.setInt(5, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("modificar -> error al modificar el coche");
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public List<Coche> obtener(String marca) {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,marca,modelo,tipoMotor,kilometros from coche where marca = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, marca);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setMarca(rs.getString(2));
				c.setModelo(rs.getString(3));
				c.setTipoMotor(rs.getString(4));
				c.setKilometros(rs.getInt(5));
				listaCoches.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Coche c = null;
		
		String query = "select ID,marca,modelo,tipoMotor,kilometros from coche where id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				c = new Coche();
				c.setId(rs.getInt(1));
				c.setMarca(rs.getString(2));
				c.setModelo(rs.getString(3));
				c.setTipoMotor(rs.getString(4));
				c.setKilometros(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return c;
	}

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "select ID,marca,modelo,tipoMotor,kilometros from coche ";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche c = new Coche();
				c.setId(rs.getInt(1));
				c.setMarca(rs.getString(2));
				c.setModelo(rs.getString(3));
				c.setTipoMotor(rs.getString(4));
				c.setKilometros(rs.getInt(5));
				listaCoches.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

	
	public boolean abrirConexion() {
		String cadenaConexion = "jdbc:mysql://localhost:3306/basecoches";
		String user = "root";
		String pass = "";

		try  {
			conexion = DriverManager.getConnection(cadenaConexion, user, pass);
		return true;
		
		} catch(SQLException e) {
			System.out.println("Error al añadir una nueva persona");
			System.out.println(e.getMessage());
		return false;
		}
	}
	
	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
