package Test;

import Carrito.Articulo;
import Comercio.Comercio;
import Comercio.Contacto;
import Comercio.Ubicacion;

public class TestCodBarra {

	public static void main(String[] args) {

		try {
			// creo comercio
			Comercio comercio = new Comercio(
					new Contacto("almacengranate", "15151651", new Ubicacion(-38.5545, -58.7396)), "Almacen Granate",
					30610252334L, 100, 5, 3, 20, 30);

			// agrego un articulo con el codigo incorrecto
			comercio.agregarLstArticulo("jabón", "1234567890416", 30);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
