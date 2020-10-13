package Carrito;

import java.time.LocalDate;

public abstract class Entrega {
	static int idEntrega = 0;
	protected int id;
	protected LocalDate fecha;
	protected boolean efectivo;

	public Entrega(LocalDate fecha, boolean efectivo) {
		this.id = ++idEntrega;
		this.fecha = fecha;
		this.efectivo = efectivo;
	}

	public int getId() {
		return id;
	}

	public void setId() {
		this.id = ++idEntrega;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public boolean isEfectivo() {
		return efectivo;
	}

	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}

	public boolean equals(LocalDate fecha) {
		if (getFecha().compareTo(fecha)==0) {
			return true;
		}
		return false;
	}

}