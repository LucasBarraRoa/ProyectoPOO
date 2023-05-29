package proyecto;

import java.util.Scanner;

public class Main {
	// Arreglos de inventarios para tener muchos inventarios, y de sus correspondientes nombres.
	static InventarioLetras[] inventarios = new InventarioLetras[1000];
	static String[] nombres = new String[1000];
	// contador como atributo para ocupar en varios métodos transversalmente.
	static int cont = 0;
	public static void main(String[] args) {
		// inputs distintos para que se ingresen.
		Scanner input = new Scanner(System.in);
		Scanner inputString = new Scanner(System.in);
		Scanner inputChar = new Scanner(System.in);
		boolean menu = true;
		// mensaje de input inválido.
		String invalido = "Ingrese una opción válida.\n>>> ";
		char auxChar;
		// while de menú
		while (menu) {
			String aux = "";
			int opcion = 0;
			int opcion2 = 0;
			int opcion3 = 0;
			int opcion4 = 0;
			// despliegue de opciones
			System.out.println("Ingrese una opción:"
					+ "\n1. Crear nuevo inventario."
					+ "\n2. Encriptar/desencriptar.");
			// abre nuevas opciones dependiendo de cuantos inventarios tengamos.
			if (cont > 0)
				System.out.println("3. Operaciones con inventario.\n4. Desplegar inventario(s).\n5. Amplificar inventario.");
			if (cont > 1)
				System.out.println("6. Sumar/restar inventarios.");
			System.out.print("0. Salir."
					+ "\n>>> ");
			opcion = input.nextInt();
			// salida/cierre de menú
			if (opcion == 0) {
				menu = false;
			}
			// opción para crear un nuevo inventario, pide si el inventario a crear es en inglés o español, si se ingresa inglés con un caracter español,
			// se pregunta de nuevo si es en inglés o español, y luego procede.
			if (opcion == 1) {
				System.out.print("Ingrese el idioma del inventario.\n1. Inglés.\n2. Español.\n>>> ");
				opcion2 = input.nextInt();
				while (opcion2 > 2 || opcion2 < 1 ) {
					System.out.print(invalido);
					opcion2 = input.nextInt();
				}
				System.out.print("Ingrese un String para crear un inventario:\n>>> ");
				aux = inputString.nextLine();
				if (opcion2 == 1 && isSpanish(aux)) {
					System.out.println("Detectamos caracteres en español para un inventario en inglés."
							+"\n¿Desea continuar creandolo en inglés o español?");
					System.out.print("1. Inglés.\n2. Español.\n>>> ");
					opcion = input.nextInt();
					if (opcion2 == 1)
						inventarios[cont] = new InventarioLetras(aux);
					else if (opcion2 == 2) {
						inventarios[cont] = new InventarioLetrasEsp(aux);
						aux += "(esp)";
					}
				} else if (opcion2 == 1) {
					inventarios[cont] = new InventarioLetras(aux);
				} else if (opcion2 == 2) {
					inventarios[cont] = new InventarioLetrasEsp(aux);
					aux += " (esp)";
				} 
				System.out.println("Inventario creado.");
				nombres[cont] = aux;
				cont++;
			} // opción 2, usa inventarios temporales para encriptar, en inglés o español, así que no necesita la creación de un inventario antes.
			else if (opcion == 2)  {
				System.out.print("Ingrese el String a encriptar/desencriptar:"
						+ "\n>>> ");
				aux = inputString.nextLine();
				System.out.print("Ingrese una opción:"
						+ "\n1. Encriptar."
						+ "\n2. Desencriptar."
						+ "\n>>> ");
				opcion2 = input.nextInt();
				while (opcion2 > 2 || opcion2 < 1 ) {
					System.out.print(invalido);
					opcion2 = input.nextInt();
				}
				System.out.print("Ingrese el idioma para proceder:"
						+ "\n1. Inglés."
						+ "\n2. Español."
						+ "\n>>> ");
				opcion3 = input.nextInt();
				while (opcion3 > 2 || opcion3 < 1 ) {
					System.out.print(invalido);
					opcion3 = input.nextInt();
				}
				System.out.print("Ingrese cuantos con espacios quiere encriptar/desencriptar:\n>>> ");
				opcion4 = input.nextInt();
				while (opcion4 < 0) {
					System.out.print(invalido);
					opcion4 = input.nextInt();
				} // dependiendo de la combinación, pasará encriptar o desencriptar.
				if (opcion2 == 1 && opcion3 == 1) 
					new InventarioLetras().encriptarPalabra(aux, opcion4);
				else if (opcion2 == 1 && opcion3 == 1) 
					new InventarioLetrasEsp().encriptarPalabra(aux, opcion4);
				else if (opcion2 == 2 && opcion3 == 1) 
					new InventarioLetras().desencriptarPalabra(aux, opcion4);
				else if (opcion2 == 2 && opcion3 == 2) 
					new InventarioLetrasEsp().desencriptarPalabra(aux, opcion4);
			} // cuando ya tenemos al menos 1 inventario, otorga la posibilidad de usar varias operaciones de inventario.
			else if (opcion == 3 && cont > 0) {
				System.out.println("Seleccione un inventario:");
				desplegar();
				System.out.print(">>> ");
				opcion2 = input.nextInt();
				while (opcion2 < 1 || opcion2 > cont) {
					System.out.print(invalido);
					opcion2 = input.nextInt();
				}
				System.out.print("Ingrese una opción:\n1. Settear valor de un char en el inventario."
						+ "\n2. Conseguir valor de un char en el inventario."
						+ "\n3. Conseguir tamaño de inventario."
						+ "\n4. Imprimir toString."
						+ "\n5. Desplegar inventariado."
						+ "\n6. Chequear si está vacío."
						+ "\n>>> ");
				opcion3 = input.nextInt();
				while (opcion3 > 6 || opcion3 < 1 ) {
					System.out.print(invalido);
					opcion3 = input.nextInt();
				}
				if (opcion3 == 1) {
					System.out.print("¿Qué caracter desea settear? (Sólo el primer caracter ingresado será considerado)."
							+ "\n>>> ");
					auxChar = inputChar.next().charAt(0);
					System.out.print("Ingrese a que cantidad desea settearla."
							+ "\n>>> ");
					opcion = input.nextInt();
					while (opcion < 0) {
						System.out.println("Ingrese un valor mayor o igual a 0.\n>>> ");
						opcion = input.nextInt();
					}
					inventarios[opcion2 - 1].set(auxChar, opcion);
					System.out.println("Setteado correctamente.");
				} else if (opcion3 == 2) {
					System.out.print("¿Qué caracter desea conseguir? (Sólo el primer caracter ingresado será considerado)."
							+ "\n>>> ");
					auxChar = inputChar.next().charAt(0);
					System.out.println("La cantidad de " + auxChar + " en el inventario seleccionado es de " + inventarios[opcion2 - 1].get(auxChar) + ".");
				} else if (opcion3 == 3) {
					System.out.println("El inventario es de tamaño " + inventarios[opcion2-1].size() + ".");
				} else if (opcion3 == 4) {
					System.out.println("El toString de tal inventario es " + inventarios[opcion2-1]);
				} else if (opcion3 == 5) {
					System.out.println("Inventario: " + nombres[opcion2 - 1]);
					System.out.println("(String) (Tamaño) (¿Está vacío?) (Valores del inventario)");
					print(inventarios[opcion2 - 1]);
				} else if (opcion3 == 6) {
					if (inventarios[opcion2 - 1].isEmpty())
						System.out.println("El inventario " + nombres[opcion2 - 1] + " está vacío.");
					else 
						System.out.println("El inventario " + nombres[opcion2 - 1] + " no está vacío.");
				}
			} // despliega una lista de inventarios que tenemos en el menú, en orden.
			else if (opcion == 4 && cont > 0) {
				desplegar();
			} // elige un inventario, para amplificarlo por un factor.
			else if (opcion == 5 && cont > 0) {
				System.out.println("Seleccione un inventario:");
				desplegar();
				System.out.print(">>> ");
				opcion2 = input.nextInt();
				while (opcion2 < 1 || opcion2 > cont) {
					System.out.print(invalido);
					opcion2 = input.nextInt();
				}
				System.out.print("Ingrese un factor por el cual amplificar el inventario:\n>>> ");
				opcion3 = input.nextInt();
				while (opcion3 < 0) {
					System.out.print(invalido);
					opcion3 = input.nextInt();
				}
				System.out.println("Resultado: " + nombres[opcion2 - 1] + " amplificado por " + opcion3 + ".");
				System.out.println("(String) (Tamaño) (¿Está vacío?) (Valores del inventario)");
				print(inventarios[opcion2 - 1].amplifies(opcion3));

			} // si ya tenemos al menos 2 inventarios, otorga la posibilidad de sumar o restar estos, seleccionándolos. 
			else if (opcion == 6 && cont > 1) {
				System.out.println("Seleccione el inventario base:");
				desplegar();
				System.out.print(">>> ");
				opcion2 = input.nextInt();
				while (opcion2 < 1 || opcion2 > cont) {
					System.out.print(invalido);
					opcion2 = input.nextInt();
				}
				System.out.println("Seleccione el 2do inventario:");
				desplegar2(opcion2 - 1);
				System.out.print(">>> ");
				opcion3 = input.nextInt();
				while (opcion3 < 1 || opcion3 > cont) {
					System.out.print(invalido);
					opcion3 = input.nextInt();
				}
				// revisa si son del mismo idioma, si no, no lleva a cabo la operación.
				if (inventarios[opcion2 - 1].getClass() == inventarios[opcion3 - 1].getClass()) {
					System.out.print("Ingrese la operación:"
							+ "\n1. Sumar inventarios."
							+ "\n2. Restar inventarios."
							+ "\n>>> ");
					opcion4 = input.nextInt();
					while (opcion4 > 2 || opcion4 < 1 ) {
						System.out.print(invalido);
						opcion4 = input.nextInt();
					}
					if (opcion4 == 1) {
						System.out.println("El resultado es:");
						System.out.println("(String) (Tamaño) (¿Está vacío?) (Valores del inventario)");
						print(inventarios[opcion2 - 1].add(inventarios[opcion3 - 1]));
					}
					if (opcion4 == 2) {
						if (inventarios[opcion2 - 1].subtract(inventarios[opcion3 - 1]) == null) {
							System.out.println("El resultado de la resta entre estos inventarios es nulo.");
						} else { 
							System.out.println("El resultado es:");
							System.out.println("(String) (Tamaño) (¿Está vacío?) (Valores del inventario)");
							print(inventarios[opcion2 - 1].subtract(inventarios[opcion3 - 1]));
						}
					}
				} else {
					// si no eran del mismo idioma, despliega un aviso.
					System.out.println("Los inventarios seleccionados no pertenecen al mismo idioma. Vuelva a intenarlo.");
				}
				// si se ingresó cuaquier otra opción en el menú, pide de nuevo que hacer.
			} else if (menu) {
				System.out.println("Ingrese una opción válida.");
			}
		}
		System.out.println("Programa finalizado.");
		// se cierran los Scanners.
		input.close();
		inputChar.close();
		inputString.close();
	}
	// método para imprimir la lista de inventarios que tengamos disponibles.
	public static void desplegar() {
		for (int i = 0; i < cont; i++) {
			System.out.println((i + 1) + ". " + nombres[i]);
		}
	}
	// diferente del otro ya que este muestra si fue seleccionado en el menú de sumas y restas.
	public static void desplegar2(int discriminante) {
		for (int i = 0; i < cont; i++) {
			System.out.print((i + 1) + ". " + nombres[i]);
			if (i == discriminante)
				System.out.print(" (seleccionado)");
			System.out.println();
		}
	}
	// imprime información general de un inventario, como su toString, tamaño, si está vacío, y las cantidades de cada caracter.
	public static void print(InventarioLetras inv) {
		if (inv != null) {
			System.out.print(inv + " " + inv.size() + " " + inv.isEmpty());
			for (int i = 0; i < inv.largoInv; i++) {
				System.out.print(" " + inv.inventario[i]);
			}
			System.out.println("");
		} else {
			System.out.println("null");
		}
	}
	// revisa si un String puede estar en español o no.
	public static boolean isSpanish(String frase) {
		String[] caracteres = {"ñ","á","é","í","ó","ú"};
		for (String letra : caracteres) {
			if (frase.toLowerCase().contains(letra))
				return true;
		}
		return false;
	}
}
