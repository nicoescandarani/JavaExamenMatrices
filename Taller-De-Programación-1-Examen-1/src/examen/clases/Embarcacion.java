package examen.clases;

public class Embarcacion {

	private static final int TOTAL_MESES = 12;
	private String patente;
	private Fecha fechaDeIngreso;
	private int camaAsignada;
	private Pago[] pagos;

	public Embarcacion(String patente, Fecha fechaDeIngreso, int camaAsignada) {
		setCamaAsignada(camaAsignada);
		setPatente(patente);
		setFechaDeIngreso(fechaDeIngreso);
		pagos = new Pago[TOTAL_MESES];
	}

	/**
	 * Indica si la embarcacion tiene deuda. Debe resolverse de la forma mas
	 * eficiente posible.
	 * 
	 * @param fechaActual
	 *            La fecha del dia.
	 * @return Un booleano que indica si hay deuda.
	 */
	public boolean tieneDeuda(Fecha fechaActual) {
		return this.pagos[fechaActual.getMes() - 1] == null;
	}

	/**
	 * Devuelve el importe total acumulado de todo lo pagado en el anio.
	 * 
	 * @param fechaActual
	 *            La fecha del dia.
	 * @return el importe acumulado.
	 */public double totalAbonado(Fecha fechaActual) {
			double importe = 0;
			int i = 0;
			while (i < fechaActual.getMes()) {
				if (pagos[i] != null) {
					importe += pagos[i].getMonto();
				}
				i++;
			}
			return importe;
		}

	public int getCamaAsignada() {
		return camaAsignada;
	}

	public Fecha getFechaDeIngreso() {
		return fechaDeIngreso;
	}

	private int getMesInicial(Fecha fechaActual) {
		return (fechaActual.getAnio() == fechaDeIngreso.getAnio()) ? fechaDeIngreso.getMes() : 1;
	}

	public String getPatente() {
		return this.patente;
	}

	public boolean patenteBuscada(String patente) {
		return this.patente.equalsIgnoreCase(patente);
	}

	private int primerMesImpago(Fecha fechaActual) {
		int m = getMesInicial(fechaActual);
		int mActual = fechaActual.getMes();
		while (m <= mActual && pagos[m - 1] != null) {
			m++;
		}
		return m;
	}

	public void registrarPago(Fecha fechaDeHoy, String cbu, int importe) {
		int mes = primerMesImpago(fechaDeHoy);
		pagos[mes - 1] = new Pago(fechaDeHoy, cbu, importe);
	}

	public void setCamaAsignada(int camaAsignada) {
		this.camaAsignada = camaAsignada;
	}

	public void setFechaDeIngreso(Fecha fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}

	private void setPatente(String patente) {
		this.patente = patente;
	}

	@Override
	public String toString() {
		return "Embarcacion [patente=" + patente + ", fechaDeIngreso=" + fechaDeIngreso + ", camaAsignada="
				+ camaAsignada + "]";
	}

}