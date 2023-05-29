package proyecto;
import java.util.Arrays;

public class InventarioLetras {
	// variable que contiene un String a procesar.
	protected String data = "";
	// recuento del inventario.
	protected int recuento;
	// variable interna usada para procesos de recorrido y rangos.
	protected int largoInv;
	// arreglo en enteros que por índice, contiene la cantidad de ocurrencias de una letra.
	protected int[] inventario;
	// variable que contiene los caracteres válidos del alfabeto en el que se está manejando.
	protected String alfabeto;
	// arreglo que contiene cada letra de alfabeto por separado, usado para encontrar índices.
	protected String[] separado;
	// valor default del encriptado César.
	protected int cambio = 3;
	// constructor vacío, utiliza inicializar().
	public InventarioLetras() {
		// dejamos data vacío.
		// alfabeto queda sólo con letras en inglés.
		alfabeto = "abcdefghijklmnopqrstuvwxyz";
		// 26 letras hay en este alfabeto por lo tanto largoInv es 26.
		largoInv = 26;
		// inicializa variables que solo funcionan cuando ya declaramos las 2 anteriores.
		inicializar();
	}
	// 
	public InventarioLetras(String data) {
		// para simplificar, llevamos el data a minúsculas.
		this.data = data.toLowerCase().replace(" ", "");
		alfabeto = "abcdefghijklmnopqrstuvwxyz";
		largoInv = 26;
		inicializar();
		// surtimos el inventario con el método surtir() (línea 54).
		surtir();
	}
	// método interno, usado para declarar estas variables que, a pesar de que no cambian entre idiomas,
	// solo pueden ser declaradas después de que un objeto sea inicializado con su idioma, por lo tanto,
	// no se pueden dejar ya declaradas en los atributos.
	protected void inicializar() {
		this.separado = alfabeto.split("");
		this.recuento  = 0;
		this.inventario = new int[largoInv];
	}
	// método interno utilizable en ambos idiomas, sirve teniendo ya nuestro String data, recoger las
	// ocurrencias de cada letra, y añadirla al arreglo de enteros "inventario".
	protected void surtir() {
		// recorre cada letra de data.
		for (String letra : this.data.split("")) {
			// si la letra está en el alfabeto:
			if (in(letra.charAt(0))) {
				// aumenta en 1 al inventario en el índice de tal letra, si por ejemplo,
				// la letra es "c", inventario[2] aumenta su conteo.
				inventario[indice(letra.charAt(0))] += 1;
				// aumenta también el recuento total.
				recuento += 1;
			}
		}
	}
	public char encriptarCesar(char letra) {
		// retorna ' ' si el char ingresado no existe en el alfabeto.
		if (!in(letra))
			return ' ';
		// retorna una condición especial si la letra es x, y o z, pasando de vuelta a los abc.
		if ("xyz".contains(String.valueOf(letra).toLowerCase())) {
			return separado[indice(letra) + cambio - largoInv].charAt(0);
		}
		// de otra manera, retorna el caracter en separado[] en el índice de la letra + cambio, (3 por default).
		return separado[indice(letra) + cambio].charAt(0);
	}
	public char desencriptarCesar(char letra) {
		// retorna ' ' si el char ingresado no existe en el alfabeto.
		if (!in(letra))
			return ' ';
		// retorna una condición especial si la letra es a, b o c, pasando de vuelta a los xyz.
		if ("abc".contains(String.valueOf(letra).toLowerCase())) {
			return separado[indice(letra) - cambio + largoInv].charAt(0);
		}
		// de otra manera, retorna el caracter en separado[] en el índice de la letra + cambio, (3 por default).
		return separado[indice(letra) - cambio].charAt(0);
	}
	public void encriptarPalabra(String palabra, int valor) {
		// String encriptado de resultado.
		String encriptado = "";
		// si queremos encriptar con otra cantidad de saltos, se cambia el atributo valor.
		cambio = valor;
		// recorriendo cada letra de la palabra ingresada:
		for (String letra : palabra.split("")) {
			// implementa encriptarCesar que retorna el caracter ingresado, y es añadido al String de resultado.
			encriptado += encriptarCesar(letra.charAt(0));
		}
		// print para mostrar el encriptado.
		System.out.println("La palabra [" + palabra + "] encriptada "+ valor + " veces es: [" + encriptado + "]");
		// cambio setteado en su default si es que cambió, si no, igual.
		cambio = 3;
	}
	public void desencriptarPalabra(String palabra, int valor) {
		// String encriptado de resultado.
		String encriptado = "";
		// si queremos encriptar con otra cantidad de saltos, se cambia el atributo valor.
		cambio = valor;
		// recorriendo cada letra de la palabra ingresada:
		for (String letra : palabra.split("")) {
			// implementa desencriptarCesar que retorna el caracter ingresado, y es añadido al String de resultado.
			encriptado += desencriptarCesar(letra.charAt(0));
		}
		// print para mostrar el desencriptado.
		System.out.println("La palabra [" + palabra + "] desencriptada "+ valor + " veces es: [" + encriptado + "]");
		// cambio setteado en su default si es que cambió, si no, igual.
		cambio = 3;
	}
	// método para obtener la cantidad de ocurrencias de una letra.
	public int get(char letra) {
		// intenta, si el índice de la letra o caracter ingresado es negativo (lo será si no está dentro del alfabeto),
		// arroja una excepción.
		try { 
			if (indice(letra) < 0) throw new IllegalArgumentException("Valor ingresado ilegal/inválido: ");
			// si no sucede, procederá a retornar del arreglo "inventario" contenido en el índice de la letra en el alfabeto.
			return inventario[indice(letra)];
		} catch (IllegalArgumentException e) {
			// si sucede la excepción, imprime el mensaje de la excepción y retorna -1.
			System.out.print(e.getMessage());
			return -1;
		}
	}
	// método para modificar el recuento de una letra en el inventario, utiliza letra (a modificar) y valor (a cuanto).
	public void set(char letra, int valor) {
		try {
			// si el valor ingresado es negativo o la letra no está en nuestro alfabeto, arroja la excepción de argumento ilegal.
			if ((valor < 0) || !in(letra)) 
				throw new IllegalArgumentException("Valor ingresado ilegal/inválido: ");
			// si no, procederá a cambiar el recuento según la diferencia (sirve para aumentar o disminuir el inventario),
			recuento += valor - inventario[indice(letra)];
			// y cambia el valor en el inventario en el índice de la letra.
			inventario[indice(letra)] = valor;
		} catch (IllegalArgumentException e) {
			// si sucede la excepción, simplemente se arroja el mensaje de esta y no cambia nada.
			System.out.print(e.getMessage());
		}	
	}
	// método para imprimir las letras en orden.
	public String toString() {
		// variable que será retornado, y empieza con "[".
		String retorno = "[";
		// desde 0 hasta (pero no) largoInv (25 en inglés, 26 en español) (índices de un inventario):
		for (int i = 0; i < largoInv; i++) {
			// según la cantidad de ocurrencias contenidas en el inventario.
			for (int e = 0; e < inventario[i]; e++) {
				// se le agrega tal letra a retorno, si inventario[letra] es 0, no hará nada y pasa a la siguiente letra.
				retorno += separado[i];
			}
		}
		// por último, al tener "[abcde...", se retorna cerrando el corchete.
		return retorno + "]";
	}
	// método que ve si el inventario está vacío
	public boolean isEmpty() {
		// si el recuento (totales de ocurrencias de letras) es 0, retorna verdadero.
		if (recuento == 0) return true;
		// retorna falso (si no sucede lo anterior).
		return false;
	}
	// método interno que retorna el índice de una letra, del alfabeto:
	// (inglés) indice('a') = 0, indice('b') = 1, ..., indice('y') = 24, indice('z') = 25. 
	// (español) indice('a') = 0, indice('b') = 1, ..., indice('y') = 25, indice('z') = 26. 
	protected int indice(char letra) {
		// retorna el indice de la letra, del arreglo (como lista para obtener indexOf) "separado".
		return Arrays.asList(separado).indexOf(String.valueOf(letra).toLowerCase());
	}
	// retorna el total de ocurrencias del inventario.
	public int size() {
		// simplemente retorna recuento.
		return recuento;
	}
	// método interno que comprueba si una letra existe en nuestro alfabeto:
	// (inglés) in('a') = true, in('ñ') = false, in('1') = false, in('!') = false.
	// (español) in('a') = true, in('ñ') = true, in('1') = false, in('!') = false.
	protected boolean in(char letra) {
		// retorna si un caracter está contenido en alfabeto, definido en inglés o español.
		// esto funciona en ambos idiomas ya que alfabeto cambia según el idioma en el que trabajamos, y se hereda.
		if (alfabeto.contains(String.valueOf(letra))) return true;
		return false;
	}
	// método para obtener el String ingresado, para uso en métodos como add y substract.
	public String getData() {
		return data;
	}
	// retorna una suma de 2 inventario de letras.
	public InventarioLetras add(InventarioLetras otro) {
		// retorna un nuevo inventario, concatenando el data de este inventario, y el del otro inventario,
		// después, al construirse se harán los nuevos totales.
		return new InventarioLetras(this.data + otro.getData());
	}
	// método interno que retorna una amplificación de data, según n.
	// separado del método amplifies para ser utilizado en los 2 idiomas.
	protected String amp(int n) {
		// el String data que tenemos, será añadido n veces a amplificado y retornado como un nuevo String.
		String amplificado = "";
		for (int i = 0; i < n; i++) {
			amplificado += data;
		}
		// si data = "abczz" y n = 3, retornará "abczabczabcz".
		return amplificado;
	}
	// retorna una amplificación del inventario, que se construirá con el String retornado desde amp.
	// si se ingresa n = 3, como vimos anteriormente, se construirá un nuevo inventario con data = "abczabczabcz",
	// así amplificado data en el nuevo objeto.
	public InventarioLetras amplifies(int n) {
		return new InventarioLetras(amp(n));
	}
	// método interno separado de substract para ser utilizado en ambos idiomas.
	// retorna un String, de un inventario menos otro inventario.
	protected String sub(InventarioLetras otro) {
		String sustraido = "";
		// desde 0 hasta (pero no) largoInv (25 en inglés, 26 en español) (índices de un inventario):
		for (int i = 0; i < largoInv; i++) {
			// si la ocurrencia de una letra - la ocurrencia de la misma letra en el otro inventario es negativa:
			// procede a retornar null y termina el proceso.
			if (inventario[i] - otro.inventario[i] < 0) return null;
			// si no, por ejemplo con inventarios [aaabbc] y [aa], a partir de la resta, agrega lo que queda en letras a sustraido.
			for (int e = 0; e < inventario[i] - otro.inventario[i]; e++){
				sustraido += separado[i];
			}
		}
		// por lo tanto, en el ejemplo anterior solo nos quedaría un string [abbc], y se retorna el nuevo String.
		return sustraido;
	}
	public InventarioLetras subtract(InventarioLetras otro) {
		// si en sub() se retornó nulo (una resta salió negativa), retorna nulo también.
		if (sub(otro) == null)
			return null;
		// si no, retorna un nuevo inventario de objetos con el nuevo String ya sustraido ([abbc] en el ejemplo anterior).
		return new InventarioLetras(sub(otro));
	}
}

