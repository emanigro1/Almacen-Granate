package Carrito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Comercio.*;

public class Carrito {
	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento;
	private Cliente cliente;
	ArrayList<ItemCarrito> lstItemCarrito;
	private Entrega entrega;

	public Carrito(int id, LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = false;
		this.cliente = cliente;
		this.lstItemCarrito = new ArrayList<ItemCarrito>();
		this.descuento = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean getCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(ArrayList<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
		setCerrado(true);
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado + ", descuento="
				+ descuento + ", cliente=" + cliente + ", lstItemCarrito=" + lstItemCarrito + ", entrega=" + entrega
				+ "]\n";
	}

	/**
	 * Agrega items al carrito
	 * 
	 * @param articuloNuevo articulo a ingresar
	 * @param cantidad      cantidad de articulos a ingresar
	 * @return boolean Si se logro agregar el item a la lista
	 * @throws Exception si el articulo no existe en el comercio
	 */

	public boolean agregarlstItemCarritoA(Articulo articuloNuevo, int cantidad) throws Exception {
		boolean encontrado = false;
		int i = 0;

		while (i < lstItemCarrito.size() && encontrado == false) {

			if (lstItemCarrito.get(i).getArticulo().equals(articuloNuevo)) {

				lstItemCarrito.get(i).setCantidad(lstItemCarrito.get(i).getCantidad() + cantidad);


				encontrado = true;
			}
			i++;
		} // SI NO FUE ENCONTRADO LO AGREGA
		if (!encontrado) {

			lstItemCarrito.add(new ItemCarrito(articuloNuevo, cantidad));

			encontrado = true;
		}
		return encontrado;
	}

	/**
	 * quita items del carrito
	 * 
	 * @param articuloNuevo articulo nuevo a ingresar
	 * @param cantidad      cantidad a ingresar
	 * @return boolean si se pudo quitar el item
	 * @throws si la cantidad de articulos a buscar en carrito es mayor o no se
	 *            encuentra el articulo
	 */
	public boolean quitarlstItemCarritoA(Articulo articuloNuevo, int cantidad) throws Exception {
		boolean itemRemovido = false;
		int i = 0;

		// BUSCO HASTA ENCONTRAR EL ARTICULO
		while (i < lstItemCarrito.size() && itemRemovido == false) {
			// BUSCO UNA COINCIDENCIA CON EL PRODUCTO
			if (lstItemCarrito.get(i).getArticulo().equals(articuloNuevo)) {

				// SI LA CANTIDAD ES LA MISMA REMUEVO, SI ES MENOR LE RESTO
				if (lstItemCarrito.get(i).getCantidad() == cantidad) {
					System.out.println("Misma cantidad de articulos, eliminado " + articuloNuevo);
					lstItemCarrito.remove(lstItemCarrito.get(i));
					itemRemovido = true;
				}
				// SI LA CANTIDAD ES MENOR LA RESTO
				else if (lstItemCarrito.get(i).getCantidad() > cantidad) {
					lstItemCarrito.get(i).setCantidad(lstItemCarrito.get(i).getCantidad() - cantidad);
					System.out.println("Restado del carrito " + articuloNuevo );

					itemRemovido = true;
				}
			}

			i++;
		}
		if (!itemRemovido) {// SI EL ARTICULO NO EXISTE

			throw new Exception("No existe el articulo en el carrito " + articuloNuevo );
		}
		return itemRemovido;
	}

	/**
	 * calcula total del carrito sin descuento
	 * 
	 * @return el total del carrito
	 */
	public double calcularTotalCarrito() {
		double total = 0;

		int i = 0;

		while (i < lstItemCarrito.size()) {// SUMO CADA ITEM
			total += lstItemCarrito.get(i).calcularSubTotalItem();
			i++;
		}
		return total;
	}

	/**
	 * Este método calcula el descuento a la segunda unidad si el dia es el correcto
	 * 
	 * @param diaDescuento           es un int que indica un dia de la semana
	 * @param porcentajeDescuentoDia es un double que contiene el porcentaje a
	 *                               descontar de la segunda unidad de los articulos
	 * @return Descuento aplicado a las segundas unidades
	 */
	public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuentoDia) {
		double precioArticulo = 0;
		int unidadesConDescuento = 0;

		if (diaDescuento == LocalDate.now().getDayOfWeek().getValue()) {

			for (ItemCarrito iterador : lstItemCarrito) {
				// UNA ITERACIÓN POR CADA ITEM DEL CARRITO
				if (iterador.getCantidad() > 1) { // SI LA CANTIDAD DEL ARTICULO DEL ITEM CARRITO ES MÁS DE 1
					unidadesConDescuento = iterador.getCantidad() / 2; // LAS UNIDADES CON DESCUENTO SERÁN LA MITAD DE
					precioArticulo = iterador.getArticulo().getPrecio(); // OBTENGO EL PRECIO DEL ARTICULO
					this.descuento = this.descuento + unidadesConDescuento * precioArticulo * porcentajeDescuentoDia / 100;
					// CALCULO EL DESCUENTO DEL ARTICULO Y LOS SUMO AL ACUMULADOR
				}
			}
		}

		return this.descuento;
	}

