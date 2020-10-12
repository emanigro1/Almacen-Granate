package Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import Carrito.Envio;

import Comercio.Cliente;
import Comercio.Comercio;
import Comercio.Contacto;
import Comercio.Turno;
import Comercio.Ubicacion;

public class TestAlmacen {

	public static void main(String[] args) {

		try {
			// INSTANCIO COMERCIO
			// diaDescuento: 7 - porcentajeDescuentoDia: 100 - porcentajeDescuentoEfectivo: 50
			
			Comercio comercio = new Comercio(
					new Contacto("almacengranate", "15151651", new Ubicacion(-38.5545, -58.7396)), "Almacen Granate",
					30610252334L, 100, 5, 7, 10, 25);

			// Agrego articulos al comercio
			comercio.agregarLstArticulo("jabón", "1234567890418", 30);
			comercio.agregarLstArticulo("sal", "6382730434473", 20);
			comercio.agregarLstArticulo("arroz", "9659376765214", 40);
			comercio.agregarLstArticulo("harina", "4950671922148", 10);
			comercio.agregarLstArticulo("lavandina", "7388386942842", 60);
			comercio.agregarLstArticulo("leche", "5752907913932", 70);
			comercio.agregarLstArticulo("aceite", "6046202617466", 33);
			comercio.agregarLstArticulo("huevos", "5701050395621", 74);
			comercio.agregarLstArticulo("levadura", "7615596342361", 58);
			comercio.agregarLstArticulo("azucar", "5575951290145", 44);

			// agrego dia de retiro
			comercio.agregarDiaRetiro(1, LocalTime.of(6, 00), LocalTime.of(18, 00), 1); // LUNES
			comercio.agregarDiaRetiro(2, LocalTime.of(6, 00), LocalTime.of(18, 00), 2); // MARTES
			comercio.agregarDiaRetiro(3, LocalTime.of(6, 00), LocalTime.of(18, 00), 3); // MIERCOES
			comercio.agregarDiaRetiro(4, LocalTime.of(6, 00), LocalTime.of(18, 00), 1); // JUEVES
			comercio.agregarDiaRetiro(5, LocalTime.of(6, 00), LocalTime.of(18, 00), 1); // VIERNES
			comercio.agregarDiaRetiro(6, LocalTime.of(6, 00), LocalTime.of(18, 00), 1); // SABADO
			comercio.agregarDiaRetiro(7, LocalTime.of(6, 00), LocalTime.of(18, 00), 2); // DOMINGO

			// creo clientes
			Cliente cliente1 = comercio.nuevoCliente("emanigro@gmail.com", "53613131", -38.0055, -57.5426, "Nigro",
					"Emanuel", 30659878L, 'm');
			Cliente cliente2 = comercio.nuevoCliente("nahuelponce@gmail.com", "1564165156", 2312L, 2312L, "Ponce",
					"Gaston", 42231767L, 'f');
			Cliente cliente3 = comercio.nuevoCliente("maurirossi@gmail.com", "1532326323", 315L, 25L, "Mauricio",
					"Rossi", 63987541L, 'f');
			Cliente cliente4 = comercio.nuevoCliente("fedeprocs@gmail.com", "15151611", 123L, 823L, "Federic", "Procs",
					35426987L, 'm');

			// creo carritos
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente1);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente2);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente3);
			comercio.agregarLstCarrito(LocalDate.now(), LocalTime.now(), cliente4);

			// Agrego items al carrito 1
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 4);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 6);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("9659376765214"), 1);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 2);
			comercio.traerCarritoId(1).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 5);


			// Agrego items al carrito 2
			comercio.traerCarritoId(2).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 4);
			comercio.traerCarritoId(2).agregarlstItemCarritoA(comercio.traerArticuloCod("6382730434473"), 6);
			comercio.traerCarritoId(2).agregarlstItemCarritoA(comercio.traerArticuloCod("9659376765214"), 1);
			comercio.traerCarritoId(2).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 2);
			comercio.traerCarritoId(2).agregarlstItemCarritoA(comercio.traerArticuloCod("4950671922148"), 5);
			
			// AGREGO OTRO ITEM CARRITO 3
			comercio.traerCarritoId(3).agregarlstItemCarritoA(comercio.traerArticuloCod("5575951290145"), 4);
			comercio.traerCarritoId(3).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 6);
			comercio.traerCarritoId(3).agregarlstItemCarritoA(comercio.traerArticuloCod("5752907913932"), 1);
			comercio.traerCarritoId(3).agregarlstItemCarritoA(comercio.traerArticuloCod("7388386942842"), 2);
			comercio.traerCarritoId(3).agregarlstItemCarritoA(comercio.traerArticuloCod("5575951290145"), 5);


			// AGREGO OTRO ITEM CARRITO 4
			comercio.traerCarritoId(4).agregarlstItemCarritoA(comercio.traerArticuloCod("7388386942842"), 4);
			comercio.traerCarritoId(4).agregarlstItemCarritoA(comercio.traerArticuloCod("7615596342361"), 6);
			comercio.traerCarritoId(4).agregarlstItemCarritoA(comercio.traerArticuloCod("5752907913932"), 1);
			comercio.traerCarritoId(4).agregarlstItemCarritoA(comercio.traerArticuloCod("6046202617466"), 2);
			comercio.traerCarritoId(4).agregarlstItemCarritoA(comercio.traerArticuloCod("5575951290145"), 5);

			
			
			// AGREGO ENTREGA ENVIO AL CARRITO 1
			comercio.traerCarritoId(1).nuevaEntrega(LocalDate.now(), true, LocalTime.now(), LocalTime.of(18, 30),
					cliente1.getContacto().getUbicacion(), comercio.getContacto().getUbicacion(),
					comercio.getCostoFijo(), comercio.getCostoPorKm());

			// AGREGO ENTREGA RETIRO AL SEGUNDO CARRITO 2,3,4
			comercio.traerCarritoId(2).nuevaEntrega(LocalDate.now(), true, LocalTime.of(06, 00));
			comercio.traerCarritoId(3).nuevaEntrega(LocalDate.now(), true, LocalTime.of(16, 00));
			comercio.traerCarritoId(4).nuevaEntrega(LocalDate.now(), false, LocalTime.of(12, 00));

			
			// TRAE EL PRIMER TURNO LIBRE DISPONIBLE
			System.out.println("Primer turno disponible: "+comercio.traerHoraRetiro(LocalDate.now()));
			
			System.out.println();
			
			// AGENDA CON LOS TURNOS OCUPADOS Y DISPONIBLES ORDENADOS
			System.out.println(comercio.generarAgenda(LocalDate.now()));

			System.out.println();

			
			// MUESTRO CARRITOS
			for (int i = 1; i <= 4; i++) {
				System.out.println("Total carrito "+i+" sin descuento: " + comercio.traerCarritoId(i).calcularTotalCarrito());
				System.out.println(
						"Descuento: " + comercio.traerCarritoId(i).calcularDescuentoCarrito(comercio.getDiaDescuento(),
								comercio.getPorcentajeDescuentoDia(), comercio.getPorcentajeDescuentoEfectivo()));
				System.out.println("Total carrito: " + comercio.traerCarritoId(i).totalAPagarCarrito() + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}