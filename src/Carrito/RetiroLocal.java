package Carrito;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal extends Entrega {
	private LocalTime horaEntrega;

	public RetiroLocal(LocalDate fecha, boolean efectivo,LocalTime horaEntrega) {
		super(fecha, efectivo);
		this.horaEntrega=horaEntrega;
		
	}

	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
 
	
	
	
}
