package empleados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionAcme {

	public Map<Integer, List<Empleado>> leerArchivoDePersonal(String entrada) throws SectorInvalidException {
		/*
		 * que retorna un mapa de listas de empleados donde la Key es el número de
		 * sector y el Value una lista con todos los empleados de ese sector
		 */
		Map<Integer, List<Empleado>> mapEmpleados = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(entrada))) {

			String linea;

			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(",");

				int sector = Integer.parseInt(datos[0]);

				if (sector < 100 || sector > 300) {
					throw new SectorInvalidException("Sector invalido");
				}

				int legajo = Integer.parseInt(datos[1]);
				String apeNombre = datos[2];
				int antigüedad = Integer.parseInt(datos[3]);

				// Crear un empleado
				Empleado empleado = new Empleado(sector, legajo, apeNombre, antigüedad);

				// Obtener una lista de programas o crear si no existe para el canal
				List<Empleado> listaEmpleados = mapEmpleados.getOrDefault(sector, new ArrayList<>());

				// Agregar a listaEmpleados
				listaEmpleados.add(empleado);

				// Actualizar Mapa
				mapEmpleados.put(sector, listaEmpleados);
			}

			for (Map.Entry<Integer, List<Empleado>> entry : mapEmpleados.entrySet()) {
				Integer clave = entry.getKey();
				List<Empleado> listEmpleados = entry.getValue();

				System.out.println(clave);
			}

		} catch (IOException e) {
			System.err.println("Error al leer archivo " + e.getMessage());
		} catch (Exception e1) {
			System.err.println("Error " + e1.getMessage());
		}
		return mapEmpleados;
	}

	public void listarEmpleadosDelSector(int sector, Map<Integer, List<Empleado>> empleados) {

	}

	public static void main(String[] args) {
		GestionAcme ga = new GestionAcme();
		try {
			ga.leerArchivoDePersonal("personal.csv");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
