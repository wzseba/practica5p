package empleados;

public class Empleado {

	private int sector;
	private int legajo;
	private String apeNombre;
	private int antiguedad;

	public Empleado(int sector, int legajo, String apeNombre, int antiguedad) {
		this.sector = sector;
		this.legajo = legajo;
		this.apeNombre = apeNombre;
		this.antiguedad = antiguedad;
	}

	public int getSector() {
		return sector;
	}

	@Override
	public String toString() {
		return "legajo: " + legajo + " nombre: " + apeNombre + " antiguedad: " + antiguedad;
	}

}