	/**
	 * Este método calcula el descuento tras el pago en efectivo
	 * 
	 * @param porcentajeDescuentoEfectivo es un double que contiene el porcenteje a
	 *                                    descontar del carrito
	 * @return El descuento aplicado al total del carrito
	 */
	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
		// EL DESCUENTO SERIA EL TOTAL POR EL PORCENTAJE A
		return calcularTotalCarrito() * porcentajeDescuentoEfectivo / 100;
	}

	/**
	 * Este método compara los descuentos
	 * 
	 * @param diaDescuento                es un int que indica un dia de la semana
	 * @param porcentajeDescuentoDia      es un double que contiene el porcentaje a
	 *                                    descontar de la segunda unidad de los
	 *                                    articulos
	 * @param porcentajeDescuentoEfectivo es un double que contiene el porcenteje a
	 *                                    descontar del carrito
	 * @return El descuento más grande
	 */
	public double calcularDescuentoCarrito(int diaDescuento, double porcentajeDescuentoDia,
			double porcentajeDescuentoEfectivo) {

		// Si la entrega es en efectivo realizo:
		if (entrega.isEfectivo()) {

			// si el descueto del dia es mayor o igual al de efectivo realizo descuento
			if (calcularDescuentoDia(diaDescuento,
					porcentajeDescuentoDia) >= calcularDescuentoEfectivo(porcentajeDescuentoEfectivo)) {

				this.descuento = calcularDescuentoDia(diaDescuento, porcentajeDescuentoDia);

			} else {

				this.descuento = calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
			}

		} else {

			// si no es efectivo realizo el descuento dia si es que hay
			this.descuento = calcularDescuentoDia(diaDescuento, porcentajeDescuentoDia);
		}

		return this.descuento;
	}

	/**
	 * Este método se encarga de calcular el total final del carrito con el
	 * descuento aplicado
	 * 
	 * @return El total menos el descuento del carrito
	 */

	public double totalAPagarCarrito() {
		double resultado;
		// Si existe una entrega con envio lo sumo
		if (entrega instanceof Envio) {

			System.out.println("Costo envio: " + ((Envio) getEntrega()).getCosto());

			// le sumo el envio
			resultado = calcularTotalCarrito() - this.descuento + ((Envio) getEntrega()).getCosto();

		} else {
			// agregar turno disponible imprimimos el turno asignado
			// envio el resultado sin envio
			System.out.println("Retira por local.");

			resultado = calcularTotalCarrito() - this.descuento;

		}
		return resultado;
	}

	
	// 	METODO PARA SETEAR ENTREGA POR ENVIO
	public void nuevaEntrega(LocalDate fecha, boolean efectivo, LocalTime horaHasta, LocalTime horaDesde,
			Ubicacion ubicacion, Ubicacion ubicacionC, double costoFijo, double costoPorKm) throws Exception {
		if (entrega != null) {
			throw new Exception("Ya existe una entrega con retiro local");
		}
		// setea ENVIO NUEVO
		setEntrega(new Envio(fecha, efectivo, horaHasta, horaDesde, ubicacion, ubicacionC, costoFijo, costoPorKm));
	}

// 	METODO PARA SETEAR ENTREGA POR RETIRO LOCAL
	public void nuevaEntrega(LocalDate fecha, boolean efectivo,LocalTime horaEntrega) throws Exception {

		if (entrega != null) {
			throw new Exception("Ya existe una entrega con envio");
		}
		setEntrega(new RetiroLocal( fecha,  efectivo, horaEntrega));
	}	
}