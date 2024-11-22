package empleados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionAcme {

	public Map<Integer, List<Empleado>> leerArchivoDePersonal(String entrada) throws IOException {
		/*
		 * que retorna un mapa de listas de empleados donde la Key es el número de
		 * sector y el Value una lista con todos los empleados de ese sector
		 */
		Map<Integer, List<Empleado>> mapEmpleados = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {

			String linea;

			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",");

				try {
					int sector = Integer.parseInt(datos[0]);
					if (sector < 100 || sector > 300) {
						/*
						 * Si quiero continuar con la carga de datos en el mapa deberia implementar
						 * try-catch dentro del while
						 */
						throw new SectorInvalidException();
					}

					int legajo = Integer.parseInt(datos[1]);
					String apeNombre = datos[2];
					int antigüedad = Integer.parseInt(datos[3]);

					// Crear un empleado
					Empleado empleado = new Empleado(sector, legajo, apeNombre, antigüedad);

					// Obtener una lista de empleados o crear si no existe para el sector
					List<Empleado> listaEmpleados = mapEmpleados.getOrDefault(sector, new ArrayList<>());

					// Agregar a listaEmpleados
					listaEmpleados.add(empleado);

					// Actualizar Mapa
					mapEmpleados.put(sector, listaEmpleados);

				} catch (SectorInvalidException e) {
					System.err.println("sector incorrecto...");
				}
			}

		} catch (IOException e) {
			System.err.println("Error al leer archivo " + e.getMessage());
		}
		return mapEmpleados;
	}

	public void listarEmpleadosDelSector(int sector, Map<Integer, List<Empleado>> empleados) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("empleadosDelSector" + sector + ".csv"))) {

			List<Empleado> listEmpleados = empleados.get(sector);

			bw.write("Sector: " + sector + "\n");
			for (Empleado empleado : listEmpleados) {
				bw.write(empleado.toString() + "\n");
			}

		} catch (IOException e) {
			System.err.println("Error al escribir archivo " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		GestionAcme ga = new GestionAcme();
		Map<Integer, List<Empleado>> mapEmpleado;

		mapEmpleado = ga.leerArchivoDePersonal("personal.csv");
		ga.listarEmpleadosDelSector(100, mapEmpleado);
		ga.listarEmpleadosDelSector(200, mapEmpleado);
		ga.listarEmpleadosDelSector(150, mapEmpleado);
	}

}
