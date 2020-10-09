package Test;

import java.time.LocalDate;
import java.time.LocalTime;

import Carrito.Envio;

import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.Contacto;

import Comercio.Ubicacion;

public class TestAlmacen {

	public static void main(String[] args) {

		try {
			// creo comercio
			Comercio comercio = new Comercio(
					new Contacto("almacengranate", "15151651", new Ubicacion(-38.5545, -58.7396)), "Almacen Granate",
					30610252334L, 100, 5, 4, 10, 25);

			// creo clientes
			Cliente cliente1 = comercio.nuevoCliente("emanigro@gmail.com", "53613131", -38.0055, -57.5426, "Nigro",
					"Emanuel", 30659878L, 'm');
			Cliente cliente2 = comercio.nuevoCliente("nahuelponce@gmail.com", "1564165156", 2312L, 2312L, "Ponce",
					"Gaston", 42231767L, 'f');
			Cliente cliente3 = comercio.nuevoCliente("maurirossi@gmail.com", "1532326323", 315L, 25L, "Mauricio",
					"Rossi", 63987541L, 'f');
			Cliente cliente4 = comercio.nuevoCliente("fedeprocs@gmail.com", "15151611", 123L, 823L, "Federic", "Procs",
					35426987L, 'm');

			// Agrego articulos al comercio
			comercio.agregarLstArticulo("jabón", "1234567890418", 30);
			comercio.agregarLstArticulo("sal", "6382730434473", 30);
			comercio.agregarLstArticulo("arroz", "9659376765214", 30);
			comercio.agregarLstArticulo("harina", "4950671922148", 30);
			comercio.agregarLstArticulo("lavandina", "7388386942842", 30);
			comercio.agregarLstArticulo("leche", "5752907913932", 30);
			comercio.agregarLstArticulo("aceite", "6046202617466", 30);
			comercio.agregarLstArticulo("huevos", "5701050395621", 30);
			comercio.agregarLstArticulo("levadura", "7615596342361", 30);
			comercio.agregarLstArticulo("azucar", "5575951290145", 30);

			// creo carritos
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente1);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente2);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente3);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente4);
			

			// agrego dia de retiro
			comercio.agregarDiaRetiro(1, LocalTime.of(5, 30), LocalTime.of(18, 30), 1);

			// Agrego items al carrito
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 4);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 6);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("9659376765214"), 1);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 2);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 5);

			// agrego envio
			comercio.traerCarritoId(1).nuevaEntrega(LocalDate.now(), false, LocalTime.now(), LocalTime.of(18, 30),
					cliente1.getContacto().getUbicacion(), comercio.getContacto().getUbicacion(),
					comercio.getCostoFijo(), comercio.getCostoPorKm());

			System.out.println();
			System.out.println("Total carrito sin descuento: " + comercio.traerCarritoId(1).calcularTotalCarrito());
			System.out.println("Descuento: "
					+ comercio.traerCarritoId(1).calcularDescuentoCarrito(LocalDate.now().getDayOfWeek().getValue(),
							comercio.getPorcentajeDescuentoDia(), comercio.getPorcentajeDescuentoEfectivo()));
			System.out.println("Total carrito: " + comercio.traerCarritoId(1).totalAPagarCarrito());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}