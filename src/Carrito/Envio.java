package Carrito;

import java.time.LocalDate;
import java.time.LocalTime;

import Comercio.Ubicacion;

/**
 * @author peqe_
 *
 */
public class Envio extends Entrega {
	private LocalTime horaHasta;
	private LocalTime horaDesde;
	private double costo;
	private Ubicacion ubicacion;
	
	public Envio( LocalDate fecha, boolean efectivo, LocalTime horaHasta, LocalTime horaDesde, Ubicacion ubicacion, Ubicacion ubicacionC, double costoFijo , double costoPorKm) {
		super( fecha, efectivo);
		this.horaHasta = horaHasta;
		this.horaDesde = horaDesde;
		this.ubicacion = ubicacion;
		this.setCosto(ubicacionC, costoFijo , costoPorKm);
		
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public double getCosto() {
		return costo;
	}
	
	
	/**
	 * SETEA COSTO 
	 */
	public void setCosto(Ubicacion ubicacionC, double costoFijo, double costoPorKm) {
		double distancia =  distanciaCoord(ubicacion.getLatitud(), ubicacion.getLongitud(), ubicacionC.getLatitud(), ubicacionC.getLongitud());
		this.costo = (distancia * costoPorKm) + costoFijo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	

	
	/**DEVUELDE LA DISTANCIA ENTRE DOS PUNTOS
			
	 * @return 	DISTANCIA:DOUBLE
	 */
	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371; //en kilómetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 =Math.pow(sindLat, 2)+Math.pow(sindLng, 2)*Math.cos(Math.toRadians(lat1))*
		Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
		}
	
	
}