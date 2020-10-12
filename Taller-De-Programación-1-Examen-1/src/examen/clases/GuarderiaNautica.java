package examen.clases;

public class GuarderiaNautica {

	private static final int CANT_NIVELES = 2;
	private static final int CAMAS_X_NIVEL = 4;
	private Fecha fechaDeHoy;
	private Embarcacion[][] embarcaciones;
	private int primerNivelConLugar;
	private int primeraCamaLibre;

	/**
	 * Constructor.
	 * 
	 * @param fecha
	 *            Recibe la fecha del dia
	 */
	public GuarderiaNautica(Fecha fecha) {
		this.primerNivelConLugar = 0;
		this.primeraCamaLibre = 0;
		this.embarcaciones = new Embarcacion[CANT_NIVELES][CAMAS_X_NIVEL];
		this.fechaDeHoy = fecha;
	}

	/**
	 * Metodo privado que debe devolver la embarcacion con la misma patente
	 * recibida por parametro, o null.
	 * 
	 * @param patente
	 *            La patente de la embarcacion buscada.
	 * @return La embarcacion o null.
	 */
	public Embarcacion obtenerEmbarcacionPorPatente(String patente) {
			// TODO completar
			Embarcacion embarcacion = null;
			int i = 0;
			int j = 0;
			while (i < embarcaciones.length && embarcacion == null) {
				while (j<embarcaciones[i].length && embarcacion == null) {
					if (embarcaciones[i][j] != null) {
						if (embarcaciones[i][j].getPatente().matches(patente)) {
							embarcacion = embarcaciones[i][j];
						} else {
							j++;
						}
					}
				}
				i++;
			}
			return embarcacion;
		}

	/**
	 * Muestra los datos de cada embarcacion (patente, cama asignada y fecha de
	 * ingreso) más el importe recaudado en el anio y si adeuda cuotas o no.
	 */
	public void mostrarPagosPorEmbarcacion() {
		Embarcacion embarcacion;
		for (int i = 0; i < this.embarcaciones.length; i++) {
			for (int j = 0; j < this.embarcaciones[i].length; j++) {
				embarcacion = this.embarcaciones[i][j];
				if(embarcacion != null) {
					System.out.println(embarcacion + " " + embarcacion.totalAbonado(fechaDeHoy) + " Adeuda: " + embarcacion.tieneDeuda(fechaDeHoy));
				}
			}
			
		}
	}

	public void pagarCuota(String patente, String cbu, int importe) {
		Embarcacion embarcacion = obtenerEmbarcacionPorPatente(patente);
		if (embarcacion != null) {
			if (embarcacion.tieneDeuda(fechaDeHoy)) {
				embarcacion.registrarPago(fechaDeHoy, cbu, importe);
			}
		}
	}

	public void registrarEmbarcacion(String patente, Fecha fecha) {
		if (primerNivelConLugar < CANT_NIVELES) {
			embarcaciones[primerNivelConLugar][primeraCamaLibre] = new Embarcacion(patente, fecha,
					(primerNivelConLugar + 1) * 10 + primeraCamaLibre + 1);
			primeraCamaLibre++;
			if (primeraCamaLibre == CAMAS_X_NIVEL) {
				primerNivelConLugar++;
				primeraCamaLibre = 0;
			}
		}
	}

}