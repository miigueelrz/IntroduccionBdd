import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BaseCoches {

	public static void main(String[] args) {
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		String cadenaConexion = "jdbc:mysql://localhost:3306/basecoches";
		String user = "root";
		String pass = "";

		do {
			try (Connection con = DriverManager.getConnection(cadenaConexion, user, pass)) {

				System.out.println("Conectado a la base de datos");
				System.out.println("---------------------------");
				System.out.println("Porfavor introduzca los datos");

				String sql = "INSERT INTO coche (marca, modelo, tipoMotor, kilometros) VALUES (?, ?, ?, ?)";

				String marca = sc.nextLine();
				String modelo = sc.nextLine();
				String tipoMotor = sc.nextLine();
				int kilometros = sc.nextInt();

				System.out.println("Se va a ejecutar la siguiente sentencia SQL:");
				System.out.println(sql);

				PreparedStatement sentencia;
				sentencia = con.prepareStatement(sql);

				sentencia.setString(1, marca);
				sentencia.setString(2, modelo);
				sentencia.setString(3, tipoMotor);
				sentencia.setInt(4, kilometros);

				int afectados = sentencia.executeUpdate();

				System.out.println("Sentencia SQL ejecutada con éxito");
				System.out.println("Registros afectados: " + afectados);
				System.out.println("Quieress añadir otro coche?");
				System.out.println("1:si");
				System.out.println("2:no");
				int decision = sc.nextInt();

				do {
					if (decision == 1) {
						flag = false;
					} else
						flag = true;
				} while (decision != 1 && decision != 2);

			} catch (SQLException e) {
				System.out.println("Error al añadir una nueva persona");
				System.out.println(e.getMessage());
			}
		} while (flag == false);
		System.out.println("Programa finalizado");
	}
}
