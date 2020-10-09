package Comercio;

import java.util.Objects;

public abstract class Actor {
	static int idActor=0;
	protected int id;
	protected Contacto contacto;


	public Actor(Contacto contacto) {
		this.id = ++idActor;
		this.contacto = contacto;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", " + contacto + "]";
	}

	protected boolean validarIdentificadorUnicoDNI(long identificador) {
		boolean valido;
		String a = String.valueOf(identificador);
		int largo = a.length();
		if (largo == 8 && (identificador > 10000000L && identificador < 99999999L)) {
			valido = true;
		} else {
			valido = false;
		}
		return valido;
	}

	protected boolean validarIdentificadorUnicoCUIT(long iden) throws Exception {
		String cadenaIden = String.valueOf(iden);
		int largo = cadenaIden.length();
		if (largo != 11 && !(iden > 300000000000L && iden < 30999999999L)
				|| (iden > 330000000000L && iden < 34999999999L)) {
			// COMPRUEBA LONGITUD QUE SEA DE 11 DIGITOS
			// comprueba que empiece con 30, 33, 34
			throw new Exception("El cuit de persona juridica debe comenzar con 30, 33 o 34");

		}
		char[] cuitArray = cadenaIden.toCharArray();
		Integer[] serie = { 5, 4, 3, 2, 7, 6, 5, 4, 3, 2 };
		Integer aux = 0;
		for (int i = 0; i < 10; i++) {
			aux += Character.getNumericValue(cuitArray[i]) * serie[i];
		}
		aux = 11 - (aux % 11);
		if (aux == 11) {
			aux = 0;
		}
		return Objects.equals(Character.getNumericValue(cuitArray[10]), aux);
	}

}
