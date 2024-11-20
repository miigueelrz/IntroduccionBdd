package modelo.interfaz;

import java.util.List;
import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

public class Interfaz {

	GestorCoche gestorCoche = new GestorCoche();
	boolean flag = false;
	private static Scanner sc = new Scanner(System.in);
	private static Scanner scString = new Scanner(System.in);

	public int menu() {
		int eleccion = 0;
		System.out.println("Porfavor eliga lo que desea hacer");
		System.out.println("----------------------------------");
		System.out.println("Dar de alta Coche PULSA 1");
		System.out.println("Dar de baja Coche PULSA 2");
		System.out.println("Modificar Coche PULSA 3");
		System.out.println("Buscar coche por id PULSA 4");
		System.out.println("Buscar coches por marca PULSA 5");
		System.out.println("Ver lista de coches PULSA 6");
		System.out.println("Cerrar conexion con el programa PULSA 0");
		eleccion = sc.nextInt();
		return eleccion;
	}

	public void mostrarInterfaz() {
		do {
			int eleccion = menu();
			switch (eleccion) {

			case 1:
				Coche c = new Coche();
				System.out.println("Elija los atributos de su coche:");

				System.out.println("Marca:");
				String marca = scString.nextLine();

				System.out.println("Modelo:");
				String modelo = scString.nextLine();
				System.out.println("Tipo de Motor:");
				String tipoMotor = scString.nextLine();

				System.out.println("kilometros:");
				int kilometros = sc.nextInt();
				c.setMarca(marca);
				c.setModelo(modelo);
				c.setKilometros(kilometros);
				c.setTipoMotor(tipoMotor);
				int alta = gestorCoche.alta(c);
				if (alta == 0) {
					System.out.println("Se dio de alta el coche correctamente");
				} else
					System.out.println("hubo un error al dar de alta el coche");

				System.out.println("-------------------------------");
				break;
			case 2:
				System.out.println("Porfavor introduzca el id del coche que quiere dar de baja:");
				int id = sc.nextInt();
				boolean baja = gestorCoche.baja(id);
				if (baja) {
					System.out.println("Se eliminó su coche correctamente");
				} else
					System.out.println("hubo un error");
				break;
			case 3:
				System.out.println("Porfavor introduzca el id del coche que quiere modificar:");
				int idModificar = sc.nextInt();
				Coche c2 = new Coche();
				System.out.println("Elija los atributos de su coche para modificarlo:");

				System.out.println("Marca:");
				String marca2 = scString.nextLine();

				System.out.println("Modelo:");
				String modelo2 = scString.nextLine();
				System.out.println("Tipo de Motor:");
				String tipoMotor2 = scString.nextLine();

				System.out.println("kilometros:");
				int kilometros2 = sc.nextInt();
				c2.setMarca(marca2);
				c2.setModelo(modelo2);
				c2.setKilometros(kilometros2);
				c2.setTipoMotor(tipoMotor2);
				boolean modificar = gestorCoche.modificar(c2, idModificar);
				if(modificar){
					System.out.println("Se  ha modificado correctamente");
				}
				break;
			case 4:
				System.out.println("escriba el id del coche que quiere buscar:");
				int ide = sc.nextInt();
				Coche c1 = gestorCoche.obtener(ide);
				System.out.println("su coche es:" + c1);
				break;
			case 5:
				System.out.println("escriba el Marca de los coches que quiere buscar:");
				String marca1 = sc.nextLine();
				sc.nextLine();
				List<Coche> listaCocheModelo = gestorCoche.obtener(marca1);
				System.out.println("su lista de coches es:" + listaCocheModelo);
				break;
			case 6:
				List<Coche> listaCoches = gestorCoche.listar();

				System.out.println("Su lista de coches es:" + listaCoches);
				break;
			case 0:
				flag = true;
				break;
			}
		} while (!flag);

		System.out.println("Ha cortado la conexion...................................");
		sc.close();
	}
}
