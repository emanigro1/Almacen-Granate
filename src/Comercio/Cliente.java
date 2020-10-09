package Comercio;

public class Cliente extends Actor{
	private String apellido;
	private String nombre;
	private long dni;
	
	public Cliente(Contacto contacto, String apellido, String nombre, long dni, char genero)throws Exception {
		super(contacto);
		this.apellido = apellido;
		this.nombre = nombre;
		this.setDni(dni);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
		

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) throws Exception  {
		if(! validarIdentificadorUnicoDNI(dni))  {
			throw new Exception("DNI invalido");
		}
		this.dni = dni;
	}

	@Override
	public String toString() {
		return "Cliente [apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + ", id=" + id + ", "
				+ contacto + "]";
	}
	
	
	
}
