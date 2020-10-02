package Test;

import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.Contacto;
import Comercio.Ubicacion;

public class TestValidarIdentificadores {

	public static void main(String[] args) {
		try {
			Cliente cliente1 = new Cliente( new Contacto("emanigro@gmail.com", "53613131", new Ubicacion(515L, 154L)),
					"Nigro", "Emanuel", 999999999L, 'm');
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			Comercio comercio = new Comercio(new Contacto("almacengranate", "15151651", new Ubicacion(22L, 255L)),
					"Almacen Granate", 35610252334L, 100, 40, 3, 20, 30);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
