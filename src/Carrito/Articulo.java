package Carrito;

public class Articulo {
	private int id;
	private String nombre;
	private String codBarra;
	private double precio;

	public Articulo(int id, String nombre, String codBarra, double precio) throws Exception { // {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.setCodBarra(codBarra);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) throws Exception {
		if (!validarCodBarras(codBarra)) {
			throw new Exception("Codigo de barra no valido.");
		}
		this.codBarra = codBarra;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * Validador de codigo de barras
	 * 
	 * @param codBarra codigo de barras a verificar
	 * @return boolean si el codigo ingresado fue valido
	 */
	public boolean validarCodBarras(String codBarra) {
		boolean valido;
		// PARSEO EL STRING A UN ARRAY DE CHAR
		String[] codigoString = codBarra.split("");
		// INSTANCIO INT ARRAY CON LONGITUD DEL ULTIMO ARRAY CHAR
		int[] codigoInt = new int[codigoString.length];
		int sumaPares = 0, sumaImpares = 0;

		if (codBarra.length() == 13) {

			for (int i = 0; i < codigoString.length; i++) {
				// PARSEO EL ARRAY CHAR A INT ARRAY
				codigoInt[i] = Integer.parseInt(codigoString[i]);
				// SUMO LOS DIGITOS PARES MENOS EL DIGITO 12
				if (i % 2 == 0 && i != 12) {
					sumaPares = sumaPares + codigoInt[i];
				}
				// SUMO LOS DIGITOS IMPARES
				if (i % 2 == 1) {
					sumaImpares = sumaImpares + codigoInt[i];
				}
			}
			int imparesParesSumados, contador = 0;
			// SUMO Y MULTIPLICO POR 3 LAS SUMAS PARES E IMPARES
			imparesParesSumados = sumaPares + sumaImpares * 3;

			// CUENTO CUANTOS NUMEROS FALTAN PARA LLEGAR A SU SUPERIOR MULTIPLO DE 10
			while (imparesParesSumados % 10 != 0) {
				contador++;
				imparesParesSumados++;
			}
			// SI LA DIFERENCIA ANTERIOR DA IGUAL AL ULTIMO DIGITO DE LA BARRA ENTONCES ES
			// VALIDA
			if (contador == codigoInt[12]) {
				this.codBarra = codBarra;
				valido = true;
			} else {
				System.out.println("Codigo invalido");
				valido = false;
			}
			// LONGITUD INVALIDA
		} else {
			System.out.println("La longitud del codigo es invalida");
			valido = false;
		}
		return valido;
	}

	public boolean equals(Articulo articulo) throws Exception {
		boolean esIgual = false;
		if (articulo == null) {
			throw new Exception("Articulo inexistente en el comercio");
		}
		if (id == articulo.getId() || codBarra == articulo.getCodBarra()) {
			esIgual = true;
		}
		return esIgual;
	}

	public boolean equals(String codBarraN) {
		boolean esIgual = false;

		if (codBarra.equals(codBarraN)) {
			esIgual = true;
		}
		return esIgual;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", codBarra=" + codBarra + ", precio=" + precio + "]\n";
	}

}
