package Test;

import java.time.LocalDate;
import java.time.LocalTime;

import Carrito.Articulo;
import Carrito.Carrito;
import Carrito.ItemCarrito;
import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.Contacto;
import Comercio.DiaRetiro;
import Comercio.Ubicacion;

public class AgregarQuitarCarrito {

	public static void main(String[] args) {

		try {
			// INSTANCIO COMERCIO
			// diaDescuento: 1 - porcentajeDescuentoDia: 100 - porcentajeDescuentoEfectivo: 50
			
			Comercio comercio = new Comercio(
					new Contacto("almacengranate", "15151651", new Ubicacion(-38.5545, -58.7396)), "Almacen Granate",
					30610252334L, 100, 5, 1, 10, 25);

			// Creo cliente
			Cliente cliente = comercio.nuevoCliente("emanigro@gmail.com", "53613131", -38.0055, -57.5426, "Nigro",
					"Emanuel", 30659878L, 'm');					
			
			// Agrego articulos al comercio
			comercio.agregarLstArticulo( "arroz", "9659376765214", 30);
			comercio.agregarLstArticulo( "sal", "6382730434473", 30);
			comercio.agregarLstArticulo("levadura", "7615596342361", 30);
			comercio.agregarLstArticulo( "leche", "5752907913932", 30);
			

			
			// Creo carrito
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente);

			
			// Agrego items a carrito
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("9659376765214"), 8);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 1);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 2);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 1);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 3);
			
	
			
			// Elimino productos del carrito
			// La cantidad es menor, lo resta
			comercio.traerCarritoId(1).quitarlstItemCarritoA(comercio.traerArticuloCod("9659376765214"), 6);
			// La cantidad es la misma, lo elimina de carrito
			comercio.traerCarritoId(1).quitarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 1);
			// si el item no existe en el carrito lanza excepcion
			comercio.traerCarritoId(1).quitarlstItemCarritoA(comercio.traerArticuloCod("5752907913932"), 2);


		} catch (Exception e) {
			e.printStackTrace();
		}		

	}

}
