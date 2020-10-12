package Comercio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Carrito.Articulo;
import Carrito.Carrito;
import Carrito.Entrega;
import Carrito.Envio;
import Carrito.RetiroLocal;
import Carrito.ItemCarrito;

/**
 * @author peqe_
 *
 */
/**
 * @author peqe_
 *
 */
public class Comercio extends Actor {
	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento;
	private int porcentajeDescuentoDia;
	private int porcentajeDescuentoEfectivo;
	ArrayList<DiaRetiro> lstDiaRetiro;
	ArrayList<Carrito> lstCarrito;
	ArrayList<Articulo> lstArticulo;

	public Comercio(Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) throws Exception {
		super(contacto);
		this.nombreComercio = nombreComercio;
		this.setCuit(cuit);
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
		this.lstDiaRetiro = new ArrayList<DiaRetiro>();
		this.lstCarrito = new ArrayList<Carrito>();
		this.lstArticulo = new ArrayList<Articulo>();
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) throws Exception {
		if (!validarIdentificadorUnicoCUIT(cuit)) {
			throw new Exception("CUIT Invalido");
		}
		this.cuit = cuit;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	@Override
	public String toString() {
		return "Comercio [nombreComercio=" + nombreComercio + ", cuit=" + cuit + ", costoFijo=" + costoFijo
				+ ", costoPorKm=" + costoPorKm + ", diaDescuento=" + diaDescuento + ", porcentajeDescuentoDia="
				+ porcentajeDescuentoDia + ", porcentajeDescuentoEfectivo=" + porcentajeDescuentoEfectivo
				+ ", lstDiaRetiro=" + lstDiaRetiro + ", lstCarrito=" + lstCarrito + ", lstArticulo=" + lstArticulo
				+ ", Contacto=" + super.toString() + "]";
	}

	public ArrayList<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(ArrayList<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	public ArrayList<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(ArrayList<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public ArrayList<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(ArrayList<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	// AGREGA OBJETOS A LAS LISTAS
	public void agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) {

		lstDiaRetiro.add(new DiaRetiro(idNuevo("diaRetiros"), diaSemana, horaDesde, horaHasta, intervalo));
	}

	public void agregarLstArticulo(String nombre, String codBarra, double precio) throws Exception {
		lstArticulo.add(new Articulo(idNuevo("articulos"), nombre, codBarra, precio));
	}
	
	public void agregarLstCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		lstCarrito.add(new Carrito(idNuevo("carritos"), fecha, hora, cliente));
	}
	
	// instancias de clases relacionadas

	public Cliente nuevoCliente(String email, String celular, double latitud, double longitud, String apellido,
			String nombre, long dni, char genero) throws Exception {

		return new Cliente(new Contacto(email, celular, new Ubicacion(latitud, longitud)), apellido, nombre, dni,
				genero);
	}

	public Contacto nuevoContacto(String email, String celular, Ubicacion ubicacion) {
		return new Contacto(email, celular, ubicacion);
	}
		
	
	/** RECIBE UN CODIGO DE BARRAS CON EL CUAL BUSCA UN ARTICULO
	 * 
	 * @param codBarra  
	 * @return ARTICULO 
	 */
	public Articulo traerArticuloCod(String codBarra) {
		Articulo traerArt = null;
		int i = 0;

		while (i < lstArticulo.size() && traerArt == null) {

			if (lstArticulo.get(i).getCodBarra().equals(codBarra)) {
				traerArt = lstArticulo.get(i);
			}
			i++;
		}
		return traerArt;
	}



	/** RECIBE UN ID CON EL CUAL TRAE UN CARRITO
	 * 
	 * @param id
	 * @return CARRITO
	 */
	public Carrito traerCarritoId(int id) {
		Carrito traerCarrito = null;

		for (Carrito str : lstCarrito) {
			if (str.getId() == id) {
				traerCarrito = str;
			}
		}
		return traerCarrito;
	}



	
	/**	RECIBE UN STRING INDICANDO EN QUE LISTA GENERAR EL ID Y RETORNA UNO NUEVO
	 * 
	 * @param LISTA
	 * @return ID NUEVO
	 */
	public int idNuevo(String lista) {
		int idNuevo = 1;
		int i = 0;

		if (lista.equalsIgnoreCase("articulos")) {
			while (i < lstArticulo.size()) {

				if (lstArticulo.get(i).getId() == idNuevo) {
					idNuevo++;
					i = -1;
				}
				i++;

			}
		}
		if (lista.equalsIgnoreCase("carritos")) {
			while (i < lstCarrito.size()) {

				if (lstCarrito.get(i).getId() == idNuevo) {
					idNuevo++;
					i = -1;
				}
				i++;

			}
		}
		if (lista.equalsIgnoreCase("diaRetiros")) {
			while (i < lstDiaRetiro.size()) {

				if (lstDiaRetiro.get(i).getId() == idNuevo) {
					idNuevo++;
					i = -1;
				}
				i++;

			}
		}

		return idNuevo;
	}



	/** RECIBE UNA FECHA Y BUSCA SI HAY DIA DE RETIRO, SI LO ENCUENTRA LO DEVUELVE
	 * 
	 * @param FECHA
	 * @return DIA DE RETIRO 
	 */
	public DiaRetiro traerDiaRetiroPorDiaSemana(LocalDate diaSemana) {
		int diaSemanaNum = diaSemana.getDayOfWeek().getValue();
		DiaRetiro dia = null;
		for (DiaRetiro diaRetiro : lstDiaRetiro) {
			if (diaRetiro.getDiaSemana() == diaSemanaNum) {
				dia = diaRetiro;
			}
		}
		return dia;

	}

	
	/** RECIBE UNA FECHA CON LA QUE BUSCA LOS RETIROS DE LOS CARRITOS Y CREA UNA LISTA DE TURNOS OCUPADOS
	 * 
	 * @param FECHA 
	 * @return LISTA CON TURNOS OCUPADOS 
	 */
	public ArrayList<Turno> traerTurnosOcupados(LocalDate fecha) {
		ArrayList<Turno> turnosOcupados = new ArrayList<Turno>();

		for (Carrito carrito : lstCarrito) {
			if (carrito.getEntrega() != null) {
				if (carrito.getEntrega().getFecha().equals(fecha) && carrito.getEntrega() instanceof RetiroLocal) {
					turnosOcupados.add(new Turno(fecha, ((RetiroLocal) carrito.getEntrega()).getHoraEntrega(), true));
				}
			}
		}
		return turnosOcupados;
	}

	
	/** DEVUELVE UNA LISTA CON TURNOS LIBRES RELACIONADA CON LA FECHA PASADA POR PARAMETRO
	 * 
	 * @param FECHA 
	 * @return LISTA CON TURNOS LIBRES
	 */
	public ArrayList<Turno> traerTurnosLibres(LocalDate fecha) {

		ArrayList<Turno> turnosLibres = new ArrayList<Turno>();
		ArrayList<Turno> turnosOcupados = traerTurnosOcupados(fecha);

		DiaRetiro diaRetiro = traerDiaRetiroPorDiaSemana(fecha);
		LocalTime horaInicio = diaRetiro.getHoraDesde();

		boolean encontrado = false;

		while (horaInicio.isBefore(diaRetiro.getHoraHasta())) { // COMPRUEBA SI LA HORA DE INICIO ES SUPERIO A LA HORA
			encontrado = false;
			// MAXIMA HASTA CUANDO SE ERMITE ENTREGAR TURNOS LIBRES
			for (Turno turnoOcupado : turnosOcupados) {
				if (turnoOcupado.getHora() == horaInicio) {
					encontrado = true;
				}
			}
			if (!encontrado) {
				turnosLibres.add(new Turno(fecha, horaInicio, false)); // Agrego un turno libre con la fecha mandada por
																		// parametro y la hora praviamente comprobada
																		// que no supera la hora maxima
			}
			horaInicio = horaInicio.plusHours(diaRetiro.getIntervalo()); // LE SUMO EL INTERVALO DE HORAS PARA CADA //
																			// TURNO
		}
		return turnosLibres;
	}

	
	/** RECIBE UNA FECHA CON LA CUAL GENERA UNA AGENDA CON TURNOS OCUPADOS Y LIBRES
	 * 
	 * @param FECHA	 
	 * @return LISTA DE AGENDA
	 */
	public ArrayList<Turno> generarAgenda(LocalDate fecha) {
		ArrayList<Turno> agendaCompleta = new ArrayList<Turno>();
		agendaCompleta.addAll(traerTurnosLibres(fecha));
		agendaCompleta.addAll(traerTurnosOcupados(fecha));
		
		Turno auxiliar = null;

		for (int i = 1; i < agendaCompleta.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (agendaCompleta.get(i).getHora().isBefore(agendaCompleta.get(j).getHora())) {
					auxiliar = agendaCompleta.get(i);
					agendaCompleta.set(i, agendaCompleta.get(j));
					agendaCompleta.set(j, auxiliar);
				}
			}
		}
		return agendaCompleta;
	}
	
	public LocalTime traerHoraRetiro(LocalDate fecha) {
		ArrayList<Turno> turnosLibres = traerTurnosLibres(fecha);
	return turnosLibres.get(0).getHora();
	}
}
