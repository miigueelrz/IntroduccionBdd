package modelo.entidad;

import java.util.Objects;

public class Coche {
	private int id;
	private String marca;
	private String modelo ;
	private String tipoMotor ;
	private int kilometros;
	
	
	public Coche(int id, String marca, String modelo, String tipoMotor, int kilometros) {
		super();
		this.id = id;
		this.marca = marca;
		this.modelo = modelo;
		this.tipoMotor = tipoMotor;
		this.kilometros = kilometros;
	}
	
	
	public Coche() {
		super();
		// TODO Auto-generated constructor stub
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMarca() {
		return marca;
	}


	public void setMarca(String marca) {
		this.marca = marca;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getTipoMotor() {
		return tipoMotor;
	}


	public void setTipoMotor(String tipoMotor) {
		this.tipoMotor = tipoMotor;
	}


	public int getKilometros() {
		return kilometros;
	}


	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, kilometros, marca, modelo, tipoMotor);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		return id == other.id && kilometros == other.kilometros && Objects.equals(marca, other.marca)
				&& Objects.equals(modelo, other.modelo) && Objects.equals(tipoMotor, other.tipoMotor);
	}


	@Override
	public String toString() {
		return "CocheEntidad [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", tipoMotor=" + tipoMotor
				+ ", kilometros=" + kilometros + "]";
	}
	
	

	
}
